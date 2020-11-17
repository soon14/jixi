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
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionExecuteDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


/**
 * 巡检执行情况详细表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectionexecutedetail" )
@Api(value = "sfinspectionexecutedetail", tags = "sfinspectionexecutedetail管理")
public class SfInspectionExecuteDetailController {

    private final  SfInspectionExecuteDetailService sfInspectionExecuteDetailService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfInspectionExecuteDetail 巡检执行情况详细表
     * @return
     */
    @GetMapping("/page" )
    public R getSfInspectionExecuteDetailPage(Page page, SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return new R<>(sfInspectionExecuteDetailService.page(page, Wrappers.query(sfInspectionExecuteDetail)));
    }


    /**
     * 通过id查询巡检执行情况详细表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfInspectionExecuteDetailService.getById(id));
    }

    /**
     * 新增巡检执行情况详细表
     * @param sfInspectionExecuteDetail 巡检执行情况详细表
     * @return R
     */
    @SysLog("新增巡检执行情况详细表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecutedetail_add')" )
    public R save(@RequestBody SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return new R<>(sfInspectionExecuteDetailService.save(sfInspectionExecuteDetail));
    }

    /**
     * 修改巡检执行情况详细表
     * @param sfInspectionExecuteDetail 巡检执行情况详细表
     * @return R
     */
    @SysLog("修改巡检执行情况详细表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecutedetail_edit')" )
    public R updateById(@RequestBody SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return new R<>(sfInspectionExecuteDetailService.updateById(sfInspectionExecuteDetail));
    }

    /**
     * 通过id删除巡检执行情况详细表
     * @param id id
     * @return R
     */
    @SysLog("删除巡检执行情况详细表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecutedetail_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(sfInspectionExecuteDetailService.removeById(id));
    }


    /**
     * APP执行巡检操作接口(任务Id必传)
     * @param sfInspectionExecuteDetail 巡检执行情况详细表
     * @return R
     */
    @SysLog("APP执行巡检操作接口" )
    @PostMapping("/update")
    // @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecutedetail_edit')" )
    public R updateForCheck(SfInspectionExecuteDetail sfInspectionExecuteDetail, MultipartFile[] files) {
        return new R<>(sfInspectionExecuteDetailService.updateForCheck(sfInspectionExecuteDetail,files));
    }

    /**
     /**(无token)
     * 查询当月结束时间小于当前系统时间的记录
     * sfInspectionExecuteDetail
     * @param sfInspectionExecuteDetail
     * @return R
     */
    @GetMapping("/findLastModifi" )
    public SfInspectionExecuteDetail selectLastModifiExecuteDetail(@Param("sfInspectionExecuteDetail")
                                                                SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return sfInspectionExecuteDetailService.selectLastModifiExecuteDetail(sfInspectionExecuteDetail);
    }


    /**(无token)
     * 根据任务id和巡检状态查询已巡检的巡检点总数
     * sfInspectionExecuteDetail
     * @param sfInspectionExecuteDetail
     * @return R
     */
    @GetMapping("/findByIsInspectionCount" )
    public Integer findByIsInspectionCount(@Param("sfInspectionExecuteDetail") SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return sfInspectionExecuteDetailService.findByIsInspectionCount(sfInspectionExecuteDetail);
    }


}
