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

import com.pig4cloud.pigx.common.security.util.SecurityUtils;
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
import com.pig4cloud.pigx.admin.api.entity.SysOrgType;
import com.pig4cloud.pigx.admin.service.SysOrgTypeService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * 机构类型表
 *
 * @author lhd
 * @date 2019-05-15 15:26:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysorgtype" )
@Api(value = "sysorgtype", tags = "sysorgtype管理")
public class SysOrgTypeController {

    private final  SysOrgTypeService sysOrgTypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysOrgType 机构类型表
     * @return
     */
    @GetMapping("/page" )
    public R getSysOrgTypePage(Page page, SysOrgType sysOrgType) {
        return new R<>(sysOrgTypeService.page(page, Wrappers.query(sysOrgType)));
    }


    /**
     * 通过id查询机构类型表
     * @param typeId id
     * @return R
     */
    @GetMapping("/{typeId}" )
    public R getById(@PathVariable("typeId" ) String typeId) {
        return new R<>(sysOrgTypeService.getById(typeId));
    }

    /**
     * 新增机构类型表
     * @param sysOrgType 机构类型表
     * @return R
     */
    @SysLog("新增机构类型表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_orgtype_add')" )
    public R save(@RequestBody SysOrgType sysOrgType) {
        sysOrgType.setCreateTime(LocalDateTime.now());
        sysOrgType.setCreateUserId(SecurityUtils.getUser().getId());
        return new R<>(sysOrgTypeService.save(sysOrgType));
    }

    /**
     * 修改机构类型表
     * @param sysOrgType 机构类型表
     * @return R
     */
    @SysLog("修改机构类型表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_orgtype_edit')" )
    public R updateById(@RequestBody SysOrgType sysOrgType) {
        return new R<>(sysOrgTypeService.updateById(sysOrgType));
    }

    /**
     * 通过id删除机构类型表
     * @param typeId id
     * @return R
     */
    @SysLog("删除机构类型表" )
    @DeleteMapping("/{typeId}" )
    @PreAuthorize("@pms.hasPermission('sys_orgtype_del')" )
    public R removeById(@PathVariable String typeId) {
        return new R<>(sysOrgTypeService.removeById(typeId));
    }
    
    
    @SysLog("获取机构类型select")
	@GetMapping("/getSelect")
	public R getSelect() {
		return new R(sysOrgTypeService.list());
	}

}
