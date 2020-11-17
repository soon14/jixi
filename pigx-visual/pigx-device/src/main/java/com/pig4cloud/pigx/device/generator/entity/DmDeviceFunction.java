/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.device.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * 设备功能关系表
 *
 * @author lhd
 * @date 2019-06-20 10:15:38
 */
@Data
@TableName("dm_device_function")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备功能关系表")
public class DmDeviceFunction extends Model<DmDeviceFunction> {
	private static final long serialVersionUID = 1L;

	/**
	 * 设备id
	 */
	private String devId;
	/**
	 * 功能id
	 */
	@NotNull(message = "功能ID不能为空")
	private String funcId;
	/**
	 * 产品类型
	 */
	@NotNull(message = "产品类型不能为空")
	private Integer typeId;
	/**
	 * 生产厂家id
	 */
	@NotNull(message = "生产厂家不能为空")
	private String factorId;
	/**
	 * 品牌id
	 */
	@NotNull(message = "品牌ID不能为空")
	private String brandId;
	/**
	 * 产品id
	 */
	@NotNull(message = "产品ID不能为空")
	private String prodId;

	/**
	 * 冗余：功能类型
	 */
	private Integer funcType;
	/**
	 * 事件类型
	 */
	private Integer eventType;
	/**
	 * 数据类型
	 */
	private Integer dataType;
	/**
	 * 计量单位ID
	 */
	private String unitId;
	/**
	 * 阈值上限
	 */
	@DecimalMax(value = "10000", message = "阈值上限最大值是10000")
	private Double upperLimit;
	/**
	 * 阈值下限
	 */
	@DecimalMax(value = "10000", message = "阈值下限最大值是10000")
	private Double lowerLimit;
	/**
	 * 开关量
	 */
	private Integer volumeSwitch;
	/**
	 * 描述
	 */
	private String remark;

	/**
	 * 功能标识符
	 */
	private String funcCode;

	/**
	 * 功能名称
	 */
	@TableField(exist = false)
	private String name;

}
