package com.pig4cloud.pigx.device.generator.controller;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.device.generator.entity.DmAlarminfo;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceFunction;
import com.pig4cloud.pigx.device.generator.entity.DmRealtimingData;
import com.pig4cloud.pigx.device.generator.feign.DeviceAlarmFeign;
import com.pig4cloud.pigx.device.generator.service.DeviceFunctionService;
import com.pig4cloud.pigx.device.generator.service.DeviceService;
import com.pig4cloud.pigx.device.generator.service.DmRealtimingDataService;
import com.pig4cloud.pigx.device.generator.utils.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@AllArgsConstructor
@Api(value = "deviceSmokeFromIot", tags = "烟感数据获取")
@RequestMapping("/deviceSmokeFromIot")
public class DeviceSmokeFromIotController {

    private final DeviceFunctionService deviceFunctionService;

    private final DeviceService deviceService;

    private final DmRealtimingDataService dmRealtimingDataService;

    private final DeviceAlarmFeign deviceAlarmFeign;

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/smokeNbData")
    public String smokeNbData(HttpServletRequest req)throws ClientException {
        String str = handleRequest(req);
        //System.out.println("电信开放平台主动推送的数据：" + str);
        JSONObject JSONObj = StrToJson.strToJsonObj(str);
        String deviceId = JSONObj.getString("deviceId");
//        String gatewayId = JSONObj.getString("gatewayId");
        JSONObject service = (JSONObject) JSONObj.get("service");
//        String serviceId = service.getString("serviceId");
//        String serviceType = service.getString("serviceType");
        JSONObject deviceDataObj = (JSONObject) service.get("data");
        String deviceData = deviceDataObj.getString("SystemCmdRawData");
        if (deviceData != null && !"".equals(deviceData)) {
            DmDevice dmdevice = deviceService.selectDmdeviceByImei(deviceId);
            DmRealtimingData dmRealtimingData = new DmRealtimingData();
            String deviceStatusCode = "1";
            String event = ""; //描述
            String data = ""; //烟雾值
            String data2 = ""; //温度值
            String realtimingdata = "{";
            if (deviceData.length() == 174 || deviceData.length() == 236) {
                System.out.println("海曼烟感174长度上传的报文信息：" + deviceData);
                HMYGBeanInfo hmInfo = new HMParserUtils(deviceData).getYgBeanInfo();
                deviceStatusCode = String.valueOf(hmInfo.getYGDeviceAlarmStatusCode());
                event = hmInfo.getYGDeviceCom();
                data = String.valueOf(hmInfo.getYGDeviceSmokeConCode());
                data2 = String.valueOf(hmInfo.getYGDeviceTempertureConCode());
            }  else if (deviceData.length() == 24) {
                System.out.println("百思威烟感上传的报文信息：" + deviceData);
                YGBeanInfo dataInfo = new YGParserUtils(deviceData).getYgBeanInfo();
                deviceStatusCode = String.valueOf(dataInfo.getDeviceCode());
                event = dataInfo.getYGDeviceCom();
                data = String.valueOf(dataInfo.getYGDeviceSmokeCon());
            } else if (deviceData.length() == 158) {
                System.out.println("海曼可燃气上传的报文信息：" + deviceData);
                HMGasBeanInfo hmInfo = new HMGasParserUtils(deviceData).getGasBeanInfo();
                deviceStatusCode = String.valueOf(hmInfo.getGasDeviceAlarmStatusCode());
                event = hmInfo.getGasDeviceCom();
                data = String.valueOf(hmInfo.getGasDeviceGasConCode());
            } else if (deviceData.length() == 88) {
                //System.out.println("福赛尔上传的报文信息：" + deviceData);
                FusaierEntity parse = new Mes6698().parse(deviceData);
                deviceStatusCode = parse.getDeviceStatusCode();
                event = parse.getEvent();
                data = parse.getData2();
                data2 = parse.getData6();
            }
            List<DmDeviceFunction> deviceDataById = deviceFunctionService.findDeviceDataById(dmdevice.getId());
            if(deviceDataById != null && deviceDataById.size() > 0){
                for (DmDeviceFunction dmDeviceFunction: deviceDataById) {
                    if("烟感温度".equals(dmDeviceFunction.getName())){
                        if(!"".equals(data2)){
                            realtimingdata += dmDeviceFunction.getFuncCode() + ":" +data2 +",";
                        }
                    }
                    if("烟雾浓度".equals(dmDeviceFunction.getName())){
                        if(!"".equals(data)){
                            realtimingdata += dmDeviceFunction.getFuncCode() + ":" +data +",";
                        }
                    }
                }
            }
            realtimingdata = realtimingdata.substring(0, realtimingdata.length() - 1) + "}";
            if("}".equals(realtimingdata)){
                realtimingdata = "{}";
            }
            dmRealtimingData.setLarmInfo("正常");
            if(dmdevice != null){
            if("2".equals(deviceStatusCode) || "3".equals(deviceStatusCode)){
                DmAlarminfo dmAlarminfo = new DmAlarminfo();
                DmDevice dmDeviceupdate =  new DmDevice();
                dmAlarminfo.setDeviceCode(dmdevice.getCode());
                dmAlarminfo.setSendTime(LocalDateTime.now());
                dmAlarminfo.setEventCount(1);
                dmAlarminfo.setEventDesc(event);
                if("2".equals(deviceStatusCode)){
                    dmAlarminfo.setAlarmTypeId(2);
                    dmAlarminfo.setEventType(3);
                    dmAlarminfo.setAlarmType("烟雾报警");
                    dmDeviceupdate.setAlarmType(2);
                    dmRealtimingData.setLarmInfo("火灾");
                }
                if("3".equals(deviceStatusCode)){
                    dmAlarminfo.setAlarmTypeId(3);
                    dmAlarminfo.setAlarmType("设备故障");
                    dmAlarminfo.setEventType(5);
                    dmDeviceupdate.setAlarmType(3);
                    dmRealtimingData.setLarmInfo("故障");
                }
                dmAlarminfo.setAlarmValue(data);
                dmAlarminfo.setDeviceStatus(0);
                dmAlarminfo.setAlarmTime(LocalDateTime.now());
                deviceAlarmFeign.save(dmAlarminfo, SecurityConstants.FROM_IN);
                deviceAlarmFeign.pushAlarmInfo(dmAlarminfo,SecurityConstants.FROM_IN);
                String address = dmdevice.getNetworkUnitName()+""+dmdevice.getBuildName()+""+dmdevice.getCountyName()+""+dmdevice.getPosition();
                String text = "尊敬的用户:您好,北京东霖安全卫士提醒您，位于"+address+ "的"+dmdevice.getName()+"发生报警，请及时处理";
                String uuid = UUID.randomUUID().toString().replace("-", "");
                Map<String,String> map = deviceAlarmFeign.getUnitInfoById(dmdevice.getNetworkUnitId());
                if(map != null  && !map.isEmpty()){
                   String phone = map.get("telephone");
                    AlidayuUtil.aliSendMsgAlarm(dmdevice,df.format(new Date()),phone);
                    CallUtil.call(text,phone,uuid);
                }
                dmDeviceupdate.setId(dmdevice.getId());
                deviceService.updateDevice(dmDeviceupdate);
            }
                dmRealtimingData.setRealtimingdata(realtimingdata);
                dmRealtimingData.setDeviceCode(dmdevice.getCode());
                dmRealtimingData.setDeviceName(dmdevice.getName());
                dmRealtimingData.setDeviceAddress(dmdevice.getPosition());
                dmRealtimingData.setDeviceType(dmdevice.getDeviceType());
                dmRealtimingData.setSendTime(LocalDateTime.now());
                dmRealtimingData.setDeviceType(dmdevice.getTypeId().toString());
                dmRealtimingDataService.save(dmRealtimingData);
            }

        }
        return "200 OK";
    }

    /**
     * 注册设备通知
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/registerDevice")
    public String registerDevice(HttpServletRequest req) {
        String str = handleRequest(req);
        return "200 OK";
    }

    /**
     * 电信平台上删除注册在电信平台的设备：删除设备通知
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "deleteDevice")
    public String deleteDevice(HttpServletRequest req) {
        String str = handleRequest(req);
        JSONObject JSONObj = StrToJson.strToJsonObj(str);
        // deviceId是电信平台对这个设备生成的唯一标识。
        String imei = JSONObj.getString("deviceId");
        //根据imei把设备状态修改为离线
        DmDevice dmDevice = new DmDevice();
        dmDevice.setImei(imei);
        dmDevice.setStatus("0");
        deviceService.updateDeviceByImei(dmDevice);
        return "200 OK";
    }

    /**
     * 接收电信开放平台推送的设备信息变化，如已绑定 0：ONLINE;关机：1；未绑定 2（离线）：OFFLINE;异常
     * 3：ABNORMAL;INBOX这个状态还不知道什么情况下出现
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "changeDevice")
    public String changeDevice(HttpServletRequest req) {
        String str = handleRequest(req);
        // 当电信平台返回数据是ONLINE时，说明此设备已经开机且可以通讯
        JSONObject JSONObj = StrToJson.strToJsonObj(str);
        // deviceId是电信平台对这个设备生成的唯一标识。
        String imei = JSONObj.getString("deviceId");
        String deviceInfoObj = JSONObj.getString("deviceInfo");
        JSONObject deviceInfo = StrToJson.strToJsonObj(deviceInfoObj);
        // 获取设备状态
        String online = deviceInfo.getString("status");

        DmDevice dmDevice = new DmDevice();
        dmDevice.setImei(imei);
        // 在平台绑定成功后更新设备表状态
        if ("ONLINE".equals(online)) {
            //在线
            dmDevice.setStatus("1");
            deviceService.updateDeviceByImei(dmDevice);
        }
        // 离线
        if ("OFFLINE".equals(online)) {
            //离线
            dmDevice.setStatus("0");
            deviceService.updateDeviceByImei(dmDevice);
        }
        // 异常
        if ("ABNORMAL".equals(online)) {
            //异常
            dmDevice.setStatus("2");
            deviceService.updateDeviceByImei(dmDevice);
        }

        try {
            //推送数据加不加
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "200 OK";
    }

    /**
     * 处理请求数据
     *
     * @param req
     * @return
     */
    public static String handleRequest(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> map = new HashMap<>();

        BufferedReader reader = null;
        try {
            reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            if (sb.length() > 1) {
                sb.replace(sb.length() - 1, sb.length(), "");
            }
        } catch (Exception e) {


        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {

                }
            }
        }
        return sb.toString();
    }

}