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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.entity.DmUnit;
import com.pig4cloud.pigx.device.generator.service.CommonFeignService;
import com.pig4cloud.pigx.device.generator.service.DmUnitService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pig4cloud.pigx.device.generator.vo.DmUnitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.pig4cloud.pigx.device.generator.common.Constant.PRODUCT_CATEGORY;


/**
 * 计量单位表
 *
 * @author lhd
 * @date 2019-06-20 10:15:50
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmunit" )
@Api(value = "dmunit", tags = "dmunit管理")
public class DmUnitController {

    private final  DmUnitService dmUnitService;
    private CommonFeignService commonFeignService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dmUnit 计量单位表
     * @return
     */
    @GetMapping("/page" )
    public R getDmUnitPage(Page page, DmUnit dmUnit) {
        R r = new R<>(dmUnitService.page(page, Wrappers.query(dmUnit)));
        List<DmUnitVO> dmUnitVOList = new ArrayList<>();
        for(DmUnit dataDmUnit : (List<DmUnit>)((Page)r.getData()).getRecords()){
            DmUnitVO dmUnitVO = new DmUnitVO();
            BeanUtils.copyProperties(dataDmUnit,dmUnitVO);
            dmUnitVO.setProductTypeName(commonFeignService.getInterfaceDictByType(PRODUCT_CATEGORY).get(String.valueOf(dmUnitVO.getTypeId())));
            dmUnitVOList.add(dmUnitVO);
        }
        ((Page) r.getData()).setRecords(dmUnitVOList);
        return r;
    }


    /**
     * 通过id查询计量单位表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(dmUnitService.getById(id));
    }

    /**
     * 新增计量单位表
     * @param dmUnit 计量单位表
     * @return R
     */
    @SysLog("新增计量单位表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_dmunit_add')" )
    public R save(@RequestBody DmUnit dmUnit) {
    	PigxUser user = SecurityUtils.getUser();
    	dmUnit.setCreateUserId(user.getId());
    	dmUnit.setCreateUser(user.getUsername());
        return new R<>(dmUnitService.save(dmUnit));
    }

    /**
     * 修改计量单位表
     * @param dmUnit 计量单位表
     * @return R
     */
    @SysLog("修改计量单位表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_dmunit_edit')" )
    public R updateById(@RequestBody DmUnit dmUnit) {
    	PigxUser user = SecurityUtils.getUser();
    	dmUnit.setUpdateUserId(user.getId());
    	dmUnit.setUpdateUser(user.getUsername());
    	dmUnit.setUpdateTime(LocalDateTime.now());
        return new R<>(dmUnitService.updateById(dmUnit));
    }

    /**
     * 通过id删除计量单位表
     * @param id id
     * @return R
     */
    @SysLog("删除计量单位表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_dmunit_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(dmUnitService.removeById(id));
    }
    /**
	 * 获取产品select
	 * 
	 * @param typeId 产品类别
	 * @return
	 */
	@SysLog("获取单位select")
	@GetMapping("/select")
	public R selectUnit(String typeId) {
		return new R<>(dmUnitService.getSelectUnit(typeId));
	}
}
