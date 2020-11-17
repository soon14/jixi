package com.pig4cloud.pigx.api.device.entity;

import lombok.Data;

@Data
public class TrafficTotal {

    private Long totalFlow;

    private Long usedFlow;

    private Long averageConsume;
}
