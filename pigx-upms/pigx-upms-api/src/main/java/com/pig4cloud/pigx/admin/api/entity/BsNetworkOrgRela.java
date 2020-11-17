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

package com.pig4cloud.pigx.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联网单位机构关系表
 *
 * @author pigx code generator
 * @date 2019-08-14 09:45:20
 */
@Data
@TableName("bs_network_org_rela")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "联网单位机构关系表")
public class BsNetworkOrgRela extends Model<BsNetworkOrgRela> {
private static final long serialVersionUID = 1L;

    /**
   * 主键ID
   */
    @TableId
    private String id;
    /**
   * 联网单位ID
   */
    private String unitId;
    /**
   * 联网单位名称
   */
    private String unitName;
    /**
   * 机构类型CODE
   */
    private String orgTypeCode;
    /**
   * 机构id
   */
    private String orgId;
    /**
   * 机构名称
   */
    private String orgName;
  
}
