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

package com.pig4cloud.pigx.api.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.SfInspectionPoint;
import com.pig4cloud.pigx.api.application.service.SfInspectionPointService;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 巡检点表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectionpoint" )
@Api(value = "sfinspectionpoint", tags = "sfinspectionpoint管理")
public class SfInspectionPointController {

    private final  SfInspectionPointService sfInspectionPointService;
    /**
     * 巡检点绑定二维码
     * @param sfInspectionPoint
     * @return
     */
    @PostMapping("/bindingQRCode")
    public R bindingQRCode(@RequestBody SfInspectionPoint sfInspectionPoint) {
    	sfInspectionPoint.setBinding(Constant.BINDING_STATUS_YES);
    	try {
    		sfInspectionPointService.bindingQRCode(sfInspectionPoint);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(true);
    }

    /**
     * 根据联网单位ID,建筑ID查询巡检点列表信息
     * （巡检方案配置巡检点时使用）
     * @param page 分页对象
     * @param sfInspectionPoint 巡检点表
     * @return
     */
    @GetMapping("/pageList" )
    public R findPointByOrgBuildId(Page page, SfInspectionPoint sfInspectionPoint) {
    	IPage<List<SfInspectionPoint>> list=null;
    	try {
    		list=sfInspectionPointService.findPointByOrgBuildId(page, sfInspectionPoint);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(list);
    }

}
