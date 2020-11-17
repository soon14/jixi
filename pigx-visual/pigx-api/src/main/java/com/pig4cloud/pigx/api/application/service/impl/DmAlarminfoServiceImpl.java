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
package com.pig4cloud.pigx.api.application.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import com.pig4cloud.pigx.api.application.mapper.DmAlarminfoMapper;
import com.pig4cloud.pigx.api.application.service.DmAlarminfoService;
import com.pig4cloud.pigx.api.application.vo.DmAlarminfoVo;
import com.pig4cloud.pigx.api.application.vo.GroupDataVo;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;

import lombok.AllArgsConstructor;

import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 事件上送数据
 *
 * @author pigx code generator
 * @date 2019-08-08 11:37:42
 */
@Service
@AllArgsConstructor
public class DmAlarminfoServiceImpl extends ServiceImpl<DmAlarminfoMapper, DmAlarminfo> implements DmAlarminfoService {

    private final DmAlarminfoMapper dmAlarminfoMapper;

    @Override
    public List<DmAlarminfoVo> getDmAlarmRecord(Page page, DmAlarminfoVo vo, List<String> unitIds) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.getDmAlarmRecord(page, vo, unitIds);
    }

    @Override
    public DmAlarminfoVo getDmAlarmDetail(String alarmId) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.getDmAlarmDetail(alarmId);
    }

    @Override
    public boolean alarmHandle(DmAlarminfo info) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        info.setHandlePerson(SecurityUtils.getUser().getId());
        info.setHandleTime(LocalDateTime.now());
        if (Constant.HANDLE_REUSLT_CLEAR_FAULT.equals(info.getHandleResult())
                || Constant.HANDLE_REUSLT_NORMAL.equals(info.getHandleResult())) {
            info.setAlarmTypeId(Constant.ALARM_TYPE_NORMAL);
            dmAlarminfoMapper.updateDevice(info);
        }
        info.setDeviceStatus(Constant.HANDLE_STATUS_YES);
        return dmAlarminfoMapper.alarmHandle(info);
    }

    @Override
    public boolean updateAlarmStatus(DmAlarminfo info) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.updateAlarmStatus(info);
    }

    @Override
    public boolean saveDmAlarminfo(DmAlarminfo info)  throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return save(info);
    }

    @Override
    public DmAlarminfo selectLastAlarm(String devCode) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.selectLastAlarm(devCode);
    }

    @Override
	public List<DmAlarminfoVo> getAlarmInfoByEvent(String eventType,Integer deviceStatus,List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmAlarminfoMapper.getAlarmInfoByEvent(eventType,deviceStatus,unitIds);
	}

    @Override
    public List<DmAlarminfoVo> findAlarmInfoWeek(DmAlarminfo DmAlarminfo) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.findAlarmInfoWeek(DmAlarminfo);
    }

    @Override
    public List<DmAlarminfoVo> findAlarmInfoByTime(DmAlarminfo DmAlarminfo, List<String> unitIds) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.findAlarmInfoByTime(DmAlarminfo,unitIds);
    }

    @Override
    public List<Map> tJAlarmInfo(DmAlarminfo dmAlarminfo) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.tJAlarmInfo(dmAlarminfo);
    }

    @Override
    public List<Map> findAlarmInfoCountByTime(DmAlarminfo dmAlarminfo) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.findAlarmInfoCountByTime(dmAlarminfo);
    }

    @Override
    public List<Map> selectUnitAlarmDeviceStatistics(List<String> unitIds) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.selectUnitAlarmDeviceStatistics(unitIds);
    }

    @Override
    public List<Map> selectDeviceAlarmTrendStatistics(List<String> unitIds) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        return dmAlarminfoMapper.selectDeviceAlarmTrendStatistics(unitIds);
    }

    @Override
    public List<GroupDataVo> selectGraphStatistics(DmAlarminfo dmAlarminfo) throws Exception{
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
        List<DmAlarminfoVo> listData = dmAlarminfoMapper.selectGraphStatistics(dmAlarminfo);
        List<GroupDataVo> groupDataVoList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            GroupDataVo groupDataVo = new GroupDataVo();
            // 已处理告警条数
            Integer processedAlarmTotal = 0;
            //未处理告警条数
            Integer untreatedAlarmTotal = 0;
            //已处理故障条数
            Integer processedFaultTotal = 0;
            //未处理故障条数
            Integer untreatedFaultTotal = 0;
            for (DmAlarminfoVo dmAlarminfoVo : listData) {
                int productTypeId = Integer.parseInt(dmAlarminfoVo.getTypeId());
                if (productTypeId == i) {
                    //未处理
                    if (dmAlarminfoVo.getDeviceStatus() == 0) {
                        //告警
                        if (dmAlarminfoVo.getAlarmTypeId() == 2) {
                            untreatedAlarmTotal++;
                            //故障
                        } else if (dmAlarminfoVo.getAlarmTypeId() == 3) {
                            untreatedFaultTotal++;
                        }
                     //已处理
                    } else {
                        //告警
                        if (dmAlarminfoVo.getAlarmTypeId() == 2) {
                            processedAlarmTotal++;
                            //故障
                        } else if (dmAlarminfoVo.getAlarmTypeId() == 3) {
                            processedFaultTotal++;
                        }
                    }
                }
            }
            groupDataVo.setProcessedAlarmTotal(processedAlarmTotal);
            groupDataVo.setProcessedFaultTotal(processedFaultTotal);
            groupDataVo.setUntreatedAlarmTotal(untreatedAlarmTotal);
            groupDataVo.setUntreatedFaultTotal(untreatedFaultTotal);
            groupDataVo.setProductType(i);
            groupDataVoList.add(groupDataVo);
        }
        return groupDataVoList;
    }

}
