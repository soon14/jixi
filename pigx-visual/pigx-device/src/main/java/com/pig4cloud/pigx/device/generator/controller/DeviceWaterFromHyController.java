package com.pig4cloud.pigx.device.generator.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.device.generator.entity.*;
import com.pig4cloud.pigx.device.generator.feign.DeviceAlarmFeign;
import com.pig4cloud.pigx.device.generator.service.DeviceFunctionService;
import com.pig4cloud.pigx.device.generator.service.DeviceService;
import com.pig4cloud.pigx.device.generator.service.DmRealtimingDataService;
import com.pig4cloud.pigx.device.generator.utils.AlidayuUtil;
import com.pig4cloud.pigx.device.generator.utils.CallUtil;
import com.pig4cloud.pigx.device.generator.utils.StrToJson;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Api(value = "deviceWaterFromHyController", tags = "水系统数据获取")
@RequestMapping("/deviceWaterFromHyController")
public class DeviceWaterFromHyController {

    private final DeviceService deviceService;

    private final DeviceFunctionService deviceFunctionService;

    private final DeviceAlarmFeign deviceAlarmFeign;

    private final DmRealtimingDataService dmRealtimingDataService;

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ResponseBody
    @RequestMapping(value = {"/getHyDeviceInfo"}, method = {RequestMethod.POST})
    @Inner(value = false)
    public String getHyDeviceInfo(@RequestBody String deviceInfo) throws ClientException {
        System.out.println("getHyDeviceInfo ====> " + deviceInfo);
        if("www".equals(deviceInfo) || deviceInfo.length() < 10 ){
            return "ok";
        }
        HyDeviceInfo hyDeviceInfo = JSON.parseObject(deviceInfo, HyDeviceInfo.class);
        DmDevice dmdevice = deviceService.selectDmdeviceByCode(hyDeviceInfo.getDeviceNo());
        List<DmDeviceFunction> deviceDataById = deviceFunctionService.findDeviceDataById(dmdevice.getId());
        String realtimingdata = "";
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
        DmRealtimingData dmRealtimingData = new DmRealtimingData();
        DmAlarminfo dmAlarminfo = new DmAlarminfo();
        dmRealtimingData.setLarmInfo("正常");
        if (deviceDataById != null && deviceDataById.size() > 0) {
            for (DmDeviceFunction dmDeviceFunction : deviceDataById) {
                if ("液压".equals(dmDeviceFunction.getName())) {
                    if (hyDeviceInfo.getHY00() != null && !"".equals(hyDeviceInfo.getHY00())) {
                        realtimingdata += dmDeviceFunction.getFuncCode() + ":" + hyDeviceInfo.getHY00();
                    }
                    double yeya = Double.parseDouble(myformat.format(Double.parseDouble(hyDeviceInfo.getHY00())));
                    if (yeya > dmDeviceFunction.getUpperLimit() || yeya < dmDeviceFunction.getLowerLimit()) {
                        dmRealtimingData.setLarmInfo("液压报警");
                        dmAlarminfo.setAlarmType("液压报警");
                        dmAlarminfo.setEventDesc("液压报警");
                        dmAlarminfo.setEventType(8);
                        dmAlarminfo.setAlarmValue(hyDeviceInfo.getHY00());
                    }

                }
                if ("液位".equals(dmDeviceFunction.getName())) {
                    if (hyDeviceInfo.getHY03() != null && !"".equals(hyDeviceInfo.getHY03())) {
                        realtimingdata += dmDeviceFunction.getFuncCode() + ":" + hyDeviceInfo.getHY03();
                    }
                    double yewei = Double.parseDouble(myformat.format(Double.parseDouble(hyDeviceInfo.getHY03())));
                    if (yewei > dmDeviceFunction.getUpperLimit() || yewei < dmDeviceFunction.getLowerLimit()) {
                        dmRealtimingData.setLarmInfo("液位报警");
                        dmAlarminfo.setAlarmType("液位报警");
                        dmAlarminfo.setEventDesc("液位报警");
                        dmAlarminfo.setEventType(9);
                        dmAlarminfo.setAlarmValue(hyDeviceInfo.getHY03());
                    }
                }
            }
        }
        realtimingdata = "{" + realtimingdata + "}";
        if (dmdevice != null) {
            if ("液压报警".equals(dmRealtimingData.getLarmInfo()) || "液位报警".equals(dmRealtimingData.getLarmInfo())) {
                DmDevice dmDeviceupdate = new DmDevice();
                dmAlarminfo.setDeviceCode(dmdevice.getCode());
                dmAlarminfo.setSendTime(LocalDateTime.now());
                dmAlarminfo.setEventCount(1);
                dmAlarminfo.setAlarmTypeId(2);
                dmDeviceupdate.setAlarmType(2);
                dmAlarminfo.setDeviceStatus(0);
                dmAlarminfo.setAlarmTime(LocalDateTime.now());
                deviceAlarmFeign.save(dmAlarminfo, SecurityConstants.FROM_IN);
                deviceAlarmFeign.pushAlarmInfo(dmAlarminfo,SecurityConstants.FROM_IN);
                String address = dmdevice.getNetworkUnitName() + "" + dmdevice.getBuildName() + "" + dmdevice.getCountyName() + "" + dmdevice.getPosition();
                String text = "尊敬的用户:您好,北京东霖安全卫士提醒您，位于" + address + "的" + dmdevice.getName() + "发生报警，请及时处理";
                String uuid = UUID.randomUUID().toString().replace("-", "");
                Map<String, String> map = deviceAlarmFeign.getUnitInfoById(dmdevice.getNetworkUnitId());
                if (map != null && !map.isEmpty()) {
                    String phone = map.get("telephone");
                    AlidayuUtil.aliSendMsgAlarm(dmdevice, df.format(new Date()), phone);
                    CallUtil.call(text, phone, uuid);
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
        return "ok";
    }

    @RequestMapping(value = "/getHyDeviceYeyaInfo")
    @Inner(value = false)
    public String getHyDeviceYeyaInfo(HttpServletRequest req) throws ClientException{
        String str = handleRequest(req);
        JSONObject JSONObj = StrToJson.strToJsonObj(str);
        String deviceId = JSONObj.getString("deviceId");
        JSONObject service = (JSONObject) JSONObj.get("service");
        String data = service.getString("data");
        HuanyuDeviceEntity huanyuDeviceEntity = JSON.parseObject(data, HuanyuDeviceEntity.class);
        DmDevice dmdevice = deviceService.selectDmdeviceByImei(deviceId);
        List<DmDeviceFunction> deviceDataById = deviceFunctionService.findDeviceDataById(dmdevice.getId());
        String realtimingdata = "";
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
        DmRealtimingData dmRealtimingData = new DmRealtimingData();
        DmAlarminfo dmAlarminfo = new DmAlarminfo();
        dmRealtimingData.setLarmInfo("正常");
        if (deviceDataById != null && deviceDataById.size() > 0) {
            for (DmDeviceFunction dmDeviceFunction : deviceDataById) {
                if ("液压".equals(dmDeviceFunction.getName())) {
                    if (huanyuDeviceEntity.getC401() != null && !"".equals(huanyuDeviceEntity.getC401())) {
                        realtimingdata += dmDeviceFunction.getFuncCode() + ":" + huanyuDeviceEntity.getC401();
                    }
                    double yeya = Double.parseDouble(myformat.format(Double.parseDouble(huanyuDeviceEntity.getC401())));
                    if (yeya > dmDeviceFunction.getUpperLimit() || yeya < dmDeviceFunction.getLowerLimit()) {
                        dmRealtimingData.setLarmInfo("液压报警");
                        dmAlarminfo.setAlarmType("液压报警");
                        dmAlarminfo.setEventDesc("液压报警");
                        dmAlarminfo.setEventType(8);
                        dmAlarminfo.setAlarmValue(huanyuDeviceEntity.getC401());
                    }

                }
                if ("液位".equals(dmDeviceFunction.getName())) {
                    if (huanyuDeviceEntity.getC257() != null && !"".equals(huanyuDeviceEntity.getC257())) {
                        realtimingdata += dmDeviceFunction.getFuncCode() + ":" + huanyuDeviceEntity.getC257();
                    }
                    double yewei = Double.parseDouble(myformat.format(Double.parseDouble(huanyuDeviceEntity.getC257())));
                    if (yewei > dmDeviceFunction.getUpperLimit() || yewei < dmDeviceFunction.getLowerLimit()) {
                        dmRealtimingData.setLarmInfo("液位报警");
                        dmAlarminfo.setAlarmType("液位报警");
                        dmAlarminfo.setEventDesc("液位报警");
                        dmAlarminfo.setEventType(9);
                        dmAlarminfo.setAlarmValue(huanyuDeviceEntity.getC257());
                    }
                }
            }
        }
        realtimingdata = "{" + realtimingdata + "}";
        if (dmdevice != null) {
            if ("液压报警".equals(dmRealtimingData.getLarmInfo()) || "液位报警".equals(dmRealtimingData.getLarmInfo())) {
                DmDevice dmDeviceupdate = new DmDevice();
                dmAlarminfo.setDeviceCode(dmdevice.getCode());
                dmAlarminfo.setSendTime(LocalDateTime.now());
                dmAlarminfo.setEventCount(1);
                dmAlarminfo.setAlarmTypeId(2);
                dmDeviceupdate.setAlarmType(2);
                dmAlarminfo.setDeviceStatus(0);
                dmAlarminfo.setAlarmTime(LocalDateTime.now());
                deviceAlarmFeign.save(dmAlarminfo,SecurityConstants.FROM_IN);
                deviceAlarmFeign.pushAlarmInfo(dmAlarminfo,SecurityConstants.FROM_IN);
                String address = dmdevice.getNetworkUnitName() + "" + dmdevice.getBuildName() + "" + dmdevice.getCountyName() + "" + dmdevice.getPosition();
                String text = "尊敬的用户:您好,北京东霖安全卫士提醒您，位于" + address + "的" + dmdevice.getName() + "发生报警，请及时处理";
                String uuid = UUID.randomUUID().toString().replace("-", "");
                Map<String, String> map = deviceAlarmFeign.getUnitInfoById(dmdevice.getNetworkUnitId());
                if (map != null && !map.isEmpty()) {
                    String phone = map.get("telephone");
                    AlidayuUtil.aliSendMsgAlarm(dmdevice, df.format(new Date()), phone);
                    CallUtil.call(text, phone, uuid);
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
       // Map<String, Object> map = new HashMap<>();

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
