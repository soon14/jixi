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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:39
 */
@Data
@TableName("sf_fire_maintenance")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消防维保表")
public class SfFireMaintenance extends Model<SfFireMaintenance> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId
    private String id;
    /**
   * 维保单编号
   */
    private String code;
    /**
   * 维保公司
   */
    private String companyId;
    
    private String companyName;
    /**
   * 物业员工联系方式
   */
    private String contactWay;
    /**
   * 产品类别
   */
    private String produceCate;
    /**
   * 产品名称
   */
    private String produceName;
    /**
   * 厂家ID
   */
    private String factorId;
    /**
   * 品牌ID
   */
    private String brandId;
    /**
   * 区域编码
   */
    private String area;
    /**
   * 设备编号
   */
    private String deviceCode;
    /**
   * 设备名称
   */
    private String deviceName;
    /**
   * 联网单位id
   */
    private String networkUnitId;
    
    private String networkUnitName;
    /**
   * 建筑id
   */
    private String buildId;
    
    private String buildName;
    /**
   * 楼层id
   */
    private String countyId;
    
    private String countyName;
    /**
   * 所在位置
   */
    private String position;
    /**
   * 故障描述
   */
    private String faultDesc;
    /**
   * 申请人id
   */
    private String createUserId;
    /**
   * 申请人姓名
   */
    private String createUserName;
    /**
   * 申请时间
   */
    private LocalDateTime createTime;
    /**
   * 修改人id
   */
    private String updateUserId;
    /**
   * 修改人姓名
   */
    private String updateUserName;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
  
}
