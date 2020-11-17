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

package com.pig4cloud.pigx.api.device.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.device.dto.DmProductDTO;
import com.pig4cloud.pigx.api.device.entity.DmProduct;
import com.pig4cloud.pigx.api.device.vo.DmProductVo;

/**
 * 产品表
 *
 * @author lhd
 * @date 2019-06-20 10:15:52
 */
public interface DmProductService extends IService<DmProduct> {

	/**
     * 获取产品select
     * @param brandId 品牌ID
     * @return
     */
	List<DmProductDTO> getSelectBrandId(String brandId) throws Exception;
	
	/**
	   *  查询产品列表
	 * 
	 * @param page
	 * @param dmProduct
	 * @return
	 */
	IPage<List<DmProductVo>> getProductList(Page page, DmProduct dmProduct) throws Exception;
}