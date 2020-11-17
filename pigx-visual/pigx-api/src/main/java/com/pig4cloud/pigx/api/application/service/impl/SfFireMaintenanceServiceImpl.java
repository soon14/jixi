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
package com.pig4cloud.pigx.api.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.application.dto.SfFireMaintenanceDto;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import com.pig4cloud.pigx.api.application.mapper.SfFireMaintenanceMapper;
import com.pig4cloud.pigx.api.application.service.SfFireMaintenanceService;
import com.pig4cloud.pigx.api.application.vo.MaintenanceDataVo;
import com.pig4cloud.pigx.api.application.vo.SfFireMaintenanceVo;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-08-14 14:12:02
 */
@Service
@AllArgsConstructor
public class SfFireMaintenanceServiceImpl extends ServiceImpl<SfFireMaintenanceMapper, SfFireMaintenance> implements SfFireMaintenanceService {

	private final SfFireMaintenanceMapper sfFireMaintenanceMapper;
	@Override
	public boolean saveMaintenance(SfFireMaintenance tenance) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return save(tenance);
	}

	@Override
	public IPage<List<SfFireMaintenance>> getMaintenanceRecord(Page page,SfFireMaintenanceDto tenance,List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireMaintenanceMapper.getMaintenanceRecord(page,tenance,unitIds);
	}

	@Override
	public SfFireMaintenanceVo getMaintenanceDetail(String mainId) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireMaintenanceMapper.getMaintenanceDetail(mainId);
	}

	@Override
	public boolean maintenanceHandle(SfFireMaintenance tenance) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireMaintenanceMapper.maintenanceHandle(tenance);
	}

	@Override
	public List<MaintenanceDataVo> selectGraphStatistics(SfFireMaintenance sfFireMaintenance) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		List<SfFireMaintenanceVo> listData = sfFireMaintenanceMapper.selectGraphStatistics(sfFireMaintenance);
		List<MaintenanceDataVo> groupDataVoList = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			MaintenanceDataVo maintenanceDataVo = new MaintenanceDataVo();
			// 已处理维保条数
			Integer processedMaintenanceTotal = 0;
			//未处理维保条数
			Integer untreatedMaintenanceTotal = 0;
			for (SfFireMaintenanceVo sfFireMaintenanceVo : listData) {
				if(sfFireMaintenanceVo.getProduceCate()==null)continue;
				int productTypeId = Integer.parseInt(sfFireMaintenanceVo.getProduceCate());
				if (productTypeId == i) {
					//未处理
					if (sfFireMaintenanceVo.getHandleStatus() == 0) {
						untreatedMaintenanceTotal++;
						//已处理
					} else {
						processedMaintenanceTotal++;
					}
				}
			}
			maintenanceDataVo.setProcessedMaintenanceTotal(processedMaintenanceTotal);
			maintenanceDataVo.setUntreatedMaintenanceTotal(untreatedMaintenanceTotal);
			maintenanceDataVo.setProductType(i);
			groupDataVoList.add(maintenanceDataVo);
		}
		return groupDataVoList;
	}

	@Override
	public List<SfFireMaintenanceVo> getMaintenanceRecordByTime(SfFireMaintenance sfFireMaintenance, List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireMaintenanceMapper.getMaintenanceRecordByTime(sfFireMaintenance, unitIds);
	}

	@Override
	public List<SfFireMaintenanceVo> getMaintenanceEvent(List<String> unitIds,Integer deviceStatus) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_APPLICATION);
		return sfFireMaintenanceMapper.getMaintenanceEvent(unitIds,deviceStatus);
	}


}
