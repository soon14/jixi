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
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecute;
import com.pig4cloud.pigx.smartff.generator.feign.UserFeign;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectionexecute" )
@Api(value = "sfinspectionexecute", tags = "sfinspectionexecute管理")
public class SfInspectionExecuteController {

    private final  SfInspectionExecuteService sfInspectionExecuteService;


    @Autowired
    private UserFeign userFeign;
    /**
     * 分页查询
     * @param page 分页对象
     * @param sfInspectionExecute 巡检执行表
     * @return
     */
    @GetMapping("/page" )
    public R getSfInspectionExecutePage(Page page, SfInspectionExecute sfInspectionExecute) {
        List<String> list = userFeign.getUserLoingUnitIds();
        sfInspectionExecute.setUnitIds(list);
        return new R<>(sfInspectionExecuteService.findTasktList(page,sfInspectionExecute));
    }


    /**
     * 通过id查询巡检执行表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfInspectionExecuteService.getById(id));
    }

    /**
     * 新增巡检执行表
     * @param sfInspectionExecute 巡检执行表
     * @return R
     */
    @SysLog("新增巡检执行表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecute_add')" )
    public R save(@RequestBody SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.save(sfInspectionExecute));
    }

    /**
     * 修改巡检执行表
     * @param sfInspectionExecute 巡检执行表
     * @return R
     */
    @SysLog("修改巡检执行表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecute_edit')" )
    public R updateById(@RequestBody SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.updateById(sfInspectionExecute));
    }

    /**
     * 通过id删除巡检执行表
     * @param id id
     * @return R
     */
    @SysLog("删除巡检执行表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sfinspectionexecute_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(sfInspectionExecuteService.removeById(id));
    }


    /**
     *通过任务id(taskid),查询巡检执行表记录（单条巡检任务）。
     * @param sfInspectionExecute
     * @return
     */
    @SysLog("查询巡检执行表" )
    @GetMapping("/getOne" )
    public R getByTaskId(SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.selectByTaskId(sfInspectionExecute));
    }


    /**
     *通过任务id,查询当前巡检人员姓名和电话。
     * @param sfInspectionExecute
     * @return
     */
    @SysLog("查询巡检人员姓名电话" )
    @GetMapping("/getPersionList" )
    public R getByTaskIdToPersion(SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.selectByTaskIdToPersion(sfInspectionExecute));
    }

    /**
     *通过巡检执行任务中的巡检点ID查询巡检点表
     * @param sfInspectionExecute
     * @return
     */
    @SysLog("查询任务下的巡检点" )
    @GetMapping("/getPeointList" )
    public R getByTaskIdToPoint(SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.selectByTaskIdToPointList(sfInspectionExecute));
    }

    /**
     *巡检执行任务的查询（统计数据,包含任务完成率）
     * @param page,sfInspectionExecute
     * @auth zm
     * @date 2019-08-11 15:56:59
     * @return
     */
    @SysLog("巡检任务的查询" )
    @GetMapping("/findInspectionTaskData" )
    public R findInspectionTaskData(Page page,SfInspectionExecute sfInspectionExecute) {
        List<String> list = userFeign.getUserLoingUnitIds();
        sfInspectionExecute.setUnitIds(list);
        return new R<>(sfInspectionExecuteService.selectByOrgId(page,sfInspectionExecute));
    }


    /**(无token)
     * 查询当月结束时间小于当前系统时间的记录
     * @return
     */
    @SysLog("定时任务调用巡检执行任务")
    @GetMapping("/findExcuteByEndTime")
    List<SfInspectionExecute> findTaskByJobEndTime(){
       return sfInspectionExecuteService.findTaskByJobEndTime();
    }



    /**(无token)
     * 修改巡检执行表
     * @param sfInspectionExecute 巡检执行表
     * @return R
     */
    @SysLog("修改巡检执行表" )
    @PutMapping("/updateByIdForJob")
    public R updateByIdForJob(@RequestBody SfInspectionExecute sfInspectionExecute) {
        return new R<>(sfInspectionExecuteService.updateById(sfInspectionExecute));
    }
}
