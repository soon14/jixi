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
package com.pig4cloud.pigx.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkOrgRela;
import com.pig4cloud.pigx.admin.mapper.BsNetworkOrgRelaMapper;
import com.pig4cloud.pigx.admin.service.BsNetworkOrgRelaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联网单位机构关系表
 *
 * @author pigx code generator
 * @date 2019-08-14 09:45:20
 */
@Service
@AllArgsConstructor
public class BsNetworkOrgRelaServiceImpl extends ServiceImpl<BsNetworkOrgRelaMapper, BsNetworkOrgRela> implements BsNetworkOrgRelaService {

	private final BsNetworkOrgRelaMapper bsNetworkOrgRelaMapper;

	@Override
	public void saveBatch(List<BsNetworkOrgRela> bsNetworkOrgRelaList) {
		bsNetworkOrgRelaMapper.saveBatch(bsNetworkOrgRelaList);
	}

	@Override
	public void deleteByOrgId(String orgId) {
		bsNetworkOrgRelaMapper.deleteByOrgId(orgId);
	}

	@Override
	public List<String> findByOrgId(String orgId) {
		return bsNetworkOrgRelaMapper.findByOrgId(orgId);
	}
}
