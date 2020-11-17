package com.pig4cloud.pigx.device.generator.vo;

import com.pig4cloud.pigx.device.generator.entity.DmUnit;
import lombok.Data;

import java.io.Serializable;

@Data
public class DmUnitVO extends DmUnit implements Serializable {
    // 产品类别标签名
    private String productTypeName;
}
