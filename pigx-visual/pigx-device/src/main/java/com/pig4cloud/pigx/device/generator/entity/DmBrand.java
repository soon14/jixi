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
 * 品牌表
 *
 * @author lhd
 * @date 2019-06-20 10:15:55
 */
@Data
@TableName("dm_brand")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "品牌表")
public class DmBrand extends Model<DmBrand> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id，32位去-的uuid
   */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
   * 品牌编码
   */
    @NotNull(message  = "品牌编码不能为空")
	@NotBlank(message = "品牌编码不能为空字符串")
    private String code;
    /**
   * 品牌名称
   */
    @NotNull(message  = "品牌名称不能为空")
	@NotBlank(message = "品牌名称不能为空字符串")
    private String name;
    /**
   * 厂家ID
   */
    @NotNull(message  = "厂家ID不能为空")
	@NotBlank(message = "厂家ID不能为空字符串")
    private String factorId;
    
    /**
   * 描述
   */
    private String remark;
    /**
   * 创建人ID
   */
    private String createUserId;
    
    /**
     * 创建人姓名
     */
    private String createUser;
    
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改人ID
   */
    private String updateUserId;
    /**
     * 修改人姓名
     */
    private String updateUser;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
  
}
