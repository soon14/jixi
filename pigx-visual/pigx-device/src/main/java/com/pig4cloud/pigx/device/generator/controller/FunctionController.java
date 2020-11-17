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

package com.pig4cloud.pigx.device.generator.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.entity.DmFunction;
import com.pig4cloud.pigx.device.generator.service.FunctionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/function")
@Api(value = "function", tags = "function管理")
public class FunctionController {

	private final FunctionService functionService;

	/**
	 * 分页查询
	 * 
	 * @param page       分页对象
	 * @param dmFunction 功能表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmFunctionPage(Page page, DmFunction dmFunction) {
		return new R<>(functionService.getFunctionList(page, dmFunction));
	}

	/**
	 * 查询不属于指定设备的功能
	 * 
	 * @param page
	 * @param dmFunction
	 * @return
	 */
	@GetMapping("/noDeviceFunction")
	public List<DmFunction> getNoDeviceFunction(String devId) {
		return functionService.getNoDeviceFunction(devId);
	}

	/**
	 * 通过id查询功能表
	 * 
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
		return new R<>(functionService.getById(id));
	}

	/**
	 * 新增功能表
	 * 
	 * @param dmFunction 功能表
	 * @return R
	 */
	@SysLog("新增功能表")
	@PostMapping
	public R save(@Valid @RequestBody DmFunction dmFunction) {
		PigxUser user = SecurityUtils.getUser();
		dmFunction.setCreateUserId(user.getId());
		dmFunction.setCreateUser(user.getUsername());
		return new R<>(functionService.save(dmFunction));
	}

	/**
	 * 修改功能表
	 * 
	 * @param dmFunction 功能表
	 * @return R
	 */
	@SysLog("修改功能表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('generator_function_edit')")
	public R updateById(@Valid @RequestBody DmFunction dmFunction) {
		PigxUser user = SecurityUtils.getUser();
		dmFunction.setUpdateUserId(user.getId());
		dmFunction.setUpdateUser(user.getUsername());
		dmFunction.setUpdateTime(LocalDateTime.now());
		return new R<>(functionService.updateById(dmFunction));
	}

	/**
	 * 通过id删除功能表
	 * 
	 * @param id id
	 * @return R
	 */
	@SysLog("删除功能表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('generator_function_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(functionService.removeById(id));
	}

}
