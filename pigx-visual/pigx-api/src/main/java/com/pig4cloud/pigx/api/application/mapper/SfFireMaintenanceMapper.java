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

package com.pig4cloud.pigx.api.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.dto.SfFireMaintenanceDto;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import com.pig4cloud.pigx.api.application.vo.SfFireMaintenanceVo;

/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-08-14 14:12:02
 */
public interface SfFireMaintenanceMapper extends BaseMapper<SfFireMaintenance> {
	
	IPage<List<SfFireMaintenance>> getMaintenanceRecord(Page page,@Param("query")SfFireMaintenanceDto sfFireMaintenance,@Param("unitIds")List<String> unitIds) throws Exception;

	
	SfFireMaintenanceVo getMaintenanceDetail(@Param("mainId")String mainId) throws Exception;
	
	boolean maintenanceHandle(@Param("save")SfFireMaintenance tenance) throws Exception;
	
	List<SfFireMaintenanceVo> getMaintenanceEvent(@Param("unitIds")List<String> unitIds,@Param("deviceStatus")Integer deviceStatus) throws Exception;

	/**
	 * App本周维保统计
	 * @param sfFireMaintenance
	 * @return
	 */
	List<SfFireMaintenanceVo> selectGraphStatistics(SfFireMaintenance sfFireMaintenance) throws Exception;

	List<SfFireMaintenanceVo>  getMaintenanceRecordByTime(@Param("query") SfFireMaintenance sfFireMaintenance,@Param("unitIds")List<String> unitIds) throws Exception;
}
