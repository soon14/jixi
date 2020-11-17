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
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceVO;

import java.util.List;

/**
 * 设备表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
public interface DeviceService extends IService<DmDevice> {

	/**
	 *  查询设备列表
	 *
	 * @param page
	 * @param dmProduct
	 * @return
	 */
	List<DmDeviceVO> getDeviceList(Page page, DmDevice dmDevice);
	/**
	 *    设备激活
	 * @param code
	 * @return
	 */
	boolean activate(String code);

	/**
	 * 查询不属于该设备分组的设备信息
	 *
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, DmDeviceGroupVO vo);

	/**
	 * 查询属于该设备分组的设备信息
	 *
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmDeviceVO>> getGroupDevice(Page page, DmDeviceGroupVO vo);

	/**
	 * 保存设备信息
	 *
	 * @param dmDevice
	 * @return
	 */
	boolean saveDevice(DmDevice dmDevice);

	/**
	 * 修改设备信息
	 *
	 * @param dmDevice
	 * @return
	 */
	boolean updateDevice(DmDevice dmDevice);

	/**
	 * 获取视频设备的select
	 *
	 * @param dmDevice
	 * @return
	 */
	List<DmDevice> selectVideoDevice(DmDevice dmDevice);

	/**
	 * 设备列表查询（通过产品类别或者联网单位（支持模糊查询））
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmDeviceVO>> findDeviceList(Page page,DmDevice dmDevice);


	DmDevice selectDmdeviceByImei(String imei);

	DmDevice selectDmdeviceByCode(String code);

	void updateDeviceByImei(DmDevice dmDevice);

}
