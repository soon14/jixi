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

package com.pig4cloud.pigx.device.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.entity.DmFunction;
import com.pig4cloud.pigx.device.generator.vo.DmFunctionVo;

import java.util.List;

/**
 * 功能表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
public interface FunctionService extends IService<DmFunction> {
	/**
	     * 查询功能列表
	 * 
	 * @param page
	 * @param dmFunction
	 * @return
	 */
	IPage<List<DmFunctionVo>> getFunctionList(Page page, DmFunction dmFunction);

	/**
	    * 查询不属于该设备的功能
	 * 
	 * @param devId
	 * @return
	 */
	List<DmFunction> getNoDeviceFunction(String devId);
}
