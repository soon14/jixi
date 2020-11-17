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

import javax.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
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
import com.pig4cloud.pigx.admin.api.dto.BuildinfoDTO;
import com.pig4cloud.pigx.admin.api.entity.BsBuildinfo;
import com.pig4cloud.pigx.admin.api.entity.BsCounty;
import com.pig4cloud.pigx.admin.service.BsBuildinfoService;
import com.pig4cloud.pigx.admin.service.BsCountyService;
import com.pig4cloud.pigx.common.core.constant.CacheConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsbuildinfo" )
@Api(value = "bsbuildinfo", tags = "bsbuildinfo管理")
public class BsBuildinfoController {

    private final  BsBuildinfoService bsBuildinfoService;
    private final BsCountyService bsCountyService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param buildinfoDTO 楼体信息表
     * @return
     */
    @GetMapping("/page" )
    public R getBsBuildinfoPage(Page page, BuildinfoDTO buildinfoDTO) {
        return new R<>(bsBuildinfoService.getBuildinfoPage(page, buildinfoDTO));
    }


    /**
     * 通过id查询楼体信息表
     * @param buildId id
     * @return R
     */
    @GetMapping("/{buildId}" )
    public R getById(@PathVariable("buildId" ) String buildId) {
        return new R<>(bsBuildinfoService.getById(buildId));
    }

    /**
     * 通过id查询建筑楼层
     * @param buildId
     * @return
     */
    @GetMapping("/item/page")
    public R getCountyByBuildId(Page page, BsCounty bsCounty) {
    	R R =new R<>(bsCountyService.page(page, Wrappers.query(bsCounty)));
    	return R;
    }
    
    /**
     * 新增楼体信息表
     * @param bsBuildinfo 楼体信息表
     * @return R
     */
    @SysLog("新增楼体信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('bs_buildinfo_add')" )
    public R save(@RequestBody BsBuildinfo bsBuildinfo) {
        return new R<>(bsBuildinfoService.save(bsBuildinfo));
    }

    @SysLog("新增楼层" )
    @PostMapping("/item")
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public R saveItem(@RequestBody BsCounty county) {
    	return new R<>(bsCountyService.save(county));
    }
    
    /**
     * 修改楼体信息表
     * @param bsBuildinfo 楼体信息表
     * @return R
     */
    @SysLog("修改楼体信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('bs_buildinfo_edit')" )
    public R updateBiuldinfo(@Valid @RequestBody BuildinfoDTO buildinfoDTO) {
        return new R<>(bsBuildinfoService.updateBuildinfo(buildinfoDTO));
    }

    /**
     * 通过id删除楼体信息表
     * @param buildId id
     * @return R
     */
    @SysLog("删除楼体信息表" )
    @DeleteMapping("/{buildId}" )
    @PreAuthorize("@pms.hasPermission('bs_buildinfo_del')" )
    public R removeById(@PathVariable String buildId) {
        return new R<>(bsBuildinfoService.deleteBuildinfo(buildId));
    }
 
    /**
	    * 获取所在建筑select
	 * 
	 * @param networkUnitId 联网单位id
	 * @return
	 */
	@SysLog("获取所在建筑select")
	@GetMapping("/select")
	public R selectBuild(String networkUnitId) {
		return new R<>(bsBuildinfoService.selectBuild(networkUnitId));
	}
    @SysLog("建筑select列表")
    @GetMapping("/select/{networkUnitId}")
    public R selectByNetworkUnitId (@PathVariable String networkUnitId) {
    	BsBuildinfo buildinfo = new BsBuildinfo();
    	buildinfo.setNetworkUnitId(networkUnitId);
    	return new R<>(bsBuildinfoService.list(Wrappers.<BsBuildinfo>query(buildinfo)));
    } 
    
    
//    @SysLog("查看楼层列表")
//    @GetMapping("/getCountyList" )
//    @PreAuthorize("@pms.hasPermission('bs_buildinfo_county')" )
//    public R getCountyList(Page page, BsBuildinfo bsBuildinfo) {
//        return new R<>(bsCountyService.page(page, Wrappers.query(bsBuildinfo)));
//    }
}
