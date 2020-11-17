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


import com.pig4cloud.pigx.smartff.generator.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionPointService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 巡检点表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectionpoint" )
@Api(value = "sfinspectionpoint", tags = "sfinspectionpoint管理")
public class SfInspectionPointController {

    private final  SfInspectionPointService sfInspectionPointService;

    @Autowired
    private UserFeign userFeign;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfInspectionPoint 巡检点表
     * @return
     */
    @GetMapping("/page" )
    public R getSfInspectionPointPage(Page page, SfInspectionPoint sfInspectionPoint) {
        sfInspectionPoint.setIsremoved(0);
        List<String> list = userFeign.getUserLoingUnitIds();
        sfInspectionPoint.setUnitIds(list);
        return new R<>(sfInspectionPointService.findPointList(page,sfInspectionPoint));
    }


    /**
     * 通过id查询巡检点表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfInspectionPointService.getById(id));
    }

    /**
     * 新增巡检点表
     * @param sfInspectionPoint 巡检点表
     * @return R
     */
    @SysLog("新增巡检点表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionpoint_add')" )
    public R save(@RequestBody SfInspectionPoint sfInspectionPoint) {
        sfInspectionPoint.setCreatetime(LocalDateTime.now());
        return new R<>(sfInspectionPointService.save(sfInspectionPoint));
    }

    /**
     * 修改巡检点表
     * @param sfInspectionPoint 巡检点表
     * @return R
     */
    @SysLog("修改巡检点表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionpoint_edit')" )
    public R updateById(@RequestBody SfInspectionPoint sfInspectionPoint) {
        sfInspectionPoint.setModifytime(LocalDateTime.now());
        return new R<>(sfInspectionPointService.updateById(sfInspectionPoint));
    }

    /**
     * 通过id删除巡检点表(逻辑删除修改isRemove字段)
     * @param id id
     * @return R
     */
    @SysLog("删除巡检点表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionpoint_del')" )
    public R removeById(@PathVariable String id) {
        SfInspectionPoint sfInspectionPoint = new SfInspectionPoint();
        sfInspectionPoint.setId(id);
        sfInspectionPoint.setIsremoved(1);
        return new R<>(sfInspectionPointService.updateById(sfInspectionPoint));
    }


    /**
     * 根据联网单位ID,建筑ID查询巡检点列表信息
     * （巡检方案配置巡检点时使用）
     * @param page 分页对象
     * @param sfInspectionPoint 巡检点表
     * @return
     */
    @GetMapping("/pageList" )
    public R findPointByOrgBuildId(Page page, SfInspectionPoint sfInspectionPoint) {
        sfInspectionPoint.setIsremoved(0);
        List<String> list = userFeign.getUserLoingUnitIds();
        sfInspectionPoint.setUnitIds(list);
        return new R<>(sfInspectionPointService.findPointByOrgBuildId(page, sfInspectionPoint));
    }

}
