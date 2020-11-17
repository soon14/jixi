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
import com.pig4cloud.pigx.api.application.entity.SfFireReport;
import com.pig4cloud.pigx.api.application.service.SfFireReportService;
import com.pig4cloud.pigx.api.application.vo.SfFireReportVo;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 消防报告表
 *
 * @author pigx code generator
 * @date 2019-09-18 11:38:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sffirereport" )
@Api(value = "sffirereport", tags = "sffirereport管理")
public class SfFireReportController {

    private final  SfFireReportService sfFireReportService;
    
    private final  BsNetworkingUnitService bsNetworkingUnitService;
    
    private final SysDictItemService sysDictItemService;
    
    private final SysUserService sysUserService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sfFireReport 消防报告表
     * @return
     */
    @GetMapping("/page" )
    public R getSfFireReportPage(Page page, SfFireReport sfFireReport) {
    	try {
    	List<SfFireReportVo> list=sfFireReportService.getSfFireReportPage(page, sfFireReport);
    	if(list!=null&&list.size()>0) {
    		for(SfFireReportVo vo:list) {
    			if(vo==null)continue;
    			vo.setUnitName(bsNetworkingUnitService.getUnitById(vo.getUnitid()).getName());
    			//查询报表类型数据字典
    	    	Map<String,String> mp=sysDictItemService.getItemMap("reporttype");
    			vo.setReporttypeName(mp.get(vo.getReporttype()));
    			vo.setCreateName(sysUserService.selectUserVoById(vo.getCreator()).getNickname());
    			}
    		}
    	page.setRecords(list);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(page);
    }
    
    /**
               * 查询消防报告
     * @param sfFireReport
     * @return
     */
    @GetMapping("/getSfFireReportByCons" )
    @Inner(value=false)
    public R getSfFireReportByCons(SfFireReport sfFireReport) {
    	List<SfFireReport> list=null;
    	try {
        	list=sfFireReportService.getSfFireReportByCons(sfFireReport);
        	
        	}catch(Exception e) {
        		return new R<>(e);
        	}
        	return new R<>(list);
    }

    /**
                *  生成周报
     * @return
     */
    @GetMapping("/generateWeekly" )
    public R generateWeekly() {
      //查询联网单位,根据联网单位生成报告
      List<BsNetworkingUnit> list=bsNetworkingUnitService.list();
      if(list!=null&&list.size()>0) {
    	  for(BsNetworkingUnit unit:list) {
    		  if(unit==null)continue;
    		  //根据联网单位查询设备的总数
    		  
    		  
    	  }
      }
    	return new R<>();
    }
    /**
                * 生成月报
     * @return
     */
    @GetMapping("/generateMonthly" )
    public R generateMonthly() {
    	
    	return new R<>();
    }

    /**
               * 生成年报
     * @return
     */
    @GetMapping("/generateAnnualReport" )
    public R generateAnnualReport() {
    	
    	return new R<>();
    }
}
