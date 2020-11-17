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
import java.util.Date;

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
import com.pig4cloud.pigx.admin.api.dto.CityDTO;
import com.pig4cloud.pigx.admin.api.entity.BsCity;
import com.pig4cloud.pigx.admin.service.BsCityService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 城市表
 *
 * @author lhd
 * @date 2019-05-15 15:38:02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bscity" )
@Api(value = "bscity", tags = "bscity管理")
public class BsCityController {

    private final  BsCityService bsCityService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param cityDTO 城市表
     * @return
     */
    @GetMapping("/page" )
    public R getBsCityPage(Page page, CityDTO cityDTO) {
        return new R<>(bsCityService.getCityList(page, cityDTO));
    }


    /**
     * 通过id查询城市表
     * @param cityId id
     * @return R
     */
    @GetMapping("/{cityId}" )
    public R getById(@PathVariable("cityId" ) String cityId) {
        return new R<>(bsCityService.getById(cityId));
    }

    /**
     * 新增城市表
     * @param bsCity 城市表
     * @return R
     */
    @SysLog("新增城市表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('bs_city_add')" )
    public R save(@RequestBody BsCity bsCity) {
    	bsCity.setCreateUserId(SecurityUtils.getUser().getId());
    	bsCity.setCreateTime(LocalDateTime.now());
        return new R<>(bsCityService.save(bsCity));
    }

    /**
     * 修改城市表
     * @param bsCity 城市表
     * @return R
     */
    @SysLog("修改城市表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('bs_city_edit')" )
    public R updateById(@RequestBody BsCity bsCity) {
    	bsCity.setUpdateUserId(SecurityUtils.getUser().getId());
    	bsCity.setUpdateTime(LocalDateTime.now());
        return new R<>(bsCityService.updateById(bsCity));
    }

    /**
     * 通过id删除城市表
     * @param cityId id
     * @return R
     */
    @SysLog("删除城市表" )
    @DeleteMapping("/{cityId}" )
    @PreAuthorize("@pms.hasPermission('bs_city_del')" )
    public R removeById(@PathVariable String cityId) {
        return new R<>(bsCityService.removeById(cityId));
    }

    
    @GetMapping("/select")
    public R getSelect() {
    	return new R<>(bsCityService.list(Wrappers.<BsCity>query()));
    }
    
    
    @GetMapping("/select/{provId}")
    public R getSelect(@PathVariable("provId") String provId) {
    	BsCity city = new BsCity();
    	city.setProvId(provId);
    	return new R<>(bsCityService.list(Wrappers.<BsCity>query(city)));
    }
}
