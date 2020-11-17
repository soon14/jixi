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
package com.pig4cloud.pigx.device.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.device.generator.dto.DmBrandDTO;
import com.pig4cloud.pigx.device.generator.entity.DmBrand;
import com.pig4cloud.pigx.device.generator.mapper.DmBrandMapper;
import com.pig4cloud.pigx.device.generator.service.BrandService;
import com.pig4cloud.pigx.device.generator.service.DmBrandService;
import com.pig4cloud.pigx.device.generator.vo.DmBrandVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌表
 *
 * @author zm
 * @date 2019-07-25 09:30:30
 */
@Service
@AllArgsConstructor
public class BrandServiceImpl extends ServiceImpl<DmBrandMapper, DmBrand> implements BrandService {

	private final DmBrandMapper dmBrandMapper;
	
	/**
	 * 根据厂家ID查询品牌信息
	 * 
	 */
	@Override
	public List<DmBrandDTO> getSelectFactorId(String factorId) {
		return dmBrandMapper.getSelectFactorId(factorId);
	}

	@Override
	public IPage<List<DmBrandVO>> getBrandList(Page page, DmBrand dmBrand) {
		
		return dmBrandMapper.getBrandList(page, dmBrand);
	}

}
