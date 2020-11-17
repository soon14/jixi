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

package com.pig4cloud.pigx.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 楼层信息
 *
 * @author pigx code generator
 * @date 2019-09-19 17:34:02
 */
@Data
@TableName("bs_county")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "楼层信息")
public class BsCounty extends Model<BsCounty> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(value = "county_id", type = IdType.UUID)
    private String countyId;
    /**
   * 
   */
    private String code;
    /**
   * 
   */
    private String name;
    /**
   * 
   */
    private String deptId;
    /**
   * 
   */
    private String deptName;
    /**
   * 
   */
    private String buildId;
    /**
   * 
   */
    private String buildName;
    /**
   * 
   */
    private String backgroundImage;
    /**
   * 
   */
    private Integer area;
    /**
   * 
   */
    private Integer refractoryRating;
    /**
   * 所在位置
   */
    private String position;
    /**
   * 
   */
    private Integer usefulness;
    /**
   * 消防设施情况：有无消防设施及设施相关信息
   */
    private String deviceStatus;
    /**
   * 责任人姓名：
   */
    private String managerName;
    /**
   * 责任人公民身份证号码：要求符合标准GB 11643
   */
    private String managerIdNum;
    /**
   * 责任人电话：
   */
    private String managerPhone;
    /**
   * 确立消防安全重点部位的原因：
   */
    private String reason;
    /**
   * 防火标志的设立情况：
   */
    private String signStatus;
    /**
   * 危险源情况
   */
    private String dangerSource;
    /**
   * 消防安全管理措施
   */
    private String measure;
    /**
   * 属性集合,用json表示
   */
    private String description;
    /**
   * 备注
   */
    private String remark;
    /**
   * 
   */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime modifyTime;
    /**
   * 是否生效
   */
    private Integer inure;
    /**
   * 是否删除状态：0-正常；1-被删除
   */
    private Integer isRemoved;
  
}
