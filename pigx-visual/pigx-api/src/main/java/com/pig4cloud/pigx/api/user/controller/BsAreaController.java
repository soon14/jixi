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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pigx.api.user.entity.BsArea;
import com.pig4cloud.pigx.api.user.service.BsAreaService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 区域表
 *
 * @author lhd
 * @date 2019-05-15 15:38:07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsarea")
@Api(value = "bsarea", tags = "bsarea管理")
public class BsAreaController {

	private final BsAreaService bsAreaService;
	
	private final SysUserService sysUserService;

	/**
	 * 根据编码查询地区信息 
	 * @param code
	 * @return
	 */
	  @GetMapping("/getAreaByCode" )
	  public R getAreaByCode(String code) {
		  BsArea area=null;
	    	try {
	    		area = bsAreaService.getAreaByCode(code);
	    	}catch(Exception e) {
	    		return new R<>(e);
	    	}
	    	return new R<>(area);
	    }
	/**
	   * 获取当前登录用户所属联网单位的区域信息
	 * 
	 * @return R
	 */
	@GetMapping("/getAreaLongonUser")
	public R getAreaLongonUser() {
		List<BsArea> list=null;
		try {
			list=bsAreaService.getAreaLongonUser(sysUserService.getUserLoingUnitIds());
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	
	@SysLog("查询区域select")
	@GetMapping("/select")
    public R getSelect() {
		DynamicDataSourceContextHolder.clearDataSourceType();
    	return new R<>(bsAreaService.list(Wrappers.<BsArea>query()));
    }

	@SysLog("根据城市查询区域select")
	@GetMapping("/select/{cityId}")
	public R getSelect(@PathVariable String cityId) {
		DynamicDataSourceContextHolder.clearDataSourceType();
		BsArea area = new BsArea();
		area.setCityId(cityId);
		return new R<>(bsAreaService.list(Wrappers.<BsArea>query(area)));
	}
}
