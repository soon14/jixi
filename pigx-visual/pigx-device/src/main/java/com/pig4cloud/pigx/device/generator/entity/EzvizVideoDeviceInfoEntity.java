package com.pig4cloud.pigx.device.generator.entity;

import lombok.Data;

import java.util.List;
@Data
public class EzvizVideoDeviceInfoEntity {

    private List<VideoDeviceInfoEntity> data;

    private String code;

    private String msg;

}
