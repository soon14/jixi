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

package com.pig4cloud.pigx.api.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.device.dto.DmDeviceDto;
import com.pig4cloud.pigx.api.device.entity.DmDevice;
import com.pig4cloud.pigx.api.device.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.api.device.vo.DmDeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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
	List<DmDeviceVO> getDeviceList(Page page, DmDevice dmDevice,List<String> unitIds) throws Exception;
	/**
	    *    设备激活
	 * @param code
	 * @return
	 */
	boolean activate(String code) throws Exception;

	/**
	 * 查询不属于该设备分组的设备信息
	 *
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, DmDeviceGroupVO vo) throws Exception;

	/**
	 * 查询属于该设备分组的设备信息
	 *
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	IPage<List<DmDeviceVO>> getGroupDevice(Page page, DmDeviceGroupVO vo) throws Exception;

	/**
	 * 保存设备信息
	 *
	 * @param dmDevice
	 * @return
	 */
	boolean saveDevice(DmDevice dmDevice) throws Exception;

	/**
	 * 修改设备信息
	 *
	 * @param dmDevice
	 * @return
	 */
	boolean updateDevice(DmDevice dmDevice) throws Exception;

	/**
	 * 获取视频设备的select
	 *
	 * @param dmDevice
	 * @return
	 */
	List<DmDevice> selectVideoDevice(DmDevice dmDevice) throws Exception;

	/**
	 * 查询指定联网单位下的所有设备
	 *
	 * @param unitId
	 * @return
	 */
	IPage<List<DmDevice>> getDmDeviceByUnitIds(Page page,List<String> list) throws Exception;

	/**
	   * 根据联网单位Id查询设备位置信息
	 *
	 * @param unitId
	 * @return
	 */
	List<DmDevice> getDevicePostionByUnitId(String unitId,String buildId,String countyId) throws Exception;

	/**
	    *  根据查询条件查询设备信息列表
	 *
	 * @param page
	 * @param dmDevice
	 * @param unitIds
	 * @return
	 */
	IPage<List<DmDevice>> queryDeviceInfo(Page page,DmDeviceDto dmDevice,List<String> unitIds) throws Exception;

	/**
	    * 根据设备类型查询设备数量
	 * @param typeId
	 * @return
	 */
	Integer queryDeviceCountByType(DmDevice dmDevice,List<String> unitIds) throws Exception;

	/**
	    * 根据设备id查询设备信息
	 *
	 * @param devId
	 * @return
	 */
	DmDeviceVO getDeviceInfoById(String devId) throws Exception;

	/**
	    * 根据设备编码查询设备信息
	 *
	 * @param devCode
	 * @return
	 */
	DmDeviceVO getDeviceInfoByCode(String devCode) throws Exception;
	/**
	    *  查询设备信息根据联网单位、建筑、楼层、位置
	 * @param dmDevice
	 * @return
	 */
	List<DmDeviceVO> getDeviceInfoByCons(DmDevice dmDevice) throws Exception;

	/**
	 * 获取当前登录用户联网单位下的设备信息
	 * @param unitIds
	 * @return
	 */
	List<DmDeviceVO> getDeviceInfoBigData(DmDevice dmDevice,List<String> unitIds) throws Exception;

	/**
	 * 根据设备编号 获取最新一条实时数据
	 * @param dCode
	 * @return
	 */
	String getRealtimingData(String dCode)throws Exception;

	/**
	 * 获取设备关联的视频地址
	 * @param unitIds
	 * @return
	 */
	List<Map<String,String>> getVideoAddressByDeviceId(String devId) throws Exception;

	/**
	 * 获取设备信息根据产品类别
	 * @param typeId
	 * @return
	 */
	List<DmDeviceVO> getDeviceInfoByType(String typeId,List<String> unitIds) throws Exception;

	/**
	   *  获取电器火灾的实时数据根据设备编码
	 * @param devCode
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> getElectricalFireByDevCode(DmDevice dm) throws Exception;

	/**
	  *    获取access token
	 * @return
	 */
	String getAccesstoken();

	/**
	 * 根据联网单位id,查询设备的总数量
	 * @param unitId
	 * @return
	 */
	Integer getTotalCountByUnitId(String unitId);
	/**
	 * 根据联网单位id,查询完好设备数量
	 * @param unitId
	 * @return
	 */
	Integer getIntactCountByUnitId(String unitId);

	/**
	 * 统计联网单位下设备的在线、离线数量
	 * @param unitIds
	 * @return
	 */
	List<Map> findDeviceStatusGroup(@Param("unitIds")List<String> unitIds);


	/**
	 * 设备故障、真实火情（近一周）、离线统计
	 * @param unitIds
	 * @return
	 */
	List<Map> findDeviceAlarmStatus(@Param("unitIds")List<String> unitIds);


	/**
	 * 查询设备故障、离线的设备列表
	 * @param status
	 * @param unitIds
	 * @return
	 */
	List<DmDeviceVO> findDeviceByStatus(String alarmType,String status,List<String> unitIds) throws Exception;

	/**
	 * 查询发生火灾信息列表
	 * @param unitIds
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> findDeviceByFireStatus(List<String> unitIds)  throws Exception;

	/**
	 * 查询视频地址
	 * @param unitIds
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> queryVideoAddrByUnitId(List<String> unitIds) throws Exception;
}


