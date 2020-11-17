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
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 教育培训表
 *
 * @author pigx code generator
 * @date 2019-09-20 13:21:44
 */
@Data
@TableName("sf_education")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "教育培训表")
public class SfEducation extends Model<SfEducation> {
private static final long serialVersionUID = 1L;

    /**
   * ID
   */
    @TableId(type = IdType.UUID)
    private String id;
    /**
   * 标题(课程名称)
   */
    private String title;
    /**
   * 内容（文本）
   */
    private String detail;
    /**
   * 缩略图id
   */
    private String thumbnailId;
    /**
   * 文件类型 1-文件，2-图片，3-视频
   */
    private Integer fileType;
    /**
   * 
   */
    private String fileId;
    /**
   * 浏览次数
   */
    private Integer hits;
    /**
   * 发布时间
   */
    private LocalDateTime issueTime;
    /**
   * 开始时间
   */
    private LocalDateTime beginTime;
    /**
   * 结束时间
   */
    private LocalDateTime endTime;
  
}
