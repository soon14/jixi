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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecute;
import com.pig4cloud.pigx.api.application.vo.SfInspectionResultVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
public interface SfInspectionExecuteMapper extends BaseMapper<SfInspectionExecute> {

	List<SfInspectionResultVO> getInspectionRecord(Page page,@Param("inspectStatus")List<Integer> inspectStatus,@Param("unitIds")List<String> unitIds) throws Exception;
}
