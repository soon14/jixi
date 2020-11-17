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

package com.pig4cloud.pigx.device.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.dto.DmGroupDTO;
import com.pig4cloud.pigx.device.generator.entity.DmGroup;
import com.pig4cloud.pigx.device.generator.vo.DmGroupVo;

import java.util.List;

/**
 * 设备分组表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
public interface GroupService extends IService<DmGroup> {

	/**
	 * 获取分组select
	 * 
	 * @param parentId 父级ID
	 * @return
	 */
	List<DmGroupDTO> getSelectParentId(String parentId);

	/**
	 * 根据分组信息,取得父分组信息
	 * 
	 * @param groupId
	 * @return
	 */
	List<DmGroup> getParentGroup(String groupId);

	/**
	 * 查询分组信息列表
	 * 
	 * @param page
	 * @param dmGroup
	 * @return
	 */
	IPage<List<DmGroupVo>> getGroupList(Page page, DmGroup dmGroup);

}
