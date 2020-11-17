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
package com.pig4cloud.pigx.api.application.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.application.entity.SfFireReport;
import com.pig4cloud.pigx.api.application.mapper.SfFireReportMapper;
import com.pig4cloud.pigx.api.application.service.SfFireReportService;
import com.pig4cloud.pigx.api.application.vo.SfFireReportVo;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 消防报告表
 *
 * @author pigx code generator
 * @date 2019-09-18 11:38:04
 */
@Service
@AllArgsConstructor
public class SfFireReportServiceImpl extends ServiceImpl<SfFireReportMapper, SfFireReport> implements SfFireReportService {
	
	private final SfFireReportMapper sfFireReportMapper;
	@Override
	public List<SfFireReport> getSfFireReportByCons(SfFireReport sfFireReport) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireReportMapper.getSfFireReportByCons(sfFireReport);
	}
	@Override
	public List<SfFireReportVo> getSfFireReportPage(Page page,SfFireReport sfFireReport) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireReportMapper.getSfFireReportPage(page,sfFireReport);
	}

}
