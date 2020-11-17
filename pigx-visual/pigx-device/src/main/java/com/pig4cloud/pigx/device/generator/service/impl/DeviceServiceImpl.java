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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.admin.api.entity.SysDictItem;
import com.pig4cloud.pigx.device.generator.common.Constant;
import com.pig4cloud.pigx.device.generator.entity.*;
import com.pig4cloud.pigx.device.generator.feign.CommonFeign;
import com.pig4cloud.pigx.device.generator.mapper.DmDeviceFunctionMapper;
import com.pig4cloud.pigx.device.generator.mapper.DmDeviceMapper;
import com.pig4cloud.pigx.device.generator.mapper.DmFunctionMapper;
import com.pig4cloud.pigx.device.generator.mapper.DmRealtimingDataMapper;
import com.pig4cloud.pigx.device.generator.service.DeviceService;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.device.generator.vo.DmDeviceVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@Service
@AllArgsConstructor
@Slf4j
public class DeviceServiceImpl extends ServiceImpl<DmDeviceMapper, DmDevice> implements DeviceService {

    private final DmDeviceMapper dmDeviceMapper;

    private final DmFunctionMapper dmFunctionMapper;

    private final DmDeviceFunctionMapper dmDeviceFunctionMapper;

    private final DmRealtimingDataMapper dmRealtimingDataMapper;

    private  final DmDeviceServiceImpl dmDeviceServiceImpl;


    @Autowired
    private CommonFeign commonFeign;


    @Override
    public List<DmDeviceVO> getDeviceList(Page page, DmDevice dmDevice) {
        List<DmDeviceVO> list = dmDeviceMapper.getDeviceList(page, dmDevice);
        return list;
    }

    @Override
    public boolean activate(String code) {
        // 调用第三方设备激活接口
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, DmDeviceGroupVO vo) {
        return dmDeviceMapper.getNoGroupDevice(page, vo);
    }

    @Override
    public IPage<List<DmDeviceVO>> getGroupDevice(Page page, DmDeviceGroupVO vo) {
        return dmDeviceMapper.getGroupDevice(page, vo);
    }


    @Override
    public boolean saveDevice(DmDevice dmDevice) {
        boolean flag = false;
        //保存设备信息
        flag = save(dmDevice);
        if(Constant.PROD_TYPE_REMOTE_VIDEO.equals(dmDevice.getTypeId())){
            DmDevice dmDevice1 = dmDeviceServiceImpl.selectVideoDeviceInfo(dmDevice);
            dmDeviceMapper.updateDeviceByCode(dmDevice1);
        }
        String appId = "";
        String secret = "";
        if(Constant.PROD_TYPE_NB_SMOKE.equals(dmDevice.getTypeId())){
            appId = "qPPuGRpnS7Ndvj07xFeqwwqqyL0a";
            secret = "PSvqX7O2XP2JQGYvEDRJRgB2gVIa";
            dmDeviceServiceImpl.register(appId,secret,dmDevice.getId());
        }
        if(Constant.PROD_TYPE_WATER_SYS.equals(dmDevice.getTypeId())){
            if(dmDevice.getName()!=null && dmDevice.getName().contains("液压")){
                appId = "fXBTWZFtzrRIGeP6Gldvf48n2hEa";
                secret = "_SMku7T8IvWgrwCa4QTXpnlaUd8a";
            }else if(dmDevice.getName()!=null && dmDevice.getName().contains("液位")){
                appId = "vCGhTkZCwFfg_x2VVaqBCy2YHOUa";
                secret = "jeKW2KKBOPLaOrxB8z1aQj_mKk8a";
            }

            dmDeviceServiceImpl.register(appId,secret,dmDevice.getId());
        }
        // 获取产品Id
        String prodId = dmDevice.getProdId();
        // 获取设备所选产品的功能信息
        List<DmFunction> dmFunctionList = dmFunctionMapper.getFunctionByProdId(prodId);
        if (dmFunctionList != null && dmFunctionList.size() > 0) {
            for (DmFunction f : dmFunctionList) {
                if (f == null)
                    continue;
                DmDeviceFunction entity = new DmDeviceFunction();
                entity.setDevId(dmDevice.getId());
                entity.setFuncId(f.getId());
                entity.setTypeId(dmDevice.getTypeId());
                entity.setFactorId(dmDevice.getFactorId());
                entity.setBrandId(dmDevice.getBrandId());
                entity.setProdId(dmDevice.getProdId());
                entity.setFuncType(f.getFuncType());
                entity.setEventType(f.getEventType());
                entity.setDataType(f.getDataType());
                entity.setUnitId(f.getUnitId());
                entity.setFuncCode(f.getCode());
                entity.setUpperLimit(f.getUpperLimit());
                entity.setLowerLimit(f.getLowerLimit());
                entity.setVolumeSwitch(f.getVolumeSwitch());
                dmDeviceFunctionMapper.insert(entity);
            }
        }
        // 如果不为远程视频,可以选择关联视频设备
        if (!Constant.PROD_TYPE_REMOTE_VIDEO.equals(dmDevice.getTypeId())) {
            String addrs = dmDevice.getVideoAddr();
            if (!StringUtils.isEmpty(addrs)) {
                String[] vids = addrs.split(",");
                for (String vid : vids) {
                    if (vid == null)
                        continue;
                    flag = dmDeviceMapper.insertDeviceVideo(dmDevice.getId(), vid);
                }
            }
        }
        return flag;
    }

    @Override
    public boolean updateDevice(DmDevice dmDevice) {
        return this.updateById(dmDevice);
    }


    @Override
    public IPage<List<DmDeviceVO>> findDeviceList(Page page, DmDevice dmDevice) throws NullPointerException {
        List<DmDeviceVO> listVo = new ArrayList<>();
        //设备列表
        List<DmDeviceVO> deviceList = dmDeviceMapper.findDeviceList(page, dmDevice);
        //获取字典表中的任务状态
        Map<Integer, String> alarmMap = dictToMap("alarm_type");
        //获取字典表中的设备在线状态
        Map<Integer, String> deviceMap = dictToMap("device_status");
        for (int i = 0; i < deviceList.size(); i++) {
            DmDeviceVO dm = deviceList.get(i);
            //根据设备Id将设备功能关系表中的功能列表查出
            List<DmDeviceFunction> deviceFunctionList = dmDeviceFunctionMapper.findDeviceDataById(dm.getId());
            //根据设备编码将实时数据表中的最新的一条记录取出
            DmRealtimingData realTimingData = dmRealtimingDataMapper.selectLast(dm.getCode());
            if (null != realTimingData) {
                String dataMsg = realTimingData.getRealtimingdata();
                Integer deviceType = Integer.parseInt(realTimingData.getDeviceType());
                JSONObject jsonObject = null;
                if (deviceType != 4) {
                    try {
                        jsonObject = JSONObject.parseObject(dataMsg);
                    } catch (JSONException e) {
                        log.info("实时数据JSON数据格式异常，当前实时数据异常Id为：{}", realTimingData.getId());
                        continue;
                    }
                }
                DmDeviceVO dmDeviceVO = new DmDeviceVO();
                dmDeviceVO.setSendTime(realTimingData.getSendTime());
                dmDeviceVO.setId(dm.getId());
                dmDeviceVO.setNetworkUnitName(dm.getNetworkUnitName());
                dmDeviceVO.setPosition(dm.getPosition());
                dmDeviceVO.setVideoAddr(dm.getVideoAddr());
                dmDeviceVO.setStatus(dm.getStatus());
                dmDeviceVO.setName(dm.getName());
                dmDeviceVO.setCode(dm.getCode());
                //将实时数据中的告警《信息》存入
                dmDeviceVO.setAlarmInfo(realTimingData.getLarmInfo());
                //设备状态目前是：在线、离线
                dmDeviceVO.setStatusValue(deviceMap.get(Integer.parseInt(dm.getStatus())));
                for (DmDeviceFunction dmDeviceFunction : deviceFunctionList) {
                    //功能标识符（和功能表中的功能编码一致）
                    String funCode = dmDeviceFunction.getFuncCode();
                    //产品类别
                    Integer typeId = dm.getTypeId();
                    switch (typeId) {
                        case 1://电器火灾
                            if (funCode.equals("DF003")) {
                                //1路温度
                                dmDeviceVO.setTemp1(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF004")) {
                                //2路温度
                                dmDeviceVO.setTemp2(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF005")) {
                                //3路温度
                                dmDeviceVO.setTemp3(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF006")) {
                                //4路温度
                                dmDeviceVO.setTemp4(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF007")) {
                                //A相电流
                                dmDeviceVO.setElectricA(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF008")) {
                                //B相电流
                                dmDeviceVO.setElectricB(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF009")) {
                                //C相电流
                                dmDeviceVO.setElectricC(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF010")) {
                                //A相电压
                                dmDeviceVO.setVoltageA(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF011")) {
                                //B相电压
                                dmDeviceVO.setVoltageB(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF012")) {
                                //C相电压
                                dmDeviceVO.setVoltageC(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF013")) {
                                //剩余电流
                                dmDeviceVO.setSurplusElectric(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF014")) {
                                //电弧
                                dmDeviceVO.setElectricArc(jsonObject.getString(funCode));
                            }
                            break;
                        case 2://视频监控 (设备表中的AlarmType)
                            dmDeviceVO.setAlarmInfo(realTimingData.getLarmInfo());
                            break;
                        case 3://NB烟感
                            if (funCode.equals("DF001")) {
                                //烟感温度
                                dmDeviceVO.setNbTemp(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF002")) {
                                //烟感浓度
                                dmDeviceVO.setNbSmokeScope(jsonObject.getString(funCode));
                            }
                            //将实时数据中的告警信息存入
                            dmDeviceVO.setAlarmInfo(realTimingData.getLarmInfo());
                            break;
                        case 4://火灾报警 (设备表中的AlarmType)
                            //将设备表中的告警《类型》存入
                            dmDeviceVO.setAlarmTypeValue(alarmMap.get(dm.getAlarmType()));
                            dmDeviceVO.setAlarmInfo(realTimingData.getLarmInfo());
                            break;
                        case 5://水系统
                            if (funCode.equals("DF015")) {
                                //液压
                                dmDeviceVO.setPressure(jsonObject.getString(funCode));
                            } else if (funCode.equals("DF016")) {
                                //液位
                                dmDeviceVO.setLevel(jsonObject.getString(funCode));
                            }
                            //将实时数据中的告警信息存入
                            dmDeviceVO.setAlarmInfo(realTimingData.getLarmInfo());
                            break;
                        case 6://其他系统 (设备表中的AlarmType)
                            //暂时不展示
                            // DF017   消火栓压力
                            // DF018   消火栓液位
                            // DF019   温度
                            // DF020  湿度
                            // DF021  流量
                            // DF022  静压
                            // DF023  差压
                            // DF024  振动
                            // DF025 水浸
                            // DF026 进压
                            // DF027 回压
                            // DF028 倾角
                            // if (funCode.equals("DF017")) {
                            //     //消火栓
                            //     dmDeviceVO.setFirePressure(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF004")) {
                            //     //消火栓
                            //     dmDeviceVO.setTemp2(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF005")) {
                            //     //消火栓
                            //     dmDeviceVO.setTemp3(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF006")) {
                            //     //消火栓
                            //     dmDeviceVO.setTemp4(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF007")) {
                            //     //消火栓
                            //     dmDeviceVO.setElectricA(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF008")) {
                            //     //消火栓
                            //     dmDeviceVO.setElectricB(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF009")) {
                            //     //消火栓
                            //     dmDeviceVO.setElectricC(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF010")) {
                            //     //消火栓
                            //     dmDeviceVO.setVoltageA(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF011")) {
                            //     //B相电压
                            //     dmDeviceVO.setVoltageB(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF012")) {
                            //     //C相电压
                            //     dmDeviceVO.setVoltageC(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF013")) {
                            //     //剩余电流
                            //     dmDeviceVO.setSurplusElectric(jsonObject.getString(funCode));
                            // } else if (funCode.equals("DF014")) {
                            //     //电弧
                            //     dmDeviceVO.setElectricArc(jsonObject.getString(funCode));
                            // }
                            break;
                    }
                }
                listVo.add(dmDeviceVO);
            }
        }
        page.setRecords(listVo);
        return page;
    }

    @Override
    public List<DmDevice> selectVideoDevice(DmDevice dmDevice) {
        return dmDeviceMapper.selectVideoDevice(dmDevice);
    }

    public Map<Integer, String> dictToMap(String type) {
        List<SysDictItem> listDict = commonFeign.getInterfaceDictByType(type);
        String s = JSON.toJSONString(listDict);
        List<SysDictItem> array = JSONArray.parseArray(s, SysDictItem.class);
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            Integer value = Integer.valueOf(array.get(i).getValue());
            String description = array.get(i).getDescription();
            map.put(value, description);
        }
        return map;
    }


    public DmDevice selectDmdeviceByImei(String imei) {
        return dmDeviceMapper.selectDmdeviceByImei(imei);
    }

    @Override
    public DmDevice selectDmdeviceByCode(String code) {
        return dmDeviceMapper.selectDmdeviceByCode(code);
    }

    @Override
    public void updateDeviceByImei(DmDevice dmDevice) {
        dmDeviceMapper.updateDeviceByImei(dmDevice);
    }

}
