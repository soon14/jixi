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

package com.pig4cloud.pigx.api.user.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.user.dto.UserDTO;
import com.pig4cloud.pigx.api.user.dto.UserInfo;
import com.pig4cloud.pigx.api.user.entity.SysRole;
import com.pig4cloud.pigx.api.user.entity.SysUser;
import com.pig4cloud.pigx.api.user.vo.UserVO;
import com.pig4cloud.pigx.common.core.util.R;

/**
 * 用户表
 *
 * @author lhd
 * @date 2019-05-15 15:38:16
 */
public interface SysUserService extends IService<SysUser> {
	/**
	 * 查询用户信息
	 *
	 * @param sysUser 用户
	 * @return userInfo
	 */
	UserInfo findUserInfo(SysUser sysUser) throws Exception;

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	IPage getUsersWithRolePage(Page page, UserDTO userDTO) throws Exception;

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean deleteUserById(SysUser sysUser) throws Exception;

	/**
	 * 更新当前用户基本信息
	 *
	 * @param userDto 用户信息
	 * @return Boolean
	 */
	R<Boolean> updateUserInfo(UserDTO userDto) throws Exception;
	
	/**
	    * 更新当前用户密码
	 *
	 * @param userDto 用户信息
	 * @return Boolean
	 */
	R<Boolean> updateUserPassword(UserDTO userDto) throws Exception;

	/**
	 * 更新指定用户信息
	 *
	 * @param userDto 用户信息
	 * @return
	 */
	Boolean updateUser(UserDTO userDto) throws Exception;

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserVO selectUserVoById(String id);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	List<SysUser> listAncestorUsers(String username) throws Exception;

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveUser(UserDTO userDto) throws Exception;
	
	/**
	    *  获取当前登录用户的角色信息
	 * 
	 * @return
	 */
	List<SysRole> getRole() throws Exception;
	
	/**
	    *  获取当前登录用户的角色信息根据归属系统
	 * 
	 *  @param belongSys 归属系统
	 * @return
	 */
	List<SysRole> getRoleByBelongSys(String belongSys) throws Exception;
	
	/**
	    * 查询机构下的所有用户
	 * 
	 * @param page
	 * @param unitId
	 * @return
	 */
	IPage<List<SysUser>> getUserByOrgId(Page page,String orgId) throws Exception;
	
	/**
	 * 联网单位所属物业公司下的人员
	 * @param unitId
	 * @return
	 */
	List<SysUser> getUserByUnitId(String unitId,String orgTypeCode) throws Exception;
	
	/**
	    *  根据用户ids,查询用户信息
	 * 
	 * @param userIds
	 * @return
	 */
	List<SysUser> getUserByUserIds(String[] userIds) throws Exception;
	
	 /**
	  * 获取当前登录用户下的unitIds,用于数据权限控制
	  * 
	  * @return
	  */
	List<String> getUserLoingUnitIds() throws Exception;
	
	/**
	 * 获取巡检人员信息,根据登录用户所属联网单位
	 * @return
	 */
	List<UserVO> getUserInfoByUnitIds(List<String> unitIds,String orgType) throws Exception;
	
}
