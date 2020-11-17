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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.dto.ProvinceDTO;
import com.pig4cloud.pigx.api.user.entity.BsProvince;
import com.pig4cloud.pigx.api.user.mapper.BsProvinceMapper;
import com.pig4cloud.pigx.api.user.service.BsProvinceService;
import com.pig4cloud.pigx.api.user.vo.ProvinceVO;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 省份表
 *
 * @author lhd
 * @date 2019-05-15 15:38:10
 */
@Slf4j
@Service
@AllArgsConstructor
public class BsProvinceServiceImpl extends ServiceImpl<BsProvinceMapper, BsProvince> implements BsProvinceService {

	private final BsProvinceMapper bsProvinceMapper;
	
	@Override
	public IPage<List<ProvinceVO>> getProvinceList(Page page, ProvinceDTO provinceDTO) throws Exception{
		return bsProvinceMapper.getProvinceList(page, provinceDTO);
	}

	@Override
	public ProvinceVO getProvinceByCode(String code) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsProvinceMapper.getProvinceByCode(code);
	}

}
