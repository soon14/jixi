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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 消防报告表
 *
 * @author pigx code generator
 * @date 2019-09-20 13:21:34
 */
@Data
@TableName("sf_fire_report")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消防报告表")
public class SfFireReport extends Model<SfFireReport> {
private static final long serialVersionUID = 1L;

    /**
   * 报告id
   */
    @TableId
    private String id;
    /**
   * 联网单位id
   */
    private String unitid;
    /**
   * 用户id
   */
    private String userid;
    /**
   * 报告类型
   */
    private String reporttype;
    /**
   * 年份
   */
    private String rYear;
    /**
   * 月份/周数
   */
    private String monthorweek;
    /**
   * 文件名称
   */
    private String filename;
    /**
   * 消防设备总数
   */
    private String deviceTotal;
    /**
   * 设备完好率
   */
    private String deviceIntactRate;
    /**
   * 应装设备
   */
    private String deviceShould;
    /**
   * 电器火灾设备
   */
    private String deviceElectriFire;
    /**
   * 设备报警
   */
    private String deviceAlarm;
    /**
   * 误报
   */
    private String misreport;
    /**
   * 已处理
   */
    private String processed;
    /**
   * 同比
   */
    private String compared;
    /**
   * 消防演练
   */
    private String fireDrill;
    /**
   * 消防培训
   */
    private String fireTraining;
    /**
   * 视频学习
   */
    private String videoLearning;
    /**
   * 文章宣传
   */
    private String culture;
    /**
   * 巡检次数
   */
    private String inspectionTimes;
    /**
   * 存在隐患率
   */
    private String hiddenTroubleRate;
    /**
   * 设备维保总次数
   */
    private String maintenanceTimes;
    /**
   * 维修次数
   */
    private String repairTimes;
    /**
   * 更换数量
   */
    private String replaceTimes;
    /**
   * 结构类型
   */
    private String structureType;
    /**
   * 消防水池
   */
    private String firePool;
    /**
   * 消防通道
   */
    private String fireExit;
    /**
   * 消防安全评分
   */
    private String fireRating;
    /**
   * 评估等级 
   */
    private String assessmentLevel;
    /**
   * 评估人
   */
    private String creator;
    /**
   * 评估时间
   */
    private LocalDateTime createtime;
  
}
