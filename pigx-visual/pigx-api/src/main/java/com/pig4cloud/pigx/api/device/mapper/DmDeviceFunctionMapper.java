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

package com.pig4cloud.pigx.api.device.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.device.entity.DmDeviceFunction;
import com.pig4cloud.pigx.api.device.vo.DmDeviceFunctionVo;

/**
 * 设备功能关系表
 *
 * @author lhd
 * @date 2019-06-20 10:15:38
 */
public interface DmDeviceFunctionMapper extends BaseMapper<DmDeviceFunction> {

    /**
     * 查询设备所属功能列表
     *
     * @param page
     * @param dmProduct
     * @return
     */
    IPage<List<DmDeviceFunctionVo>> getDeviceFunctionList(Page page, @Param("query") DmDeviceFunction dmDeviceFunction) throws Exception;

    /**
     * 根据设备id删除设备功能
     *
     * @param devId
     * @return
     */
    boolean deleteDeviceFunction(@Param("devId") String devId) throws Exception;

    /**
     * 更新设备功能信息
     *
     * @param devId
     * @return
     */
    boolean updateDeviceFunctionInfo(@Param("entity") DmDeviceFunction dmDeviceFunction) throws Exception;

    /**
     * 通过设备id查询出设备功能关系表有（可能是多条）
     * @param devId
     * @return
     */
    List<DmDeviceFunction> findDeviceDataById(String devId)  throws Exception;

}
