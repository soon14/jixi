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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.user.entity.SysDictItem;
import com.pig4cloud.pigx.api.user.mapper.SysDictItemMapper;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 字典项
 *
 * @author lengleng
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {
    
	private final SysDictItemMapper sysDictItemMapper;
	
    
	@Override
	public Map<String, String> getItemMap(String type) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		Map<String,String> map=new HashMap<String,String>();
		List<SysDictItem> list=sysDictItemMapper.getDictItemByType(type);
		if(list!=null&&list.size()>0) {
			for(SysDictItem item:list) {
				if(item==null)continue;
				map.put(item.getValue(), item.getLabel());
			}
		}
		
		return map;
	}

	@Override
	public List<SysDictItem> getDictByType(String type) throws Exception {
		DynamicDataSourceContextHolder.clearDataSourceType();
		return sysDictItemMapper.getDictItemByType(type);
	}

}
