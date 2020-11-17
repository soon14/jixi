package com.pig4cloud.pigx.daemon.elastic.feign;


import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionExecute;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.daemon.elastic.entity.SfInspectionTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zm
 * @desc巡检任务
 * @time 2019-08-09 15:07:39
 */
@FeignClient(contextId = "inspectionTaskFeign",value = "pigx-smartff")
@Service
public interface InspectionTaskFeign {

    /**
     * 从巡检任务表执行数据到巡检执行表和巡检执行详细表中
     * @param sfInspectionTask
     * @return R
     */
    @PostMapping("/sfinspectiontask/insertRelation")
    R save(@RequestBody SfInspectionTask sfInspectionTask);


    /**
     * 关联巡检执行表查询当月巡检任务List
     *
     * @return R
     */
    @GetMapping("/sfinspectiontask/list")
    List<SfInspectionTask> findTaskByData();




    @GetMapping("/sfinspectionexecute/findExcuteByEndTime")
    List<SfInspectionExecute> findTaskByJobEndTime();


    /**
     * 查询当月结束时间小于当前系统时间的记录
     */
    @GetMapping("/sfinspectionexecutedetail/findLastModifi" )
    SfInspectionExecuteDetail selectLastModifiExecuteDetail(@Param("sfInspectionExecuteDetail")
                                                            SfInspectionExecuteDetail sfInspectionExecuteDetail);


    /**
     * 根据任务id和巡检状态查询已巡检的巡检点总数
     * sfInspectionExecuteDetail
     */
    @GetMapping("/sfinspectionexecutedetail/findByIsInspectionCount" )
    public Integer findByIsInspectionCount(@Param("sfInspectionExecuteDetail") SfInspectionExecuteDetail sfInspectionExecuteDetail);


    /**
     * 修改巡检执行表
     * @param sfInspectionExecute 巡检执行表
     * @return R
     */
    @PutMapping("/sfinspectionexecute/updateByIdForJob")
    public R updateByIdForJob(@RequestBody SfInspectionExecute sfInspectionExecute);
}
