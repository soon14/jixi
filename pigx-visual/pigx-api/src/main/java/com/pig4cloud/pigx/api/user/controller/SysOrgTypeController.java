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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pig4cloud.pigx.api.user.entity.SysOrgType;
import com.pig4cloud.pigx.api.user.service.SysOrgTypeService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 机构类型表
 *
 * @author lhd
 * @date 2019-05-15 15:26:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysorgtype" )
@Api(value = "sysorgtype", tags = "sysorgtype管理")
public class SysOrgTypeController {

    private final  SysOrgTypeService sysOrgTypeService;


    /**
     * 通过id查询机构类型表
     * @param typeId id
     * @return R
     */
    @GetMapping("/getById" )
    public R getById(String typeId) {
    	SysOrgType sysOrgType=null;
    	try {
    		sysOrgType=sysOrgTypeService.getById(typeId);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(sysOrgType);
    }


    
    
    @SysLog("获取机构类型select")
	@GetMapping("/getSelect")
	public R getSelect() {
		return new R(sysOrgTypeService.list());
	}

}
