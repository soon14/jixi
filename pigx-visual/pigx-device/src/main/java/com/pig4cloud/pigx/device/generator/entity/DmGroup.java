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

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备分组表
 *
 * @author lhd
 * @date 2019-06-21 10:37:34
 */
@Data
@TableName("dm_group")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备分组表")
public class DmGroup extends Model<DmGroup> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
	@TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
   * 分组编码
   */
	@NotNull(message  = "分组编码不能为空")
	@NotBlank(message = "分组编码不能为空字符串")
    private String code;
    /**
   * 分组名称
   */
	@NotNull(message  = "分组名称不能为空")
	@NotBlank(message = "分组名称不能为空字符串")
    private String name;
    /**
   * 父级id
   */
	@NotNull(message  = "父级ID不能为空")
	@NotBlank(message = "父级ID不能为空字符串")
    private String parentId;
    /**
   * 描述
   */
    private String remark;
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
  
}
