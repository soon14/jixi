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

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区域表
 *
 * @author lhd
 * @date 2019-05-15 15:38:07
 */
@Data
@TableName("bs_area")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "区域表")
public class BsArea extends Model<BsArea> {
private static final long serialVersionUID = 1L;

    /**
   * 区域id
   */
    @TableId(value = "area_id", type = IdType.UUID)
    private String areaId;
    /**
   * 国标编码
   */
    private String code;
    /**
   * 区域名称
   */
    private String name;
    /**
   * 城市id
   */
    private String cityId;
    /**
   * 创建人id
   */
    private String createUserId;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改人id
   */
    private String updateUserId;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
  
}
