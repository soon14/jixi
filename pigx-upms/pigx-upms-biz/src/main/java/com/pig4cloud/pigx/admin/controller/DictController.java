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


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.admin.api.entity.SysDict;
import com.pig4cloud.pigx.admin.api.entity.SysDictItem;
import com.pig4cloud.pigx.admin.service.SysDictItemService;
import com.pig4cloud.pigx.admin.service.SysDictService;
import com.pig4cloud.pigx.common.core.constant.CacheConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {
	private final SysDictService sysDictService;
	private final SysDictItemService sysDictItemService;

	/**
	 * 通过ID查询字典信息
	 *
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sysDictService.getById(id));
	}

	/**
	 * 分页查询字典信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, SysDict sysDict) {
		return new R<>(sysDictService.page(page, Wrappers.query(sysDict)));
	}

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type", unless = "#result == null")
	public R getDictByType(@PathVariable String type) {
		System.out.println("dict/type");
		List<SysDictItem> list=sysDictItemService.list(Wrappers
				.<SysDictItem>query().lambda()
				.eq(SysDictItem::getType, type));
		list.sort((SysDictItem o1,SysDictItem o2) -> o1.getSort().compareTo(o2.getSort()));
		return new R<>(list);
	}

	/**
	 * 添加字典
	 *
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public R save(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.save(sysDict));
	}

	/**
	 * 删除字典，并且清除字典缓存
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(sysDictService.removeDict(id));
	}

	/**
	 * 修改字典
	 *
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R updateById(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.updateById(sysDict));
	}

	/**
	 * 分页查询
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项
	 * @return
	 */
	@GetMapping("/item/page")
	public R getSysDictItemPage(Page page, SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.page(page, Wrappers.query(sysDictItem)));
	}


	/**
	 * 通过id查询字典项
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/{id}")
	public R getDictItemById(@PathVariable("id") String id) {
		return new R<>(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R save(@RequestBody SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("修改字典项")
	@PutMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R updateById(@RequestBody SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.updateById(sysDictItem));
	}

	/**
	 * 通过id删除字典项
	 *
	 * @param id id
	 * @return R
	 */
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R removeDictItemById(@PathVariable String id) {
		return new R<>(sysDictItemService.removeById(id));
	}


	/**
	 * 通过字典类型查找字典（服务直接的接口调用）
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/getInterfaceDictByType")
	public List<SysDictItem> getInterfaceDictByType(String type) {
		System.out.println("------------------------------------------------服务调用--------------------------------------------");
		return sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
	}
}
