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

package com.pig4cloud.pigx.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.admin.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsnetworkingunit" )
@Api(value = "bsnetworkingunit", tags = "bsnetworkingunit管理")
public class BsNetworkingUnitController {

    private final  BsNetworkingUnitService bsNetworkingUnitService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bsNetworkingUnit 联网单位表
     * @return
     */
    @GetMapping("/page" )
    public R getBsNetworkingUnitPage(Page page, BsNetworkingUnit bsNetworkingUnit) {
        return new R<>(bsNetworkingUnitService.page(page, Wrappers.query(bsNetworkingUnit)));
    }


    /**
     * 通过id查询联网单位表
     * @param unitId id
     * @return R
     */
    @GetMapping("/{unitId}" )
    public R getById(@PathVariable("unitId" ) String unitId) {
        return new R<>(bsNetworkingUnitService.getById(unitId));
    }

    /**
     * 新增联网单位表
     * @param bsNetworkingUnit 联网单位表
     * @return R
     */
    @SysLog("新增联网单位表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('bs_networkingunit_add')" )
    public R save(@RequestBody BsNetworkingUnit bsNetworkingUnit) {
        return new R<>(bsNetworkingUnitService.save(bsNetworkingUnit));
    }

    /**
     * 修改联网单位表
     * @param bsNetworkingUnit 联网单位表
     * @return R
     */
    @SysLog("修改联网单位表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('bs_networkingunit_edit')" )
    public R updateById(@RequestBody BsNetworkingUnit bsNetworkingUnit) {
        return new R<>(bsNetworkingUnitService.updateById(bsNetworkingUnit));
    }

    /**
     * 通过id删除联网单位表
     * @param unitId id
     * @return R
     */
    @SysLog("删除联网单位表" )
    @DeleteMapping("/{unitId}" )
    @PreAuthorize("@pms.hasPermission('bs_networkingunit_del')" )
    public R removeById(@PathVariable String unitId) {
        return new R<>(bsNetworkingUnitService.removeById(unitId));
    }

    @SysLog("联网单位select接口")
    @GetMapping("/select/list")
    public R select() {
    	return new R<>(bsNetworkingUnitService.list(Wrappers.<BsNetworkingUnit>query()));
    }
}
