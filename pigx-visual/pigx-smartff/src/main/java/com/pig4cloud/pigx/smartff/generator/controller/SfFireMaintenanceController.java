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

package com.pig4cloud.pigx.smartff.generator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.smartff.generator.entity.SfFireMaintenance;
import com.pig4cloud.pigx.smartff.generator.service.SfFireMaintenanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:42:39
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sffiremaintenance" )
@Api(value = "sffiremaintenance", tags = "sffiremaintenance管理")
public class SfFireMaintenanceController {

    private final  SfFireMaintenanceService sfFireMaintenanceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfFireMaintenance 消防维保表
     * @return
     */
    @GetMapping("/page" )
    public R getSfFireMaintenancePage(Page page, SfFireMaintenance sfFireMaintenance) {
        return new R<>(sfFireMaintenanceService.page(page, Wrappers.query(sfFireMaintenance)));
    }


    /**
     * 通过id查询消防维保表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(sfFireMaintenanceService.getById(id));
    }

    /**
     * 新增消防维保表
     * @param sfFireMaintenance 消防维保表
     * @return R
     */
    @SysLog("新增消防维保表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_sffiremaintenance_add')" )
    public R save(@RequestBody SfFireMaintenance sfFireMaintenance) {
        return new R<>(sfFireMaintenanceService.save(sfFireMaintenance));
    }

    /**
     * 修改消防维保表
     * @param sfFireMaintenance 消防维保表
     * @return R
     */
    @SysLog("修改消防维保表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_sffiremaintenance_edit')" )
    public R updateById(@RequestBody SfFireMaintenance sfFireMaintenance) {
        return new R<>(sfFireMaintenanceService.updateById(sfFireMaintenance));
    }

    /**
     * 通过id删除消防维保表
     * @param id id
     * @return R
     */
    @SysLog("删除消防维保表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_sffiremaintenance_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(sfFireMaintenanceService.removeById(id));
    }

}
