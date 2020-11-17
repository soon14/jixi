package com.pig4cloud.pigx.device.generator.xinchuan;

import com.pig4cloud.pigx.admin.api.feign.RemoteLogService;
import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.device.generator.entity.DmAlarminfo;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.entity.DmRealtimingData;
import com.pig4cloud.pigx.device.generator.feign.DeviceAlarmFeign;
import com.pig4cloud.pigx.device.generator.service.DeviceService;
import com.pig4cloud.pigx.device.generator.service.DmRealtimingDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author lz
 */
@Slf4j
@Component
@AllArgsConstructor
public class BusData {

    private final DeviceService deviceService;

    private final DmRealtimingDataService dmRealtimingDataService;

    private final DeviceAlarmFeign deviceAlarmFeign;

    private final RemoteLogService remoteLogService;

    /**
     * 保存业务数据 (实时数据、报警数据)
     * @param data 字节数组转成的字符串 如:40 40 00 00
     */
    public void procesData(String data) {

        HashMap<String, String> map = splitData(data);
        String imei = map.get("IMEI");
        String command = map.get("command");
        String event = map.get("event");
        String info1 = map.get("info1");
        String info2 = map.get("info2");
        String info3 = map.get("info3");
        //logger.info("IMEI:" + imei + " 命令:" + command + " 事件:" + event + " 回路:" + info1 + " 地址:" + info2  + " 主机:" + info3);
        log.info("IMEI:" + imei + " 命令:" + CommandEnum.getDesc(command) + " 事件:" + EventEnum.getDesc(event) + " 信息:" + " 回路:" + info1 + " 地址:" + info2  + " 主机:" + info3);

        //保存实时数据
        DmDevice dd = deviceService.selectDmdeviceByCode(imei);
        if(null == dd){
            log.error("未找到该设备，请先添加此设备信息！");
        }else {
            DmRealtimingData drd = new DmRealtimingData();
            drd.setDeviceCode(imei);
            drd.setDeviceName(dd.getName());
            drd.setDeviceAddress(dd.getPosition());
            drd.setDeviceType(dd.getTypeId().toString());
            drd.setRealtimingdata(data);
            drd.setLarmInfo(EventEnum.getDesc(event));
            drd.setSendTime(LocalDateTime.now());
            dmRealtimingDataService.save(drd);
        }

        //保存报警数据
        if(event.equals(EventEnum.event01.getType()) || event.equals(EventEnum.event03.getType()) ||
                event.equals(EventEnum.event12.getType())||event.equals(EventEnum.event13.getType())){
            DmAlarminfo dai = new DmAlarminfo();
            dai.setDeviceCode(imei);
            dai.setSendTime(LocalDateTime.now());
            dai.setEventCount(1);
            dai.setAlarmTime(LocalDateTime.now());
            //火警
            if(event.equals(EventEnum.event01.getType())){
                dai.setEventType(AlarmEnum.alarm7.getType());
                dai.setEventDesc(EventEnum.event01.getDesc());
                dai.setAlarmType(dd.getName() + EventEnum.event01.getDesc());
                dai.setAlarmValue(dd.getPosition());
                //故障
            }else if(event.equals(EventEnum.event03.getType())){
                dai.setEventType(AlarmEnum.alarm5.getType());
                dai.setEventDesc(EventEnum.event03.getDesc());
                dai.setAlarmType(dd.getName() + EventEnum.event03.getDesc());
                dai.setAlarmValue(dd.getPosition());
                //主电故障
            }else if(event.equals(EventEnum.event12.getType())){
                dai.setEventType(AlarmEnum.alarm5.getType());
                dai.setEventDesc(EventEnum.event12.getDesc());
                dai.setAlarmType(dd.getName() + EventEnum.event12.getDesc());
                dai.setAlarmValue(dd.getPosition());
                //备电故障
            }else if(event.equals(EventEnum.event13.getType())){
                dai.setEventType(AlarmEnum.alarm5.getType());
                dai.setEventDesc(EventEnum.event13.getDesc());
                dai.setAlarmType(dd.getName() + EventEnum.event13.getDesc());
                dai.setAlarmValue(dd.getPosition());
            }
            deviceAlarmFeign.save(dai,SecurityConstants.FROM_IN);
            deviceAlarmFeign.pushAlarmInfo(dai,SecurityConstants.FROM_IN);
        }

    }


    /**
     * 数据截取并处理，得到 IMEI、命令、事件、信息(回路、地址、主机)
     *
     * @param data
     * @return
     */
    public HashMap<String, String> splitData(String data) {
        HashMap<String, String> map = new HashMap<>();
        String[] s = data.split(" ");
        StringBuffer sb = new StringBuffer();
        String str = sb.append(s[20]).append(s[19]).append(s[18]).append(s[17]).append(s[16]).append(s[15]).append(s[14]).toString();
        String imei = new BigInteger(str, 16).toString(10);
        map.put("IMEI", imei);
        map.put("command", s[21]);
        map.put("event", s[22]);
        sb.setLength(0);
        map.put("info1", hex2Str(s[29]));
        map.put("info2", hex2Str(s[30]));
        map.put("info3", hex2Str(s[31]));
        return map;
    }

    /**
     * 16进制转10进制字符串
     * @param hex
     * @return
     */
    public String hex2Str(String hex){
        return String.valueOf(Integer.parseInt(hex, 16));
    }
}
