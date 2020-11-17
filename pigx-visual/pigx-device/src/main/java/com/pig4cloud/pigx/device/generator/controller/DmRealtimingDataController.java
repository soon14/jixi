package com.pig4cloud.pigx.device.generator.controller;


import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.device.generator.entity.DmRealtimingData;
import com.pig4cloud.pigx.device.generator.service.DmRealtimingDataService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 实时数据表
 *
 * @author zm
 * @data 2019-07-24 15:50:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/realtimingdata")
@Api(value = "realtiming", tags = "realtiming管理")
public class DmRealtimingDataController {

    private final DmRealtimingDataService dmRealtimingDataService;

    /**
     * 新增实时数据表
     *
     * @param dmRealtimingData 实时数据表
     * @return R
     */
    @SysLog("新增实时数据")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_realtimingData_add')")
    public R save(@Valid @RequestBody DmRealtimingData dmRealtimingData) {
        return new R<>(dmRealtimingDataService.save(dmRealtimingData));
    }

    @GetMapping("/{deviceCode}")
    public R findOne(@PathVariable("deviceCode") String deviceCode) {
        return new R<>(dmRealtimingDataService.selectLast(deviceCode));
    }


}
