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

package com.pig4cloud.pigx.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.admin.api.dto.BuildinfoDTO;
import com.pig4cloud.pigx.admin.api.entity.BsBuildinfo;
import com.pig4cloud.pigx.admin.api.vo.BuildinfoVO;
import com.pig4cloud.pigx.admin.api.vo.CountyVO;

/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
public interface BsBuildinfoService extends IService<BsBuildinfo> {
	
	/**
	 * 分页查询建筑
	 * @param page
	 * @param buildinfoDTO
	 * @return
	 */
	IPage<List<BuildinfoVO>> getBuildinfoPage(Page page, BuildinfoDTO buildinfoDTO);

	/**
	 * 更新建筑信息
	 * @param buildinfoDTO
	 * @return
	 */
	Boolean updateBuildinfo(BuildinfoDTO buildinfoDTO);

	/**
	 * 删除建筑记录
	 * @param bsBuildinfo
	 * @return
	 */
	Boolean deleteBuildinfo(String buildId);

	IPage<List<CountyVO>> getCountyByBuildId(Page page, BuildinfoDTO buildinfoDTO);
	
	/**
	   * 查询所在建筑信息
	 * 
	 * @param networkUnitId
	 * @return
	 */
	List<BuildinfoDTO> selectBuild(String networkUnitId);
	
}
