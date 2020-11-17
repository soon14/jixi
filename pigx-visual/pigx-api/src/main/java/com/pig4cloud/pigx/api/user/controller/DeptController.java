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

package com.pig4cloud.pigx.api.user.controller;

import java.util.ArrayList;
import java.util.List;

import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pig4cloud.pigx.api.user.entity.SysDept;
import com.pig4cloud.pigx.api.user.service.SysDeptService;
import com.pig4cloud.pigx.api.user.vo.SysDeptVO;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", tags = "部门管理模块")
public class DeptController {
	private final SysDeptService sysDeptService;

	private final SysUserService sysUserService;

	/**
	 * 查询登录用户的部门信息
	 *
	 * @return SysDept
	 */
	@GetMapping(value = "/getByDeptId")
	public R getByDeptId() {
		SysDept dept=sysDeptService.getById(SecurityUtils.getUser().getDeptId());
		List<SysDept> list=new ArrayList<SysDept>();
		list.add(dept);
		return new R<>(list);
	}
	
	
	/**
	 * 获取非deptId的所有机构
	 * @param deptId
	 * @return
	 */
	@SysLog("获取部门select")
	@GetMapping("/getSelect/{deptId}")
	public R getSelect(@PathVariable String deptId) {
		List<SysDeptVO> vos=null;
		try {
			vos=sysDeptService.getSelect(deptId);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R(vos);
	}
	
	@SysLog("获取所有部门select")
	@GetMapping("/getSelect")
	public R getSelect() {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return new R(sysDeptService.list());
	}

	/**
	 * 查询登录用户下的维保公司
	 *
	 * @return List
	 */
	@GetMapping(value = "/queryMaintenanceCompany")
	public R queryMaintenanceCompany(String networkUnitId) {
		List<SysDept> list=null;
		List<String> unitIds=new ArrayList<>();
		try {
			//如果联网单位id为空,执行正常权限控制
			if(StringUtils.isEmpty(networkUnitId)){
				unitIds.addAll(sysUserService.getUserLoingUnitIds());
			}else{
				unitIds.add(networkUnitId);
			}
			 list = sysDeptService.queryMaintenanceCompany(Constant.ORG_TYPE_CODE_WBGS,unitIds);
		}catch(Exception e){
			return new R<>(e);
		}
		return new R<>(list);
	}

}
