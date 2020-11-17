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

package com.pig4cloud.pigx.device.generator.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.device.generator.dto.DmGroupDTO;
import com.pig4cloud.pigx.device.generator.entity.DmGroup;
import com.pig4cloud.pigx.device.generator.vo.DmGroupVo;

/**
 * 设备分组表
 *
 * @author lhd
 * @date 2019-06-21 10:37:34
 */
public interface DmGroupMapper extends BaseMapper<DmGroup> {

	/**
	 * 获取分组select
	 * 
	 * @param parentId 父级ID
	 * @return
	 */
	List<DmGroupDTO> getSelectParentId(String parentId);

	/**
	 * 根据分组id查询父分组
	 * 
	 * @param groupId
	 * @return
	 */
	DmGroup getParentGroup(@Param("parentId") String parentId);

	/**
	 * 查询分组信息列表
	 * 
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmGroupVo>> getGroupList(Page page, @Param("query") DmGroup dmGroup);
}
