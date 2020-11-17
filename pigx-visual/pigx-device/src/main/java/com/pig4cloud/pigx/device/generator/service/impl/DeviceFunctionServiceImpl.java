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
package com.pig4cloud.pigx.device.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceFunction;
import com.pig4cloud.pigx.device.generator.entity.DmFunction;
import com.pig4cloud.pigx.device.generator.mapper.DmDeviceFunctionMapper;
import com.pig4cloud.pigx.device.generator.service.DeviceFunctionService;
import com.pig4cloud.pigx.device.generator.service.DmDeviceService;
import com.pig4cloud.pigx.device.generator.service.DmFunctionService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceFunctionVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备功能关系表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@Service
@AllArgsConstructor
public class DeviceFunctionServiceImpl extends ServiceImpl<DmDeviceFunctionMapper, DmDeviceFunction>
		implements DeviceFunctionService {

	private final DmDeviceFunctionMapper dmDeviceFunctionMapper;

	private final DmFunctionService dmFunctionService;

	private final DmDeviceService dmDeviceService;

	@Override
	public IPage<List<DmDeviceFunctionVo>> getDeviceFunctionList(Page page, DmDeviceFunctionVo dmDeviceFunctionVo) {
		return dmDeviceFunctionMapper.getDeviceFunctionList(page, dmDeviceFunctionVo);
	}

	@Override
	public boolean saveDeviceFunction(String devId, String funcIds) {
		// 功能id有可能有多个,以逗号相分隔
		String[] ids = funcIds.split(",");
		// 存放设备功能实体
		List<DmDeviceFunction> dfList = new ArrayList<DmDeviceFunction>();
		if (ids.length > 0) {
			// 查询设备实体
			DmDevice dd = dmDeviceService.getById(devId);
			if (dd == null)
				return false;
			for (String funcId : ids) {
				if (funcId == null)
					continue;
				// 查询功能实体
				DmFunction fDto = dmFunctionService.getById(funcId);
				if (fDto == null)
					continue;
				DmDeviceFunction entity = new DmDeviceFunction();
				entity.setDevId(devId);
				entity.setFuncId(funcId);
				entity.setTypeId(dd.getTypeId());
				entity.setFactorId(dd.getFactorId());
				entity.setBrandId(dd.getBrandId());
				entity.setProdId(dd.getProdId());
				entity.setFuncType(fDto.getFuncType());
				entity.setDataType(fDto.getDataType());
				entity.setUpperLimit(fDto.getUpperLimit());
				entity.setLowerLimit(fDto.getLowerLimit());
				entity.setVolumeSwitch(fDto.getVolumeSwitch());
				dfList.add(entity);
			}
		}
		// 批量保存
		return this.saveBatch(dfList);
	}

	@Override
	public boolean updateDeviceFunction(String devId, String funcIds) {
		// 删除设备的功能信息
		dmDeviceFunctionMapper.deleteDeviceFunction(devId);
		// 重新加入设备功能信息
		return saveDeviceFunction(devId, funcIds);
	}

	@Override
	public boolean updateDeviceFunctionInfo(DmDeviceFunction dmDeviceFunction) {
		return dmDeviceFunctionMapper.updateDeviceFunctionInfo(dmDeviceFunction);
	}


	@Override
	public List<DmDeviceFunction> findDeviceDataById(String devId) {
		return dmDeviceFunctionMapper.findDeviceDataById(devId);
	}

}
