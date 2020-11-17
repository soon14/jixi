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
package com.pig4cloud.pigx.smartff.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionPointMapper;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionPointService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 巡检点表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:59
 */
@Service
@AllArgsConstructor
public class SfInspectionPointServiceImpl extends ServiceImpl<SfInspectionPointMapper, SfInspectionPoint> implements SfInspectionPointService {

	private SfInspectionPointMapper sfInspectionPointMapper;


	@Override
	public IPage<List<SfInspectionPoint>> findPointByOrgBuildId(Page page, SfInspectionPoint sfInspectionPoint) {
		return sfInspectionPointMapper.findPointByOrgBuildId(page,sfInspectionPoint);
	}

	@Override
	public IPage<List<SfInspectionPoint>> findPointList(Page page, SfInspectionPoint sfInspectionPoint) {
		return sfInspectionPointMapper.findPointList(page,sfInspectionPoint);
	}
}
