package com.pig4cloud.pigx.device.generator.entity;

import lombok.Data;

@Data
public class EzvizVideoDeviceStatusEntity {

    private VideoDeviceStatusEntity data; //设备状态

    private String code; //返回码

    private String msg; //描述
}
