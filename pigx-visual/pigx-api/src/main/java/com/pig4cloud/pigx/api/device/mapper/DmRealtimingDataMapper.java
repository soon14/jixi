package com.pig4cloud.pigx.api.device.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pigx.api.device.entity.DmRealtimingData;

/**
 * 实时数据表
 *
 * @author zm
 * @data 2019-07-24 15:50:52
 */
public interface DmRealtimingDataMapper extends BaseMapper<DmRealtimingData> {

    /**
     * 通过设备编码获取实时数据表中最新的一条数据
     * @param  deviceCode
     * @return
     */
    DmRealtimingData selectLast(String deviceCode)  throws Exception;
    
    /**
               *  获取电器火灾的实时数据根据设备编码
     * @param devCode
     * @param sendTime
     * @return
     * @throws Exception
     */
    List<DmRealtimingData> getElectricalFireByDevCode(@Param("devCode")String devCode,@Param("sendTime")String sendTime) throws Exception;
}