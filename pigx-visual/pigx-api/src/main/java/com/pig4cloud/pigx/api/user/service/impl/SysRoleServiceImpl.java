/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pigx.api.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.entity.SysRole;
import com.pig4cloud.pigx.api.user.entity.SysRoleMenu;
import com.pig4cloud.pigx.api.user.mapper.SysRoleMapper;
import com.pig4cloud.pigx.api.user.mapper.SysRoleMenuMapper;
import com.pig4cloud.pigx.api.user.service.SysRoleService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.constant.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	private SysRoleMapper sysRoleMapper;

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List findRolesByUserId(String userId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return baseMapper.listRolesByUserId(userId);
	}

	/**
	 * 通过角色ID，删除角色,并清空角色菜单缓存
	 *
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeRoleById(String id) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		sysRoleMenuMapper.delete(Wrappers
			.<SysRoleMenu>update().lambda()
			.eq(SysRoleMenu::getRoleId, id));
		return this.removeById(id);
	}

	/**
	    * 分页查询角色信息
	 *
	 * @param page 分页对象
	 * @param belongSys 所属系统
	 * @return 分页对象
	 */
	@Override
	public IPage getRoleByBelongSysPage(Page page, SysRole sysRole) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return sysRoleMapper.getRoleByBelongSysPage(page, sysRole);
	}

	/**
	    *   查询角色根据用户id和所属系统
	 * 
	 * @param userId
	 * @param belongSys
	 * @return
	 */
	public List<SysRole> findRolesByUserIdBelongSys(String userId, String belongSys) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return sysRoleMapper.listRolesByUserIdBelongSys(userId,belongSys);
	}

	/**
	 *  通过角色id,查询角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public SysRole getById(String roleId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return super.getById(roleId);
	}

}
