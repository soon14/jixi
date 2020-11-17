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

package com.pig4cloud.pigx.api.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 功能表
 *
 * @author lhd
 * @date 2019-06-20 10:15:42
 */
@Data
@TableName("dm_function")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "功能表")
public class DmFunction extends Model<DmFunction> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id，32位去-的uuid
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 功能编码
	 */
	@NotNull(message = "功能编码不能为空")
	@Size(min = 2, max = 50, message = "功能编码长度只能在2-50之间")
	private String code;
	/**
	 * 功能名称
	 */
	@NotNull(message = "功能名称不能为空")
	@Size(min = 2, max = 50, message = "功能名称长度只能在2-100之间")
	private String name;
	/**
	 * 厂家ID
	 */
	@NotNull(message = "厂家ID不能为空")
	@Size(max = 32, message = "厂家ID最大长度为32")
	private String factorId;
	/**
	 * 品牌ID
	 */
	@NotNull(message = "品牌ID不能为空")
	@Size(max = 32, message = "品牌ID最大长度为32")
	private String brandId;
	/**
	 * 产品ID
	 */
	@NotNull(message = "产品ID不能为空")
	@Size(max = 32, message = "产品ID最大长度为32")
	private String prodId;
	/**
	 * 产品类别
	 */
	private Integer typeId;
	/**
	 * 功能类型
	 */
	private Integer funcType;
	/**
	 * 功能类型为事件时，绑定属性id
	 */
	@Size(max = 32, message = "功能类型最大长度为32")
	private String funcId;
	/**
	 * 事件类型
	 */
	private Integer eventType;
	/**
	 * 数据类型
	 */
	private Integer dataType;
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
	 * 计量单位id
	 */
	@Size(max = 32, message = "计量单位量最大长度为32")
	private String unitId;
	/**
	 * 功能选择，是否必选
	 */
	private Integer required;
	/**
	 * 描述
	 */
	@Size(max = 255, message = "功能描述长度不能超过255")
	private String remark;
	/**
	 * 创建人id
	 */
	@NotNull(message = "创建人ID不能为空")
	@Size(max = 32, message = "创建人ID长度不能超过32")
	private String createUserId;
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	/**
	 * 修改人id
	 */
	@Size(max = 32, message = "修改人ID长度不能超过32")
	private String updateUserId;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

}
