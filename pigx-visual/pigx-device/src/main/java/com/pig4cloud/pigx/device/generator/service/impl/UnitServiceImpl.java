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
package com.pig4cloud.pigx.device.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.device.generator.entity.DmUnit;
import com.pig4cloud.pigx.device.generator.mapper.DmUnitMapper;
import com.pig4cloud.pigx.device.generator.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计量单位表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@Service
@AllArgsConstructor
public class UnitServiceImpl extends ServiceImpl<DmUnitMapper, DmUnit> implements UnitService {
	
	private final DmUnitMapper dmUnitMapper;
	
	@Override
	public List<DmUnit> getSelectUnit(String typeId) {
		return dmUnitMapper.getSelectUnit(typeId);
	}

}
