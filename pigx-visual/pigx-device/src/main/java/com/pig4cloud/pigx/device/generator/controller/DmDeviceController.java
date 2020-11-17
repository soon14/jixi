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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.common.Constant;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.service.CommonFeignService;
import com.pig4cloud.pigx.device.generator.service.DmDeviceService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static com.pig4cloud.pigx.device.generator.common.Constant.PRODUCT_CATEGORY;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmdevice")
@Api(value = "dmdevice", tags = "dmdevice管理")
public class DmDeviceController {

    private DmDeviceService dmDeviceService;
    private CommonFeignService commonFeignService;

    /**
     * 分页查询
     *
     * @param page       分页对象
     * @param dmDeviceVO 设备表
     * @return
     */
    @GetMapping("/page")
    public R getDmDevicePage(Page page, DmDeviceVO dmDeviceVO) {
        List<DmDeviceVO> deviceList = dmDeviceService.getDeviceList(page, dmDeviceVO);
        for (DmDeviceVO dmDevice : deviceList) {
            dmDevice.setProductTypeName(commonFeignService.getInterfaceDictByType(PRODUCT_CATEGORY).get(String.valueOf(dmDevice.getTypeId())));
        }
        return new R<>(deviceList);
    }

    /**
     * 根据设备编码激活设备
     *
     * @param code code
     * @return R
     */
    @GetMapping("/activate")
    public R activate(String code) {
        return new R<>(dmDeviceService.activate(code));
    }

    /**
     * 通过id查询设备表
     *
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") String id) {
        return new R<>(dmDeviceService.getById(id));
    }

    /**
     * 根据分组id,查询不属于该分组的设备信息
     *
     * @param
     * @return R
     */
    @GetMapping("/getNoGroupDevice")
    public R getNoGroupDevice(Page page, DmDeviceGroupVO vo) {
        return new R<>(dmDeviceService.getNoGroupDevice(page, vo));
    }

    /**
     * 根据分组id,查询属于该分组的设备信息
     *
     * @param
     * @return R
     */
    @GetMapping("/getGroupDevice")
    public R getGroupDevice(Page page, DmDeviceGroupVO vo) {
        return new R<>(dmDeviceService.getGroupDevice(page, vo));
    }

    /**
     * 新增设备表
     *
     * @param dmDevice 设备表
     * @return R
     */
    @SysLog("新增设备表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_dmdevice_add')")
    public R save(@Valid @RequestBody DmDevice dmDevice) {
        PigxUser user = SecurityUtils.getUser();
        dmDevice.setCreateUserId(user.getId());
        dmDevice.setCreateUser(user.getUsername());
        dmDevice.setAlarmType(Constant.ALARM_TYPE_NORMAL);
        dmDevice.setStatus(Constant.DEVICE_STATUS_ON_LINE.toString());
        QueryWrapper<DmDevice> queryWrapper = new QueryWrapper<>();
        DmDevice dd = dmDeviceService.getOne(queryWrapper.eq("code", dmDevice.getCode()));
        if (null != dd) {
            return new R<>("此设备已经存在，请勿重复添加！");
        }
        return new R<>(dmDeviceService.saveDevice(dmDevice));
    }

    /**
     * 修改设备表
     *
     * @param dmDevice 设备表
     * @return R
     */
    @SysLog("修改设备表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_dmdevice_edit')")
    public R updateById(@Valid @RequestBody DmDevice dmDevice) {
        PigxUser user = SecurityUtils.getUser();
        dmDevice.setUpdateUserId(user.getId());
        dmDevice.setUpdateUser(user.getUsername());
        dmDevice.setUpdateTime(LocalDateTime.now());
        return new R<>(dmDeviceService.updateDevice(dmDevice));
    }

    /**
     * 通过id删除设备表
     *
     * @param id id
     * @return R
     */
    @SysLog("删除设备表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('generator_dmdevice_del')")
    public R removeById(@PathVariable String id) {
        return new R<>(dmDeviceService.removeById(id));
    }

    /**
     * 获取视频设备的select
     *
     * @param dmDevice 设备实体
     * @return
     */
    @SysLog("获取视频设备的select")
    @GetMapping("/select")
    public R selectVideoDevice(DmDevice dmDevice) {
        dmDevice.setTypeId(Constant.PROD_TYPE_REMOTE_VIDEO);
        return new R<>(dmDeviceService.selectVideoDevice(dmDevice));
    }

    /**
     * 获取视频设备的状态
     *
     * @param
     * @return
     */
    @SysLog("获取视频设备的状态")
    @PostMapping("/updateVideoDeviceStatus")
    public void updateVideoDeviceStatus() {
        dmDeviceService.updateVideoDeviceStatus();
    }

    @GetMapping("/getAccesstoken")
    @Inner(value = false)
    public R getAccesstoken() {
        return new R<>(dmDeviceService.getAccesstoken());
    }
}
