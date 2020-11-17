package com.pig4cloud.pigx.device.generator.vo;

import java.io.Serializable;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceFunction;
import lombok.Data;

@Data
public class DmDeviceFunctionVo extends DmDeviceFunction implements Serializable {

	// 功能名称
	private String funcName;
	// 标识符  此字段在功能关系表中已添加
	// private String funcCode;
	// 厂家名称
	private String factorName;
	// 品牌名称
	private String brandName;
	// 产品名称
	private String prodName;
	// 设备名称
	private String deviceName;
	// 产品类别标签名
	private String productTypeName;
}
