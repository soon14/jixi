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

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.device.generator.dto.DmGroupDTO;
import com.pig4cloud.pigx.device.generator.entity.DmGroup;
import com.pig4cloud.pigx.device.generator.mapper.DmGroupMapper;
import com.pig4cloud.pigx.device.generator.service.DmGroupService;
import com.pig4cloud.pigx.device.generator.vo.DmGroupVo;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 设备分组表
 *
 * @author lhd
 * @date 2019-06-21 10:37:34
 */
@Service
@AllArgsConstructor
public class DmGroupServiceImpl extends ServiceImpl<DmGroupMapper, DmGroup> implements DmGroupService {

	private final DmGroupMapper dmGroupMapper;
	
	@Override
	public List<DmGroupDTO> getSelectParentId(String parentId) {
		return dmGroupMapper.getSelectParentId(parentId);
	}

	@Override
	public List<DmGroup> getParentGroup(String groupId) {
		List<DmGroup> list=new ArrayList<DmGroup>();
		DmGroup dto=(DmGroup) this.getById(groupId);
		list.add(dto);
		if(dto!=null&&!StringUtils.isEmpty(dto.getParentId())) {
			 recursive(dto.getParentId(),list);
		}
		return list;
	}
	
	//递归方法,如果父id不等于空,循环取出父分组实体
	private void recursive(String parentId,List list) {
		DmGroup dto=new DmGroup();
		dto=dmGroupMapper.getParentGroup(parentId);
		if(dto!=null&&!StringUtils.isEmpty(dto.getParentId())) {
			list.add(dto);
			recursive(dto.getParentId(),list);
		}
	}

	@Override
	public IPage<List<DmGroupVo>> getGroupList(Page page, DmGroup dmGroup) {
		
		return dmGroupMapper.getGroupList(page, dmGroup);
	}

}
