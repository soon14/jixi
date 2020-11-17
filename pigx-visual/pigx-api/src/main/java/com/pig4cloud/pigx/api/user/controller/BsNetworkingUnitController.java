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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pig4cloud.pigx.api.device.entity.DmDevice;
import com.pig4cloud.pigx.api.device.service.DmDeviceService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
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
@Api(value = "BsNetworkingUnit", tags = "bsnetworkingunit管理")
public class BsNetworkingUnitController {

    private final  BsNetworkingUnitService bsNetworkingUnitService;

    private final SysUserService sysUserService;

    private final DmDeviceService dmDeviceService;

	/**
	 * 获取单位信息
	 *
	 * @param unitId
	 * @return
	 */
    @GetMapping("/getUnitById")
    public R getUnitById(String unitId) {
    	NetworkingUnitVo vo=null;
    	try {
    		vo=bsNetworkingUnitService.getUnitById(unitId);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(vo);
    }
	/**
	 * 获取单位信息
	 *
	 * @param unitId
	 * @return
	 */
    @GetMapping("/getUnitInfoById")
    @Inner(value=false)
    public Map<String,String> getUnitInfoById(@RequestParam  String unitId) {
    	NetworkingUnitVo vo=null;
    	Map<String,String> m=new HashMap<String,String>();
		try {
			vo = bsNetworkingUnitService.getUnitById(unitId);
			m.put("contacts", vo.getContacts());
	    	m.put("telephone", vo.getTelephone());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return m;
    }

	/**
	    *  根据区域Id查询联网单位
	 * @param areaId
	 * @return
	 */
    @GetMapping("/getUnitByAreaId")
    public R getUnitByAreaId(String areaId) {
    	List<BsNetworkingUnit> list=null;
    	try {
			list = bsNetworkingUnitService.getUnitByAreaId(areaId, sysUserService.getUserLoingUnitIds());
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(list);
    }
   /**
	    * 查询联网单位
	 * @return
	 */
    @SysLog("联网单位select接口")
    @GetMapping("/select/list")
    public R select() {
    	List<BsNetworkingUnit> list=null;
    	try {
    		list=bsNetworkingUnitService.select(sysUserService.getUserLoingUnitIds());
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(list);
    }

    /**
               * 分页查询
     * @param page 分页对象
     * @param bsNetworkingUnit 联网单位表
     * @return
     */
    @GetMapping("/page" )
    public R getBsNetworkingUnitPage(Page page, BsNetworkingUnit bsNetworkingUnit) {
    	try {
    	List<String> unitIds=sysUserService.getUserLoingUnitIds();
    	List<NetworkingUnitVo> list=bsNetworkingUnitService.getBsNetworkingUnitPage(page, bsNetworkingUnit,unitIds);
    	page.setRecords(list);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(page);
    }

	/**
	 * 查询当前登录用户下的所属联网单位
	 * @return
	 */
	@PostMapping("/getBsNetworkingUnitList" )
	public R getBsNetworkingUnitList(@RequestBody  BsNetworkingUnit bsNetworkingUnit) {
		List<BsNetworkingUnit> list=null;
		try {
			List<String> unitIds = sysUserService.getUserLoingUnitIds();
			list = bsNetworkingUnitService.getBsNetworkingUnitList(unitIds,bsNetworkingUnit.getName());
		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	@GetMapping("/findNetWorkDetail" )
	public R findNetWorkDetail(String unitId) throws Exception {
		List<Map> listMap = bsNetworkingUnitService.findNetWorkDetail(unitId);
		List<String> listUnitIds = new ArrayList<>();
		listUnitIds.add(unitId);
		Integer counts = dmDeviceService.queryDeviceCountByType(new DmDevice(), listUnitIds);
		Map map = new HashMap();
		map.put("counts",counts);
		listMap.add(map);
		return new R<>(listMap);
	}

}
