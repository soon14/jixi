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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.common.minio.service.MinioTemplate;
import com.pig4cloud.pigx.api.application.entity.SfEducation;
import com.pig4cloud.pigx.api.application.mapper.SfEducationMapper;
import com.pig4cloud.pigx.api.application.service.SfEducationService;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 教育培训表
 *
 * @author pigx code generator
 * @date 2019-09-20 13:21:44
 */
@Service
@AllArgsConstructor
public class SfEducationServiceImpl extends ServiceImpl<SfEducationMapper, SfEducation> implements SfEducationService {
	
	private final SfEducationMapper sfEducationMapper;
	
	private final MinioTemplate minioTemplate;
	@Override
	public List<SfEducation> getSfEducationPage(Page page, SfEducation sfEducation) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfEducationMapper.getSfEducationPage(page,sfEducation);
	}
	@Override
	public boolean saveEducation(SfEducation sfEducation) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return save(sfEducation);
	}
	@Override
	public SfEducation getById(String id) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return super.getById(id);
	}
	@Override
	public boolean removeById(String id) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		SfEducation education=super.getById(id);
		//删除文件
		String fileIds=education.getFileId();
		if(!StringUtils.isEmpty(fileIds)) {
			String[] Ids=fileIds.split(",");
			for(String i:Ids) {
				if(StringUtils.isEmpty(i))continue;
				String[] nameArray = StrUtil.split(i, StrUtil.DASHED);
				minioTemplate.removeObject(nameArray[0],nameArray[1]);
			}
		}
		return super.removeById(id);
	}

	public boolean updateByEduId(SfEducation sfEducation)  throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return super.updateById(sfEducation);
	}
}
