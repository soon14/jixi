package com.pig4cloud.pigx.device.generator.feign;

import com.pig4cloud.pigx.device.generator.entity.DmAlarminfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "alarmFeign",value = "pigx-api")
@Service
public interface AlarmFeign {


    @GetMapping("/dmalarminfo/selectLastAlarm")
    DmAlarminfo selectLastAlarm(@RequestParam("devCode") String devCode);



    /**
     * 获取当前登录用户下的unitIds,用于数据权限控制
     *
     * @return
     */
    @GetMapping("/user/getUserLoingUnitIds" )
    List<String> getUserLoingUnitIds();
}
