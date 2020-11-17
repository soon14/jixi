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

import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.user.entity.BsNetworkOrgRela;
import com.pig4cloud.pigx.api.user.service.BsNetworkOrgRelaService;
import com.pig4cloud.pigx.api.user.service.SysDeptService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import java.util.List;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 联网单位机构关系表
 *
 * @author pigx code generator
 * @date 2019-08-14 09:45:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bsnetworkorgrela" )
@Api(value = "bsnetworkorgrela", tags = "bsnetworkorgrela管理")
public class BsNetworkOrgRelaController {

    private final  BsNetworkOrgRelaService bsNetworkOrgRelaService;
    
    private final SysDeptService sysDeptService;

    /**
              *   根据联网单位Id,查询维保公司
     * 
     * @param unitId
     * @return
     */
    @GetMapping("/getOrgByUnitId" )
    public R getOrgByUnitId(BsNetworkOrgRela bsNetworkOrgRela) {
    	 bsNetworkOrgRela.setOrgTypeCode(Constant.ORG_TYPE_CODE_WBGS);
    	 BsNetworkOrgRela rela=null;
    	 try {
    		 rela=bsNetworkOrgRelaService.getOrgByUnitId(bsNetworkOrgRela);
    	 }catch(Exception e) {
    		 return new R<>(e);
    	 }
    	 return new R<>(rela);
    }
    
    /**
     * 获取当前登录用户的联网机构Id的集合
     * @return
     */
    @GetMapping("/getSelectUnitIds" )
    public List<String> getSelectUnitIds() {
    	List<String> list=null;
    	try {
    	List<String> orgIds=sysDeptService.getSelectDeptId(SecurityUtils.getUser().getDeptId());
    	list =bsNetworkOrgRelaService.findUnitIdsByorgIds(orgIds);
    	}catch(Exception e) {
    		e.getMessage();
    	}
    	return list;
    }
    

}
