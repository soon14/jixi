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

package com.pig4cloud.pigx.admin.controller;

import java.time.LocalDateTime;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.admin.api.entity.BsArea;
import com.pig4cloud.pigx.admin.service.BsAreaService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 区域表
 *
 * @author lhd
 * @date 2019-05-15 15:38:07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsarea")
@Api(value = "bsarea", tags = "bsarea管理")
public class BsAreaController {

	private final BsAreaService bsAreaService;

	/**
	 * 分页查询
	 * 
	 * @param page   分页对象
	 * @param bsArea 区域表
	 * @return
	 */
	@GetMapping("/page")
	public R getBsAreaPage(Page page, BsArea bsArea) {
		return new R<>(bsAreaService.page(page, Wrappers.query(bsArea)));
	}

	/**
	 * 通过id查询区域表
	 * 
	 * @param areaId id
	 * @return R
	 */
	@GetMapping("/{areaId}")
	public R getById(@PathVariable("areaId") String areaId) {
		return new R<>(bsAreaService.getById(areaId));
	}

	/**
	 * 新增区域表
	 * 
	 * @param bsArea 区域表
	 * @return R
	 */
	@SysLog("新增区域表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('bs_area_add')")
	public R save(@RequestBody BsArea bsArea) {
		bsArea.setCreateUserId(SecurityUtils.getUser().getId());
		bsArea.setCreateTime(LocalDateTime.now());
		return new R<>(bsAreaService.save(bsArea));
	}

	/**
	 * 修改区域表
	 * 
	 * @param bsArea 区域表
	 * @return R
	 */
	@SysLog("修改区域表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('bs_area_edit')")
	public R updateById(@RequestBody BsArea bsArea) {
		return new R<>(bsAreaService.updateById(bsArea));
	}

	/**
	 * 通过id删除区域表
	 * 
	 * @param areaId id
	 * @return R
	 */
	@SysLog("删除区域表")
	@DeleteMapping("/{areaId}")
	@PreAuthorize("@pms.hasPermission('bs_area_del')")
	public R removeById(@PathVariable String areaId) {
		return new R<>(bsAreaService.removeById(areaId));
	}
	
	@SysLog("查询区域select")
	@GetMapping("/select")
    public R getSelect() {
    	return new R<>(bsAreaService.list(Wrappers.<BsArea>query()));
    }

	@SysLog("根据城市查询区域select")
	@GetMapping("/select/{cityId}")
	public R getSelect(@PathVariable String cityId) {
		BsArea area = new BsArea();
		area.setCityId(cityId);
		return new R<>(bsAreaService.list(Wrappers.<BsArea>query(area)));
	}
}
