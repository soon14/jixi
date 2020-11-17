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

package com.pig4cloud.pigx.api.user.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构类型表
 *
 * @author lhd
 * @date 2019-05-15 15:26:16
 */
@Data
@TableName("sys_org_type")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "机构类型表")
public class SysOrgType extends Model<SysOrgType> {
private static final long serialVersionUID = 1L;

    /**
   * 类型id
   */
    @TableId(value = "type_id", type = IdType.UUID)
    private String typeId;
    /**
   * 机构类型名称
   */
    private String name;
    /**
   * 机构类型编码
   */
    private String code;
    /**
   * 
   */
    private String createUserId;
    /**
   * 
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
  
}
