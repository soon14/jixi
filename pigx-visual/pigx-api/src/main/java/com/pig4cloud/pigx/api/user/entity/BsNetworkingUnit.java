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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
@Data
@TableName("bs_networking_unit")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "联网单位表")
public class BsNetworkingUnit extends Model<BsNetworkingUnit> {
private static final long serialVersionUID = 1L;

    /**
   * 主键ID
   */
    @TableId(value = "unit_id", type = IdType.UUID)
    private String unitId;
    /**
   * 联网单位名称
   */
    private String name;
    /**
   * 机构类型
   */
    private String orgType;
    /**
   * 所属机构单位
   */
    private String orgId;
    /**
   * 所属省
   */
    private String provId;
    /**
   * 所属市
   */
    private String cityId;
    /**
   * 所属区域
   */
    private String areaId;
    /**
   * 地址
   */
    private String address;
    /**
   * 联系人
   */
    private String contacts;
    /**
   * 联系人电话
   */
    private String telephone;
    /**
     *   经度
     */
    private BigDecimal longitude;
    /**
     *  纬度
     */
    private BigDecimal latitude;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 创建人
   */
    private String createUserId;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
   * 修改人
   */
    private String updateUserId;
  
}
