package com.pig4cloud.pigx.daemon.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionExecute;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.daemon.elastic.feign.InspectionTaskFeign;
import com.pig4cloud.pigx.daemon.elastic.vo.SfInspectionResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zm
 * @date 2019/8/9
 * 巡检定时任务（结束）
 */
@Slf4j
public class InspectionSimpleJobEnd implements SimpleJob {

    @Autowired
    private InspectionTaskFeign inspectionTaskFeign;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("zm创建的elasticJob----End成功啦:{}", shardingContext.getJobName());
        List<SfInspectionExecute> list = inspectionTaskFeign.findTaskByJobEndTime();
        //循环执行表中的任务列表
        for (SfInspectionExecute sfInspectionExecute : list) {
            //将执行任务中的结束时间取出
            LocalDateTime endTime = sfInspectionExecute.getEndTime();
            //将巡检点总数取出
            String str = null;
            String[] arrStr = null;
            try {
                str = sfInspectionExecute.getTourlist();
                arrStr = str.split(",");
            } catch (NullPointerException e) {
                log.info("当前巡检执行任务中的巡检点Id为空");
            }
            Integer pointTotal = arrStr.length;
            //查询巡检执行详细表中 已巡检过(isInspection = 1) 的巡检点总记录数
            SfInspectionExecuteDetail sfInspectionExecuteDetail = new SfInspectionExecuteDetail();
            sfInspectionExecuteDetail.setIsInspection(1);
            sfInspectionExecuteDetail.setTaskid(sfInspectionExecute.getTaskid());
            Integer finishCount = inspectionTaskFeign.findByIsInspectionCount(sfInspectionExecuteDetail);
            //系统当前时间
            LocalDateTime nowTime = LocalDateTime.now();
            SfInspectionExecute sfInspectionExecute1 = new SfInspectionExecute();
            //超时
            if (endTime.isBefore(nowTime)) {
                if (finishCount != pointTotal) {
                    //修改执行表中的巡检任务状态为"4-逾期未完成"
                    sfInspectionExecute1.setId(sfInspectionExecute.getId());
                    sfInspectionExecute1.setStatus(4);
                    inspectionTaskFeign.updateByIdForJob(sfInspectionExecute1);
                } else {
                    //根据任务Id查询本次任务中最后巡检的一条巡检点数据接口
                    SfInspectionExecuteDetail sfInspectionExecuteDetail2 = new SfInspectionExecuteDetail();
                    sfInspectionExecuteDetail2.setTaskid(sfInspectionExecute.getTaskid());
                    SfInspectionExecuteDetail lastExecuteDetail =
                            inspectionTaskFeign.selectLastModifiExecuteDetail(sfInspectionExecuteDetail2);
                    //取出最后巡检的巡检点时间
                    LocalDateTime lastModifiTime = lastExecuteDetail.getPointTime();
                    if (lastModifiTime.isBefore(endTime)) {
                        //修改执行表中的巡检任务状态为"2-已完成"
                        sfInspectionExecute1.setId(sfInspectionExecute.getId());
                        sfInspectionExecute1.setStatus(2);
                        inspectionTaskFeign.updateByIdForJob(sfInspectionExecute1);
                    } else {
                        //修改执行表中的巡检任务状态为"3-逾期完成"
                        sfInspectionExecute1.setId(sfInspectionExecute.getId());
                        sfInspectionExecute1.setStatus(3);
                        inspectionTaskFeign.updateByIdForJob(sfInspectionExecute1);
                    }

                }
            } else {
                if (finishCount == pointTotal) {
                    //修改执行表中的巡检任务状态为"2-已完成"
                    sfInspectionExecute1.setId(sfInspectionExecute.getId());
                    sfInspectionExecute1.setStatus(2);
                    inspectionTaskFeign.updateByIdForJob(sfInspectionExecute1);
                }
            }
        }
    }
}
