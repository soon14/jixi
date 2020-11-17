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

package com.pig4cloud.pigx.api.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.user.entity.SysRole;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(String userId) throws Exception;

	/**
	 * 通过角色ID，删除角色
	 *
	 * @param id
	 * @return
	 */
	Boolean removeRoleById(String id) throws Exception;
	
	/**
	    * 根据所属系统 分页查询角色信息
	 * @param page
	 * @param belongSys
	 * @return
	 */
	IPage getRoleByBelongSysPage(Page page, SysRole sysRole) throws Exception;
	
	/**
	   * 根据用户id所属系统查询角色信息
	 * @param userId
	 * @param belongSys
	 * @return
	 */
	List<SysRole> findRolesByUserIdBelongSys(String userId,String belongSys) throws Exception;

	/**
	 * 根据角色id,查询角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	SysRole getById(String roleId) throws Exception;

}