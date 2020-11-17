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

package com.pig4cloud.pigx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkOrgRela;

import java.util.List;

/**
   *  联网单位机构关系表
 *
 * @author pigx code generator
 * @date 2019-08-14 09:45:20
 */
public interface BsNetworkOrgRelaMapper extends BaseMapper<BsNetworkOrgRela> {
	
	void saveBatch(List<BsNetworkOrgRela> bsNetworkOrgRelaList);

	/**
	 * 通过机构ID删除联网单位机构关系信息
	 * @param orgId
	 */
	void deleteByOrgId(String orgId);

	/**
	 * 通过机构ID删除联网单位机构关系信息中的联网单位ID
	 * @param orgId
	 * @return
	 */
	List<String> findByOrgId(String orgId);
}
