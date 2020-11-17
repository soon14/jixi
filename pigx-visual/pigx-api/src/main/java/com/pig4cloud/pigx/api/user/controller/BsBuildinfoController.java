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

package com.pig4cloud.pigx.api.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.user.dto.BuildinfoDTO;
import com.pig4cloud.pigx.api.user.service.BsBuildinfoService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.BuildinfoVO;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 楼体信息表
 *
 * @author lhd
 * @date 2019-05-15 15:37:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsbuildinfo" )
@Api(value = "bsbuildinfo", tags = "bsbuildinfo管理")
public class BsBuildinfoController {

    private final  BsBuildinfoService bsBuildinfoService;
    
    private final SysUserService sysUserService;
    

 
    /**
	    * 获取所在建筑select
	 * 
	 * @param networkUnitId 联网单位id
	 * @return
	 */
	@SysLog("获取所在建筑select")
	@GetMapping("/select")
	public R selectBuild(String networkUnitId) {
		DynamicDataSourceContextHolder.clearDataSourceType();
		List<BuildinfoDTO> dtos=null;
		try {
			dtos=bsBuildinfoService.selectBuild(networkUnitId);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(dtos);
	}
	
    /**
               * 分页查询
     * @param page 分页对象
     * @param buildinfoDTO 楼体信息表
     * @return
     */
    @GetMapping("/page" )
    public R getBsBuildinfoPage(Page page, BuildinfoDTO buildinfoDTO) {
    	try {
    	List<String> unitIds=sysUserService.getUserLoingUnitIds();
    	List<BuildinfoVO> list=bsBuildinfoService.getBuildinfoPage(page, buildinfoDTO,unitIds);
    	page.setRecords(list);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(page);
    }
	
}
