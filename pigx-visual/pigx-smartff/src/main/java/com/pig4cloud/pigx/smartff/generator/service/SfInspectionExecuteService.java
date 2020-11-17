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
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecute;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;
import com.pig4cloud.pigx.smartff.generator.entity.SysUser;
import com.pig4cloud.pigx.smartff.generator.vo.SfInspectionResultVO;

import java.util.List;

/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
public interface SfInspectionExecuteService extends IService<SfInspectionExecute> {

    /**
     * 通过任务id,查询巡检执行表记录。
     * @param sfInspectionExecute
     * @return
     */
    SfInspectionExecute selectByTaskId(SfInspectionExecute sfInspectionExecute);

    /**
     * 通过任务id,查询当前巡检人员姓名和电话（巡检执行表中的巡检人员的id集合）。
     * @param sfInspectionExecute
     * @return
     */
    List<SysUser> selectByTaskIdToPersion(SfInspectionExecute sfInspectionExecute);


    /**
     * 通过巡检执行任务中taskId查询巡检点列表接口
     * @param sfInspectionExecute
     * @return
     */
    List<SfInspectionPoint> selectByTaskIdToPointList(SfInspectionExecute sfInspectionExecute);

    /**
     * 根据联网单位（业主单位）ID，或者任务状态查询执行表
     * @param page,sfInspectionExecute
     * @return
     */
    IPage<List<SfInspectionResultVO>> selectByOrgId(Page page, SfInspectionExecute sfInspectionExecute);


    /**
     * 查询当月结束时间小于当前系统时间的记录
     * @return
     */
    List<SfInspectionExecute> findTaskByJobEndTime();



    IPage<List<SfInspectionExecute>> findTasktList(Page page,SfInspectionExecute sfInspectionExecute);

}
