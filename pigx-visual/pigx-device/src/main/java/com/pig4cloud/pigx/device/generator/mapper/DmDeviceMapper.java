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

package com.pig4cloud.pigx.device.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
public interface DmDeviceMapper extends BaseMapper<DmDevice> {

	/**
	 * 查询设备列表
	 * 
	 * @param page
	 * @param dmProduct
	 * @return
	 */
	List<DmDeviceVO> getDeviceList(Page page, @Param("query") DmDevice dmDevice);

	/**
	 * 查询不属于该分组的设备信息
	 * 
	 * @param page
	 * @param groupId
	 * @return
	 */
	IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, @Param("query") DmDeviceGroupVO vo);

	/**
	 * 查询属于该分组的设备信息
	 * 
	 * @param page
	 * @param groupId
	 * @return
	 */
	IPage<List<DmDeviceVO>> getGroupDevice(Page page, @Param("query") DmDeviceGroupVO vo);

	List<String> getDeviceCodeList();

	/**
	 * 插入数据到设备视频关系表
	 *
	 * @param devId
	 * @param videoId
	 * @return
	 */
	boolean insertDeviceVideo(@Param("devId") String devId, @Param("videoId") String videoId);

	/**
	 * 获取视频设备的select
	 *
	 * @param dmDevice
	 * @return
	 */
	List<DmDevice> selectVideoDevice(@Param("query") DmDevice dmDevice);
	
	/**
	 * 获取联网单位下的所有设备
	 * 
	 * @param unitId
	 * @return
	 */
	IPage<List<DmDevice>> getDmDeviceByOrgId(Page page,@Param("unitIds") List<String> unitIds);

    /**
     * 设备查询（默认根据产品类别或联网单位（支持模糊查））
     *
     * @param page
     * @param dmDevice
     * @return
     */
    List<DmDeviceVO> findDeviceList(Page page, @Param("query") DmDevice dmDevice);


	DmDevice selectDmdeviceByImei(String imei);

	DmDevice selectDmdeviceByCode(String code);

	void updateDeviceByCode(DmDevice dmDevice);

	List<String>  selectVideoDeviceCodeList();

	void updateDeviceByImei(DmDevice dmDevice);
}
