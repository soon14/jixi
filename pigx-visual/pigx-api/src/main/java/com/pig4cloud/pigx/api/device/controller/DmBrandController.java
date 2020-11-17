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

package com.pig4cloud.pigx.api.device.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pig4cloud.pigx.api.device.dto.DmBrandDTO;
import com.pig4cloud.pigx.api.device.service.DmBrandService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 品牌表
 *
 * @author lhd
 * @date 2019-06-20 10:15:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmbrand" )
@Api(value = "dmbrand", tags = "dmbrand管理")
public class DmBrandController {

    private final  DmBrandService dmBrandService;

    /**
               *  获取品牌select
     * @param factorId 厂家的ID
     * @return
     */
    @SysLog("获取品牌select")
    @GetMapping("/select")
    public R selectFactorId(String factorId) {
    	List<DmBrandDTO> dtos=null;
    	try {
    		dtos=dmBrandService.getSelectFactorId(factorId);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(dtos);
    }

}
