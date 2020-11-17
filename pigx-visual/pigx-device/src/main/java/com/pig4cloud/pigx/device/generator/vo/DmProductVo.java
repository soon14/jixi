package com.pig4cloud.pigx.device.generator.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.device.generator.entity.DmProduct;
import lombok.Data;

@Data
public class DmProductVo extends DmProduct implements Serializable {
	 //厂家名称
	private String factorName;
	//品牌名称
	private String brandName;
	// 产品类别标签名
	private String productTypeName;
}
