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
import com.pig4cloud.pigx.device.generator.entity.DmDeviceGroup;
import com.pig4cloud.pigx.device.generator.mapper.DmDeviceGroupMapper;
import com.pig4cloud.pigx.device.generator.service.DeviceGroupService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备分组关系表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@Service
@AllArgsConstructor
public class DeviceGroupServiceImpl extends ServiceImpl<DmDeviceGroupMapper, DmDeviceGroup> implements DeviceGroupService {

	private final DmDeviceGroupMapper deviceGroupMapper;
	
	@Override
	public boolean removeDmDeviceGroup(DmDeviceGroupVO dmDeviceGroup) {
		return deviceGroupMapper.removeDmDeviceGroup(dmDeviceGroup);
	}

	@Override
	public boolean saveDeviceGroup(String groupId, String devIds) {
		// 设备id有可能有多个,以逗号相分隔
				String[] ids = devIds.split(",");
				// 存放设备分组实体
				List<DmDeviceGroup> dgList = new ArrayList<DmDeviceGroup>();
				if (ids.length > 0) {
					for(int i=0;i<ids.length;i++) {
						String devId=ids[i];
						if(devId==null)continue;
						DmDeviceGroup dg=new DmDeviceGroup();
						dg.setDeviceId(devId);
						dg.setGroupId(groupId);
						dgList.add(dg);
					  }
					}
				//批量保存
				return this.saveBatch(dgList);
	}

	@Override
	public Integer getDeviceCount(String groupId) {
		return deviceGroupMapper.getDeviceCount(groupId);
	}


}
