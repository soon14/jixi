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

package com.pig4cloud.pigx.device.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.dto.DmBrandDTO;
import com.pig4cloud.pigx.device.generator.entity.DmBrand;
import com.pig4cloud.pigx.device.generator.vo.DmBrandVO;

import java.util.List;

/**
 * 品牌表
 *
 * @author zm
 * @date 2019-07-25 09:30:30
 */
public interface BrandService extends IService<DmBrand> {

	/**
	 * 根据厂家ID获取品牌select
	 * @param factorId
	 * @return
	 */
	List<DmBrandDTO> getSelectFactorId(String factorId);
	
	/**
	 * 查询品牌列表
	 * 
	 * @param page
	 * @param dmBrand
	 * @return
	 */
	IPage<List<DmBrandVO>> getBrandList(Page page, DmBrand dmBrand);
}
