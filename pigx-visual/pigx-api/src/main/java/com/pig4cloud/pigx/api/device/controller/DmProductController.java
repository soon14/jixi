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

package com.pig4cloud.pigx.api.device.controller;

import com.pig4cloud.pigx.api.device.dto.DmProductDTO;
import com.pig4cloud.pigx.api.device.service.DmProductService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * 产品表
 *
 * @author lhd
 * @date 2019-06-20 10:15:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmproduct")
@Api(value = "dmproduct", tags = "dmproduct管理")
public class DmProductController {

	private final DmProductService dmProductService;

	/**
	 * 获取产品select
	 * 
	 * @param brandId 品牌ID
	 * @return
	 */
	@SysLog("获取产品select")
	@GetMapping("/select")
	public R selectBrandId(String brandId) {
		List<DmProductDTO> dtos=null;
		try {
			dtos=dmProductService.getSelectBrandId(brandId);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(dtos);
	}

}
