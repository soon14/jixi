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

package com.pig4cloud.pigx.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.admin.api.dto.BuildinfoDTO;
import com.pig4cloud.pigx.admin.api.entity.BsBuildinfo;
import com.pig4cloud.pigx.admin.api.vo.BuildinfoVO;
import com.pig4cloud.pigx.common.data.datascope.DataScope;

/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
public interface BsBuildinfoMapper extends BaseMapper<BsBuildinfo> {
	
	/**
	 * 分页查询用户信息（含角色）
	 *
	 * @param page      分页
	 * @param buildinfoDTO   查询参数
	 * @param dataScope
	 * @return list
	 */
	IPage<List<BuildinfoVO>> getBuildinfoVosPage(Page page, @Param("query") BuildinfoDTO buildinfoDTO, DataScope dataScope);

	/**
	    *    根据业主单位获取建筑select
	 * @param networkUnitId
	 * @return
	 */
	List<BuildinfoDTO> selectBuild(@Param("networkUnitId") String networkUnitId);
}
