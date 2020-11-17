package com.pig4cloud.pigx.daemon.elastic.job;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionTask;
import com.pig4cloud.pigx.daemon.elastic.feign.InspectionTaskFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zm
 * @date 2019/8/9
 * 巡检定时任务（开始）
 */
@Slf4j
public class InspectionSimpleJobStart implements SimpleJob {


    @Autowired
    private InspectionTaskFeign inspectionTaskFeign;


    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("zm创建的elasticJob----Start执行啦:{}", shardingContext.getJobName());
        List<SfInspectionTask> list = inspectionTaskFeign.findTaskByData();
        for (SfInspectionTask sfInspectionTask : list) {
            log.info("*************任务开始执行*************:{}",list.size() );
            //巡检任务开始执行时间
            LocalDateTime beginTime = sfInspectionTask.getBeginTime();
            //系统当前时间
            LocalDateTime nowTime = LocalDateTime.now();
            //转换格式
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //截取时间
            String srtBeginTime = df.format(beginTime).split(" ")[0];
            String strNowTime = df.format(nowTime).split(" ")[0];
            if (strNowTime.equals(srtBeginTime)) {
                log.info("开始生成巡检执行任务了:{}", shardingContext.getJobName());
                inspectionTaskFeign.save(sfInspectionTask);
                log.info("生成巡检执行任务的时间:{}", df.format(nowTime));
            }
        }


    }
}
