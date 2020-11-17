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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.dto.DeptTree;
import com.pig4cloud.pigx.api.user.entity.SysDept;
import com.pig4cloud.pigx.api.user.entity.SysDeptRelation;
import com.pig4cloud.pigx.api.user.mapper.SysDeptMapper;
import com.pig4cloud.pigx.api.user.service.SysDeptRelationService;
import com.pig4cloud.pigx.api.user.service.SysDeptService;
import com.pig4cloud.pigx.api.user.vo.SysDeptVO;
import com.pig4cloud.pigx.api.user.vo.TreeUtil;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2018-01-20
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

	private final SysDeptMapper sysDeptMapper;
	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 添加信息部门
	 *
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDept dept) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);
		return Boolean.TRUE;
	}

	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDeptById(String id) throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		// 级联删除部门
		List<String> idList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getAncestor, id)).stream()
				.map(SysDeptRelation::getDescendant).collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			this.removeByIds(idList);
		}

		// 删除部门级联关系
		sysDeptRelationService.deleteAllDeptRealtion(id);
		return Boolean.TRUE;
	}

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDept sysDept) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		// 更新部门状态
		this.updateById(sysDept);
		// 更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return getDeptTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 构建部门树
	 *
	 * @param depts 部门
	 * @return
	 */
	private List<DeptTree> getDeptTree(List<SysDept> depts) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		List<DeptTree> treeList = depts.stream().filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, "0");
	}

	@Override
	public SysDeptVO getVOById(String id) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return sysDeptMapper.getVOById(id);
	}

	@Override
	public List<SysDeptVO> getSelect(String deptId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return this.list(Wrappers.<SysDept>query().lambda().ne(SysDept::getDeptId, deptId)).stream().map(dept -> {
			SysDeptVO vo = new SysDeptVO();
			vo.setDeptId(dept.getDeptId());
			vo.setName(dept.getName());
			return vo;
		}).collect(Collectors.toList());
		
	}

	@Override
	public List<String> getSelectDeptId(String deptId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		List<SysDept> list=this.list(Wrappers.emptyWrapper());
		List<String> trees = new ArrayList<>();
		trees.add(deptId);
		
		return queryRecursion(list,trees,deptId);
	}

	@Override
	public List<SysDept> queryMaintenanceCompany(String orgType, List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return sysDeptMapper.queryMaintenanceCompany(orgType,unitIds);
	}

	/**
	 * 递归查询子机构id
	 * @param list
	 * @param trees
	 * @param deptId
	 * @return
	 */
	public List<String> queryRecursion(List<SysDept> list,List<String> trees,String deptId)  throws Exception{
        for (SysDept dept : list) {
			if (deptId.equals(dept.getParentId())) {
				trees.add(dept.getDeptId());
				queryRecursion(list,trees,dept.getDeptId());
			}
		}
		return trees;
	}
	@Override
	public List<DeptTree> getTreeByDept(String deptId) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		List<DeptTree> treeList = this.list(Wrappers.emptyWrapper()).stream().filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, deptId);
	}
}
