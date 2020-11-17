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

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecuteDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 巡检执行情况详细表
 *
 * @author zm
 * @date 2019-08-11 10:30:36
 */
public interface SfInspectionExecuteDetailService extends IService<SfInspectionExecuteDetail> {

    /**
     * APP执行巡检操作接口
     * @param sfInspectionExecuteDetail
     * @return
     */
    String updateForCheck(SfInspectionExecuteDetail sfInspectionExecuteDetail, MultipartFile[] files);

    /**
     * 根据任务Id查询本次任务中最后巡检的一条巡检点数据
     * @param sfInspectionExecuteDetail
     * @return
     */
    SfInspectionExecuteDetail selectLastModifiExecuteDetail(SfInspectionExecuteDetail sfInspectionExecuteDetail);


    /**
     * 根据任务id和巡检状态查询已巡检的巡检点总数
     * @param sfInspectionExecuteDetail
     * @return
     */
    Integer findByIsInspectionCount(SfInspectionExecuteDetail sfInspectionExecuteDetail);
}
