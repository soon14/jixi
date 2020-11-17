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

package com.pig4cloud.pigx.api.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.user.dto.ProvinceDTO;
import com.pig4cloud.pigx.api.user.entity.BsProvince;
import com.pig4cloud.pigx.api.user.service.BsProvinceService;
import com.pig4cloud.pigx.api.user.vo.ProvinceVO;
import com.pig4cloud.pigx.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;


/**
 * 省份表
 *
 * @author lhd
 * @date 2019-05-15 15:38:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsprovince" )
@Api(value = "bsprovince", tags = "bsprovince管理")
public class BsProvinceController {

    private final  BsProvinceService bsProvinceService;

    /**
     * 根据code,获取省份信息
     * @param code
     * @return
     */
    @GetMapping("/getProvinceByCode" )
    public R getProvinceByCode(String code) {
    	ProvinceVO vo=null;
    	try {
    		vo=bsProvinceService.getProvinceByCode(code);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	
    	return new R<>(vo);
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param bsProvince 省份表
     * @return
     */
    @GetMapping("/page" )
    public R getBsProvincePage(Page page, ProvinceDTO bsProvince) {
    	IPage<List<ProvinceVO>> list=null;
    	try {
    		list=bsProvinceService.getProvinceList(page, bsProvince);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(list);
    }


    /**
     * 通过id查询省份表
     * @param provId id
     * @return R
     */
    @GetMapping("/{provId}" )
    public R getById(@PathVariable("provId" ) String provId) {
        return new R<>(bsProvinceService.getById(provId));
    }

    
    @GetMapping("/select")
    public R getSelect() {
    	return new R<>(bsProvinceService.list(Wrappers.<BsProvince>query()));
    }
}
