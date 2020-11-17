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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 巡检执行表
 *
 * @author zm
 * @date 2019-08-10 13:20:06
 */
@Data
@TableName("sf_inspection_execute")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "巡检执行表")
public class SfInspectionExecute extends Model<SfInspectionExecute> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(type =IdType.UUID )
    private String id;
    /**
   * 任务id
   */
    private String taskid;
    /**
   * 任务名称
   */
    private String taskname;
    /**
   * 联网单位ID
   */
    private String orgId;
    /**
   * 联网单位名称
   */
    private String orgName;
    /**
   * 建筑id
   */
    private String buildid;
    /**
   * 建筑名称
   */
    private String tourBuildName;
    /**
     * 巡检点Id集合
     */
    private String tourlist;
    /**
   * 巡检人员集合
   */
    private String persons;
    /**
   * 开始时间
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    /**
   * 结束时间
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    /**
   * 任务状态:巡检状态（1,进行中;2,已完成;3,已逾期）
   */
    private Integer status;
    /**
   * 创建人ID
   */
    private String createUserId;
    /**
   * 创建人姓名
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createUserName;
    /**
   * 创建时间
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;
    /**
   * 备注
   */
    private String remark;
  
}
