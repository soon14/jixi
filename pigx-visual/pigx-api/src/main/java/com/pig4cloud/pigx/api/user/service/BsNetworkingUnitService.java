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

package com.pig4cloud.pigx.api.user.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import org.apache.ibatis.annotations.Param;

/**
 * 联网单位表
 *
 * @author lhd
 * @date 2019-06-06 14:39:37
 */
public interface BsNetworkingUnitService extends IService<BsNetworkingUnit> {

     NetworkingUnitVo getUnitById(String unitId)  throws Exception;

     List<BsNetworkingUnit> getUnitByAreaId(String areaId,List<String> unitIds)  throws Exception;

     List<String> getUnitIdByUnitId(String orgId)  throws Exception;

     List<String> getUnitIdByAreaId(String areaId,List<String> unitIds)  throws Exception;

     List<BsNetworkingUnit> select(List<String> unitIds)  throws Exception;

     List<NetworkingUnitVo> getBsNetworkingUnitPage(Page page, BsNetworkingUnit bsNetworkingUnit,List<String> unitIds) throws Exception;

     List<BsNetworkingUnit> getBsNetworkingUnitList(List<String> unitIds,String name) throws Exception;

     List<Map> findNetWorkDetail(String unitId) throws Exception;
}
