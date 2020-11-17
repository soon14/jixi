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

package com.pig4cloud.pigx.device.generator.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.common.security.service.PigxUser;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import com.pig4cloud.pigx.device.generator.common.Constant;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;
import com.pig4cloud.pigx.device.generator.entity.HttpPostBody;
import com.pig4cloud.pigx.device.generator.feign.AlarmFeign;
import com.pig4cloud.pigx.device.generator.service.DeviceService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

import com.pig4cloud.pigx.device.generator.vo.DmDeviceGroupVO;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 设备表
 *
 * @author zm
 * @date 2019-07-25 10:10:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/device")
@Api(value = "device", tags = "device管理")
public class DeviceController {

	private final DeviceService deviceService;

	@Autowired
	private AlarmFeign alarmFeign;

	/**
	 * 分页查询
	 *
	 * @param page     分页对象
	 * @param dmDevice 设备表
	 * @return
	 */
	@GetMapping("/page")
	public R getDmDevicePage(Page page, DmDevice dmDevice) {
		return new R<>(page.setRecords(deviceService.getDeviceList(page, dmDevice)));
	}

	/**
	 * 根据设备编码激活设备
	 *
	 * @param code code
	 * @return R
	 */
	@GetMapping("/activate")
	public R activate(String code) {
		return new R<>(deviceService.activate(code));
	}

	/**
	 * 通过id查询设备表
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
		if (StringUtils.isEmpty(id)){
			return new R().setCode(0).setMsg("请选一条记录");
		}
		return new R<>(deviceService.getById(id));
	}

	/**
	 * 根据分组id,查询不属于该分组的设备信息
	 *
	 * @param groupId
	 * @return R
	 */
	@GetMapping("/getNoGroupDevice")
	public R getNoGroupDevice(Page page, DmDeviceGroupVO vo) {
		return new R<>(deviceService.getNoGroupDevice(page, vo));
	}

	/**
	 * 根据分组id,查询属于该分组的设备信息
	 *
	 * @param groupId
	 * @return R
	 */
	@GetMapping("/getGroupDevice")
	public R getGroupDevice(Page page,DmDeviceGroupVO vo) {
		return new R<>(deviceService.getGroupDevice(page, vo));
	}

	/**
	 * 新增设备表
	 *
	 * @param dmDevice 设备表
	 * @return R
	 */
	@SysLog("新增设备表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('generator_device_add')")
	public R save(@Valid @RequestBody DmDevice dmDevice) {
		PigxUser user = SecurityUtils.getUser();
		dmDevice.setCreateUserId(user.getId());
		dmDevice.setCreateUser(user.getUsername());
		dmDevice.setAlarmType(Constant.ALARM_TYPE_NORMAL);
		dmDevice.setStatus(Constant.DEVICE_STATUS_ON_LINE.toString());
		return new R<>(deviceService.saveDevice(dmDevice));
	}

	/**
	 * 修改设备表
	 *
	 * @param dmDevice 设备表
	 * @return R
	 */
	@SysLog("修改设备表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('generator_device_edit')")
	public R updateById(@RequestBody DmDevice dmDevice) {
		PigxUser user = SecurityUtils.getUser();
		dmDevice.setUpdateUserId(user.getId());
		dmDevice.setUpdateUser(user.getUsername());
		dmDevice.setUpdateTime(LocalDateTime.now());
		return new R<>(deviceService.updateDevice(dmDevice));
	}

	/**
	 * 通过id删除设备表(逻辑删除)
	 *
	 * @param id
	 * @return R
	 */
	@SysLog("删除设备表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('generator_device_del')")
	public R removeById(@PathVariable("id") String id) {
		DmDevice dmDevice = new DmDevice();
		dmDevice.setIsDelete(1);
		dmDevice.setId(id);
		return new R<>(deviceService.updateById(dmDevice));
	}


	/**
	 * 获取视频设备的select
	 * @param dmDevice 设备实体
	 * @return
	 */
	@SysLog("获取视频设备的select")
	@GetMapping("/select")
	public R selectVideoDevice(DmDevice dmDevice) {
		dmDevice.setTypeId(Constant.PROD_TYPE_REMOTE_VIDEO);
		return new R<>(deviceService.selectVideoDevice(dmDevice));
	}


	/**
	 * 设备数据查询（实时数据）
	 * @param page
	 * @param dmDevice
	 * @return
	 */
	@GetMapping("/pageList")
	public R findDeviceList(Page page, DmDevice dmDevice){
		List<String> list = alarmFeign.getUserLoingUnitIds();
		dmDevice.setUnitIds(list);
		return new R<>(deviceService.findDeviceList(page,dmDevice));
	}

	/**
	 * 水系统设备添加回调地址
     * @return
	 */
	@GetMapping("/waterSystem")
	@Inner(value = false)
	public String waterSystem(){
		HttpPostBody testCla = new HttpPostBody();
		testCla.setLoginName("dlgs");
		testCla.setPassword("123456");
		testCla.setCallbackUrl("http://39.97.103.251:4500/deviceWaterFromHyController/getHyDeviceInfo");
		String jsonString =JSON.toJSONString(testCla);
		String url = "http://47.105.55.134:8072/v1.0.0/subscribeData";
		String sendJsonHttpPost = sendPost(url,jsonString);
		JSONObject json_test = JSONObject.parseObject(sendJsonHttpPost);
		if("200".equals(json_test.get("responseCode").toString())){
			System.out.println("回调地址注册成功");
		}
		return "ok";
	}

	private static final String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	public static String sendPost(String url, String jsonStr) {
		String result = null;
		// 字符串编码
		StringEntity entity = new StringEntity(jsonStr, Consts.UTF_8);
		// 设置content-type
		entity.setContentType("application/json");
		HttpPost httpPost = new HttpPost(url);
		// 防止被当成攻击添加的
		httpPost.setHeader("User-Agent", userAgent);
		// 接收参数设置
		httpPost.setHeader("Accept", "application/json");
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
		} catch (IOException e) {
		} finally {
			// 关闭CloseableHttpResponse
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
}
