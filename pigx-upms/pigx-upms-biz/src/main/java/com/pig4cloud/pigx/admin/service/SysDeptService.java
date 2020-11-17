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

package com.pig4cloud.pigx.admin.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.admin.api.dto.DeptTree;
import com.pig4cloud.pigx.admin.api.entity.SysDept;
import com.pig4cloud.pigx.admin.api.vo.SysDeptVO;

/**
 * 部门管理
 *
 * @author lhd
 * @date 2019-05-15 15:38:28
 */
public interface SysDeptService extends IService<SysDept> {
	
	SysDeptVO getVOById(String id);
	
	/**
	 * 查询部门树菜单
	 *
	 * @return 树
	 */
	List<DeptTree> selectTree();

	/**
	 * 添加信息部门
	 *
	 * @param sysDeptVO
	 * @return
	 */
	Boolean saveDept(SysDeptVO sysDeptVO);

	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	Boolean removeDeptById(String id);

	/**
	 * 更新部门
	 *
	 * @param sysDeptVO 部门信息
	 * @return 成功、失败
	 */
	Boolean updateDeptById(SysDeptVO sysDeptVO);
	
	List<SysDeptVO> getSelect(String id);
	
	/**
	   * 构建当前部门及其子部门的树结构
	 * 
	 * @param deptId
	 * @return
	 */
	List<DeptTree> getTreeByDept(String deptId);


	List<String> getSelectDeptId(String deptId);
}
