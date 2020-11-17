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
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionTask;
import com.pig4cloud.pigx.smartff.generator.feign.UserFeign;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 巡检任务表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectiontask" )
@Api(value = "sfinspectiontask", tags = "sfinspectiontask管理")
public class SfInspectionTaskController {

    private final  SfInspectionTaskService sfInspectionTaskService;


    @Autowired
    private UserFeign userFeign;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfInspectionTask 巡检任务表
     * @return
     */
    @GetMapping("/page" )
    public R getSfInspectionTaskPage(Page page, SfInspectionTask sfInspectionTask) {
        List<String> list = userFeign.getUserLoingUnitIds();
        sfInspectionTask.setUnitIds(list);
        return new R<>(sfInspectionTaskService.findTasktList(page,sfInspectionTask));
    }


    /**
     * 通过id查询巡检任务表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfInspectionTaskService.getById(id));
    }

    /**
     * 新增巡检任务表
     * @param sfInspectionTask 巡检任务表
     * @return R
     */
    @SysLog("新增巡检任务表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectiontask_add')" )
    public R save(@RequestBody SfInspectionTask sfInspectionTask) {
        sfInspectionTask.setCreatetime(LocalDateTime.now());
        return new R<>(sfInspectionTaskService.save(sfInspectionTask));
    }

    /**
     * 修改巡检任务表
     * @param sfInspectionTask 巡检任务表
     * @return R
     */
    @SysLog("修改巡检任务表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sfinspectiontask_edit')" )
    public R updateById(@RequestBody SfInspectionTask sfInspectionTask) {
        sfInspectionTask.setModifytime(LocalDateTime.now());
        return new R<>(sfInspectionTaskService.updateById(sfInspectionTask));
    }

    /**
     * 通过id删除巡检任务表
     * @param id id
     * @return R
     */
    @SysLog("删除巡检任务表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sfinspectiontask_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(sfInspectionTaskService.removeById(id));
    }


    /**
     * 根据任务名称（支持模糊查询）或者联网单位ID查询任务列表
     * @param page 分页对象
     * @param sfInspectionTask 巡检任务表
     * @return
     */
    @GetMapping("/pageDataList" )
    public R findByTaskNameList(Page page, SfInspectionTask sfInspectionTask) {
        return new R<>(sfInspectionTaskService.findByTaskNameList(page,sfInspectionTask));
    }

    /**
     * 关联巡检执行表查询当月巡检任务List（此接口为定时任务专用无token）
     * @return
     */
    @GetMapping("/list")
    public List<SfInspectionTask> findTaskByData (){
        return sfInspectionTaskService.findTaskByJobData();
    }


    /**
     * 巡检任务配发接口
     * @param sfInspectionTask 巡检任务表
     * @return R
     */
    @SysLog("任务临时配发接口" )
    @PutMapping("/allotment")
    @PreAuthorize("@pms.hasPermission('generator_sfinspectiontask_edit')" )
    public R allotmentTask(@RequestBody SfInspectionTask sfInspectionTask) {

        return new R<>(sfInspectionTaskService.updateById(sfInspectionTask));
    }

    /**
     * 从巡检任务表执行数据到巡检执行表和巡检执行详细表中（定时任务专用无token）
     * @param sfInspectionTask
     * @return R
     */
    @SysLog("定时配发任务调用" )
    @PostMapping("/insertRelation")
    @PreAuthorize("@pms.hasPermission('generator_sfinspectiontask_edit')" )
    public R insertRelation(@RequestBody SfInspectionTask sfInspectionTask) {
        return new R<>(sfInspectionTaskService.insertTask(sfInspectionTask));
    }

}
