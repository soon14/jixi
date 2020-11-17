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

package com.pig4cloud.pigx.admin.controller.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pig4cloud.pigx.admin.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.common.core.util.R;
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
@RequestMapping("/app/networkingunit" )
@Api(value = "appNetworkingUnit", tags = "app networkingunit管理")
public class AppNetworkingUnitController {

    private final  BsNetworkingUnitService bsNetworkingUnitService;

	/**
	 * 获取单位信息
	 * 
	 * @param unitId
	 * @return
	 */
    @GetMapping("/getUnitById")
    public R getUnitById(String unitId) {
    	return new R<>(bsNetworkingUnitService.getUnitById(unitId));
    }

}
