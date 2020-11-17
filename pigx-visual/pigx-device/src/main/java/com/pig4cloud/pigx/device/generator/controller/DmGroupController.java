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

package com.pig4cloud.pigx.device.generator.controller;

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.entity.DmGroup;
import com.pig4cloud.pigx.device.generator.service.DmGroupService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 设备分组表
 *
 * @author lhd
 * @date 2019-06-21 10:37:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmgroup" )
@Api(value = "dmgroup", tags = "dmgroup管理")
public class DmGroupController {

    private final  DmGroupService dmGroupService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dmGroup 设备分组表
     * @return
     */
    @GetMapping("/page" )
    public R getDmGroupPage(Page page, DmGroup dmGroup) {
        return new R<>(dmGroupService.getGroupList(page, dmGroup));
    }


    /**
     * 通过id查询设备分组表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(dmGroupService.getById(id));
    }

    /**
     * 通过groupId查询设备分组的父分组
     * @param id id
     * @return R
     */
    @GetMapping("/getParentGroup" )
    public R getParentGroup(String groupId) {
        return new R<>(dmGroupService.getParentGroup(groupId));
    }
    
    /**
     * 新增设备分组表
     * @param dmGroup 设备分组表
     * @return R
     */
    @SysLog("新增设备分组表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_dmgroup_add')" )
    public R save(@Valid @RequestBody DmGroup dmGroup) {
    	PigxUser user = SecurityUtils.getUser();
    	dmGroup.setCreateUserId(user.getId());
    	dmGroup.setCreateUser(user.getUsername());
        return new R<>(dmGroupService.save(dmGroup));
    }

    /**
     * 修改设备分组表
     * @param dmGroup 设备分组表
     * @return R
     */
    @SysLog("修改设备分组表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_dmgroup_edit')" )
    public R updateById(@Valid @RequestBody DmGroup dmGroup) {
    	PigxUser user = SecurityUtils.getUser();
    	dmGroup.setUpdateUserId(user.getId());
    	dmGroup.setUpdateUser(user.getUsername());
    	dmGroup.setUpdateTime(LocalDateTime.now());
        return new R<>(dmGroupService.updateById(dmGroup));
    }

    /**
     * 通过id删除设备分组表
     * @param id id
     * @return R
     */
    @SysLog("删除设备分组表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_dmgroup_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(dmGroupService.removeById(id));
    }
    
    
    /**
     * 获取分组select
     * @param parentId  父级ID
     * @return
     */
    @SysLog("获取分组select")
    @GetMapping("/select/{parentId}")
    public R selectParentId(@PathVariable String parentId) {
    	return new R<>(dmGroupService.getSelectParentId(parentId));
    }
    
}
