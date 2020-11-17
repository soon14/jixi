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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.admin.api.dto.CityDTO;
import com.pig4cloud.pigx.admin.api.entity.BsCity;
import com.pig4cloud.pigx.admin.api.vo.CityVO;
import com.pig4cloud.pigx.admin.mapper.BsBuildinfoMapper;
import com.pig4cloud.pigx.admin.mapper.BsCityMapper;
import com.pig4cloud.pigx.admin.mapper.BsCountyMapper;
import com.pig4cloud.pigx.admin.service.BsCityService;

import lombok.AllArgsConstructor;

/**
 * 城市表
 *
 * @author lhd
 * @date 2019-05-15 15:38:02
 */
@Service
@AllArgsConstructor
public class BsCityServiceImpl extends ServiceImpl<BsCityMapper, BsCity> implements BsCityService {

	private final BsCityMapper bsCityMapper;
	
	@Override
	public IPage<List<CityVO>> getCityList(Page page, CityDTO cityDTO) {
		// TODO Auto-generated method stub
		return bsCityMapper.getCityList(page, cityDTO);
	}

}
