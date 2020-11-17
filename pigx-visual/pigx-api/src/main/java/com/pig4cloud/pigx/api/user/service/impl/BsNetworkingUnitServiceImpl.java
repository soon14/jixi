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
package com.pig4cloud.pigx.api.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.mapper.BsNetworkingUnitMapper;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

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
	public NetworkingUnitVo getUnitById(String unitId) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.getUnitById(unitId);
	}

	@Override
	public List<BsNetworkingUnit> getUnitByAreaId(String areaId,List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.getUnitByAreaId(areaId,unitIds);
	}

	@Override
	public List<String> getUnitIdByUnitId(String orgId) throws Exception{
		 DynamicDataSourceContextHolder.clearDataSourceType();
		 return bsNetworkingUnitMapper.getUnitIdByUnitId(orgId);
	}

	@Override
	public List<String> getUnitIdByAreaId(String areaId,List<String> unitIds) throws Exception{
		 DynamicDataSourceContextHolder.clearDataSourceType();
		 return bsNetworkingUnitMapper.getUnitIdByAreaId(areaId,unitIds);
	}

	@Override
	public List<BsNetworkingUnit> select(List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.selectUnitInfo(unitIds);
	}

	@Override
	public List<NetworkingUnitVo> getBsNetworkingUnitPage(Page page, BsNetworkingUnit bsNetworkingUnit,List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.getBsNetworkingUnitPage(page,bsNetworkingUnit,unitIds);
	}

	@Override
	public List<BsNetworkingUnit> getBsNetworkingUnitList(List<String> unitIds,String name) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.getBsNetworkingUnitList(unitIds,name);
	}

	@Override
	public List<Map> findNetWorkDetail(String unitId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsNetworkingUnitMapper.findNetWorkDetail(unitId);
	}

}
