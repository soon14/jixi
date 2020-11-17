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

package com.pig4cloud.pigx.api.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-08-14 14:12:02
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
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
   * 维保单编号
   */
    private String code;
    /**
   * 维保公司
   */
    private String companyId;
    /**
   * 维保公司名称
   */
    private String companyName;
    /**
   * 联系方式
   */
    private String contactWay;
    /**
   * 联系人
   */
    private String contactPerson;
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
     * 区域名称
     */
      private String areaName;
      /**
       * 区域名称
       */
        private String address;
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
    /**
   * 建筑id
   */
    private String buildId;
    /**
   * 建筑名称
   */
    private String buildName;
    /**
   * 楼层id
   */
    private String countyId;
    /**
   * 楼层名称
   */
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
   * 申请机构ID
   */
    private String applyOrgId;
    /**
   * 申请机构名称
   */
    private String applyOrgName;
    /**
     * 处理状态
     */
      private Integer handleStatus;
    /**
   * 处理结果
   */
    private Integer handleResult;
    /**
    * 报警类型
    */
     private String alarmType;
     /**
      * 报警时间
      */
     private LocalDateTime alarmTime;
     /**
      * 维保来源
      */
     private Integer source;
     
     /**
                    * 图片名称(多个以逗号相分隔)
      */
     private String images;
    /**
   * 备注
   */
    private String remark;
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

    /**
     *联网单位Id集合
     */
    @TableField(exist = false)
    private List<String> unitIds;
  
}
