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

package com.pig4cloud.pigx.device.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 设备分组关系表
 *
 * @author lhd
 * @date 2019-06-21 10:37:29
 */
@Data
@TableName("dm_device_group")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备分组关系表")
public class DmDeviceGroup extends Model<DmDeviceGroup> {
private static final long serialVersionUID = 1L;

    /**
   * 分组id
   */
    @NotNull(message  = "分组ID不能为空")
	@NotBlank(message = "分组ID不能为空字符串")
    private String groupId;
    /**
   * 设备id
   */
    @NotNull(message  = "设备ID不能为空")
	@NotBlank(message = "设备ID不能为空字符串")
    private String deviceId;
  
}
