package com.pig4cloud.pigx.device.generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.device.generator.entity.DmRealtimingData;
import com.pig4cloud.pigx.device.generator.mapper.DmRealtimingDataMapper;
import com.pig4cloud.pigx.device.generator.service.DmRealtimingDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 实时数据表
 *
 * @author zm
 * @data 2019-07-24 15:50:52
 */
@Service
@AllArgsConstructor
public class DmRealtimingDataServieImpl extends ServiceImpl<DmRealtimingDataMapper,DmRealtimingData>
        implements DmRealtimingDataService {
    private final DmRealtimingDataMapper dmRealtimingDataMapper;

    @Override
    public DmRealtimingData selectLast(String deviceCode) {
        return dmRealtimingDataMapper.selectLast(deviceCode);
    }
}
