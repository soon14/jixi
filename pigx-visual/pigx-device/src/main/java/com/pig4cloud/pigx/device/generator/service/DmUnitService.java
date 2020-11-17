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

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.entity.DmUnit;

/**
 * 计量单位表
 *
 * @author lhd
 * @date 2019-06-20 10:15:50
 */
public interface DmUnitService extends IService<DmUnit> {
	/**
	 * 根据产品类别查询单位信息
	 * 
	 * @param typeId
	 * @return
	 */
	List<DmUnit> getSelectUnit(String typeId);
}
