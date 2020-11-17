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

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.entity.DmBrand;
import com.pig4cloud.pigx.device.generator.service.DmBrandService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 品牌表
 *
 * @author lhd
 * @date 2019-06-20 10:15:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmbrand" )
@Api(value = "dmbrand", tags = "dmbrand管理")
public class DmBrandController {

    private final  DmBrandService dmBrandService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dmBrand 品牌表
     * @return
     */
    @GetMapping("/page" )
    public R getDmBrandPage(Page page, DmBrand dmBrand) {

        return new R<>(dmBrandService.getBrandList(page, dmBrand));
    }


    /**
     * 通过id查询品牌表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(dmBrandService.getById(id));
    }

    /**
     * 新增品牌表
     * @param dmBrand 品牌表
     * @return R
     */
    @SysLog("新增品牌表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_dmbrand_add')" )
    public R save(@Valid @RequestBody DmBrand dmBrand) {
    	PigxUser user = SecurityUtils.getUser();
    	dmBrand.setCreateUserId(user.getId());
    	dmBrand.setCreateUser(user.getUsername());
        return new R<>(dmBrandService.save(dmBrand));
    }

    /**
     * 修改品牌表
     * @param dmBrand 品牌表
     * @return R
     */
    @SysLog("修改品牌表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_dmbrand_edit')" )
    public R updateById(@Valid @RequestBody DmBrand dmBrand) {
    	PigxUser user = SecurityUtils.getUser();
    	dmBrand.setUpdateUserId(user.getId());
    	dmBrand.setUpdateUser(user.getUsername());
    	dmBrand.setUpdateTime(LocalDateTime.now());
        return new R<>(dmBrandService.updateById(dmBrand));
    }

    /**
     * 通过id删除品牌表
     * @param id id
     * @return R
     */
    @SysLog("删除品牌表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_dmbrand_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(dmBrandService.removeById(id));
    }

    /**
     * 获取品牌select
     * @param factorId 厂家的ID
     * @return
     */
    @SysLog("获取品牌select")
    @GetMapping("/select")
    public R selectFactorId(String factorId) {
    	return new R<>(dmBrandService.getSelectFactorId(factorId));
    }

}
