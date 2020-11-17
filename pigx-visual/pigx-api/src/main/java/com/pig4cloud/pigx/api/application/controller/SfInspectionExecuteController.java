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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.common.minio.service.MinioTemplate;
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecuteDetail;
import com.pig4cloud.pigx.api.application.service.SfInspectionExecuteDetailService;
import com.pig4cloud.pigx.api.application.service.SfInspectionExecuteService;
import com.pig4cloud.pigx.api.application.service.SfInspectionPointService;
import com.pig4cloud.pigx.api.application.vo.SfInspectionExecuteDetailVo;
import com.pig4cloud.pigx.api.application.vo.SfInspectionResultVO;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.common.core.constant.CommonConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 巡检执行表
 *
 * @author pigx code generator
 * @date 2019-07-05 16:43:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sfinspectionexecute" )
@Api(value = "sfinspectionexecute", tags = "sfinspectionexecute管理")
public class SfInspectionExecuteController {

    private final  SfInspectionExecuteService sfInspectionExecuteService;
    
    private final SfInspectionExecuteDetailService sfInspectionExecuteDetailService;
    
    private final SysDictItemService sysDictItemService;
    
    private final SfInspectionPointService sfInspectionPointService;
    
    private final SysUserService sysUserService;
    
    private final MinioTemplate minioTemplate;
    

    /**
                *   获取巡检记录
     * @param page 分页对象
     * @param sfInspectionExecute 巡检执行
     * @return
     */
    @GetMapping("/getInspectionRecord" )
    public R getInspectionRecord(Page page,Integer[] inspectStatus) {
    	try {
    	List<SfInspectionResultVO> vos=sfInspectionExecuteService.getInspectionRecord(page,Arrays.asList(inspectStatus),sysUserService.getUserLoingUnitIds());
    	if(vos!=null&&vos.size()>0) {
    		//查询巡检状态数据字典
    		Map<String,String> m=sysDictItemService.getItemMap("inspection_status");
    		for(SfInspectionResultVO vo:vos) {
    			if(vo==null)continue;
    			vo.setStatusName(m.get(vo.getStatus()+""));
    			SfInspectionExecuteDetail detail=new SfInspectionExecuteDetail();
    			detail.setExecuteid(vo.getId());
    			detail.setIsInspection(Constant.INSPECTION_EXE_STATUS_YES);
    			//设置已巡检的数量
    			vo.setFinishCount(sfInspectionExecuteDetailService.findCountByIsInspection(detail));
    			//设置巡检点总数
    			detail.setIsInspection(null);
    			vo.setPointTotal(sfInspectionExecuteDetailService.findCountByIsInspection(detail));
    		}
    	}
    	page.setRecords(vos);
    	}catch(Exception e) {
    	   return new R<>(e);	
    	}
    	return new R<>(page);
    }
    
    /**
               *    查询巡检执行情况
     * @param executeId
     * @return
     */
    @GetMapping("/getExecuteDetailByExeId" )
    public R getExecuteDetailByExeId(Page page,String executeId) {
    	try {
    	List<SfInspectionExecuteDetailVo> vos=sfInspectionExecuteDetailService.getExecuteDetailByExeId(page,executeId);
    	//查询是否巡检数据字典
		Map<String,String> m=sysDictItemService.getItemMap("is_inspection");
    	if(vos!=null&&vos.size() > 0) {
    		for(SfInspectionExecuteDetailVo vo:vos) {
    			if(vo==null)continue;
    			vo.setIsInspectionName(m.get(vo.getIsInspection()+""));
    		}
    	}
    	page.setRecords(vos);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(page);
    }
    
    /**
                *   根据执行详细Id查询执行详细
     * 
     * @param detailId
     * @return
     */
    @GetMapping("/getExecuteDetailByDetailId" )
    public R getExecuteDetailByDetailId(String detailId) {
    	SfInspectionExecuteDetailVo vo=null;
    	try {
    	 vo=sfInspectionExecuteDetailService.getExecuteDetailByDetailId(detailId);
    	List<String> list=new ArrayList<String>();
    	String imageIds=vo.getPointImageId();
    	if(!StringUtils.isEmpty(imageIds)) {
    		String[] imageId=imageIds.split(",");
    		list.addAll(Arrays.asList(imageId));
    	}
    	vo.setFilesPath(list);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
    	return new R<>(vo);
    }
    
    /**
     *  保存巡检执行结果
     * @param sfInspectionExecute
     * @return
     */
    @PostMapping("/saveInspectionResult" )
    public R saveInspectionResult(SfInspectionExecuteDetailVo sfInspectionExecuteDetail,MultipartFile[] files) {
    	boolean flag = false;
    	try {
    	sfInspectionExecuteDetail.setPointTime(LocalDateTime.now());
    	sfInspectionExecuteDetail.setPointUser(SecurityUtils.getUser().getId());
    	sfInspectionExecuteDetail.setIsInspection(Constant.INSPECTION_EXE_STATUS_YES);
    	if(!StringUtils.isEmpty(sfInspectionExecuteDetail.getPointQrCode())) {
    		int count = sfInspectionPointService.findInspectionPoint(sfInspectionExecuteDetail.getPointId(), sfInspectionExecuteDetail.getPointQrCode());
    		if(count==0) {
    			return new R<>().setCode(1).setMsg("二维码和设备不匹配");
    		}
    	}
    	//存放文件名称
    	StringBuffer fileNames=new StringBuffer();
    	if(files!=null&&files.length>0) {
    		for(MultipartFile file:files) {
    			String fileName=upload(file);
    			fileNames.append(fileName).append(",");
    		}
    		sfInspectionExecuteDetail.setPointImageId(fileNames.toString());
    	  }
    	 flag = sfInspectionExecuteDetailService.saveInspectionResult(sfInspectionExecuteDetail); 
    }catch(Exception e) {
    	return new R<>(e);
    }
    	return new R<>(flag);
    }
    
    /**
     * 获取巡检事件
     * @return
     */
    @GetMapping("/getInspectionEvent" )
    public R getInspectionEvent() {
    	List<SfInspectionExecuteDetailVo> list=null;
		try {
			list = sfInspectionExecuteDetailService.getInspectionEvent(Constant.INSPECTION_STATUS_NOCOMPLETE,Constant.INSPECTION_EXE_STATUS_NO,sysUserService.getUserLoingUnitIds());
		} catch (Exception e) {
			return new R<>(e);
		}
    	return new R<>(list);
    }
    /**
     * 生成二维码字符串
     * @return
     */
    @Inner(value=false)
    @GetMapping("/getGenQrCode" )
    public R getGenQrCode() {
    	String uuid=UUID.randomUUID().toString();
    	return new R<>(uuid.replaceAll("-", ""));
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
