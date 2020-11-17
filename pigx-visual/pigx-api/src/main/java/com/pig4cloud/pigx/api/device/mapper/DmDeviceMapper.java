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

package com.pig4cloud.pigx.api.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface DmDeviceMapper extends BaseMapper<DmDevice> {

	/**
	 * 查询设备列表
	 *
	 * @param page
	 * @param dmProduct
	 * @return
	 */
	List<DmDeviceVO> getDeviceList(Page page, @Param("query") DmDevice dmDevice,@Param("unitIds")List<String> unitIds)  throws Exception;

	/**
	 * 查询不属于该分组的设备信息
	 *
	 * @param page
	 * @param groupId
	 * @return
	 */
	IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, @Param("query") DmDeviceGroupVO vo)  throws Exception;

	/**
	 * 查询属于该分组的设备信息
	 *
	 * @param page
	 * @param groupId
	 * @return
	 */
	IPage<List<DmDeviceVO>> getGroupDevice(Page page, @Param("query") DmDeviceGroupVO vo)  throws Exception;

	List<String> getDeviceCodeList()  throws Exception;

	/**
	 * 插入数据到设备视频关系表
	 *
	 * @param devId
	 * @param videoId
	 * @return
	 */
	boolean insertDeviceVideo(@Param("devId") String devId, @Param("videoId") String videoId)  throws Exception;

	/**
	 * 获取视频设备的select
	 *
	 * @param dmDevice
	 * @return
	 */
	List<DmDevice> selectVideoDevice(@Param("query") DmDevice dmDevice)  throws Exception;

	/**
	 * 获取联网单位下的所有设备
	 *
	 * @param unitId
	 * @return
	 */
	IPage<List<DmDevice>> getDmDeviceByUnitIds(Page page,@Param("unitIds") List<String> unitIds)  throws Exception;

    /**
     * 设备查询（默认根据产品类别或联网单位（支持模糊查））
     *
     * @param page
     * @param dmDevice
     * @return
     */
    IPage<List<DmDeviceVO>> findDeviceList(Page page, @Param("query") DmDevice dmDevice)  throws Exception;
    /**
                *  根据联网单位Id查询设备位置信息
     * @param unitId
     * @return
     */
    List<DmDevice> getDevicePostionByUnitId(@Param("unitId")String unitId,@Param("buildId")String buildId,@Param("countyId")String countyId)  throws Exception;

    /**
                * 查询设备信息列表
     *
     * @param page
     * @param dmDevice
     * @return
     */
    IPage<List<DmDevice>> getDeviceInfo(Page page, @Param("query") DmDeviceDto dmDevice,@Param("unitIds") List<String> unitIds)  throws Exception;

    /**
	    * 根据设备类型查询设备数量
	 * @param typeId
	 * @return
	 */
    Integer queryDeviceCountByType(@Param("query") DmDevice dmDevice,@Param("unitIds") List<String> unitIds)  throws Exception;

    /**
                 * 根据设备id查询设备详情
     * @param devId
     * @return
     */
    DmDeviceVO getDeviceInfoById(@Param("devId")String devId) throws Exception;

    /**
                * 根据设备编码查询设备信息
     * @param devCode
     * @return
     */
    DmDeviceVO getDeviceInfoByCode(@Param("devCode")String devCode) throws Exception;
    /**
                * 查询设备信息根据联网单位、建筑、楼层、位置
     *
     * @param dmDevice
     * @return
     */
    List<DmDeviceVO> getDeviceInfoByCons(@Param("query") DmDevice dmDevice)  throws Exception;

    /**
     * 获取当前登录用户联网单位下的设备信息
     * @return
     */
    List<DmDeviceVO> getDeviceInfoBigData(@Param("query")DmDevice dmDevice,@Param("unitIds")List<String> unitIds)  throws Exception;

	/**
	 * 根据设备编号 获取最新一条实时数据
	 * @param dCode
	 * @return
	 */
	String getRealtimingData(@Param("dCode")String dCode)throws Exception;

    /**
     * 获取设备关联的视频地址
     * @param devId
     * @return
     */
    List<Map<String,String>> getVideoAddressByDeviceId(@Param("devId")String devId)  throws Exception;

    /**
     * 根据设备编码获取设备信息
     * @param devCode
     * @return
     */
    DmDeviceVO getDeviceInfoByDeviceCode(@Param("devCode")String devCode)  throws Exception;

    /**
     * 获取设备信息根据产品类别
     * @param typeId
     * @param unitIds
     * @return
     */
    List<DmDeviceVO> getDeviceInfoByType(@Param("typeId")String typeId,@Param("unitIds")List<String> unitIds)  throws Exception;

    /**
     * 查询联网单位下的设备总数量
     * @param unitId
     * @return
     */
    Integer getTotalCountByUnitId(String unitId);

    /**
     * 查询联网单位下完好设备的数量
     * @param unitId
     * @return
     */
    Integer getIntactCountByUnitId(String unitId);


	void updateDeviceByCode(DmDevice dmDevice);

	/**
	 * 统计联网单位下设备的在线、离线数量
	 * @param unitIds
	 * @return
	 */
	List<Map> findDeviceStatusGroup(@Param("unitIds")List<String> unitIds);

	/**
	 * 设备故障、真是火情（近一周）、离线统计
	 * @param unitIds
	 * @return
	 */
	List<Map> findDeviceAlarmStatus(@Param("unitIds")List<String> unitIds);

	/**
	 * 查询设备故障、离线的设备列表
	 * @param status
	 * @param unitIds
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> findDeviceByStatus(@Param("alarmType") String alarmType ,
										@Param("status")String status,@Param("unitIds")List<String> unitIds)  throws Exception;


	/**
	 * 查询发生火灾信息列表
	 * @param unitIds
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> findDeviceByFireStatus(@Param("unitIds")List<String> unitIds)  throws Exception;

	/**
	 * 查询视频地址
	 * @param unitIds
	 * @return
	 * @throws Exception
	 */
	List<DmDeviceVO> queryVideoAddrByUnitId(@Param("unitIds")List<String> unitIds) throws Exception;
}
