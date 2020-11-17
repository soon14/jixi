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

package com.pig4cloud.pigx.api.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pigx.api.user.entity.BsArea;

/**
 * 区域表
 *
 * @author lhd
 * @date 2019-05-15 15:38:07
 */
public interface BsAreaMapper extends BaseMapper<BsArea> {
	
	List<BsArea> getAreaLongonUser(@Param("unitIds")List<String> unitIds) throws Exception;
	
	BsArea getAreaByCode(@Param("code")String code)  throws Exception;
}
