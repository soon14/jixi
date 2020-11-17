package com.pig4cloud.pigx.api.device.vo;

import lombok.Data;

@Data
public class DmRDataVO {

    /**
     * 设备类型
     */
    private String DType;

    /**
     * 实时数据
     */
    private String rData;
}
