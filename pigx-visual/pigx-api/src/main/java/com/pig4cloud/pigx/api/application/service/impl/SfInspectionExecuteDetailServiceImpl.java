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
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.api.application.mapper.SfInspectionExecuteDetailMapper;
import com.pig4cloud.pigx.api.application.service.SfInspectionExecuteDetailService;
import com.pig4cloud.pigx.api.application.vo.SfInspectionExecuteDetailVo;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 巡检执行情况详细表
 *
 * @author zm
 * @date 2019-08-11 10:30:36
 */
@Service
@AllArgsConstructor
public class SfInspectionExecuteDetailServiceImpl extends ServiceImpl<SfInspectionExecuteDetailMapper, SfInspectionExecuteDetail> implements SfInspectionExecuteDetailService {
	
	private final SfInspectionExecuteDetailMapper sfInspectionExecuteDetailMapper;
	
	public Integer findCountByIsInspection(SfInspectionExecuteDetail sfInspectionExecuteDetail) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteDetailMapper.findCountByIsInspection(sfInspectionExecuteDetail);
	}

	@Override
	public List<SfInspectionExecuteDetailVo> getExecuteDetailByExeId(Page page,String executeId) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteDetailMapper.getExecuteDetailByExeId(page,executeId);
	}

	@Override
	public SfInspectionExecuteDetailVo getExecuteDetailByDetailId(String detailId) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteDetailMapper.getExecuteDetailByDetailId(detailId);
	}

	@Override
	public boolean saveInspectionResult(SfInspectionExecuteDetailVo sfInspectionExecuteDetail) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteDetailMapper.saveInspectionResult(sfInspectionExecuteDetail);
	}

	@Override
	public List<SfInspectionExecuteDetailVo> getInspectionEvent(Integer status,Integer isInspection, List<String> unitIds)
			throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfInspectionExecuteDetailMapper.getInspectionEvent(status,isInspection,unitIds);
	}


   
}
