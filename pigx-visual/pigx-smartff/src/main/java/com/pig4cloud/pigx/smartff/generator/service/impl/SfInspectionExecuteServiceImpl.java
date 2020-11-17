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
package com.pig4cloud.pigx.smartff.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.smartff.generator.entity.*;
import com.pig4cloud.pigx.smartff.generator.feign.DictFeign;
import com.pig4cloud.pigx.smartff.generator.feign.UserFeign;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionExecuteDetailMapper;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionExecuteMapper;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionExecuteService;
import com.pig4cloud.pigx.smartff.generator.vo.SfInspectionResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
@Service
@AllArgsConstructor
@Slf4j
public class SfInspectionExecuteServiceImpl extends ServiceImpl<SfInspectionExecuteMapper, SfInspectionExecute> implements SfInspectionExecuteService {

    private final SfInspectionExecuteMapper sfInspectionExecuteMapper;

    private final SfInspectionExecuteDetailMapper sfInspectionExecuteDetailMapper;

    @Autowired
    private DictFeign dictFeign;

    @Autowired
    private UserFeign userFeign;

    @Override
    public SfInspectionExecute selectByTaskId(SfInspectionExecute sfInspectionExecute) {
        return sfInspectionExecuteMapper.selectByTaskId(sfInspectionExecute);
    }

    @Override
    public List<SysUser> selectByTaskIdToPersion(SfInspectionExecute sfInspectionExecute) {
        SfInspectionExecute sfInspectionExecute1 = sfInspectionExecuteMapper.selectByTaskId(sfInspectionExecute);
        String persions = null;
        String[] userIds = null;
        try {
            persions = sfInspectionExecute1.getPersons();
            userIds = persions.split(",");
        } catch (NullPointerException e) {
            log.info("当前执行任务下的巡检人Id为空");
        }
        List<SysUser> uses = userFeign.getUserByUserIds(userIds);
        return uses;
    }

    @Override
    public List<SfInspectionPoint> selectByTaskIdToPointList(SfInspectionExecute sfInspectionExecute) {
        SfInspectionExecute sfInspectionExecute1 = sfInspectionExecuteMapper.selectByTaskId(sfInspectionExecute);
        String points = sfInspectionExecute1.getTourlist();
        String[] arrPoit = points.split(",");
        List<SfInspectionPoint> sFInspectionPoint = sfInspectionExecuteMapper.selectByTaskIdToPointList(arrPoit);
        return sFInspectionPoint;
    }

    @Override
    public IPage<List<SfInspectionResultVO>> selectByOrgId(Page page, SfInspectionExecute sfInspectionExecute) {
        //根据联网单位（业主单位）ID，或者任务状态查询执行表
        List<SfInspectionResultVO> listVO = sfInspectionExecuteMapper.selectByOrgId(page, sfInspectionExecute);
        List<SfInspectionResultVO> list = new ArrayList<>();
        //获取字典表中的任务状态
        R r = dictFeign.getDictByType("inspection_status");
        Object listStatus = r.getData();
        String s = JSON.toJSONString(listStatus);
        List<SysDictItem> array = JSONArray.parseArray(s,SysDictItem.class);
        Map map = new HashMap();
        for (int i = 0;i < array.size();i++){
            String value = array.get(i).getValue();
            String description = array.get(i).getDescription();
            map.put(value,description);
        }
        //循环执行表中的任务列表
        for (SfInspectionResultVO sfInspectionResultVO : listVO) {
            //将巡检点总数取出
            String str = sfInspectionResultVO.getTourlist();
            String[] arrStr = null;
            Integer pointTotal = 0;
            try {
                arrStr = str.split(",");
                pointTotal = arrStr.length;
            } catch (NullPointerException e) {
                log.info("当前任务中的巡检点Id为空");
            }
            //查询巡检执行详细表中 已巡检过(isInspection = 1) 的巡检点总记录数
            SfInspectionExecuteDetail sfInspectionExecuteDetail = new SfInspectionExecuteDetail();
            sfInspectionExecuteDetail.setIsInspection(1);
            sfInspectionExecuteDetail.setTaskid(sfInspectionResultVO.getTaskid());
            Integer finishCount = sfInspectionExecuteDetailMapper.findByIsInspectionCount(sfInspectionExecuteDetail);
            //将VO展示
            String strStatus = (String)map.get(sfInspectionResultVO.getStatus().toString());
            sfInspectionResultVO.setStatuName(strStatus);
            sfInspectionResultVO.setFinishCount(finishCount);
            sfInspectionResultVO.setPointTotal(pointTotal);
            //计算完成率
            String rate = sfInspectionResultVO.getPercent(finishCount, pointTotal);
            sfInspectionResultVO.setFinishRate(rate);
            list.add(sfInspectionResultVO);
        }
        Page iPage = new Page();
        iPage.setRecords(list);
        iPage.setCurrent(page.getCurrent());
        iPage.setSize(page.getSize());
        iPage.setTotal(list.size());
        iPage.setSearchCount(page.isSearchCount());
        iPage.setPages(page.getPages());
        return iPage;
    }

    @Override
    public List<SfInspectionExecute> findTaskByJobEndTime() {
        return sfInspectionExecuteMapper.findTaskByJobEnd();
    }

    @Override
    public IPage<List<SfInspectionExecute>> findTasktList(Page page, SfInspectionExecute sfInspectionExecute) {
        return sfInspectionExecuteMapper.findTasktList(page,sfInspectionExecute);
    }

}
