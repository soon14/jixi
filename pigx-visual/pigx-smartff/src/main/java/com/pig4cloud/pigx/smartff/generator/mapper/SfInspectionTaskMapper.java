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

package com.pig4cloud.pigx.smartff.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检任务表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:53
 */
public interface SfInspectionTaskMapper extends BaseMapper<SfInspectionTask> {

    /**
     * 根据任务名称（支持模糊查询）或者联网单位ID查询任务列表
     * @param page
     * @param sfInspectionTask
     * @return
     */
    IPage<List<SfInspectionTask>> findByTaskNameList(Page page, @Param("query") SfInspectionTask sfInspectionTask);

    /**
     * 关联巡检执行表查询当月巡检任务List
     * @return
     */
    List<SfInspectionTask> findTaskByJob();

    /**
     * 查询巡检任务添加数据权限控制
     * @param page
     * @param sfInspectionTask
     * @return
     */
    IPage<List<SfInspectionTask>> findTasktList(Page page,@Param("query") SfInspectionTask sfInspectionTask);

}
