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

package com.pig4cloud.pigx.smartff.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 巡检方案表
 *
 * @author zm  （此表废弃,暂时保留）
 * @date 2019-08-07 17:20:48
 */
@Data
@TableName("sf_taskplan")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "巡检方案表")
public class SfTaskplan extends Model<SfTaskplan> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(type = IdType.UUID)
    private String id;
    /**
   * 方案编码
   */
    private String plancode;
    /**
   * 方案名称
   */
    private String planname;
    /**
   * 方案说明
   */
    private String plandesc;
    /**
   * 联网单位id
   */
    private String orgid;
    /**
   * 建筑ID
   */
    private String buildid;
    
    private String buildName;
    /**
   * 巡检点集合
   */
    @NotNull(message = "请选择巡检点")
    private String tourlist;
    /**
   * 方案经办人
   */
    private String person;
    /**
   * 方案经办人姓名
   */
    private String personName;
    /**
   * 创建人ID
   */
    private String createUserId;
    /**
   * 创建人姓名
   */
    private String createUserName;
    /**
   * 创建时间
   */
    private LocalDateTime createtime;
    /**
   * 修改人ID
   */
    private String modifyUserId;
    /**
   * 修改人姓名
   */
    private String modifyUserName;
    /**
   * 修改时间
   */
    private LocalDateTime modifytime;
    /**
   * 备注
   */
    private String remark;
  
}
