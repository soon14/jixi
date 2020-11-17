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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
@Data
@TableName("dm_device")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备表")
public class DmDevice extends Model<DmDevice> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id，32位去-的uuid
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 设备编码
	 */
	@NotNull(message = "设备编码不能为空")
	@Size(min = 2, max = 500, message = "设备编码长度只能在2-500之间")
	private String code;
	/**
	 * 设备名称
	 */
	@NotNull(message = "设备名称不能为空")
	@Size(min = 2, max = 100, message = "设备名称长度只能在2-100之间")
	private String name;
	/**
	 * 厂家ID
	 */
	@NotNull(message = "厂家ID不能为空")
	private String factorId;
	/**
	 * 品牌ID
	 */
	@NotNull(message = "品牌ID不能为空")
	private String brandId;
	/**
	 * 产品ID
	 */
	@NotNull(message = "产品ID不能为空")
	private String prodId;
	/**
	 * 产品类别
	 */
	private Integer typeId;
	/**
	 * 生产日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date productionDate;
	/**
	 * 有效期(月)
	 */
	@Max(value = 1000, message = "有效期不能大于1000")
	private Integer periodValidity;
	/**
	 * 联网单位id
	 */
	@NotNull(message = "联网单位ID不能为空")
	private String networkUnitId;
	/**
	 * 联网单位名称
	 */
	private String networkUnitName;
	/**
	 * 建筑id
	 */
	@NotNull(message = "建筑ID不能为空")
	private String buildId;
	/**
	 * 建筑名称
	 */
	private String buildName;
	/**
	 * 楼层id
	 */
	@NotNull(message = "楼层ID不能为空")
	private String countyId;
	
	private String countyName;
	/**
	 * 所在位置
	 */
	@NotNull(message = "所在位置不能为空")
	private String position;
	/**
	 * 视频设备绑定地址
	 */
	@Size(max = 255, message = "视频设备绑定地址长度不能超过255")
	private String videoAddr;
	@Size(max = 255, message = "视频设备绑定地址长度不能超过255")
	private String videoHdAddr;
	/**
	 * 视频设备绑定端口
	 */
	@Size(max = 10, message = "视频设备绑定端口长度不能超过10")
	private String videoPort;
	/**
	 * 视频设备绑定帐号
	 */
	@Size(max = 50, message = "视频设备绑定帐号长度不能超过50")
	private String videoUsername;
	/**
	 * 视频设备绑定密码
	 */
	@Size(max = 50, message = "视频设备绑定密码长度不能超过50")
	private String videoPassword;
	/**
	 * 描述
	 */
	@Size(max = 255, message = "设备描述长度不能超过255")
	private String remark;
	/**
	 * 状态
	 */
	@Size(max = 3, message = "设备状态长度不能超过3")
	private String status;
	
	/**
	   *  经度
	 */
	private BigDecimal longitude;
	/**
	   *  纬度
	 */
	private BigDecimal latitude;
	/**
	 * 创建人id
	 */
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

	/**
	 * 删除标记（0：未删除，1：已删除）
	 */
	private Integer isDelete;

	private String imei; // 设备注册码
	
	/**
	 * 报警类型
	 */
	private Integer alarmType;
}
