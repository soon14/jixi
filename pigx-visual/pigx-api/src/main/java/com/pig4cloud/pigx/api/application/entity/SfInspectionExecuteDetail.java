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
 * 巡检执行情况详细表
 *
 * @author zm
 * @date 2019-08-10 14:39:00
 */
@Data
@TableName("sf_inspection_execute_detail")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "巡检执行情况详细表")
public class SfInspectionExecuteDetail extends Model<SfInspectionExecuteDetail> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(type = IdType.UUID)
    private String id;
    /**
   * 任务id
   */
    private String taskid;
    /**
   * 执行id
   */
    private String executeid;
    /**
   * 巡检点id
   */
    private String pointId;
    /**
   * 巡检点名称
   */
    private String pointName;
    /**
   * 建筑ID
   */
    private String pointBuildId;
    /**
   * 建筑名称
   */
    private String pointBuildName;
    /**
   * 巡检点位置ID
   */
    private String pointRegionId;
    /**
   * 巡检点位置名称
   */
    private String pointRegionName;
    /**
   * 设备类型
   */
    private String devicetype;
    /**
   * 设备型号
   */
    private String devicenum;
    /**
   * 设备地址
   */
    private String deviceaddress;
    /**
   * 巡检时间
   */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pointTime;
    /**
   * 巡检人
   */
    private String pointUser;
    /**
   * 巡检人姓名
   */
    private String pointUserName;
    /**
   * 巡检点照片ID
   */
    private String pointImageId;
    /**
     * 是否巡检，0：未巡检，1：已巡检
     */
    private Integer isInspection;
    /**
                 * 联网机构Id
     */
    private String orgId;
    /**
            * 联网机构名称
    */
    private String orgName;
    /**
   * 描述
   */
    private String remark;
  
}
