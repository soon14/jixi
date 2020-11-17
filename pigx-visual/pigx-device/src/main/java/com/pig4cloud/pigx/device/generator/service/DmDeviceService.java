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

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceVO;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
public interface DmDeviceService extends IService<DmDevice> {

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
	 * 查询指定联网单位下的所有设备
	 * 
	 * @param unitId
	 * @return
	 */
	IPage<List<DmDevice>> getDmDeviceByOrgId(Page page,List<String> list);


	void updateVideoDeviceStatus();

	String getAccesstoken();
}
