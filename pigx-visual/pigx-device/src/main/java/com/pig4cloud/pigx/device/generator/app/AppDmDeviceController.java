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

package com.pig4cloud.pigx.device.generator.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.service.DmDeviceService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/app/dmdevice")
@Api(value = "appDmDevice", tags = "app dmdevice管理")
public class AppDmDeviceController {

	private final DmDeviceService dmDeviceService;

	/**
	 * 登录用户所属机构下的所有设备
	 * 
	 * @param unitId
	 * @return
	 */
	@GetMapping("/getDmDeviceByUnitIds")
	public R getDmDeviceByOrgId(Page page,String unitIds) {
		if(StringUtils.isEmpty(unitIds)) {
			return new R<>().setCode(1).setMsg("联网单位不能不空");
		}
		List<String> list=new ArrayList<String>();
		String[] uds=unitIds.split("_");
		for(String id:uds) {
			if(id==null)continue;
			list.add(id);
		}
		return new R<>(dmDeviceService.getDmDeviceByOrgId(page,list));
	}
	
	/**
	 * 新增设备表
	 * 
	 * @param dmDevice 设备表
	 * @return R
	 */
	@SysLog("新增设备表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_dmdevice_add')")
	public R save(@Valid @RequestBody DmDevice dmDevice) {
		PigxUser user = SecurityUtils.getUser();
		dmDevice.setCreateUserId(user.getId());
		dmDevice.setCreateUser(user.getUsername());
		return new R<>(dmDeviceService.saveDevice(dmDevice));
	}



}
