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
import com.pig4cloud.pigx.device.generator.entity.DmFunction;
import com.pig4cloud.pigx.device.generator.mapper.DmFunctionMapper;
import com.pig4cloud.pigx.device.generator.service.DmFunctionService;
import com.pig4cloud.pigx.device.generator.vo.DmFunctionVo;

import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 功能表
 *
 * @author lhd
 * @date 2019-06-20 10:15:42
 */
@Service
@AllArgsConstructor
public class DmFunctionServiceImpl extends ServiceImpl<DmFunctionMapper, DmFunction> implements DmFunctionService {

	private final DmFunctionMapper dmFunctionMapper;

	@Override
	public IPage<List<DmFunctionVo>> getFunctionList(Page page, DmFunction dmFunction) {

		return dmFunctionMapper.getFunctionList(page, dmFunction);
	}

	@Override
	public List<DmFunction> getNoDeviceFunction(String devId) {
		return dmFunctionMapper.getNoDeviceFunction(devId);
	}

}
