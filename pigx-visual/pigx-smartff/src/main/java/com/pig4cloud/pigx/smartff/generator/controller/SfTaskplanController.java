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

package com.pig4cloud.pigx.smartff.generator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.smartff.generator.entity.SfTaskplan;
import com.pig4cloud.pigx.smartff.generator.service.SfTaskplanService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 巡检方案表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sftaskplan" )
@Api(value = "sftaskplan", tags = "sftaskplan管理")
public class SfTaskplanController {

    private final  SfTaskplanService sfTaskplanService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfTaskplan 巡检方案表
     * @return
     */
    @GetMapping("/page" )
    public R getSfTaskplanPage(Page page, SfTaskplan sfTaskplan) {
        return new R<>(sfTaskplanService.page(page, Wrappers.query(sfTaskplan)));
    }


    /**
     * 通过id查询巡检方案表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfTaskplanService.getById(id));
    }

    /**
     * 新增巡检方案表
     * @param sfTaskplan 巡检方案表
     * @return R
     */
    @SysLog("新增巡检方案表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sftaskplan_add')" )
    public R save(@RequestBody SfTaskplan sfTaskplan) {
        return new R<>(sfTaskplanService.save(sfTaskplan));
    }

    /**
     * 修改巡检方案表
     * @param sfTaskplan 巡检方案表
     * @return R
     */
    @SysLog("修改巡检方案表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sftaskplan_edit')" )
    public R updateById(@RequestBody SfTaskplan sfTaskplan) {
        return new R<>(sfTaskplanService.updateById(sfTaskplan));
    }

    /**
     * 通过id删除巡检方案表
     * @param id id
     * @return R
     */
    @SysLog("删除巡检方案表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sftaskplan_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(sfTaskplanService.removeById(id));
    }

    /**
     * 分页查询(根据巡检方案名称查询列表（支持模糊查询）)
     * @param page 分页对象
     * @param sfTaskplan 巡检方案表
     * @return
     */
    @GetMapping("/pageByName" )
    public R findByPlanNameList(Page page, SfTaskplan sfTaskplan) {
        return new R<>(sfTaskplanService.findByPlanNameList(page,sfTaskplan));
    }


}
