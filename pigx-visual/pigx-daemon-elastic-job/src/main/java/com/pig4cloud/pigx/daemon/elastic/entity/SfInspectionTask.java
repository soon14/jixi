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

package com.pig4cloud.pigx.daemon.elastic.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 巡检任务表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "巡检任务表")
public class SfInspectionTask extends Model<SfInspectionTask> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    private String id;
    /**
   * 任务编码
   */
    private String taskcode;
    /**
   * 任务名称
   */
    private String taskname;
    /**
   * 任务说明
   */
    private String taskcontent;
    /**
   * 任务类型: 1-临时配发  2-定时配发
   */
    private Integer tasktype;
    /**
   * 方案ID
   */
    private String planid;
    /**
   * 开始日(1-31的数值)
   */
    private Integer kaishi;
    /**
   * 结束日(1-31的数值)
   */
    private Integer finish;
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
   * 联网单位ID
   */
    private String orgId;
    
    private String orgName;
    /**
   * 建筑id
   */
    private String buildid;
    
    private String buildName;
    /**
   * 配置巡检人员集合
   */
    private String persons;
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
  
}
