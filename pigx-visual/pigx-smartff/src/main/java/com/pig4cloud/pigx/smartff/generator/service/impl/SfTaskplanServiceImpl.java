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
package com.pig4cloud.pigx.smartff.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.smartff.generator.entity.SfTaskplan;
import com.pig4cloud.pigx.smartff.generator.mapper.SfTaskplanMapper;
import com.pig4cloud.pigx.smartff.generator.service.SfTaskplanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 巡检方案表
 *
 * @author zm
 * @date 2019-08-07 16:42:48
 */
@Service
@AllArgsConstructor
public class SfTaskplanServiceImpl extends ServiceImpl<SfTaskplanMapper, SfTaskplan> implements SfTaskplanService {

    private final SfTaskplanMapper sfTaskplanMapper;

    @Override
    public IPage<List<SfTaskplan>> findByPlanNameList(Page page,SfTaskplan sfTaskplan) {
        return sfTaskplanMapper.findByPlanNameList(page,sfTaskplan);
    }
}
