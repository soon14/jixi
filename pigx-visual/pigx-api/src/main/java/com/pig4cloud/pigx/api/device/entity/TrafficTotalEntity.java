package com.pig4cloud.pigx.api.device.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrafficTotalEntity{

    private String code;

    private String msg;

    private TrafficTotal trafficTotal;

}
