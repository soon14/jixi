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
import com.pig4cloud.pigx.device.generator.entity.DmManufactor;
import com.pig4cloud.pigx.device.generator.service.CommonFeignService;
import com.pig4cloud.pigx.device.generator.service.DmManufactorService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static com.pig4cloud.pigx.device.generator.common.Constant.PRODUCT_CATEGORY;


/**
 * 厂家表
 *
 * @author lhd
 * @date 2019-06-20 10:15:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmmanufactor" )
@Api(value = "dmmanufactor", tags = "dmmanufactor管理")
public class DmManufactorController {

    private final  DmManufactorService dmManufactorService;
    private CommonFeignService commonFeignService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dmManufactor 厂家表
     * @return
     */
    @GetMapping("/page" )
    public R getDmManufactorPage(Page page, DmManufactor dmManufactor) {
        // 分页查询厂家列表
        R r = new R<>(dmManufactorService.page(page, Wrappers.query(dmManufactor)));
        // 遍历，组装数据
        for(DmManufactor dm : (List<DmManufactor>)((Page)r.getData()).getRecords()){
            String[] prodTypeArr = dm.getProdTypes().split(",");
            StringBuffer sb = new StringBuffer();
            for(String prodType : prodTypeArr){
                // 获取字典项并拼接返回值
                sb.append(commonFeignService.getInterfaceDictByType(PRODUCT_CATEGORY).get(prodType)).append(",");
            }
            dm.setProdTypes(sb.substring(0,sb.length()-1));
        }
        return r;
    }


    /**
     * 通过id查询厂家表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(dmManufactorService.getById(id));
    }

    /**
     * 新增厂家表
     * @param dmManufactor 厂家表
     * @return R
     */
    @SysLog("新增厂家表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_dmmanufactor_add')" )
    public R save(@Valid @RequestBody DmManufactor dmManufactor) {
    	PigxUser user = SecurityUtils.getUser();
    	dmManufactor.setCreateUserId(user.getId());
    	dmManufactor.setCreateUser(user.getUsername());
        return new R<>(dmManufactorService.save(dmManufactor));
    }

    /**
     * 修改厂家表
     * @param dmManufactor 厂家表
     * @return R
     */
    @SysLog("修改厂家表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_dmmanufactor_edit')" )
    public R updateById(@Valid @RequestBody DmManufactor dmManufactor) {
    	PigxUser user = SecurityUtils.getUser();
    	dmManufactor.setUpdateUserId(user.getId());
    	dmManufactor.setUpdateUser(user.getUsername());
    	dmManufactor.setUpdateTime(LocalDateTime.now());
        return new R<>(dmManufactorService.updateById(dmManufactor));
    }

    /**
     * 通过id删除厂家表
     * @param id id
     * @return R
     */
    @SysLog("删除厂家表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_dmmanufactor_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(dmManufactorService.removeById(id));
    }
    
    /**
     * 获取厂家select
     * @param dmManufactor
     * @return
     */
    @SysLog("获取厂家select")
    @GetMapping("/select/{prodType}")
    public R selectByProdTypes(@PathVariable String prodType) {
    	return new R<>(dmManufactorService.getSelectByProdType(prodType));
    }

}
