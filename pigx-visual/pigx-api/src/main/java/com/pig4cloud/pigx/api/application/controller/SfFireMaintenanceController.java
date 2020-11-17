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

package com.pig4cloud.pigx.api.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.common.minio.service.MinioTemplate;
import com.pig4cloud.pigx.api.application.dto.SfFireMaintenanceDto;
import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import com.pig4cloud.pigx.api.application.service.DmAlarminfoService;
import com.pig4cloud.pigx.api.application.service.SfFireMaintenanceService;
import com.pig4cloud.pigx.api.application.vo.DmAlarminfoVo;
import com.pig4cloud.pigx.api.application.vo.MaintenanceDataVo;
import com.pig4cloud.pigx.api.application.vo.SfFireMaintenanceVo;
import com.pig4cloud.pigx.api.common.CommonUtils;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.device.service.DmDeviceService;
import com.pig4cloud.pigx.api.device.vo.DmDeviceVO;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.service.SysDeptService;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.common.core.constant.CommonConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 消防维保表
 *
 * @author pigx code generator
 * @date 2019-08-14 14:12:02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sffiremaintenance" )
@Api(value = "sffiremaintenance", tags = "sffiremaintenance管理")
public class SfFireMaintenanceController {

    private final  SfFireMaintenanceService sfFireMaintenanceService;
    
    private final BsNetworkingUnitService bsNetworkingUnitService;
    
    private final DmDeviceService dmDeviceService;
    
    private final SysDeptService sysDeptService;
    
    private final SysDictItemService sysDictItemService;
    
    private final DmAlarminfoService dmAlarminfoService;
    
    private final SysUserService sysUserService;
    
    private final MinioTemplate minioTemplate;



    /**
               *  新增消防维保表
     * @param sfFireMaintenance 消防维保表
     * @return R
     */
    @SysLog("新增消防维保表" )
    @PostMapping("/save")
    public R save(@RequestBody SfFireMaintenanceDto sfFireMaintenance) {
    	try {
    	//查询设备信息
    	DmDeviceVO info=dmDeviceService.getDeviceInfoByCode(sfFireMaintenance.getDeviceCode());
    	sfFireMaintenance.setFactorId(info.getFactorId());
    	sfFireMaintenance.setBrandId(info.getBrandId());
    	sfFireMaintenance.setNetworkUnitId(info.getNetworkUnitId());
    	sfFireMaintenance.setBuildId(info.getBuildId());
    	sfFireMaintenance.setBuildName(info.getBuildName());
    	sfFireMaintenance.setCountyId(info.getCountyId());
    	sfFireMaintenance.setCountyName(info.getCountyName());
    	sfFireMaintenance.setPosition(info.getPosition());
    	sfFireMaintenance.setHandleStatus(Constant.HANDLE_STATUS_NO);
    	sfFireMaintenance.setProduceCate(info.getTypeId()+"");
    	//设置维保单号
    	sfFireMaintenance.setCode(CommonUtils.getMaintenanceCode());
    	sfFireMaintenance.setCreateUserId(SecurityUtils.getUser().getId());
    	sfFireMaintenance.setCreateTime(LocalDateTime.now());
    	sfFireMaintenance.setApplyOrgId(SecurityUtils.getUser().getDeptId());
    	sfFireMaintenance.setApplyOrgName(sysDeptService.getVOById(SecurityUtils.getUser().getDeptId()).getName());
    	NetworkingUnitVo vo=bsNetworkingUnitService.getUnitById(info.getNetworkUnitId());
    	sfFireMaintenance.setArea(vo.getAreaCode());
    	sfFireMaintenance.setAreaName(vo.getAreaName());
    	sfFireMaintenance.setAddress(vo.getAddress());
    	//根据报警Id查询出报警信息
    	if(!StringUtils.isEmpty(sfFireMaintenance.getAlarmId())) {
    	DmAlarminfoVo alarmVo=dmAlarminfoService.getDmAlarmDetail(sfFireMaintenance.getAlarmId());
    	//查询报警类型数据字典
    	Map<String,String> m=sysDictItemService.getItemMap("alarm_type");
    	sfFireMaintenance.setAlarmType(m.get(alarmVo.getAlarmTypeId()+""));
    	sfFireMaintenance.setAlarmTime(alarmVo.getAlarmTime());
    	}
    	//报警状态设置为已处理
    	DmAlarminfo dma=new DmAlarminfo();
    	dma.setDeviceStatus(Constant.HANDLE_STATUS_YES);
    	dma.setDeviceCode(sfFireMaintenance.getDeviceCode());
    	dmAlarminfoService.updateAlarmStatus(dma);
    	sfFireMaintenanceService.saveMaintenance(sfFireMaintenance);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(true);
    }

    /**
                *  查询维保记录
     * 
     * @param page
     * @param sfFireMaintenance
     * @return
     */
    @GetMapping("/getMaintenanceRecord" )
    public R getMaintenanceRecord(Page page,SfFireMaintenanceDto sfFireMaintenance) {
    	IPage<List<SfFireMaintenance>> list=null;
      try {
		  List<String> roles=SecurityUtils.getRoles();
		  //如果为超级管理员,不加权限
		  if(!roles.contains(Constant.ADMIN_ROLE_ID)) {
			  String code = sysDeptService.getVOById(SecurityUtils.getUser().getDeptId()).getCode();
			  if (Constant.ORG_TYPE_CODE_WBGS.equals(code)) {
				  sfFireMaintenance.setCompanyId(SecurityUtils.getUser().getDeptId());
			  }
		  }
    	  list=sfFireMaintenanceService.getMaintenanceRecord(page,sfFireMaintenance,sysUserService.getUserLoingUnitIds());
}catch(Exception e) {
		return new R<>(e);
		}
		return new R<>(list);
		}

	/**
	 * 查询维保记录（默认查询一周）
	 * @param sfFireMaintenance
	 * @return
	 */
	    @GetMapping("/getMaintenanceRecordByTime" )
		public R getMaintenanceRecordByTime(SfFireMaintenanceDto sfFireMaintenance){
			List<SfFireMaintenanceVo> list=null;
			try {
				List<String> roles=SecurityUtils.getRoles();
				//如果为超级管理员,不加权限
				if(!roles.contains(Constant.ADMIN_ROLE_ID)) {
					String code = sysDeptService.getVOById(SecurityUtils.getUser().getDeptId()).getCode();
					if (Constant.ORG_TYPE_CODE_WBGS.equals(code)) {
						sfFireMaintenance.setCompanyId(SecurityUtils.getUser().getDeptId());
					}
				}
				List<String> unitIds=new ArrayList<>();
				//如果联网单位id为空,执行正常权限控制
				if(StringUtils.isEmpty(sfFireMaintenance.getNetworkUnitId())){
					unitIds.addAll(sysUserService.getUserLoingUnitIds());
				}else{
					unitIds.add(sfFireMaintenance.getNetworkUnitId());
				}
				list=sfFireMaintenanceService.getMaintenanceRecordByTime(sfFireMaintenance,unitIds);
				if(list!=null){
					for(SfFireMaintenanceVo vo:list){
						LocalDateTime createTime=vo.getCreateTime();
						DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd HH:mm");
						vo.setSimpleCreateTime(df.format(createTime));
					}
				}
			}catch(Exception e) {
				return new R<>(e);
			}
			return new R<>(list);
		}
/**
 *  查询维保详情
 *
 * @param mainId
 * @return
 */
    @GetMapping("/getMaintenanceDetail" )
    public R getMaintenanceDetail(String mainId) {
    	SfFireMaintenanceVo vo=null;
    	try {
    	//查询产品类别数据字典
    	Map<String,String> mp=sysDictItemService.getItemMap("product_category");
    	
    	vo=sfFireMaintenanceService.getMaintenanceDetail(mainId);
    	vo.setProduceCateName(mp.get(vo.getProduceCate()));
    	List<String> list=new ArrayList<String>();
    	String images=vo.getImages();
    	if(!StringUtils.isEmpty(images)) {
    		String[] image=images.split(",");
    		list.addAll(Arrays.asList(image));
    	}
    	vo.setFilesPath(list);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(vo);
    }
    
    /**
     *      维保处理
     * 
     * @param sfFireMaintenance
     * @return
     */
    @PostMapping("/maintenanceHandle")
    public R maintenanceHandle(SfFireMaintenance sfFireMaintenance,MultipartFile[] files) {
    	try {
    	sfFireMaintenance.setHandleStatus(Constant.HANDLE_STATUS_YES);
    	sfFireMaintenance.setUpdateUserId(SecurityUtils.getUser().getId());
    	sfFireMaintenance.setUpdateTime(LocalDateTime.now());
    	
    	//存放文件名称
    	StringBuffer fileNames=new StringBuffer();
    	if(files!=null&&files.length>0) {
    		for(MultipartFile file:files) {
    			String fileName=upload(file);
    			fileNames.append(fileName).append(",");
    		}
    		sfFireMaintenance.setImages(fileNames.toString());
    	  }
    	
    	sfFireMaintenanceService.maintenanceHandle(sfFireMaintenance);
    	}catch(Exception e) {
    		return new R<>(e);
    	}

    	return new R<>(true);
    }

	/**
	 * App维保周数据统计
	 * @param sfFireMaintenance
	 * @return
	 */
	@GetMapping("/selectGroupDate")
	public R selectGroupDate(SfFireMaintenance sfFireMaintenance) {
		List<MaintenanceDataVo> vos=null;
		try {
		List<String> list = sysUserService.getUserLoingUnitIds();
		sfFireMaintenance.setUnitIds(list);
		vos=sfFireMaintenanceService.selectGraphStatistics(sfFireMaintenance);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(vos);
	}

	/**
	 * 获取维保事件 
	 * @return
	 */
	@GetMapping("/getMaintenanceEvent")
	public R getMaintenanceEvent() {
		List<SfFireMaintenanceVo> vos=null;
		try {
			vos=sfFireMaintenanceService.getMaintenanceEvent(sysUserService.getUserLoingUnitIds(),Constant.HANDLE_STATUS_NO);
			if(vos!=null&&vos.size()>0) {
				for(SfFireMaintenanceVo vo:vos) {
					if(vo==null)continue;
					DmDeviceVO devVo=dmDeviceService.getDeviceInfoByCode(vo.getDeviceCode());
					vo.setLatitude(devVo.getLatitude());
					vo.setLongitude(devVo.getLongitude());
				}
			}
		} catch (Exception e) {
			return new R<>(e);
		}
		
		return new R<>(vos);
	}
	/**
	    * 上传文件
	 * @param file
	 * @return
	 */
	public String upload(MultipartFile file) throws Exception{
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);

		minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());

		return CommonConstants.BUCKET_NAME+StrUtil.DASHED+fileName;
	}
}
