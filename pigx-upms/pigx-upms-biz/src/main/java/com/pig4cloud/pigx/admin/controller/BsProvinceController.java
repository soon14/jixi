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
import com.pig4cloud.pigx.admin.api.dto.ProvinceDTO;
import com.pig4cloud.pigx.admin.api.entity.BsProvince;
import com.pig4cloud.pigx.admin.service.BsProvinceService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 省份表
 *
 * @author lhd
 * @date 2019-05-15 15:38:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsprovince" )
@Api(value = "bsprovince", tags = "bsprovince管理")
public class BsProvinceController {

    private final  BsProvinceService bsProvinceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bsProvince 省份表
     * @return
     */
    @GetMapping("/page" )
    public R getBsProvincePage(Page page, ProvinceDTO bsProvince) {
        return new R<>(bsProvinceService.getProvinceList(page, bsProvince));
    }


    /**
     * 通过id查询省份表
     * @param provId id
     * @return R
     */
    @GetMapping("/{provId}" )
    public R getById(@PathVariable("provId" ) String provId) {
        return new R<>(bsProvinceService.getById(provId));
    }

    /**
     * 新增省份表
     * @param bsProvince 省份表
     * @return R
     */
    @SysLog("新增省份表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('bs_province_add')" )
    public R save(@RequestBody BsProvince bsProvince) {
    	String userId = SecurityUtils.getUser().getId();
    	bsProvince.setCreateUserId(userId);
    	bsProvince.setCreateTime(LocalDateTime.now());
        return new R<>(bsProvinceService.save(bsProvince));
    }

    /**
     * 修改省份表
     * @param bsProvince 省份表
     * @return R
     */
    @SysLog("修改省份表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('bs_province_edit')" )
    public R updateById(@RequestBody BsProvince bsProvince) {
    	String userId = SecurityUtils.getUser().getId();
    	bsProvince.setUpdateUserId(userId);
    	bsProvince.setUpdateTime(LocalDateTime.now());
        return new R<>(bsProvinceService.updateById(bsProvince));
    }

    /**
     * 通过id删除省份表
     * @param provId id
     * @return R
     */
    @SysLog("删除省份表" )
    @DeleteMapping("/{provId}" )
    @PreAuthorize("@pms.hasPermission('bs_province_del')" )
    public R removeById(@PathVariable String provId) {
        return new R<>(bsProvinceService.removeById(provId));
    }
    
    @GetMapping("/select")
    public R getSelect() {
    	return new R<>(bsProvinceService.list(Wrappers.<BsProvince>query()));
    }
}
