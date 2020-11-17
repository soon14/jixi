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
import com.pig4cloud.pigx.admin.api.entity.BsCounty;
import com.pig4cloud.pigx.admin.service.BsCountyService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 楼层信息
 *
 * @author lhd
 * @date 2019-05-15 15:37:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bscounty" )
@Api(value = "bscounty", tags = "bscounty管理")
public class BsCountyController {

    private final  BsCountyService bsCountyService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bsCounty 楼层信息
     * @return
     */
    @GetMapping("/page" )
    public R getBsCountyPage(Page page, BsCounty bsCounty) {
        return new R<>(bsCountyService.page(page, Wrappers.query(bsCounty)));
    }


    /**
     * 通过id查询楼层信息
     * @param countyId id
     * @return R
     */
    @GetMapping("/{countyId}" )
    public R getById(@PathVariable("countyId" ) String countyId) {
        return new R<>(bsCountyService.getById(countyId));
    }

    /**
     * 新增楼层信息
     * @param bsCounty 楼层信息
     * @return R
     */
    @SysLog("新增楼层信息" )
    @PostMapping
    public R save(@RequestBody BsCounty bsCounty) {
        return new R<>(bsCountyService.save(bsCounty));
    }

    /**
     * 修改楼层信息
     * @param bsCounty 楼层信息
     * @return R
     */
    @SysLog("修改楼层信息" )
    @PutMapping
    public R updateById(@RequestBody BsCounty bsCounty) {
        return new R<>(bsCountyService.updateById(bsCounty));
    }

    /**
     * 通过id删除楼层信息
     * @param countyId id
     * @return R
     */
    @SysLog("删除楼层信息" )
    @DeleteMapping("/{countyId}" )
    public R removeById(@PathVariable String countyId) {
        return new R<>(bsCountyService.removeById(countyId));
    }

    /**
	    * 获取所在楼层select
	 * 
	 * @param buildId 建筑id
	 * @return
	 */
	@SysLog("获取所在楼层select")
	@GetMapping("/select")
	public R selectBuild(String buildId) {
		return new R<>(bsCountyService.selectCounty(buildId));
	}
}
