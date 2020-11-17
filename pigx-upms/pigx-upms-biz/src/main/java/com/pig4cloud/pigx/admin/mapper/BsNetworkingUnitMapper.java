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

package com.pig4cloud.pigx.admin.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.admin.api.vo.NetworkingUnitVo;

import java.util.List;

/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
public interface BsNetworkingUnitMapper extends BaseMapper<BsNetworkingUnit> {

	NetworkingUnitVo getUnitById(@Param("unitId")String unitId);
	/**
	 * 批量查询联网单位机构关系
	 * @param unitIds
	 */
	List<BsNetworkingUnit> findBatch(@Param("unitIds")List<String> unitIds);
}
