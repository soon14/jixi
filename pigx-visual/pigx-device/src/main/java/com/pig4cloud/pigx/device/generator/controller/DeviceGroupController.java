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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceGroup;
import com.pig4cloud.pigx.device.generator.service.DeviceGroupService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备分组关系表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/devicegroup")
@Api(value = "devicegroup", tags = "devicegroup管理")
public class DeviceGroupController {

	private final DeviceGroupService deviceGroupService;

	/**
	 * 分页查询
	 * 
	 * @param page          分页对象
	 * @param dmDeviceGroup 设备分组关系表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmDeviceGroupPage(Page page, DmDeviceGroup dmDeviceGroup) {
		return new R<>(deviceGroupService.page(page, Wrappers.query(dmDeviceGroup)));
	}

	/**
	 * 通过id查询设备分组关系表
	 * 
	 * @param groupId id
	 * @return R
	 */
	@GetMapping("/{groupId}")
	public R getById(@PathVariable("groupId") String groupId) {
		return new R<>(deviceGroupService.getById(groupId));
	}

	/**
	 * 根据分组id,查询设备数
	 * 
	 * @param groupId id
	 * @return R
	 */
	@GetMapping("/getDeviceCount")
	public R getDeviceCount(String groupId) {
		return new R<>(deviceGroupService.getDeviceCount(groupId));
	}

	/**
	 * 新增设备分组关系表
	 * 
	 * @param dmDeviceGroup 设备分组关系表
	 * @return R
	 */
	@SysLog("新增设备分组关系表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_devicegroup_add')")
	public R save(@Valid @RequestBody DmDeviceGroup dmDeviceGroup) {
		return new R<>(deviceGroupService.save(dmDeviceGroup));
	}

	/**
	 * 新增设备分组信息
	 * 
	 * @param groupId
	 * @param devIds
	 * @return
	 */
	@PostMapping("/saveDeviceGroup")
	@PreAuthorize("@pms.hasPermission('generator_devicegroup_add')")
	public R saveDeviceFunction(String groupId, String devIds) {
		// 校验设备分组Id不能为空
		if (StringUtils.isEmpty(groupId)) {
			return new R<>().setCode(1).setMsg("请选择设备分组");
		}
		// 校验设备Ids不能为空
		if (StringUtils.isEmpty(devIds)) {
			return new R<>().setCode(1).setMsg("请选择设备");
		}
		return new R<>(deviceGroupService.saveDeviceGroup(groupId, devIds));
	}

	/**
	 * 修改设备分组关系表
	 * 
	 * @param dmDeviceGroup 设备分组关系表
	 * @return R
	 */
	@SysLog("修改设备分组关系表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('generator_devicegroup_edit')")
	public R updateById(@Valid @RequestBody DmDeviceGroup dmDeviceGroup) {
		return new R<>(deviceGroupService.updateById(dmDeviceGroup));
	}

	/**
	 * 通过分组id和设备的id删除设备分组关系表
	 * 
	 * @param dmDeviceGroup 分组实体类
	 * @return R
	 */
	@SysLog("删除设备分组关系表")
	@DeleteMapping("/delete")
	public R delete(String groupId, String deviceIds) {
		// 校验设备分组Id不能为空
		if (StringUtils.isEmpty(groupId)) {
			return new R<>().setCode(1).setMsg("请选择设备分组");
		}
		// 校验设备Ids不能为空
		if (StringUtils.isEmpty(deviceIds)) {
			return new R<>().setCode(1).setMsg("请选择设备");
		}
		String[] strs = deviceIds.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i].toString());
		}
		DmDeviceGroupVO deviceGroupVO = new DmDeviceGroupVO();
		deviceGroupVO.setGroupId(groupId);
		deviceGroupVO.setDeviceId(list);
		return new R<>(deviceGroupService.removeDmDeviceGroup(deviceGroupVO));
	}

}
