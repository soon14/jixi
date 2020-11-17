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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 巡检点表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:59
 */
@Data
@TableName("sf_inspection_point")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "巡检点表")
public class SfInspectionPoint extends Model<SfInspectionPoint> {
private static final long serialVersionUID = 1L;

    /**
   * 主键ID，32位去-的uuid
   */
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /**
   * 联网单位ID
   */
    @NotNull(message = "联网单位ID不能为空")
    private String orgId;
    /**
     * 联网单位名称
     */
    @NotNull(message = "联网单位名称不能为空")
    private String orgName;
    /**
   * 上级单位ID
   */
    private String pid;
    /**
     * 上级单位名称
     */
    private String pname;
    /**
   * 巡检点名称
   */
    @NotNull(message = "巡检点名称不能为空")
    private String pointName;
    /**
   * 一维码
   */
    private String pointBarCode;
    /**
   * RFID
   */
    private String pointRfidCode;
    /**
   * 二维码
   */
    private String pointQrCode;
    /**
   * 绑定状态 0-未绑定  1-已绑定
   */
    private Integer binding;
    /**
   * 巡检点区域类型ID
   */
    private String pointType;
    /**
   * 建筑ID
   */
    @NotNull(message = "建筑ID不能为空")
    private String pointBuildId;
    /**
     * 建筑名称
     */
    @NotNull(message = "建筑名称不能为空")
    private String pointBuildName;
    /**
   * 巡检点位置ID
   */
    @NotNull(message = "巡检点位置ID不能为空")
    private String pointRegionId;
    /**
     * 巡检点位置名称
     */
    @NotNull(message = "巡检点位置名称不能为空")
    private String pointRegionName;
    /**
     * 巡检点所属楼层
     */
    private String pointFloor;
    /**
   * 巡检点照片ID
   */
    private String pointImageId;
    /**
   * 设备类型
   */
    @NotNull(message = "设备类型不能为空")
    private String devicetype;
    /**
   * 设备地址
   */
    private String deviceaddress;
    /**
   * 设备型号
   */
    @NotNull(message = "设备型号不能为空")
    private String devicenum;
    /**
   * 生产日期
   */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "生产日期不能为空")
    private LocalDateTime produceDate;
    /**
   * 保质期
   */
    private String expirationdate;
    /**
   * 是否删除: 0-正常；1-已删除
   */
    private Integer isremoved;
    /**
   * 是否报废: 0-正常；1-已报废
   */
    private Integer isscrap;
	/**
	   *  经度
	 */
	private BigDecimal longitude;
	/**
	   *  纬度
	 */
	private BigDecimal latitude;
    /**
   * 创建人ID
   */
    private String createUserId;
    /**
   * 创建人姓名
   */
    private String createUserName;
    /**
   * 创建日期
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifytime;
    /**
   * 备注
   */
    private String remark;

    /**
     * 当前登录用户所属联网单位Id
     */
    @TableField(exist = false)
    private List<String> unitIds;
  
}
