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

package com.pig4cloud.pigx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pig4cloud.pigx.admin.api.entity.BsNetworkOrgRela;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.admin.api.utils.PrimaryKeyUtil;
import com.pig4cloud.pigx.admin.service.BsNetworkOrgRelaService;
import com.pig4cloud.pigx.admin.service.BsNetworkingUnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.admin.api.dto.DeptTree;
import com.pig4cloud.pigx.admin.api.entity.SysDept;
import com.pig4cloud.pigx.admin.api.entity.SysDeptRelation;
import com.pig4cloud.pigx.admin.api.vo.SysDeptVO;
import com.pig4cloud.pigx.admin.api.vo.TreeUtil;
import com.pig4cloud.pigx.admin.mapper.SysDeptMapper;
import com.pig4cloud.pigx.admin.service.SysDeptRelationService;
import com.pig4cloud.pigx.admin.service.SysDeptService;

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
	private final BsNetworkOrgRelaService bsNetworkOrgRelaService;
	private final BsNetworkingUnitService bsNetworkingUnitService;

	/**
	 * 添加信息部门
	 *
	 * @param sysDeptVO 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDeptVO sysDeptVO) {
		// 保存机构信息
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(sysDeptVO, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);

		// 保存机构和联网单位信息
		List<BsNetworkOrgRela> bsNetworkOrgRelaList = new ArrayList<>();
		// 通过联网单位ID批量查询联网信息并组装数据
		for(BsNetworkingUnit bsNetworkingUnit : bsNetworkingUnitService.findBatch(sysDeptVO.getBsNetworkOrgRelaIdList())){
			BsNetworkOrgRela bsNetworkOrgRela = new BsNetworkOrgRela();
			bsNetworkOrgRela.setId(PrimaryKeyUtil.getPrimaryKey());
			bsNetworkOrgRela.setUnitId(bsNetworkingUnit.getUnitId());
			bsNetworkOrgRela.setUnitName(bsNetworkingUnit.getName());
			bsNetworkOrgRela.setOrgId(sysDept.getDeptId());
			bsNetworkOrgRela.setOrgName(sysDept.getName());
			bsNetworkOrgRela.setOrgTypeCode(sysDept.getCode());
			bsNetworkOrgRelaList.add(bsNetworkOrgRela);
		}
		bsNetworkOrgRelaService.saveBatch(bsNetworkOrgRelaList);

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
	public Boolean removeDeptById(String id) {
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
	 * @param sysDeptVO 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDeptVO sysDeptVO) {
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(sysDeptVO,sysDept);
		// 更新部门状态
		this.updateById(sysDept);
		// 更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);

		// 删除和部门绑定的所有联网单位
		bsNetworkOrgRelaService.deleteByOrgId(sysDept.getDeptId());

		// 保存机构和联网单位信息
		List<BsNetworkOrgRela> bsNetworkOrgRelaList = new ArrayList<>();
		// 通过联网单位ID批量查询联网信息并组装数据
		for(BsNetworkingUnit bsNetworkingUnit : bsNetworkingUnitService.findBatch(sysDeptVO.getBsNetworkOrgRelaIdList())){
			BsNetworkOrgRela bsNetworkOrgRela = new BsNetworkOrgRela();
			bsNetworkOrgRela.setId(PrimaryKeyUtil.getPrimaryKey());
			bsNetworkOrgRela.setUnitId(bsNetworkingUnit.getUnitId());
			bsNetworkOrgRela.setUnitName(bsNetworkingUnit.getName());
			bsNetworkOrgRela.setOrgId(sysDept.getDeptId());
			bsNetworkOrgRela.setOrgName(sysDept.getName());
			bsNetworkOrgRela.setOrgTypeCode(sysDept.getCode());
			bsNetworkOrgRelaList.add(bsNetworkOrgRela);
		}
		//如果有数据,保存
		if(bsNetworkOrgRelaList.size()>0) {
			bsNetworkOrgRelaService.saveBatch(bsNetworkOrgRelaList);
		}
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() {
		return getDeptTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 构建部门树
	 *
	 * @param depts 部门
	 * @return
	 */
	private List<DeptTree> getDeptTree(List<SysDept> depts) {
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
	public SysDeptVO getVOById(String id) {
		SysDeptVO sysDeptVO = sysDeptMapper.getVOById(id);
		// 查询部门和联网单位信息中的联网单位ID
		sysDeptVO.setBsNetworkOrgRelaIdList(bsNetworkOrgRelaService.findByOrgId(sysDeptVO.getDeptId()));
		return sysDeptVO;
	}

	@Override
	public List<SysDeptVO> getSelect(String deptId) {
		return this.list(Wrappers.<SysDept>query().lambda().ne(SysDept::getDeptId, deptId)).stream().map(dept -> {
			SysDeptVO vo = new SysDeptVO();
			vo.setDeptId(dept.getDeptId());
			vo.setName(dept.getName());
			return vo;
		}).collect(Collectors.toList());
		
	}

	@Override
	public List<DeptTree> getTreeByDept(String deptId) {
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

	@Override
	public List<String> getSelectDeptId(String deptId) {
		List<SysDept> list=this.list(Wrappers.emptyWrapper());
		List<String> trees = new ArrayList<>();
		trees.add(deptId);

		return queryRecursion(list,trees,deptId);
	}
	/**
	 * 递归查询子机构id
	 * @param list
	 * @param trees
	 * @param deptId
	 * @return
	 */
	public List<String> queryRecursion(List<SysDept> list,List<String> trees,String deptId){
		for (SysDept dept : list) {
			if (deptId.equals(dept.getParentId())) {
				trees.add(dept.getDeptId());
				queryRecursion(list,trees,dept.getDeptId());
			}
		}
		return trees;
	}
}
