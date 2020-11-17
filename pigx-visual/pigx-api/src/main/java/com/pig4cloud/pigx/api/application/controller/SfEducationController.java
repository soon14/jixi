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

package com.pig4cloud.pigx.api.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.SfEducation;
import com.pig4cloud.pigx.api.application.service.SfEducationService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 教育培训表
 *
 * @author pigx code generator
 * @date 2019-09-20 13:21:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfeducation" )
@Api(value = "sfeducation", tags = "sfeducation管理")
public class SfEducationController {

    private final  SfEducationService sfEducationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfEducation 教育培训表
     * @return
     */
    @GetMapping("/page" )
    @Inner(value=false)
    public R getSfEducationPage(Page page, SfEducation sfEducation) {
    	try {
    	List<SfEducation> list=sfEducationService.getSfEducationPage(page, sfEducation);
    	page.setRecords(list);
    	}catch(Exception e) {
    		 return new R<>(e);
    	}
        return new R<>(page);
    }


    /**
     * 通过id查询教育培训表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    @Inner(value=false)
    public R getById(@PathVariable("id" ) String id) {
    	SfEducation education=null;
    	try {
    		education=sfEducationService.getById(id);
    		Integer hits=education.getHits();
    		if(hits==null||hits==0) {
    			education.setHits(1);
    		}else {
    			education.setHits(hits+1);
    		}
    		sfEducationService.updateById(education);
		} catch (Exception e) {
			return new R<>(e);
		}
        return new R<>(education);
    }

    /**
     * 新增教育培训表
     * @param sfEducation 教育培训表
     * @return R
     */
    @SysLog("新增教育培训表" )
    @PostMapping
    public R save(@RequestBody SfEducation sfEducation) {
    	try {
    		sfEducation.setIssueTime(LocalDateTime.now());
			sfEducationService.saveEducation(sfEducation);
		} catch (Exception e) {
			return new R<>(false);
		}
    	
        return new R<>(true);
    }

    /**
     * 修改教育培训表
     * @param sfEducation 教育培训表
     * @return R
     */
    @SysLog("修改教育培训表" )
    @PutMapping
    public R updateById(@RequestBody SfEducation sfEducation) {
        return new R<>(sfEducationService.updateById(sfEducation));
    }

    /**
     * 通过id删除教育培训表
     * @param id id
     * @return R
     */
    @SysLog("删除教育培训表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
    	try {
			sfEducationService.removeById(id);
		} catch (Exception e) {
			return new R<>(e);
		}
        return new R<>(true);
    }

}
