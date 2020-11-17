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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
@Data
@TableName("bs_buildinfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "楼体信息表")
public class BsBuildinfo extends Model<BsBuildinfo> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(value = "build_id", type = IdType.UUID)
    private String buildId;
    /**
   * 组织机构编号：从组织机构表里取ID值
   */
    private String deptId;
    
    /**
     * 组织机构名称
     */
    private String deptName;
    /**
     * 联网单位Id
     */
    private String networkUnitId;
    /**
   * 类型信息：从基础类型信息表里取ID值，要求符合GA/T 396-2002标准
   */
    private String typeId;
    /**
   * 自定义编号
   */
    private String code;
    /**
   * 楼体名称
   */
    private String name;
    /**
   * 楼体地址
   */
    private String address;
    /**
   * 建造日期：
   */
    private LocalDateTime constructionDate;
    /**
   * 使用性质：从基础类型表里取记录值，要求符合标准GA/T 396-2002
   */
    private Integer usefulness;
    /**
   * 火灾危险性：从基础类型表里取数据，要求符合GA/T 396-2002标准
   */
    private Integer fireRating;
    /**
   * 耐火等级：从基础类型表里取数据，要求符合标准GA/T 396-2002
   */
    private Integer refractoryRating;
    /**
   * 结构类型：从基础类型表里取数据，要求符合标准GA/T 396-2002
   */
    private Integer structureType;
    /**
   * 建筑高度：单位（米），精确到小数点后2位
   */
    private BigDecimal height;
    /**
   * 建筑面积：单位（平方米）
   */
    private Integer buildingArea;
    /**
   * 占地面积：单位（平方米）
   */
    private Integer floorArea;
    /**
   * 标准层面积：单位（平方米）
   */
    private Integer standardFloorArea;
    /**
   * 地上层数：
   */
    private Integer overgroundAmount;
    /**
   * 地上层面积：单位（平方米）
   */
    private Integer overgroundArea;
    /**
   * 地下层数：
   */
    private Integer undergroundAmount;
    /**
   * 地下层面积：单位（平方米）
   */
    private Integer undergroundArea;
    /**
   * 隧道高度：单位（米），精确到小数点后2位
   */
    private BigDecimal tunnelHeight;
    /**
   * 隧道长度：单位（米），精确到小数点后2位
   */
    private BigDecimal tunnelLength;
    /**
   * 消防控制室位置：
   */
    private String controlRoomPosition;
    /**
   * 避难层数量：
   */
    private Integer hideoutFloorAmount;
    /**
   * 避难层总面积：单位（平方米）
   */
    private Integer hideoutArea;
    /**
   * 避难层位置：
   */
    private String hideoutPosition;
    /**
   * 安全出口数量：
   */
    private Integer exitAmount;
    /**
   * 安全出口位置：
   */
    private String exitPosition;
    /**
   * 安全出口形式：
   */
    private String exitStyle;
    /**
   * 消防电梯数量：
   */
    private Integer elevatorAmount;
    /**
   * 消防电梯容纳总质量：单位（千克）
   */
    private Integer elevatorBearing;
    /**
   * 日常工作时间人数：
   */
    private Integer normalWorkerAmount;
    /**
   * 最大容纳人数：
   */
    private Integer maxWorkerAmount;
    /**
   * 储存物名称：
   */
    private String storageName;
    /**
   * 储存物数量：
   */
    private String storageAmount;
    /**
   * 储存物性质：
   */
    private String storageProperty;
    /**
   * 储存物形态：
   */
    private String storageForm;
    /**
   * 储存容积：单位（立方米）
   */
    private Integer storeSpace;
    /**
   * 主要原料：
   */
    private String material;
    /**
   * 主要产品：
   */
    private String product;
    /**
   * 毗邻建筑物情况：毗邻建筑的使用性质、结构类型、建筑高度、与本建筑物的间距等信息
   */
    private String nearbyCase;
    /**
   * 楼体图片
   */
    private String imageId;
    /**
   * 建筑立面图：
   */
    private String verticalViewImageId;
    /**
   * 消防设施平面布置图：
   */
    private String devicePlanViewImageId;
    /**
   * 建筑平面图：
   */
    private String planViewImageId;
    /**
   * json描述
   */
    private String description;
    /**
   * 备注
   */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime modifyTime;
    /**
   * 
   */
    private Integer inure;
    /**
   * 是否删除状态：0-正常；1-被删除
   */
    private Integer isRemoved;
  
}
