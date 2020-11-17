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
package com.pig4cloud.pigx.api.user.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.dto.BuildinfoDTO;
import com.pig4cloud.pigx.api.user.entity.BsBuildinfo;
import com.pig4cloud.pigx.api.user.mapper.BsBuildinfoMapper;
import com.pig4cloud.pigx.api.user.mapper.BsCountyMapper;
import com.pig4cloud.pigx.api.user.service.BsBuildinfoService;
import com.pig4cloud.pigx.api.user.vo.BuildinfoVO;
import com.pig4cloud.pigx.api.user.vo.CountyVO;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.data.datascope.DataScope;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
@Slf4j
@Service
@AllArgsConstructor
public class BsBuildinfoServiceImpl extends ServiceImpl<BsBuildinfoMapper, BsBuildinfo> implements BsBuildinfoService {

	private final BsBuildinfoMapper bsBuildinfoMapper;
	private final BsCountyMapper bsCountyMapper;
	
	@Override
	public List<BuildinfoVO> getBuildinfoPage(Page page, BuildinfoDTO buildinfoDTO,List<String> unitIds)  throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsBuildinfoMapper.getBuildinfoVosPage(page, buildinfoDTO, new DataScope(),unitIds);
	}
	
	@Override
	public IPage<List<CountyVO>> getCountyByBuildId(Page page, BuildinfoDTO buildinfoDTO)  throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsCountyMapper.getCountyByBuildIdPage(page, buildinfoDTO.getBuildId());
	}

	@Override
	public Boolean updateBuildinfo(BuildinfoDTO buildinfoDTO) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		BsBuildinfo bsBuildinfo = new BsBuildinfo();
		BeanUtils.copyProperties(buildinfoDTO, bsBuildinfo);
		
		bsBuildinfo.setModifyTime(LocalDateTime.now());
		int i = bsBuildinfoMapper.updateById(bsBuildinfo);
		
		return i == 0 ? Boolean.FALSE : Boolean.TRUE;
	}

	@Override
	public Boolean deleteBuildinfo(String buildId) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		bsCountyMapper.deleteCountyByBuildId(buildId);
		return this.removeById(buildId);
	}

	@Override
	public List<BuildinfoDTO> selectBuild(String networkUnitId) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsBuildinfoMapper.selectBuild(networkUnitId);
	}

}
