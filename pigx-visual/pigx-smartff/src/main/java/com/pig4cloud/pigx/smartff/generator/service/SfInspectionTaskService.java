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

package com.pig4cloud.pigx.smartff.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检任务表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:53
 */
public interface SfInspectionTaskService extends IService<SfInspectionTask> {

    /**
     * 根据任务名称（支持模糊查询）或者联网单位ID查询任务列表
     * @param page
     * @param sfInspectionTask
     * @return
     */
    IPage<List<SfInspectionTask>> findByTaskNameList(Page page,SfInspectionTask sfInspectionTask);

    /**
     * 关联巡检执行表查询当月巡检任务List
     * @return
     */
    List<SfInspectionTask> findTaskByJobData();

    /**
     * 从巡检任务表执行数据到巡检执行表和巡检执行详细表中
     * @param sfInspectionTask1
     */
    Integer insertTask(SfInspectionTask sfInspectionTask1);


    IPage<List<SfInspectionTask>> findTasktList(Page page,SfInspectionTask sfInspectionTask);


}
