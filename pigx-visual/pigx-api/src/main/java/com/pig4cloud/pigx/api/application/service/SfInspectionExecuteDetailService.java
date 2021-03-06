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

package com.pig4cloud.pigx.api.application.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.api.application.vo.SfInspectionExecuteDetailVo;

/**
 * 巡检执行情况详细表
 *
 * @author zm
 * @date 2019-08-11 10:30:36
 */
public interface SfInspectionExecuteDetailService extends IService<SfInspectionExecuteDetail> {

	 /**
                * 根据任务id和巡检状态查询已巡检的巡检点总数
     * @param sfInspectionExecuteDetail
     * @return
     */
    Integer findCountByIsInspection(SfInspectionExecuteDetail sfInspectionExecuteDetail) throws Exception;
    
    /**
               *    查询巡检执行情况
     * @param executeIdss
     * @return
     */
    List<SfInspectionExecuteDetailVo> getExecuteDetailByExeId(Page page,String executeId) throws Exception;
    
    /**
                *  根据执行详细Id查询执行详细
     * 
     * @param detailId
     * @return
     */
    SfInspectionExecuteDetailVo getExecuteDetailByDetailId(String detailId) throws Exception;
    
    /**
                *   保存巡检结果
     * 
     * @param sfInspectionExecuteDetail
     * @return
     */
    boolean saveInspectionResult(SfInspectionExecuteDetailVo sfInspectionExecuteDetail) throws Exception;
    
    
    List<SfInspectionExecuteDetailVo> getInspectionEvent(Integer status,Integer isInspection,List<String> unitIds) throws Exception;
}
