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

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.admin.api.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.admin.mapper.BsBuildinfoMapper;
import com.pig4cloud.pigx.admin.mapper.BsCountyMapper;
import com.pig4cloud.pigx.admin.mapper.BsNetworkingUnitMapper;
import com.pig4cloud.pigx.admin.service.BsNetworkingUnitService;

import lombok.AllArgsConstructor;

/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
@Service
@AllArgsConstructor
public class BsNetworkingUnitServiceImpl extends ServiceImpl<BsNetworkingUnitMapper, BsNetworkingUnit> implements BsNetworkingUnitService {

	private final BsNetworkingUnitMapper bsNetworkingUnitMapper;
	
	@Override
	public NetworkingUnitVo getUnitById(String unitId) {
		return bsNetworkingUnitMapper.getUnitById(unitId);
	}

	@Override
	public List<BsNetworkingUnit> findBatch(List<String> unitIds) {
		return bsNetworkingUnitMapper.findBatch(unitIds);
	}
}
