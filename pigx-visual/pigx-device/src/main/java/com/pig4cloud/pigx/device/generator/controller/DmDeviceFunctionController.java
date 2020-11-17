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

package com.pig4cloud.pigx.device.generator.controller;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.device.generator.dto.DmDeviceFunctionDTO;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceFunction;
import com.pig4cloud.pigx.device.generator.service.CommonFeignService;
import com.pig4cloud.pigx.device.generator.service.DmDeviceFunctionService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceFunctionVo;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pig4cloud.pigx.device.generator.common.Constant.PRODUCT_CATEGORY;

/**
 * 设备功能关系表
 *
 * @author lhd
 * @date 2019-06-20 10:15:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmdevicefunction")
@Api(value = "dmdevicefunction", tags = "dmdevicefunction管理")
public class DmDeviceFunctionController {

	private final DmDeviceFunctionService dmDeviceFunctionService;
	private CommonFeignService commonFeignService;

	/**
	 * 分页查询
	 * 
	 * @param page             分页对象
	 * @param dmDeviceFunctionVo 设备功能关系表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmDeviceFunctionPage(Page page, DmDeviceFunctionVo dmDeviceFunctionVo,String deviceId) {
		dmDeviceFunctionVo.setDevId(deviceId);
		R r = new R<>(dmDeviceFunctionService.getDeviceFunctionList(page, dmDeviceFunctionVo));
		for(DmDeviceFunctionVo DmDeviceFunction : (List<DmDeviceFunctionVo>)((Page)r.getData()).getRecords()){
			DmDeviceFunction.setProductTypeName(commonFeignService.getInterfaceDictByType(PRODUCT_CATEGORY).get(String.valueOf(DmDeviceFunction.getTypeId())));
		}
		return r;
	}

	/**
	 * 通过id查询设备功能关系表
	 * 
	 * @param devId id
	 * @return R
	 */
	@GetMapping("/{devId}")
	public R getById(@PathVariable("devId") String devId) {
		return new R<>(dmDeviceFunctionService.getById(devId));
	}

	/**
	 * 新增设备功能关系表
	 * 
	 * @param dmDeviceFunction 设备功能关系表
	 * @return R
	 */
	@SysLog("新增设备功能关系表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_dmdevicefunction_add')")
	public R save(@RequestBody DmDeviceFunction dmDeviceFunction) {
		return new R<>(dmDeviceFunctionService.save(dmDeviceFunction));
	}

	/**
	 * 新增设备功能信息
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/saveDeviceFunction")
	@PreAuthorize("@pms.hasPermission('generator_dmdevicefunction_add')")
	public R saveDeviceFunction(@RequestBody DmDeviceFunctionDTO dto) {
		// 校验设备Id不能为空
		if (StringUtils.isEmpty(dto.getDeviceId())) {
			return new R<>().setCode(1).setMsg("请选择设备");
		}
		// 校验功能Ids不能为空
		if (StringUtils.isEmpty(dto.getFunctionIds())) {
			return new R<>().setCode(1).setMsg("请选择功能");
		}
		return new R<>(dmDeviceFunctionService.saveDeviceFunction(dto.getDeviceId(), dto.getFunctionIds()));
	}

	/**
	 * 修改设备功能信息
	 * 
	 * @param devId
	 * @param funcIds
	 * @return
	 */
	@PostMapping("/updateDeviceFunction")
	@PreAuthorize("@pms.hasPermission('generator_dmdevicefunction_edit')")
	public R updateDeviceFunction(String deviceId, String functionIds) {
		// 校验设备Id不能为空
		if (StringUtils.isEmpty(deviceId)) {
			return new R<>().setCode(1).setMsg("请选择设备");
		}
		// 校验功能Ids不能为空
		if (StringUtils.isEmpty(functionIds)) {
			return new R<>().setCode(1).setMsg("请选择功能");
		}
		return new R<>(dmDeviceFunctionService.updateDeviceFunction(deviceId, functionIds));
	}

	/**
	 * 修改设备功能关系表
	 * 
	 * @param dmDeviceFunction 设备功能关系表
	 * @return R
	 */
	@SysLog("修改设备功能关系表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('generator_dmdevicefunction_edit')")
	public R updateById(@RequestBody DmDeviceFunction dmDeviceFunction) {
		return new R<>(dmDeviceFunctionService.updateDeviceFunctionInfo(dmDeviceFunction));
	}

	/**
	 * 通过id删除设备功能关系表
	 * 
	 * @param devId id
	 * @return R
	 */
	@SysLog("删除设备功能关系表")
	@DeleteMapping("/{devId}")
	@PreAuthorize("@pms.hasPermission('generator_dmdevicefunction_del')")
	public R removeById(@PathVariable String devId) {
		return new R<>(dmDeviceFunctionService.removeById(devId));
	}

}
