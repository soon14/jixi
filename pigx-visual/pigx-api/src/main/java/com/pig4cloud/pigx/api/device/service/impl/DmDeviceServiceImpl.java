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
package com.pig4cloud.pigx.api.device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.device.dto.DmDeviceDto;
import com.pig4cloud.pigx.api.device.entity.*;
import com.pig4cloud.pigx.api.device.mapper.*;
import com.pig4cloud.pigx.api.device.service.DmDeviceService;
import com.pig4cloud.pigx.api.device.vo.DmDeviceGroupVO;
import com.pig4cloud.pigx.api.device.vo.DmDeviceVO;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.util.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备表
 *
 * @author lhd
 * @date 2019-06-20 10:15:47
 */
@Service
@AllArgsConstructor
public class DmDeviceServiceImpl extends ServiceImpl<DmDeviceMapper, DmDevice> implements DmDeviceService {

	private final DmDeviceMapper dmDeviceMapper;

	private final DmFunctionMapper dmFunctionMapper;

	private final DmDeviceFunctionMapper dmDeviceFunctionMapper;

	private final DmProductMapper dmProductMapper;

	private final SysDictItemService sysDictItemService;

	private final DmRealtimingDataMapper dmRealtimingDataMapper;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	@Override
	public List<DmDeviceVO> getDeviceList(Page page, DmDevice dmDevice,List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceList(page, dmDevice,unitIds);
	}

	@Override
	public boolean activate(String code) throws Exception {
		// 调用第三方设备激活接口
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IPage<List<DmDeviceVO>> getNoGroupDevice(Page page, DmDeviceGroupVO vo) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getNoGroupDevice(page, vo);
	}

	@Override
	public IPage<List<DmDeviceVO>> getGroupDevice(Page page, DmDeviceGroupVO vo) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getGroupDevice(page, vo);
	}

	@Override
	public boolean saveDevice(DmDevice dmDevice) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		boolean flag=false;
		//产品类型为空,通过产品id获取产品类别
				if(dmDevice.getTypeId()==null) {
					Integer typeId=dmProductMapper.getTypeIdByProdId(dmDevice.getProdId());
					dmDevice.setTypeId(typeId);
				}
		//保存设备信息
		flag=save(dmDevice);
		String appId = "";
		String secret = "";
		if(Constant.PROD_TYPE_REMOTE_VIDEO.equals(dmDevice.getTypeId())){
			//获取刻录机 及通道号后 获取直播地址
			DmDevice video = selectVideoDeviceInfo(dmDevice);
			dmDeviceMapper.updateDeviceByCode(video);
		}
		if(Constant.PROD_TYPE_NB_SMOKE.equals(dmDevice.getTypeId())){
			appId = "qPPuGRpnS7Ndvj07xFeqwwqqyL0a";
			secret = "PSvqX7O2XP2JQGYvEDRJRgB2gVIa";
			register(appId,secret,dmDevice.getId());
		}
		if(Constant.PROD_TYPE_WATER_SYS.equals(dmDevice.getTypeId())){
			if(dmDevice.getName()!=null && dmDevice.getName().contains("液压")){
				appId = "fXBTWZFtzrRIGeP6Gldvf48n2hEa";
				secret = "_SMku7T8IvWgrwCa4QTXpnlaUd8a";
			}else if(dmDevice.getName()!=null && dmDevice.getName().contains("液位")){
				appId = "vCGhTkZCwFfg_x2VVaqBCy2YHOUa";
				secret = "jeKW2KKBOPLaOrxB8z1aQj_mKk8a";
			}
			register(appId,secret,dmDevice.getId());
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
				entity.setUpperLimit(f.getUpperLimit());
				entity.setLowerLimit(f.getLowerLimit());
				entity.setVolumeSwitch(f.getVolumeSwitch());
				entity.setFuncCode(f.getCode());
				dmDeviceFunctionMapper.insert(entity);
			}
		}
		// 如果不为远程视频,可以选择关联视频设备
		if (!Constant.PROD_TYPE_REMOTE_VIDEO.equals(dmDevice.getTypeId())) {
			String addrs = dmDevice.getVideoAddr();
			if (StringUtils.isNotEmpty(addrs)) {
				String[] vids = addrs.split(",");
				for (String vid : vids) {
					if (vid == null)
						continue;
					flag=dmDeviceMapper.insertDeviceVideo(dmDevice.getId(), vid);
				}
			}
		}
		return flag;
	}

	@Override
	public boolean updateDevice(DmDevice dmDevice) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return this.updateById(dmDevice);
	}

	@Override
	public List<DmDevice> selectVideoDevice(DmDevice dmDevice) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.selectVideoDevice(dmDevice);
	}

	@Override
	public IPage<List<DmDevice>> getDmDeviceByUnitIds(Page page,List<String> list) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDmDeviceByUnitIds(page,list);
	}

	@Override
	public List<DmDevice> getDevicePostionByUnitId(String unitId,String buildId,String countyId) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDevicePostionByUnitId(unitId,buildId,countyId);
	}

	@Override
	public IPage<List<DmDevice>> queryDeviceInfo(Page page, DmDeviceDto dmDevice,List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceInfo(page,dmDevice,unitIds);
	}

	@Override
	public Integer queryDeviceCountByType(DmDevice dmDevice,List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.queryDeviceCountByType(dmDevice,unitIds);
	}

	@Override
	public DmDeviceVO getDeviceInfoById(String devId) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		DmDeviceVO vo = dmDeviceMapper.getDeviceInfoById(devId);
		if(vo==null){return null;}
		return getDeviceRealTimeData(vo);
	}

	public DmDeviceVO getDeviceRealTimeData(DmDeviceVO dm) throws Exception{
		 //获取字典表中的任务状态
        Map<String, String> alarmMap = sysDictItemService.getItemMap("alarm_type");
        //获取字典表中的设备在线状态
        Map<String, String> deviceMap = sysDictItemService.getItemMap("device_status");
        //切换数据源
        DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		//根据设备Id将设备功能关系表中的功能列表查出
        List<DmDeviceFunction> deviceFunctionList = dmDeviceFunctionMapper.findDeviceDataById(dm.getId());
        //根据设备编码将实时数据表中的最新的一条记录取出
        DmRealtimingData realTimingData = dmRealtimingDataMapper.selectLast(dm.getCode());
        if (realTimingData!=null) {
            String dataMsg = realTimingData.getRealtimingdata();
			JSONObject jsonObject =null;
            try {
            	jsonObject = JSONObject.parseObject(dataMsg);
			}catch(Exception e){
				e.printStackTrace();
				return dm;
			}
            //将实时数据中的告警《信息》存入
            dm.setAlarmInfo(realTimingData.getLarmInfo());
            //设备状态目前是：在线、离线
            dm.setStatusValue(deviceMap.get(Integer.parseInt(dm.getStatus())));
            for (DmDeviceFunction dmDeviceFunction : deviceFunctionList) {
                //功能标识符（和功能表中的功能编码一致）
                String funCode = dmDeviceFunction.getFuncCode();
                //产品类别
                Integer typeId = dm.getTypeId();
                switch (typeId) {
                    case 1://电器火灾
                        if (funCode.equals("DF003")) {
                            //1路温度
                            dm.setTemp1(jsonObject.getString(funCode));
                        } else if (funCode.equals("DF004")) {
                            //2路温度
                            dm.setTemp2(jsonObject.getString(funCode));
                        } else if (funCode.equals("DF005")) {
                            //3路温度
                            dm.setTemp3(jsonObject.getString(funCode));
                        } else if (funCode.equals("DF006")) {
                            //4路温度
                            dm.setTemp4(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF007")) {
                            //A相电流
                            dm.setElectricA(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF008")) {
                            //B相电流
                            dm.setElectricB(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF009")) {
                            //C相电流
                            dm.setElectricC(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF010")) {
                            //A相电压
                            dm.setVoltageA(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF011")) {
                            //B相电压
                            dm.setVoltageB(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF012")) {
                            //C相电压
                            dm.setVoltageC(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF013")) {
                            //剩余电流
                            dm.setSurplusElectric(jsonObject.getString(funCode));
                        }else if (funCode.equals("DF014")) {
                            //电弧
                            dm.setElectricArc(jsonObject.getString(funCode));
                        }
                        break;
                    case 2://视频监控 (设备表中的AlarmType)
                        break;
                    case 3://NB烟感
                        if (funCode.equals("DF001")) {
                            //烟感温度
                            dm.setNbTemp(jsonObject.getString(funCode));
                        }else if(funCode.equals("DF002")){
                            //烟感浓度
                            dm.setNbSmokeScope(jsonObject.getString(funCode));
                        }
                        //将实时数据中的告警信息存入
                        dm.setAlarmInfo(realTimingData.getLarmInfo());
                        break;
                    case 4://火灾报警 (设备表中的AlarmType)
                        //将设备表中的告警《类型》存入
                        dm.setAlarmTypeValue(alarmMap.get(dm.getAlarmType()));
                        break;
                    case 5://水系统
                        if (funCode.equals("DF015")) {
                            //液压
                            dm.setPressure(jsonObject.getString(funCode));
                        }else if(funCode.equals("DF016")){
                            //液位
                            dm.setLevel(jsonObject.getString(funCode));
                        }
                        //将实时数据中的告警信息存入
                        dm.setAlarmInfo(realTimingData.getLarmInfo());
                        break;
                    case 6://其他系统 (设备表中的AlarmType)
                        //暂时不展示
                        break;
                }
             }
            }
        return dm;
	}

	@Override
	public DmDeviceVO getDeviceInfoByCode(String devCode) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceInfoByCode(devCode);
	}

	@Override
	public List<DmDeviceVO> getDeviceInfoByCons(DmDevice dmDevice) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceInfoByCons(dmDevice);
	}

	@Override
	public List<DmDeviceVO> getDeviceInfoBigData(DmDevice dmDevice,List<String> unitIds)  throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceInfoBigData(dmDevice,unitIds);
	}

	@Override
	public String getRealtimingData(String dCode) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getRealtimingData(dCode);
	}

	@Override
	public List<Map<String,String>> getVideoAddressByDeviceId(String devId)  throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getVideoAddressByDeviceId(devId);
	}

	@Override
	public List<DmDeviceVO> getDeviceInfoByType(String typeId,List<String> unitIds)  throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getDeviceInfoByType(typeId,unitIds);
	}

	@Override
	public List<DmDeviceVO> getElectricalFireByDevCode(DmDevice dm) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		//存放数据
		List<DmDeviceVO> dataList=new ArrayList<DmDeviceVO>();
		//根据设备编码取出当天的实时数据
		List<DmRealtimingData> list=dmRealtimingDataMapper.getElectricalFireByDevCode(dm.getCode(),DateUtils.getTodayDate());
		//根据设备Id将设备功能关系表中的功能列表查出
        List<DmDeviceFunction> deviceFunctionList = dmDeviceFunctionMapper.findDeviceDataById(dm.getId());
		if(list!=null&&list.size()>0) {
			for(DmRealtimingData realTimingData:list) {
				if(realTimingData==null)continue;
				String dataMsg = realTimingData.getRealtimingdata();
				JSONObject jsonObject = JSONObject.parseObject(dataMsg);
				DmDeviceVO vo=new DmDeviceVO();
				//设置发送时间
				vo.setSendTime(realTimingData.getSendTime());
				  for (DmDeviceFunction dmDeviceFunction : deviceFunctionList) {
		                //功能标识符（和功能表中的功能编码一致）
		                String funCode = dmDeviceFunction.getFuncCode();
		                if(funCode==null)continue;
		                        if (funCode.equals("DF003")) {
		                            //1路温度
		                            vo.setTemp1(jsonObject.getString(funCode));
		                        } else if (funCode.equals("DF004")) {
		                            //2路温度
		                            vo.setTemp2(jsonObject.getString(funCode));
		                        } else if (funCode.equals("DF005")) {
		                            //3路温度
		                            vo.setTemp3(jsonObject.getString(funCode));
		                        } else if (funCode.equals("DF006")) {
		                            //4路温度
		                            vo.setTemp4(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF007")) {
		                            //A相电流
		                            vo.setElectricA(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF008")) {
		                            //B相电流
		                            vo.setElectricB(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF009")) {
		                            //C相电流
		                            vo.setElectricC(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF010")) {
		                            //A相电压
		                            vo.setVoltageA(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF011")) {
		                            //B相电压
		                            vo.setVoltageB(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF012")) {
		                            //C相电压
		                            vo.setVoltageC(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF013")) {
		                            //剩余电流
		                            vo.setSurplusElectric(jsonObject.getString(funCode));
		                        }else if (funCode.equals("DF014")) {
		                            //电弧
		                            vo.setElectricArc(jsonObject.getString(funCode));
		                        }
		             }
				  dataList.add(vo);
		            }

		}
		return dataList;
	}

	/**
	  *    获取access token
	 * @return
	 */
	@Override
	public String getAccesstoken() {
		return selectAccessToken();
	}
	/**
	 * 查询accessToken
	 */
	public  String selectAccessToken(){
		String asstokens = redisTemplate.opsForValue().get("accessToken");
		if (asstokens != null && !"".equals(asstokens)){
			String getcode = getcode(asstokens);
			if("10002".equals(getcode) ){
				asstokens = getAccessToken();
			}
		}else{
			asstokens = getAccessToken();
		}
		return asstokens;
	}

	public  String getcode(String asstokens){
		HttpClient httpClient;
		HttpPost postMethod;
		HttpResponse response;
		String reponseContent = null;
		TrafficTotalEntity trafficTotalEntity = null;
		String url = "https://open.ys7.com/api/lapp/traffic/user/total?accessToken="+asstokens;
		try {
			httpClient = HttpClients.createDefault();
			postMethod = new HttpPost(url);
			// 设置请求头
			postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
			response = httpClient.execute(postMethod);
			HttpEntity httpEntity = response.getEntity();
			reponseContent = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
			trafficTotalEntity = JSON.parseObject(reponseContent, TrafficTotalEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trafficTotalEntity.getCode();
	}

	public String getAccessToken(){
		AccessTokenEntity accessTokenEntity = null;
		HttpClient httpClient;
		HttpPost postMethod;
		HttpResponse response;
		String reponseContent = null;
		String appKey = "a1c6f458fb7b467a8decaf364476187f";
		String appSecret = "285daf2788e5a5b647c796590307e744";
		String url = "https://open.ys7.com/api/lapp/token/get?appKey="+appKey+"&appSecret="+appSecret;
		try {
			httpClient = HttpClients.createDefault();
			postMethod = new HttpPost(url);
			postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
			response = httpClient.execute(postMethod);
			HttpEntity httpEntity = response.getEntity();
			reponseContent = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
			accessTokenEntity = JSON.parseObject(reponseContent, AccessTokenEntity.class);
			redisTemplate.opsForValue().set("accessToken",accessTokenEntity.getData().getAccessToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessTokenEntity.getData().getAccessToken();
	}

	@Override
	public Integer getTotalCountByUnitId(String unitId) {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getTotalCountByUnitId(unitId);
	}

	@Override
	public Integer getIntactCountByUnitId(String unitId) {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.getIntactCountByUnitId(unitId);
	}

	@Override
	public List<Map> findDeviceStatusGroup(List<String> unitIds) {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.findDeviceStatusGroup(unitIds);
	}

	@Override
	public List<Map> findDeviceAlarmStatus(List<String> unitIds) {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.findDeviceAlarmStatus(unitIds);
	}

	@Override
	public List<DmDeviceVO> findDeviceByStatus(String alarmType,String status, List<String> unitIds) throws Exception{
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.findDeviceByStatus(alarmType,status,unitIds);
	}

	@Override
	public List<DmDeviceVO> findDeviceByFireStatus(List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.findDeviceByFireStatus(unitIds);
	}

	@Override
	public List<DmDeviceVO> queryVideoAddrByUnitId(List<String> unitIds) throws Exception {
		DynamicDataSourceContextHolder.setDataSourceType(Constant.DATA_SOURCE_DEVICE);
		return dmDeviceMapper.queryVideoAddrByUnitId(unitIds);
	}

	public  DmDevice selectVideoDeviceInfo(DmDevice device){
		String asstokens = selectAccessToken();
		System.out.println("token:" + asstokens);
		System.out.println("source:" + device.getRemark());
		DmDevice dmDevice = new DmDevice();
		openLive(asstokens,device.getRemark());
		EzvizVideoDeviceInfoEntity ezvizVideoDeviceInfoEntity = selectLiveAddress(asstokens, device.getRemark());
		if (ezvizVideoDeviceInfoEntity != null && ezvizVideoDeviceInfoEntity.getData().get(0) != null && "200".equals(ezvizVideoDeviceInfoEntity.getData().get(0).getRet())){
			dmDevice.setVideoAddr(ezvizVideoDeviceInfoEntity.getData().get(0).getHls());
			dmDevice.setVideoHdAddr(ezvizVideoDeviceInfoEntity.getData().get(0).getHlsHd());
			dmDevice.setCode(device.getCode());
			EzvizVideoDeviceStatusEntity ezvizVideoDeviceStatusEntity = selectDeviceStatus(asstokens, device.getRemark());
			if(ezvizVideoDeviceStatusEntity != null && "200".equals(ezvizVideoDeviceStatusEntity.getCode())){
				dmDevice.setStatus(ezvizVideoDeviceStatusEntity.getData().getStatus().toString());
			}
		}
		return dmDevice;
	}

	/**
	 * 开通直播功能
	 */
	public  String openLive(String asstokens,String source){
		HttpClient httpClient;
		HttpPost postMethod;
		String code = "";
		String url = "https://open.ys7.com/api/lapp/live/video/open?accessToken="+asstokens+"&source="+source;
		try {
			httpClient = HttpClients.createDefault();
			postMethod = new HttpPost(url);
			// 设置请求头
			postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpClient.execute(postMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	/**
	 * 获取直播地址
	 */
	public  EzvizVideoDeviceInfoEntity selectLiveAddress(String asstokens,String source){
		EzvizVideoDeviceInfoEntity ezvizVideoDeviceInfoEntity = null;
		HttpClient httpClient;
		HttpPost postMethod;
		HttpResponse response;
		String reponseContent = null;
		String url = "https://open.ys7.com/api/lapp/live/address/get?accessToken="+asstokens+"&source="+source;
		try {
			httpClient = HttpClients.createDefault();
			postMethod = new HttpPost(url);
			// 设置请求头
			postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpClient.execute(postMethod);
			response = httpClient.execute(postMethod);
			HttpEntity httpEntity = response.getEntity();
			reponseContent = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
			ezvizVideoDeviceInfoEntity = JSON.parseObject(reponseContent, EzvizVideoDeviceInfoEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ezvizVideoDeviceInfoEntity;
	}

	/**
	 * 获取设备状态
	 */
	public  EzvizVideoDeviceStatusEntity selectDeviceStatus(String asstokens,String deviceSerial){
		EzvizVideoDeviceStatusEntity ezvizVideoDeviceStatusEntity = null;
		HttpClient httpClient;
		HttpPost postMethod;
		HttpResponse response;
		String url = "https://open.ys7.com/api/lapp/device/info?accessToken="+asstokens+"&deviceSerial="+deviceSerial;
		try {
			httpClient = HttpClients.createDefault();
			postMethod = new HttpPost(url);
			// 设置请求头
			postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpClient.execute(postMethod);
			response = httpClient.execute(postMethod);
			HttpEntity httpEntity = response.getEntity();
			String reponseContent = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
			ezvizVideoDeviceStatusEntity = JSON.parseObject(reponseContent, EzvizVideoDeviceStatusEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ezvizVideoDeviceStatusEntity;
	}

	public void register(String appId,String secret,String id){
		DmDevice dmdevice = dmDeviceMapper.selectById(id);

		String nodeId = dmdevice.getCode();
		// 注册到电信平台
		HttpsUtil httpsUtil = new HttpsUtil();
		try {
			httpsUtil.initSSLConfigForTwoWay();
			// 获取token
			String accessToken = login(httpsUtil,appId,secret);
			String urlRegister = "https://device.api.ct10649.com/iocm/app/reg/v1.1.0/deviceCredentials";

			Map<String, Object> paramSubscribe = new HashMap<>();
			paramSubscribe.put("verifyCode", nodeId);
			paramSubscribe.put("nodeId", nodeId);
			String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);
			Map<String, String> header = new HashMap<>();
			header.put("app_key", appId);
			header.put("Authorization", "Bearer" + " " + accessToken);
			HttpResponse httpResponse = httpsUtil.doPostJson(urlRegister, header, jsonRequest);
			String backData = httpsUtil.getHttpResponseBody(httpResponse);
			System.out.println(backData);
			// 解析电信平台返回的Json数据
			JSONObject JSONObj = StrToJson.strToJsonObj(backData);
			String imei = JSONObj.getString("deviceId");
			String verifyCode = JSONObj.getString("verifyCode");
			String timeout = JSONObj.getString("timeout");
			// String psk = JSONObj.getString("psk");
			// 注册成功后把电信平台返回的设备唯一标识更新到设备表中。
			if (imei == null || imei == "") {
				System.out.println("注册失败，请检查设备是否已注册");
			}
			// 修改注册的设备信息
			Map<String, Object> updateParam = new HashMap<>();
			// 注册时，电信平台返回的deviceId
			updateParam.put("deviceId", imei);
			updateParam.put("appId", appId);
			updateParam.put("name", dmdevice.getName());
			// 设备上报数据平台是否会冻结（即保存或不保存）
			// updateParam.put("mute", "\"false\"");
			// 设备型号
			updateParam.put("model", "NBIoTDevice");
			updateParam.put("protocolType", "CoAP");
			// 厂商名称
			updateParam.put("manufacturerName", "Peasway42");
			// 厂商ID唯一标识一个厂商
			updateParam.put("manufacturerId", "Peasway42");
			// deviceType即profile这个不能随便写，上传的profile中的deviceType是什么，这就就是什么
			updateParam.put("deviceType", "SmokeAlarm");

			updateDeviceInfo(updateParam, imei,appId,secret);
			DmDevice dmDevice = new DmDevice();
			dmDevice.setImei(imei);
			dmDevice.setStatus("1");
			dmDevice.setCode(dmdevice.getCode());
			dmDeviceMapper.updateDeviceByCode(dmDevice);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public String login(HttpsUtil httpsUtil,String appId,String secret) throws Exception {
		String urlLogin = "https://device.api.ct10649.com/iocm/app/sec/v1.1.0/login";
		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);
		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);
		Map<String, String> data = new HashMap<>();
		data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
		return data.get("accessToken");
	}

	/**
	 * 修改注册到电信平台的设备
	 *
	 * @param updateParam
	 * @param deviceId
	 * @throws Exception
	 */
	public void updateDeviceInfo(Map<String, Object> updateParam, String deviceId,String appId,String secret) throws Exception {
		// ssl认证
		HttpsUtil httpsUtil = sslAuthentication();
		// 获取token
		String accessToken = login(httpsUtil,appId,secret);
		StringBuilder updateDeviceInfoURL = new StringBuilder("https://device.api.ct10649.com");
		updateDeviceInfoURL.append("/iocm/app/dm/v1.4.0/devices/" + deviceId + "?appId=" + appId);

		String jsonRequest = JsonUtil.jsonObj2Sting(updateParam);
		Map<String, String> header = new HashMap<>();
		header.put("app_key", appId);
		header.put("Authorization", "Bearer" + " " + accessToken);
		HttpResponse httpResponse = httpsUtil.doPutJson(updateDeviceInfoURL.toString(), header, jsonRequest);
		String backData = httpsUtil.getHttpResponseBody(httpResponse);
	}

	public HttpsUtil sslAuthentication() throws Exception {
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		return httpsUtil;
	}

}
