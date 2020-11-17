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

package com.pig4cloud.pigx.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.user.dto.UserDTO;
import com.pig4cloud.pigx.api.user.entity.SysUser;
import com.pig4cloud.pigx.api.user.vo.UserVO;
import com.pig4cloud.pigx.common.data.datascope.DataScope;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 *
	 * @param username 用户名
	 * @return userVo
	 */
	UserVO getUserVoByUsername(String username)  throws Exception;

	/**
	 * 分页查询用户信息（含角色）
	 *
	 * @param page      分页
	 * @param userDTO   查询参数
	 * @param dataScope
	 * @return list
	 */
	IPage<List<UserVO>> getUserVosPage(Page page, @Param("query") UserDTO userDTO, DataScope dataScope)  throws Exception;

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return userVo
	 */
	UserVO getUserVoById(String id);
	
	/**
	    *   获取联网单位下的用户信息
	 * 
	 * @param page
	 * @param unitId
	 * @return
	 */
	IPage<List<SysUser>> getUserByOrgId(Page page,@Param("orgId")String orgId)  throws Exception;
	
	/**
	 * 联网单位所属物业公司下的人员
	 * 
	 * @param unitId
	 * @return
	 */
	List<SysUser> getUserByUnitId(@Param("unitId")String unitId,@Param("orgTypeCode")String orgTypeCode)  throws Exception;
	
	/**
	   * 根据userIds,查询用户信息
	 * 
	 * @param userIds
	 * @return
	 */
	List<SysUser> getUserByUserIds(@Param("userIds")String[] userIds)  throws Exception;
	
	/**
	  *   获取巡检人信息
	 * @param unitIds
	 * @param orgType
	 * @return
	 */
	List<UserVO> getUserInfoByUnitIds(@Param("unitIds")List<String> unitIds,@Param("orgTypeCode")String orgType)  throws Exception;
}
