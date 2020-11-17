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
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;


/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
public interface BsNetworkingUnitMapper extends BaseMapper<BsNetworkingUnit> {

	NetworkingUnitVo getUnitById(@Param("unitId")String unitId)  throws Exception;

	List<BsNetworkingUnit> getUnitByAreaId(@Param("areaId")String areaId,@Param("unitIds") List<String> unitIds)  throws Exception;

	List<String> getUnitIdByUnitId(@Param("orgId")String orgId)  throws Exception;

	List<String> getUnitIdByAreaId(@Param("areaId")String areaId,@Param("unitIds")List<String> unitIds)  throws Exception;

	List<BsNetworkingUnit> selectUnitInfo(@Param("unitIds")List<String> unitIds)  throws Exception;

	List<NetworkingUnitVo> getBsNetworkingUnitPage(Page page, @Param("query")BsNetworkingUnit bsNetworkingUnit,@Param("unitIds")List<String> unitIds) throws Exception;

	List<BsNetworkingUnit> getBsNetworkingUnitList(@Param("unitIds")List<String> unitIds,@Param("name")String name) throws Exception;

	/**
	 * 联网单位关联维保，消防信息
	 * @param unitId
	 * @return
	 * @throws Exception
	 */
	List<Map> findNetWorkDetail(@Param("unitId") String unitId) throws Exception;
}
