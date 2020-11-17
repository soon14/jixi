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

package com.pig4cloud.pigx.api.application.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import com.pig4cloud.pigx.api.application.vo.DmAlarminfoVo;
import com.pig4cloud.pigx.api.application.vo.GroupDataVo;

/**
 * 事件上送数据
 *
 * @author pigx code generator
 * @date 2019-08-08 11:37:42
 */
public interface DmAlarminfoService extends IService<DmAlarminfo> {

	List<DmAlarminfoVo> getDmAlarmRecord(Page page, DmAlarminfoVo vo,List<String> unitIds) throws Exception;

	DmAlarminfoVo getDmAlarmDetail(String alarmId) throws Exception;

	boolean alarmHandle(DmAlarminfo info) throws Exception;

	boolean updateAlarmStatus(DmAlarminfo info) throws Exception;

	boolean saveDmAlarminfo(DmAlarminfo info) throws Exception;

	DmAlarminfo selectLastAlarm(String devCode) throws Exception;

	/**
	 * App本周消防报警统计
	 * @param dmAlarminfo
	 * @return
	 */
	List<GroupDataVo> selectGraphStatistics(DmAlarminfo dmAlarminfo) throws Exception;

	List<DmAlarminfoVo> getAlarmInfoByEvent(String eventType,Integer deviceStatus,List<String> unitIds) throws Exception;

	/**
	 *报警故障信息周统计
	 * @return
	 */
	List<DmAlarminfoVo> findAlarmInfoWeek(DmAlarminfo DmAlarminfo) throws Exception;

	List<DmAlarminfoVo> findAlarmInfoByTime(DmAlarminfo DmAlarminfo,List<String> unitIds) throws Exception;

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

    List<Map> selectUnitAlarmDeviceStatistics(List<String> unitIds) throws Exception;

	List<Map> selectDeviceAlarmTrendStatistics(List<String> unitIds) throws Exception;
}
