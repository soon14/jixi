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

package com.pig4cloud.pigx.admin.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.pig4cloud.pigx.admin.api.vo.SysDeptVO;
import com.pig4cloud.pigx.admin.common.Constant;
import com.pig4cloud.pigx.admin.service.SysOrgTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pig4cloud.pigx.admin.api.dto.DeptTree;
import com.pig4cloud.pigx.admin.api.entity.SysDept;
import com.pig4cloud.pigx.admin.service.SysDeptService;
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

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sysDeptService.getVOById(id));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R getTree() {
		List<DeptTree> l = sysDeptService.selectTree();
		return new R<>(l);
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/treeByDeptId")
	public R getTreeByDept() {
		//获取当前登录用户的角色
		List<String> roles=SecurityUtils.getRoles();
		List<DeptTree> l=new ArrayList<DeptTree>();
		//如果为超级管理员,不加权限
		if(!roles.contains(Constant.ADMIN_ROLE_ID)) {
			SysDept dept = sysDeptService.getById(SecurityUtils.getUser().getDeptId());
			if (dept == null) {
				return new R<>(dept);
			}
			 l = sysDeptService.getTreeByDept(dept.getDeptId());
		}else{
			 l = sysDeptService.selectTree();
		}
		return new R<>(l);
	}
	
	/**
	 * 添加
	 *
	 * @param sysDeptVO 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public R save(@Valid @RequestBody SysDeptVO sysDeptVO) {
		return new R<>(sysDeptService.saveDept(sysDeptVO));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysDeptVO 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public R update(@Valid @RequestBody SysDeptVO sysDeptVO) {
		sysDeptVO.setUpdateTime(LocalDateTime.now());
		return new R<>(sysDeptService.updateDeptById(sysDeptVO));
	}
	
	/**
	 * 获取非deptId的所有机构
	 * @param deptId
	 * @return
	 */
	@SysLog("获取部门select")
	@GetMapping("/getSelect/{deptId}")
	public R getSelect(@PathVariable String deptId) {
		return new R(sysDeptService.getSelect(deptId));
	}
	
	@SysLog("获取所有所有select")
	@GetMapping("/getSelect")
	public R getSelect() {
		return new R(sysDeptService.list());
	}
}
