package com.pig4cloud.pigx.device.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pigx.device.generator.entity.DmRealtimingData;

/**
 * 实时数据表
 *
 * @author zm
 * @data 2019-07-24 15:50:52
 */
public interface DmRealtimingDataService extends IService<DmRealtimingData> {


    /**
     * 通过设备编码获取实时数据表中最新的一条数据
      * @param deviceCode
     * @return
     */
    DmRealtimingData selectLast(String deviceCode);
}
