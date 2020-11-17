package com.pig4cloud.pigx.device.generator.entity;

import lombok.Data;

@Data
public class VideoDeviceStatusEntity {

    private String deviceSerial; //设备序列号

    private String deviceName; //设备名称

    private String model; //设备型号

    private Integer  status; //设备状态

    private Integer defence; //具有防护能力的设备布撤防状态：0-睡眠，8-在家，16-外出，普通IPC布撤防状态：0-撤防，1-布防

    private Integer isEncrypt; //是否加密

    private Integer alarmSoundMode; //告警声音模式：0-短叫，1-长叫，2-静音

    private Integer offlineNotify; //设备下线是否通知：0-不通知 1-通知

    private String category; //设备大类
}
