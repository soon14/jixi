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

package com.pig4cloud.pigx.api.user.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.entity.BsArea;
import com.pig4cloud.pigx.api.user.mapper.BsAreaMapper;
import com.pig4cloud.pigx.api.user.service.BsAreaService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

import lombok.AllArgsConstructor;

/**
 * 区域表
 *
 * @author lhd
 * @date 2019-05-15 15:38:07
 */
@Service
@AllArgsConstructor
public class BsAreaServiceImpl extends ServiceImpl<BsAreaMapper, BsArea> implements BsAreaService {
    private final BsAreaMapper bsAreaMapper;
	@Override
	public List<BsArea> getAreaLongonUser(List<String> unitIds)  throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsAreaMapper.getAreaLongonUser(unitIds);
	}
	@Override
	public BsArea getAreaByCode(String code)  throws Exception{
		DynamicDataSourceContextHolder.clearDataSourceType();
		return bsAreaMapper.getAreaByCode(code);
	}

}
