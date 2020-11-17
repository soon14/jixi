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

package com.pig4cloud.pigx.device.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.entity.DmDeviceFunction;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceFunctionVo;

import java.util.List;

/**
 * 设备功能关系表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
public interface DeviceFunctionService extends IService<DmDeviceFunction> {

	/**
	    *   查询设备所属功能列表
	 *
	 * @param page
	 * @param dmDeviceFunctionVo
	 * @return
	 */
	IPage<List<DmDeviceFunctionVo>> getDeviceFunctionList(Page page, DmDeviceFunctionVo dmDeviceFunctionVo);
	/**
	    *    增加设备功能
	 * @param devId
	 * @param funcIds
	 * @return
	 */
	boolean saveDeviceFunction(String devId, String funcIds);
	/**
	    *    更新设备功能
	 * @param devId
	 * @param funcIds
	 * @return
	 */
	boolean updateDeviceFunction(String devId, String funcIds);


	/**
	 *    更新设备功能信息
	 * @param devId
	 * @param funcIds
	 * @return
	 */
	boolean updateDeviceFunctionInfo(DmDeviceFunction dmDeviceFunction);



	/**
	 * 通过设备id查询出设备功能关系表有可能是多条
	 * @param devId
	 * @return
	 */
	List<DmDeviceFunction> findDeviceDataById(String devId);
}
