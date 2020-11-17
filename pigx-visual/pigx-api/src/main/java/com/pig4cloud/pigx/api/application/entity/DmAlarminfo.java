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
 * 事件上送数据
 *
 * @author pigx code generator
 * @date 2019-08-08 11:37:42
 */
@Data
@TableName("dm_alarminfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "事件上送数据")
public class DmAlarminfo extends Model<DmAlarminfo> {
private static final long serialVersionUID = 1L;

    /**
   * 自增ID
   */
    @TableId(type = IdType.UUID)
    private String id;
    /**
   * 设备编号
   */
    private String deviceCode;
    /**
   * 上送时间
   */
    private LocalDateTime sendTime;
    /**
   * 上送事件个数
   */
    private Integer eventCount;
    /**
   * 事件类型: 1-DI变位事件 2-DO动作事件 3-告警事件 4-自检事件 5-操作事件 6-断线事件 7-消防事件
   */
    private Integer eventType;
    /**
   * 事件详情
   */
    private String eventDesc;
    /**
   * 报警时间
   */
    private LocalDateTime alarmTime;
    /**
   * 报警类型
   */
    private Integer alarmTypeId;
    /**
   * 记录值类型
   */
    private String alarmType;
    /**
   * 记录值
   */
    private String alarmValue;
    /**
   * 设备状态 ：0 未处理  1 已处理
   */
    private Integer deviceStatus;
    /**
   * 预留字段2
   */
    private String extend2;
    /**
   * 添加时间
   */
    private LocalDateTime appendTime;
    /**
   * 处理时间
   */
    private LocalDateTime handleTime;
    /**
   * 处理人
   */
    private String handlePerson;
    /**
   * 处理结果
   */
    private Integer handleResult;
    /**
   * 原因
   */
    private String handleReason;

    /**
     *联网单位Id集合
     */
    @TableField(exist = false)
    private List<String> unitIds;
  
}
