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
import com.pig4cloud.pigx.device.generator.entity.DmProduct;
import com.pig4cloud.pigx.device.generator.service.ProductService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 产品表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Api(value = "product", tags = "product管理")
public class ProductController {

	private final ProductService productService;

	/**
	 * 分页查询
	 * 
	 * @param page      分页对象
	 * @param dmProduct 产品表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmProductPage(Page page, DmProduct dmProduct) {
		return new R<>(productService.getProductList(page, dmProduct));
	}

	/**
	 * 通过id查询产品表
	 * 
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
		return new R<>(productService.getById(id));
	}

	/**
	 * 新增产品表
	 * 
	 * @param dmProduct 产品表
	 * @return R
	 */
	@SysLog("新增产品表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_product_add')")
	public R save(@Valid @RequestBody DmProduct dmProduct) {
		PigxUser user = SecurityUtils.getUser();
		dmProduct.setCreateUserId(user.getId());
		dmProduct.setCreateUser(user.getUsername());
		return new R<>(productService.save(dmProduct));
	}

	/**
	 * 修改产品表
	 * 
	 * @param dmProduct 产品表
	 * @return R
	 */
	@SysLog("修改产品表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('generator_product_edit')")
	public R updateById(@Valid @RequestBody DmProduct dmProduct) {
		PigxUser user = SecurityUtils.getUser();
		dmProduct.setUpdateUserId(user.getId());
		dmProduct.setUpdateUser(user.getUsername());
		dmProduct.setUpdateTime(LocalDateTime.now());
		return new R<>(productService.updateById(dmProduct));
	}

	/**
	 * 通过id删除产品表
	 * 
	 * @param id id
	 * @return R
	 */
	@SysLog("删除产品表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('generator_product_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(productService.removeById(id));
	}

	/**
	 * 获取产品select
	 *
	 * @param brandId 品牌ID
	 * @return
	 */
	@SysLog("获取产品select")
	@GetMapping("/select")
	public R selectBrandId(String brandId) {
		return new R<>(productService.getSelectBrandId(brandId));
	}
}
