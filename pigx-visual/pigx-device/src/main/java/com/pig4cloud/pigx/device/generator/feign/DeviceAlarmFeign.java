package com.pig4cloud.pigx.device.generator.feign;


import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.device.generator.entity.DmAlarminfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(contextId = "deviceAlarmFeign",value = "pigx-api")
@Service
public interface DeviceAlarmFeign {

    /**
     * 非web访问 @RequestHeader(SecurityConstants.FROM) String from
     * 调用方添加参数 SecurityConstants.FROM_IN
     * @param dmAlarminfo
     * @param from
     * @return
     */
    @PostMapping("/dmalarminfo/save")
    R save(@RequestBody DmAlarminfo dmAlarminfo,@RequestHeader(SecurityConstants.FROM) String from);

    @GetMapping("/bsnetworkingunit/getUnitInfoById")
    Map<String,String> getUnitInfoById(@RequestParam("unitId") String unitId);
    
    @PostMapping("/dmalarminfo/pushAlarmInfo")
    R pushAlarmInfo(@RequestBody DmAlarminfo dmAlarminfo,@RequestHeader(SecurityConstants.FROM) String from);

}
