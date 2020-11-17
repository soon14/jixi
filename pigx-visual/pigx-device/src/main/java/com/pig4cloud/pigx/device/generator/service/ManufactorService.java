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

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.dto.DmManufactorDTO;
import com.pig4cloud.pigx.device.generator.entity.DmManufactor;

import java.util.List;

/**
 * 厂家表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
public interface ManufactorService extends IService<DmManufactor> {
	
	/**
	 * 根据产品类型获取厂家select
	 * @param prodType
	 * @return
	 */
	List<DmManufactorDTO> getSelectByProdType(String prodType);
	
}
