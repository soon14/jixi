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

import com.pig4cloud.pigx.api.user.entity.BsCounty;
import com.pig4cloud.pigx.api.user.service.BsCountyService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 楼层信息
 *
 * @author lhd
 * @date 2019-05-15 15:37:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bscounty" )
@Api(value = "bscounty", tags = "bscounty管理")
public class BsCountyController {

    private final  BsCountyService bsCountyService;

    /**
	    * 获取所在楼层select
	 * 
	 * @param buildId 建筑id
	 * @return
	 */
	@SysLog("获取所在楼层select")
	@GetMapping("/select")
	public R selectBuild(String buildId) {
		List<BsCounty> list=null;
		try {
			list=bsCountyService.selectCounty(buildId);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}
}
