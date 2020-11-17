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

package com.pig4cloud.pigx.api.application.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.api.application.dto.SfFireMaintenanceDto;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import com.pig4cloud.pigx.api.application.vo.MaintenanceDataVo;
import com.pig4cloud.pigx.api.application.vo.SfFireMaintenanceVo;

/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-08-14 14:12:02
 */
public interface SfFireMaintenanceService extends IService<SfFireMaintenance> {
	
	boolean saveMaintenance(SfFireMaintenance tenance) throws Exception;
	
	IPage<List<SfFireMaintenance>> getMaintenanceRecord(Page page,SfFireMaintenanceDto tenance,List<String> unitIds) throws Exception;
	
	SfFireMaintenanceVo getMaintenanceDetail(String mainId) throws Exception;
	
	boolean maintenanceHandle(SfFireMaintenance tenance) throws Exception;
	
	List<SfFireMaintenanceVo> getMaintenanceEvent(List<String> unitIds,Integer deviceStatus) throws Exception;

	/**
	 * App本周维保统计
	 * @param sfFireMaintenance
	 * @return
	 */
	List<MaintenanceDataVo> selectGraphStatistics(SfFireMaintenance sfFireMaintenance) throws Exception;

	List<SfFireMaintenanceVo> getMaintenanceRecordByTime(SfFireMaintenance sfFireMaintenance,List<String> unitIds) throws Exception;

}
