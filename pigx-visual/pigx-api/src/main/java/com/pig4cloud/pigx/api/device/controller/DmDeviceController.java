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

package com.pig4cloud.pigx.api.device.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.device.dto.DmDeviceDto;
import com.pig4cloud.pigx.api.device.entity.DmDevice;
import com.pig4cloud.pigx.api.device.service.DmDeviceService;
import com.pig4cloud.pigx.api.device.vo.DmDeviceVO;
import com.pig4cloud.pigx.api.device.vo.DmRDataVO;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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
@RestController
@AllArgsConstructor
@RequestMapping("/dmdevice")
@Api(value = "dmDevice", tags = "dmdevice管理")
public class DmDeviceController {

	private final DmDeviceService dmDeviceService;

	private final BsNetworkingUnitService bsNetworkingUnitService;


	private final SysUserService sysUserService;

	private final SysDictItemService sysDictItemService;

	/**
	 * 登录用户所属机构下的所有设备
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/getDmDeviceByUnitIds")
	public R getDmDeviceByUnitIds(Page page,String unitIds) {
		if(StringUtils.isEmpty(unitIds)) {
			return new R<>().setCode(1).setMsg("联网单位不能不空");
		}
		List<String> list=new ArrayList<String>();
		String[] uds=unitIds.split("_");
		for(String id:uds) {
			if(id==null)continue;
			list.add(id);
		}
		IPage<List<DmDevice>> iPageList=null;
		try {
			iPageList=dmDeviceService.getDmDeviceByUnitIds(page,list);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(iPageList);
	}

	/**
	 * 新增设备表
	 *
	 * @param dmDevice 设备表
	 * @return R
	 */
	@SysLog("新增设备表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_dmdevice_add')")
	public R save(@Valid @RequestBody DmDevice dmDevice) {
		PigxUser user = SecurityUtils.getUser();
		dmDevice.setCreateUserId(user.getId());
		dmDevice.setCreateUser(user.getUsername());
		try {
			dmDevice.setAlarmType(Constant.ALARM_TYPE_NORMAL);
			dmDevice.setStatus(Constant.DEVICE_STATUS_ON_LINE.toString());
			dmDeviceService.saveDevice(dmDevice);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(true);
	}

	/**
	 * 根据联网单位Id查询设备位置信息
	 * @param unitId
	 * @return
	 */
	@GetMapping("/getDevicePostionByUnitId")
    public R getDevicePostionByUnitId(String unitId,String buildId,String countyId) {
		List<DmDevice> list=null;
		try {
			list=dmDeviceService.getDevicePostionByUnitId(unitId,buildId,countyId);
		}catch(Exception e) {
			return new R<>(e);
		}
		return  new R<>(list);
    }

	/**
	   * 分页查询
	 *
	 * @param page     分页对象
	 * @param dmDeviceVO 设备表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmDevicePage(Page page, DmDeviceVO dmDeviceVO) {
		try {
		List<String> unitIds=sysUserService.getUserLoingUnitIds();
		List<DmDeviceVO> list=dmDeviceService.getDeviceList(page, dmDeviceVO,unitIds);
		//查询产品类别数据字典
    	Map<String,String> mp=sysDictItemService.getItemMap("product_category");
    	//查询设备状态数据字典
		Map<String,String> ds=sysDictItemService.getItemMap("device_status");
		for(DmDeviceVO dm : list){
			if(dm==null)continue;
			dm.setProductTypeName(mp.get(dm.getTypeId()+""));
			dm.setStatusName(ds.get(dm.getStatus()));
		}
		page.setRecords(list);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(page);
	}
	/**
	  * 根据查询条件查询设备信息列表
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/queryDeviceInfo")
	public R queryDeviceInfo(Page page,DmDeviceDto dmDevice) {
		IPage<List<DmDevice>> list=null;
		try {
    	List<String> unitIds=new ArrayList<String>();
		//如果地区Id为空,获取登录用户下的联网单位,否则获取地区下的联网单位Id
		if(StringUtils.isEmpty(dmDevice.getAreaId())) {
	    		unitIds.addAll(sysUserService.getUserLoingUnitIds());

		}else {
			unitIds.addAll(bsNetworkingUnitService.getUnitIdByAreaId(dmDevice.getAreaId(),sysUserService.getUserLoingUnitIds()));
		}
		list=dmDeviceService.queryDeviceInfo(page, dmDevice,unitIds);
	  }catch(Exception e) {
		return new R<>(e);
	}
		return  new R<>(list);
	}

	/**
	   * 根据设备类型查询设备数量
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/queryDeviceCountByType")
	public R queryDeviceCountByType(DmDevice dmDevice) {
		Integer count=0;
      try {
    	  count=dmDeviceService.queryDeviceCountByType(dmDevice,sysUserService.getUserLoingUnitIds());
      }catch(Exception e) {
    	  return new R<>(e);
      }
		return  new R<>(count);
	}

	/**
	    * 查询设备数量根据报警类型
	 *
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/queryDeviceCountByAlarmType")
	public R queryDeviceCountByAlarmType(DmDevice dmDevice) {
		Integer count=0;
		try {
	    	  count=dmDeviceService.queryDeviceCountByType(dmDevice,sysUserService.getUserLoingUnitIds());
	      }catch(Exception e) {
	    	  return new R<>(e);
	      }
		return  new R<>(count);
	}
	/**
	    * 查询设备数量根据设备状态
	 *
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/queryDeviceCountByStatus")
	public R queryDeviceCountByStatus(DmDevice dmDevice) {
		Integer count=0;
	      try {
	    	  count=dmDeviceService.queryDeviceCountByType(dmDevice,sysUserService.getUserLoingUnitIds());
	      }catch(Exception e) {
	    	  return new R<>(e);
	      }
		return  new R<>(count);
	}

	/**
	 * 通过id查询设备表
	 *
	 * @param  devId
	 * @return R
	 */
	@GetMapping("/getDeviceInfoById")
	public R getById(String devId) {
		if (StringUtils.isEmpty(devId)){
			return new R().setCode(0).setMsg("请选一条记录");
		}
		DmDeviceVO vo=null;
	      try {
	    	  vo=dmDeviceService.getDeviceInfoById(devId);
	      }catch(Exception e) {
	    	  return new R<>(e);
	      }
		return new R<>(vo);
	}
	/**
	   *  获取电器火灾的实时数据根据设备编码
	 * @param dm
	 * @return
	 */
	@GetMapping("/getElectricalFireByDevCode")
	public R getElectricalFireByDevCode(DmDevice dm) {
		List<DmDeviceVO> vos=null;
	      try {
	    	  vos=dmDeviceService.getElectricalFireByDevCode(dm);
	      }catch(Exception e) {
	    	  return new R<>(e);
	      }
		return new R<>(vos);
	}
	/**
	    * 查询设备信息根据联网单位、建筑、楼层、位置
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/getDeviceInfoByCons")
	public R getDeviceInfoByCons(DmDevice dmDevice) {
		List<DmDeviceVO> vos=null;
		 try {
	    	  vos=dmDeviceService.getDeviceInfoByCons(dmDevice);
	      }catch(Exception e) {
	    	  return new R<>(e);
	      }
		return new R<>(vos);
	}

	/**
	 * 获取登录用户下所有设备信息
	 *
	 */
	@GetMapping("/getDeviceInfoBigData")
	public R getDeviceInfoBigData(DmDevice dmDevice) {
		List<DmDeviceVO> vos;
		List<String> list=new ArrayList<>();
		try {
			//获取当前登录用户的联网单位的集合
			if(StringUtils.isEmpty(dmDevice.getNetworkUnitId())){
				list=sysUserService.getUserLoingUnitIds();
			}else{
				list.add(dmDevice.getNetworkUnitId());
			}
			vos=dmDeviceService.getDeviceInfoBigData(dmDevice,list);
			//循环设置联网单位地址
			for(DmDeviceVO vo:vos){
				vo.setUnitAdress(bsNetworkingUnitService.getUnitById(vo.getNetworkUnitId()).getAddress());
				//查询视频设备 如无地址 请求萤石云后并保存至数据库
//				if(dmDevice.getTypeId().equals(Constant.PROD_TYPE_REMOTE_VIDEO)){
//					if(StringUtils.isNotBlank(vo.getVideo())){
//						dmDeviceService.selectLiveAddress();
//					}
//				}
			}
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(vos);

	}

	/**
	 * 获取设备实时数据 最新一条
	 * @param dCode	设备编号
	 * @return
	 */
	@GetMapping("/getRealtimingData")
	public R getRealtimingData(String dCode) {
		String realtimingData = null;
		try {
			realtimingData = dmDeviceService.getRealtimingData(dCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DmRDataVO vo = new DmRDataVO();
		if(StringUtils.isNotBlank(realtimingData)){
			String[] data = realtimingData.substring(1, 13).split(":");
			vo.setDType(data[0]);
			vo.setRData(data[1]);
		}
		return new R<>(vo);
	}

	/**
	 * 根据设备id,查询设备所属视频地址
	 * @param devId
	 * @return
	 */
	@GetMapping("/getVideoAddressByDeviceId")
	public R getVideoAddressByDeviceId(String devId) {
		List<Map<String,String>> list=null;
		 try {
			 list=dmDeviceService.getVideoAddressByDeviceId(devId);
		      }catch(Exception e) {
		    	  return new R<>(e);
		      }
		return new R<>(list);
	}

	/**
	 * 查询设备数量
	 * @return
	 */
	@GetMapping("/queryDeviceCount")
	public R queryDeviceCount(){
		Map map=new HashMap();
		try {
			DmDevice dm=new DmDevice();
			List<String> unitIds=sysUserService.getUserLoingUnitIds();
			//接入设备总数量
			int totalCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
			map.put("totalCount",totalCount);
			//报警数量
			dm.setAlarmType(Constant.ALARM_TYPE_WARNING);
			int alarmCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
			map.put("alarmCount",alarmCount);
			//故障数量
			dm.setAlarmType(Constant.ALARM_TYPE_FAULT);
			int faultCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
			map.put("faultCount",faultCount);
			//电气火灾
			dm.setAlarmType(null);
			dm.setTypeId(Constant.PROD_TYPE_ELECTRIC_FIRE);
			int electricFireCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("electricFireCount",electricFireCount);
			//视频
			dm.setTypeId(Constant.PROD_TYPE_REMOTE_VIDEO);
			int videoCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("videoCount",videoCount);
			//NB烟感
			dm.setTypeId(Constant.PROD_TYPE_NB_SMOKE);
			int smokeCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("smokeCount",smokeCount);
			//火灾报警
			dm.setTypeId(Constant.PROD_TYPE_FIRE_ALARM);
			int fireAlarmCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("fireAlarmCount",fireAlarmCount);
			//水系统
			dm.setTypeId(Constant.PROD_TYPE_WATER_SYS);
			int waterCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("waterCount",waterCount);
			//其他系统
			dm.setTypeId(Constant.PROD_TYPE_OTHER_SYS);
			int otherCount=dmDeviceService.queryDeviceCountByType(dm,unitIds);
			map.put("otherCount",otherCount);
		}catch(Exception e){
			return new R<>(e);
		}
        return new R<>(map);
	}
	/**
	 * 查询联网单位接入设备
	 */
	@GetMapping("/getUnitDeviceCount")
	public R getUnitDeviceCount(String unitId){
		Map map=new HashMap();
		DmDevice dm=new DmDevice();
		try{
		List<String> unitIds=new ArrayList<>();
		unitIds.add(unitId);
		if(!StringUtils.isEmpty(unitId)) {
			NetworkingUnitVo vo = bsNetworkingUnitService.getUnitById(unitId);
			map.put("unitName",vo.getName());
		}
		//接入设备总数量
		int totalCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
		map.put("totalCount",totalCount);
		//报警数量
		dm.setAlarmType(Constant.ALARM_TYPE_WARNING);
		int alarmCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
		map.put("alarmCount",alarmCount);
		//故障数量
		dm.setAlarmType(Constant.ALARM_TYPE_FAULT);
		int faultCount=dmDeviceService.queryDeviceCountByType(dm, unitIds);
		map.put("faultCount",faultCount);
	}catch(Exception e){
		return new R<>(e);
	}
        return new R<>(map);
	}

	/**
	 * 查询设备信息及联网单位信息根据设备编码
	 * @param deviceCode
	 * @return
	 */
	@GetMapping("/getDeviceByCode")
	public R getDeviceByCode(String deviceCode)  {
		DmDeviceVO vo=null;
		try{
			vo=dmDeviceService.getDeviceInfoByCode(deviceCode);
			if(vo!=null&&!StringUtils.isEmpty((vo.getNetworkUnitId()))){
				NetworkingUnitVo unitVo=bsNetworkingUnitService.getUnitById(vo.getNetworkUnitId());
				if(unitVo!=null){
					vo.setContacts(unitVo.getContacts());
					vo.setTelephone(unitVo.getTelephone());
				}
			}
		}catch(Exception e){
			return new R<>(e);
		}
		return new R<>(vo);
	}

	/**
	 * 获取设备信息根据产品类别
	 * @return
	 */
	@GetMapping("/getDeviceInfoByType")
	public R getDeviceInfoByType(String typeId) {
		List<DmDeviceVO> list=new ArrayList<DmDeviceVO>();
		try {
		//灭火器
		if(Constant.OUTFIRE_RESOURCE_FIRE_EXTINGUISHER.equals(typeId)) {
			DmDeviceVO vo=new DmDeviceVO();
			vo.setId("12345678978888888");
			vo.setCode("201908291607001");
			vo.setName("灭火器");
			vo.setTypeId(Integer.parseInt(typeId));
			vo.setNetworkUnitId("2222223541");
			vo.setNetworkUnitName("北京东霖消防");
			vo.setBuildId("45666");
			vo.setBuildName("OBE 4");
			vo.setCountyId("56655554");
			vo.setCountyName("四层");
			vo.setPosition("电梯旁");
			vo.setLongitude(new BigDecimal(116.531059));
			vo.setLatitude(new BigDecimal(39.81176));
			vo.setAlarmType(1);
			vo.setVideoAddr("");
			vo.setVideoHdAddr("");
			vo.setType("outfire_resource");
			list.add(vo);
		//视频监控
		}else if(Constant.OUTFIRE_RESOURCE_FIRE_VIDEO.equals(typeId)) {
		 list.addAll(dmDeviceService.getDeviceInfoByType(typeId,sysUserService.getUserLoingUnitIds()));
		//市政消防栓
		}else if(Constant.OUTFIRE_RESOURCE_FIRE_MUNICIPALFIREHYDRANT.equals(typeId)) {
			DmDeviceVO vo1=new DmDeviceVO();
			vo1.setId("12345678979999999");
			vo1.setCode("201908291621001");
			vo1.setName("市政消防栓");
			vo1.setTypeId(Integer.parseInt(typeId));
			vo1.setNetworkUnitId("2222223541");
			vo1.setNetworkUnitName("北京东霖消防");
			vo1.setBuildId("45666");
			vo1.setBuildName("OBE 4");
			vo1.setCountyId("56655554");
			vo1.setCountyName("四层");
			vo1.setPosition("电梯旁");
			vo1.setLongitude(new BigDecimal(116.535209));
			vo1.setLatitude(new BigDecimal(39.806085));
			vo1.setAlarmType(1);
			vo1.setVideoAddr("");
			vo1.setVideoHdAddr("");
			vo1.setType("outfire_resource");
			list.add(vo1);
		//非市政消防栓
		}else if(Constant.OUTFIRE_RESOURCE_FIRE_NOMUNICIPALFIREHYDRANT.equals(typeId)) {
			DmDeviceVO vo2=new DmDeviceVO();
			vo2.setId("12345678977777777");
			vo2.setCode("201908291622001");
			vo2.setName("非市政消防栓");
			vo2.setTypeId(Integer.parseInt(typeId));
			vo2.setNetworkUnitId("2222223541");
			vo2.setNetworkUnitName("北京东霖消防");
			vo2.setBuildId("45666");
			vo2.setBuildName("OBE 4");
			vo2.setCountyId("56655554");
			vo2.setCountyName("四层");
			vo2.setPosition("电梯旁");
			vo2.setLongitude(new BigDecimal(116.536741));
			vo2.setLatitude(new BigDecimal(39.805663));
			vo2.setAlarmType(1);
			vo2.setVideoAddr("");
			vo2.setVideoHdAddr("");
			vo2.setType("outfire_resource");
			list.add(vo2);
		//消防水池
		}else if(Constant.OUTFIRE_RESOURCE_FIRE_FIREPOOl.equals(typeId)) {
			list.addAll(dmDeviceService.getDeviceInfoByType(typeId,sysUserService.getUserLoingUnitIds()));
		}else {
			DmDeviceVO vo=new DmDeviceVO();
			vo.setId("12345678978888888");
			vo.setCode("201908291607001");
			vo.setName("灭火器");
			vo.setTypeId(Integer.parseInt(typeId));
			vo.setNetworkUnitId("2222223541");
			vo.setNetworkUnitName("北京东霖消防");
			vo.setBuildId("45666");
			vo.setBuildName("OBE 4");
			vo.setCountyId("56655554");
			vo.setCountyName("四层");
			vo.setPosition("电梯旁");
			vo.setLongitude(new BigDecimal(116.531059));
			vo.setLatitude(new BigDecimal(39.81176));
			vo.setAlarmType(1);
			vo.setVideoAddr("");
			vo.setVideoHdAddr("");
			vo.setType("outfire_resource");
			list.add(vo);
			DmDeviceVO vo1=new DmDeviceVO();
			vo1.setId("12345678979999999");
			vo1.setCode("201908291621001");
			vo1.setName("市政消防栓");
			vo1.setTypeId(Integer.parseInt(typeId));
			vo1.setNetworkUnitId("2222223541");
			vo1.setNetworkUnitName("北京东霖消防");
			vo1.setBuildId("45666");
			vo1.setBuildName("OBE 4");
			vo1.setCountyId("56655554");
			vo1.setCountyName("一层");
			vo1.setPosition("大门旁");
			vo1.setLongitude(new BigDecimal(116.535209));
			vo1.setLatitude(new BigDecimal(39.806085));
			vo1.setAlarmType(1);
			vo1.setVideoAddr("");
			vo1.setVideoHdAddr("");
			vo1.setType("outfire_resource");
			list.add(vo1);
			DmDeviceVO vo2=new DmDeviceVO();
			vo2.setId("12345678977777777");
			vo2.setCode("201908291622001");
			vo2.setName("非市政消防栓");
			vo2.setTypeId(Integer.parseInt(typeId));
			vo2.setNetworkUnitId("2222223541");
			vo2.setNetworkUnitName("北京东霖消防");
			vo2.setBuildId("45666");
			vo2.setBuildName("OBE 4");
			vo2.setCountyId("56655554");
			vo2.setCountyName("地下一层");
			vo2.setPosition("电梯旁");
			vo2.setLongitude(new BigDecimal(116.536741));
			vo2.setLatitude(new BigDecimal(39.805663));
			vo2.setAlarmType(1);
			vo2.setVideoAddr("");
			vo2.setVideoHdAddr("");
			vo2.setType("outfire_resource");
			list.add(vo2);
			list.addAll(dmDeviceService.getDeviceInfoByType(Constant.OUTFIRE_RESOURCE_FIRE_VIDEO,sysUserService.getUserLoingUnitIds()));
			list.addAll(dmDeviceService.getDeviceInfoByType(Constant.OUTFIRE_RESOURCE_FIRE_FIREPOOl,sysUserService.getUserLoingUnitIds()));
		}
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	/**
	  *    获取access token
	 * @return
	 */
	@GetMapping("/getAccesstoken")
	@Inner(value = false)
	public R getAccesstoken(){
		return new R<>(dmDeviceService.getAccesstoken());
	}


	@GetMapping("/findDeviceStatusGroup" )
	public R findDeviceStatusGroup() throws Exception {
		List<Map> list=null;
		try {
			List<String> unitIds = sysUserService.getUserLoingUnitIds();
			list = dmDeviceService.findDeviceStatusGroup(unitIds);
		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	/**
	 * 设备故障、真是火情（近一周）、离线统计
	 * @return
	 */
	@GetMapping("/findDeviceAlarmStatus" )
	public R findDeviceAlarmStatus() throws Exception {
		List<Map> list=null;
		try {
			List<String> unitIds = sysUserService.getUserLoingUnitIds();
			list = dmDeviceService.findDeviceAlarmStatus(unitIds);
		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	@GetMapping("/findDeviceByStatus" )
	public R findDeviceByStatus(String alarmType,String status) throws Exception {
		List<DmDeviceVO> list=null;
		try {
			List<String> unitIds = sysUserService.getUserLoingUnitIds();
			list = dmDeviceService.findDeviceByStatus(alarmType,status,unitIds);
			for (DmDeviceVO dmDeviceVO : list){
				NetworkingUnitVo unit = bsNetworkingUnitService.getUnitById(dmDeviceVO.getNetworkUnitId());
				dmDeviceVO.setContacts(unit.getContacts());
				dmDeviceVO.setTelephone(unit.getTelephone());
			}

		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}


	@GetMapping("/findDeviceByFireStatus" )
	public R findDeviceByFireStatus() throws Exception {
		List<DmDeviceVO> list=null;
		try {
			List<String> unitIds = sysUserService.getUserLoingUnitIds();
			list = dmDeviceService.findDeviceByFireStatus(unitIds);
			for (DmDeviceVO dmDeviceVO : list){
				NetworkingUnitVo unit = bsNetworkingUnitService.getUnitById(dmDeviceVO.getNetworkUnitId());
				dmDeviceVO.setContacts(unit.getContacts());
				dmDeviceVO.setTelephone(unit.getTelephone());
			}

		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	/**
	 * 查询视频地址
	 * @param unitId
	 * @return
	 */
	@GetMapping("/queryVideoAddrByUnitId")
	public R queryVideoAddrByUnitId(String unitId){
		List<DmDeviceVO> list=null;
		List<String> unitIds =new ArrayList<>();
		try {
			if(StringUtils.isEmpty(unitId)){
				unitIds = sysUserService.getUserLoingUnitIds();
			}else{
				unitIds.add(unitId);
			}
			list = dmDeviceService.queryVideoAddrByUnitId(unitIds);
		} catch (Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

}
