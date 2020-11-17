/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pigx.api.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pigx.api.user.entity.SysDictItem;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysDictService;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {
	private final SysDictItemService sysDictItemService;


	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type")
	public R getDictByType(String type) {
		List<SysDictItem> list=null;
		try {
			list=sysDictItemService.getDictByType(type);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}
	
	/**
	 * 获取banner图
	 * @return
	 */
	@GetMapping("/getBannerPic")
	public R getBannerPic() {
		List<SysDictItem> list=null;
		try {
			list=sysDictItemService.getDictByType("banner_pic");
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}


}
