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

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.entity.BsCounty;
import com.pig4cloud.pigx.api.user.mapper.BsCountyMapper;
import com.pig4cloud.pigx.api.user.service.BsCountyService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 楼层信息
 *
 * @author lhd
 * @date 2019-05-15 15:37:53
 */
@Slf4j
@Service
@AllArgsConstructor
public class BsCountyServiceImpl extends ServiceImpl<BsCountyMapper, BsCounty> implements BsCountyService {

	private final BsCountyMapper bsCountyMapper;
	
	@Override
	public int deleteCountyByBuildId(String buildId) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsCountyMapper.deleteCountyByBuildId(buildId);
	}

	@Override
	public List<BsCounty> selectCounty(String buildId) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsCountyMapper.selectCounty(buildId);
	}

}
