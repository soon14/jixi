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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 厂家表
 *
 * @author lhd
 * @date 2019-06-20 10:15:45
 */
@Data
@TableName("dm_manufactor")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "厂家表")
public class DmManufactor extends Model<DmManufactor> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id，32位去-的uuid
   */
	@TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
   * 厂家编码
   */
	 @NotNull(message  = "厂家编码不能为空")
	 @NotBlank(message = "厂家编码不能为空字符串")
    private String code;
    /**
   * 厂家名称
   */
    @NotNull(message  = "厂家名称不能为空")
    @NotBlank(message = "厂家名称不能为空字符串")
    private String name;
    /**
   * 地址
   */
    @NotNull(message  = "厂家地址不能为空")
    @NotBlank(message = "厂家地址不能为空字符串")
    private String address;
    /**
   * 联系人
   */
    @NotNull(message  = "联系人不能为空")
    @NotBlank(message = "联系人不能为空字符串")
    private String contacts;
    /**
   * 联系人电话
   */
    @NotNull(message  = "联系人电话不能为空")
    @NotBlank(message = "联系人电话不能为空字符串")
    private String phone;
    /**
   * 创建人id
   */
    private String createUserId;
    
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改人id
   */
    private String updateUserId;
    
    private String updateUser;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
     * 产品类型id，可选多个，以“，”分隔
     */
    private String prodTypes;
  
}
