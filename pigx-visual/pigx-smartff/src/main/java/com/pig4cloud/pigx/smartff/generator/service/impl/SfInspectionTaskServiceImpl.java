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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecute;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionTask;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionExecuteDetailMapper;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionExecuteMapper;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionTaskMapper;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionTaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 巡检任务表
 *
 * @author zm
 * @date 2019-08-10 16:42:53
 */
@Service
@AllArgsConstructor
@Slf4j
public class SfInspectionTaskServiceImpl extends ServiceImpl<SfInspectionTaskMapper, SfInspectionTask> implements SfInspectionTaskService {

    private final SfInspectionTaskMapper sfInspectionTaskMapper;

    private final SfInspectionExecuteMapper sfInspectionExecuteMapper;

    private final SfInspectionExecuteDetailMapper sfInspectionExecuteDetailMapper;


    @Override
    public IPage<List<SfInspectionTask>> findByTaskNameList(Page page, SfInspectionTask sfInspectionTask) {
        return sfInspectionTaskMapper.findByTaskNameList(page, sfInspectionTask);
    }

    @Override
    public List<SfInspectionTask> findTaskByJobData() {
        return sfInspectionTaskMapper.findTaskByJob();
    }


    /**
     * 巡检任务配发接口,这里处理临时配发的逻辑(重写父类的修改方法)
     *
     * @param sfInspectionTask
     * @return
     */
    public boolean updateById(SfInspectionTask sfInspectionTask) {
        //配置巡检人到巡检任务表
        boolean flag = retBool(baseMapper.updateById(sfInspectionTask));
        //查询配置成功后的巡检任务表
        SfInspectionTask sfInspectionTask1 = baseMapper.selectById(sfInspectionTask.getId());
        Integer taskType = sfInspectionTask1.getTasktype();
        //任务为临时配发时：
        if (flag && taskType == 1) {
            insertTask(sfInspectionTask1);
        }
        return flag;
    }

    /**
     * 从巡检任务表执行数据到巡检执行表和巡检执行详细表中
     *
     * @param sfInspectionTask1
     */
    public Integer insertTask(SfInspectionTask sfInspectionTask1) {
        int statu = 1;
        SfInspectionExecute sfInspectionExecute = new SfInspectionExecute();
        sfInspectionExecute.setTaskid(sfInspectionTask1.getId());
        sfInspectionExecute.setTaskname(sfInspectionTask1.getTaskname());
        sfInspectionExecute.setOrgId(sfInspectionTask1.getOrgId());
        sfInspectionExecute.setOrgName(sfInspectionTask1.getOrgName());
        sfInspectionExecute.setBuildid(sfInspectionTask1.getBuildid());
        sfInspectionExecute.setTourBuildName(sfInspectionTask1.getBuildName());
        sfInspectionExecute.setTourlist(sfInspectionTask1.getTourlist());
        sfInspectionExecute.setPersons(sfInspectionTask1.getPersons());
        sfInspectionExecute.setBeginTime(sfInspectionTask1.getBeginTime());
        sfInspectionExecute.setEndTime(sfInspectionTask1.getEndTime());
        //任务状态为进行中
        sfInspectionExecute.setStatus(1);
        sfInspectionExecute.setCreateUserId(sfInspectionTask1.getCreateUserId());
        sfInspectionExecute.setCreateUserName(sfInspectionTask1.getCreateUserName());
        sfInspectionExecute.setCreatetime(sfInspectionTask1.getCreatetime());
        sfInspectionExecute.setRemark(sfInspectionTask1.getRemark());
        //插入巡检执行表
        int flag = sfInspectionExecuteMapper.insert(sfInspectionExecute);
        String id = sfInspectionExecute.getId();
        if (flag > 0) {
            //查询插入成功后的一条数据（巡检执行表）
            SfInspectionExecute sfInspectionExecute1 = new SfInspectionExecute();
            sfInspectionExecute1.setId(id);
            SfInspectionExecute excute = sfInspectionExecuteMapper.selectByTaskId(sfInspectionExecute1);
            //取出巡检点集合
            String str = null;
            String[] strArr = null;
            try {
                str = excute.getTourlist();
                strArr = str.split(",");
            } catch (NullPointerException e) {
                log.info("当前任务表中的巡检点Id为空");
            }
            List<SfInspectionPoint> listPoint = sfInspectionExecuteMapper.selectByTaskIdToPointList(strArr);
            // List<SfInspectionExecuteDetail> listDetail = new ArrayList<>();
            for (SfInspectionPoint sfInspectionPoint : listPoint) {
                SfInspectionExecuteDetail sfInspectionExecuteDetail = new SfInspectionExecuteDetail();
                sfInspectionExecuteDetail.setTaskid(excute.getTaskid());
                sfInspectionExecuteDetail.setExecuteid(excute.getId());
                sfInspectionExecuteDetail.setPointId(sfInspectionPoint.getId());
                sfInspectionExecuteDetail.setPointName(sfInspectionPoint.getPointName());
                sfInspectionExecuteDetail.setPointBuildId(sfInspectionPoint.getPointBuildId());
                sfInspectionExecuteDetail.setPointBuildName(sfInspectionPoint.getPointBuildName());
                sfInspectionExecuteDetail.setPointRegionId(sfInspectionPoint.getPointRegionId());
                sfInspectionExecuteDetail.setPointRegionName(sfInspectionPoint.getPointRegionName());
                sfInspectionExecuteDetail.setDevicetype(sfInspectionPoint.getDevicetype());
                sfInspectionExecuteDetail.setDevicenum(sfInspectionPoint.getDevicenum());
                sfInspectionExecuteDetail.setDeviceaddress(sfInspectionPoint.getDeviceaddress());
                sfInspectionExecuteDetail.setOrgId(sfInspectionPoint.getOrgId());
                sfInspectionExecuteDetail.setOrgName(sfInspectionPoint.getOrgName());
                sfInspectionExecuteDetail.setPointFloor(sfInspectionPoint.getPointFloor());
                //默认为0，未巡检
                // listDetail.add(sfInspectionExecuteDetail);
                try {
                    sfInspectionExecuteDetailMapper.insert(sfInspectionExecuteDetail);
                } catch (Exception e) {
                    System.out.println("类名：SfInspectionTaskServiceImpl******************循环插入执行情况详细表异常*******************");
                    statu = -9999;
                }
            }
        }
        return statu;
    }

    @Override
    public IPage<List<SfInspectionTask>> findTasktList(Page page, SfInspectionTask sfInspectionTask) {
        return sfInspectionTaskMapper.findTasktList(page,sfInspectionTask);
    }

}
