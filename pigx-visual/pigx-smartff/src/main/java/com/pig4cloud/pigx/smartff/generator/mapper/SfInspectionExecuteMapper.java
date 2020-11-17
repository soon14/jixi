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
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecute;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionTask;
import com.pig4cloud.pigx.smartff.generator.vo.SfInspectionResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
public interface SfInspectionExecuteMapper extends BaseMapper<SfInspectionExecute> {

    /**
     * 通过任务id,查询巡检执行表记录。
     *
     * @param sfInspectionExecute
     * @return
     */
    SfInspectionExecute selectByTaskId(@Param("query") SfInspectionExecute sfInspectionExecute);

    /**
     * 通过巡检执行任务中的巡检点ID查询巡检点表
     *
     * @param arr
     * @return
     */
    List<SfInspectionPoint> selectByTaskIdToPointList(@Param("arr") String[] arr);

    /**
     * 根据联网单位（业主单位）ID，或者任务状态查询执行表
     * @param page,sfInspectionExecute
     * @return
     */
    List<SfInspectionResultVO> selectByOrgId(Page page, @Param("query") SfInspectionExecute sfInspectionExecute);


    /**
     * 查询当月结束时间小于当前系统时间的记录
     * @return
     */
    List<SfInspectionExecute> findTaskByJobEnd();


    /**
     * 查询巡检执行任务添加数据权限控制
     * @param page
     * @param sfInspectionExecute
     * @return
     */
    IPage<List<SfInspectionExecute>> findTasktList(Page page, @Param("query") SfInspectionExecute sfInspectionExecute);

}
