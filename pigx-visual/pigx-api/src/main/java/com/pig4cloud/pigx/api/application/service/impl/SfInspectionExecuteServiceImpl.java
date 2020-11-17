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
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecute;
import com.pig4cloud.pigx.api.application.mapper.SfInspectionExecuteDetailMapper;
import com.pig4cloud.pigx.api.application.mapper.SfInspectionExecuteMapper;
import com.pig4cloud.pigx.api.application.service.SfInspectionExecuteService;
import com.pig4cloud.pigx.api.application.vo.SfInspectionResultVO;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
@Service
@AllArgsConstructor
public class SfInspectionExecuteServiceImpl extends ServiceImpl<SfInspectionExecuteMapper, SfInspectionExecute> implements SfInspectionExecuteService {

    private final SfInspectionExecuteMapper sfInspectionExecuteMapper;

    private final SfInspectionExecuteDetailMapper sfInspectionExecuteDetailMapper;

	@Override
	public List<SfInspectionResultVO> getInspectionRecord(Page page, List<Integer> inspectStatus, List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteMapper.getInspectionRecord(page,inspectStatus,unitIds);
	}


  
}
