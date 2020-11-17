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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.smartff.generator.mapper.SfInspectionExecuteDetailMapper;
import com.pig4cloud.pigx.smartff.generator.service.SfInspectionExecuteDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 巡检执行情况详细表
 *
 * @author zm
 * @date 2019-08-11 10:30:36
 */
@Service
@AllArgsConstructor
public class SfInspectionExecuteDetailServiceImpl extends ServiceImpl<SfInspectionExecuteDetailMapper, SfInspectionExecuteDetail> implements SfInspectionExecuteDetailService {

    private final SfInspectionExecuteDetailMapper sFinspectionExcuteDetailMapper;

    // private final SfInspectionExecuteMapper sfInspectionExecuteMapper;

    @Override
    @Transactional
    public String updateForCheck(SfInspectionExecuteDetail sfInspectionExecuteDetail , MultipartFile[] files) {
        /**
         * 以下逻辑--->暂时<----去除
         */
        // String taskId = sfInspectionExecuteDetail.getTaskid();
        // SfInspectionExecute sfInspectionExecute = new SfInspectionExecute();
        // sfInspectionExecute.setTaskid(taskId);
        // //从执行表中将单条任务查出来
        // SfInspectionExecute sfInspectionExecute1 = sfInspectionExecuteMapper.selectByTaskId(sfInspectionExecute);
        // //取出任务的结束时间
        // LocalDateTime endTime = sfInspectionExecute1.getEndTime();
        // LocalDateTime nowTime = LocalDateTime.now();
        //和当前系统时间（巡检时间）作比较
        // if(endTime.isBefore(nowTime)){
        //     //修改执行表中的巡检任务状态为"3-已逾期"
        //     SfInspectionExecute sfInspectionExecute2 = new SfInspectionExecute();
        //     sfInspectionExecute2.setId(sfInspectionExecute1.getId());
        //     sfInspectionExecute2.setStatus(3);
        //     sfInspectionExecuteMapper.updateById(sfInspectionExecute2);
        // }
            String str = "";
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename();
                File dest = new File("D:\\workidea\\fireServer\\pigx\\pigx-visual\\pigx-smartff\\" +
                        "src\\main\\resources\\static\\imgs" + "/" + fileName);
                str += dest.getName()+",";
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest); // 保存文件
                }catch (FileAlreadyExistsException exception){
                    return "图片重复请更换！";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "上传失败！请联系管理员";
                }
            }
            if(str.endsWith(",")){
                str = str.substring(0, str.length() - 1);
                System.out.println( str.substring(0, str.length() - 1));
            }
            sfInspectionExecuteDetail.setPointImageId(str);
            sfInspectionExecuteDetail.setIsInspection(1);
            sfInspectionExecuteDetail.setPointTime(LocalDateTime.now());
            int result = sFinspectionExcuteDetailMapper.updateForCheck(sfInspectionExecuteDetail);
            if (result > 0) {
                return JSON.toJSONString("true");
            } else {
                return JSON.toJSONString("false");
            }
    }

    @Override
    public SfInspectionExecuteDetail selectLastModifiExecuteDetail(SfInspectionExecuteDetail sfInspectionExecuteDetail) {
        return sFinspectionExcuteDetailMapper.selectLastModifiExecuteDetail(sfInspectionExecuteDetail);
    }

    /**
     * 根据任务id和巡检状态查询已巡检的巡检点总数
     * @param sfInspectionExecuteDetail
     * @return
     */
    @Override
    public Integer findByIsInspectionCount(SfInspectionExecuteDetail sfInspectionExecuteDetail){
       return sFinspectionExcuteDetailMapper.findByIsInspectionCount(sfInspectionExecuteDetail);
    }
}
