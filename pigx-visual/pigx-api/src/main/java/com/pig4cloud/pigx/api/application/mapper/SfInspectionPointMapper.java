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

package com.pig4cloud.pigx.api.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.SfInspectionPoint;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检点表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:59
 */
public interface SfInspectionPointMapper extends BaseMapper<SfInspectionPoint> {


    /**
     * 根据联网单位ID,建筑ID查询巡检点列表信息
     * @param page
     * @param sfInspectionPoint
     * @return
     */
    IPage<List<SfInspectionPoint>> findPointByOrgBuildId(Page page, @Param("query") SfInspectionPoint sfInspectionPoint) throws Exception;
    
    /**
     * 巡检点绑定二维码
     * 
     * @param sfInspectionPoint
     * @return
     */
    boolean bindingQRCode(@Param("save") SfInspectionPoint sfInspectionPoint) throws Exception;
    
    /**
     * 根据id和二维码信息查询巡检点
     * @param id
     * @param pointQrCode
     * @return
     */
    int findInspectionPoint(@Param("id")String id,@Param("pointQrCode")String pointQrCode) throws Exception;


}
