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

package com.pig4cloud.pigx.api.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import com.pig4cloud.pigx.api.application.vo.DmAlarminfoVo;

/**
 * 事件上送数据
 *
 * @author pigx code generator
 * @date 2019-08-08 11:37:42
 */
public interface DmAlarminfoMapper extends BaseMapper<DmAlarminfo> {


	List<DmAlarminfoVo> getDmAlarmRecord(Page page, @Param("query") DmAlarminfoVo vo,@Param("unitIds") List<String> unitIds) throws Exception;

	DmAlarminfoVo getDmAlarmDetail(@Param("alarmId") String alarmId) throws Exception;

	boolean alarmHandle(@Param("save")DmAlarminfo info) throws Exception;

	boolean updateDevice(@Param("save")DmAlarminfo info) throws Exception;

	boolean updateAlarmStatus(@Param("save")DmAlarminfo info) throws Exception;

	DmAlarminfo selectLastAlarm(String devCode) throws Exception;

	List<DmAlarminfoVo> getAlarmInfoByEvent(@Param("eventType")String eventType,@Param("deviceStatus")Integer deviceStatus,@Param("unitIds") List<String> unitIds) throws Exception;

	/**
	 * App本周消防报警统计
	 * @param dmAlarminfo
	 * @return
	 */
	List<DmAlarminfoVo> selectGraphStatistics(DmAlarminfo dmAlarminfo) throws Exception;

	/**
	 *报警故障信息周统计
	 * @return
	 */
	List<DmAlarminfoVo> findAlarmInfoWeek(DmAlarminfo DmAlarminfo) throws Exception;

	List<DmAlarminfoVo> findAlarmInfoByTime(@Param("query")DmAlarminfo info,@Param("unitIds")List<String> unitIds) throws Exception;

	/**
	 * 报警情况周统计
	 * @param dmAlarminfo
	 * @return
	 */
	List<Map> tJAlarmInfo(DmAlarminfo dmAlarminfo) throws Exception;

	/**
	 * 报警处理状态数量统计
	 * @param dmAlarminfo
	 * @return
	 * @throws Exception
	 */
	List<Map> findAlarmInfoCountByTime(DmAlarminfo dmAlarminfo) throws Exception;

	List<Map> selectUnitAlarmDeviceStatistics(@Param("unitIds") List<String> unitIds) throws Exception;

	List<Map> selectDeviceAlarmTrendStatistics(@Param("unitIds")List<String> unitIds) throws Exception;
}
