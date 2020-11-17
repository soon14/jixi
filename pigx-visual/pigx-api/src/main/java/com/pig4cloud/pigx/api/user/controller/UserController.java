/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pigx.api.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.user.dto.UserDTO;
import com.pig4cloud.pigx.api.user.dto.UserInfo;
import com.pig4cloud.pigx.api.user.entity.SysOrgType;
import com.pig4cloud.pigx.api.user.entity.SysRole;
import com.pig4cloud.pigx.api.user.entity.SysUser;
import com.pig4cloud.pigx.api.user.service.BsAreaService;
import com.pig4cloud.pigx.api.user.service.BsCityService;
import com.pig4cloud.pigx.api.user.service.BsProvinceService;
import com.pig4cloud.pigx.api.user.service.SysDeptService;
import com.pig4cloud.pigx.api.user.service.SysOrgTypeService;
import com.pig4cloud.pigx.api.user.service.SysRoleService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.SysDeptVO;
import com.pig4cloud.pigx.api.user.vo.UserVO;
import com.pig4cloud.pigx.api.util.DynamicDataSourceContextHolder;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lengleng
 * @date 2018/12/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "appUser", tags = "app 用户管理模块")
public class UserController {
	private final SysUserService userService;

	private final SysRoleService sysRoleService;
	
	private final SysDeptService sysDeptService;
	
	private final  SysOrgTypeService sysOrgTypeService;
	
	private final BsProvinceService bsProvinceService;
	
	private final BsCityService bsCityService;
	
	private final BsAreaService bsAreaService;
	
	

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping("/info")
	public R info() {
		DynamicDataSourceContextHolder.clearDataSourceType();
		UserVO userVO=new UserVO();
		try {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		BeanUtils.copyProperties(user, userVO);
		userVO.setRoleList(sysRoleService.findRolesByUserId(SecurityUtils.getUser().getId()));
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(userVO);
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public R info(@PathVariable String username) {
		DynamicDataSourceContextHolder.clearDataSourceType();
		UserInfo info=null;
		try {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		info=userService.findUserInfo(user);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(info);
	}


	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R user(@RequestBody UserDTO userDto) {
		//设置归属系统
		userDto.setBelongSys(Constant.BELONG_SYS_APP);
		try {
			userService.saveUser(userDto);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(true);
	}

	/**
	 * 更新用户密码
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R updateUserPassword(@Valid @RequestBody UserDTO userDto) {
		try {
			userService.updateUserPassword(userDto);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(true);
	}

	/**
	 * 获取当前用户的角色
	 *
	 */
	@GetMapping("/role")
	public R getRole() {
		List<SysRole> list=null;
		try {
			list=userService.getRoleByBelongSys(Constant.BELONG_SYS_APP);
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(list);
	}

	/**
	    * 获取当前登录机构下的所有用户
	 *
	 * @param page
	 * @param OrgId
	 * @return
	 */
    @GetMapping("/getUserByOrgId" )
    public R getUserByOrgId(Page page, String orgId) {
    	IPage<List<SysUser>> list=null;
    	try {
    		list=userService.getUserByOrgId(page, orgId);
    	}catch(Exception e) {
    		return new R<>(e);
    	}
        return new R<>(list);
    }

    /**
	 * 联网单位所属物业公司下的人员
	 *
	 * @param unitId
	 * @return
	 */
	 @GetMapping("/getUserByUnitId" )
	 public R getUserByUnitId(String unitId) {
		 List<SysUser> list=null;
		 try {
			 list=userService.getUserByUnitId(unitId,Constant.ORG_TYPE_CODE_WYGS);
		 }catch(Exception e) {
			 return new R<>(e);
		 }
	     return new R<>(list);
	 }


	 /**
	  * 通过userIds,查询用户信息
	  * 
	  * @param userIds
	  * @return
	  */
	 @GetMapping("/getUserByUserIds" )
	 public List<SysUser> getUserByUserIds(@RequestParam("userIds") String[] userIds) {
		 List<SysUser> list=null;
		 try {
			 list=userService.getUserByUserIds(userIds);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return list;
	 }
	 /**
	  * 获取当前登录用户下的机构区域信息
	  * @return
	  */
	 @GetMapping("/getUserLoingInfo" )
	 public Map<String,String> getUserLoingInfo(){
		 Map<String,String> map=new HashMap<String,String>();
		 try {
		 UserVO userVo=userService.selectUserVoById(SecurityUtils.getUser().getId());
		 if(Constant.USER_TYPE_UNIT.equals(userVo.getType())) {
			 map.put("unitId", userVo.getUnitId());
			 map.put("orgType", Constant.ORG_TYPE_CODE_LWDW);
		 }else {
			 SysDeptVO deptVo=sysDeptService.getVOById(SecurityUtils.getUser().getDeptId());
			 if(deptVo!=null) {
				 //获取机构类别
				 String orgTypeId = deptVo.getOrgType();
				 SysOrgType orgType = sysOrgTypeService.getById(orgTypeId);
				 //存放机构类别编码
				 map.put("orgType", orgType.getCode());
				 if (Constant.ORG_TYPE_CODE_XFD.equals(orgType.getCode())) {
					 if (Constant.CENTER_LEVEL_PROVINCE.equals(deptVo.getGrade())) {
						 map.put("areaCode", deptVo.getRegionProvince());
						 map.put("areaName", bsProvinceService.getProvinceByCode(deptVo.getRegionProvince()).getName());
					 } else if (Constant.CENTER_LEVEL_CITY.equals(deptVo.getGrade())) {
						 map.put("areaCode", deptVo.getRegionCity());
						 map.put("areaName", bsProvinceService.getProvinceByCode(deptVo.getRegionProvince()).getName() + bsCityService.getCityByCode(deptVo.getRegionCity()).getName());
					 } else if (Constant.CENTER_LEVEL_COUNTY.equals(deptVo.getGrade())) {
						 map.put("areaCode", deptVo.getRegionArea());
						 map.put("areaName", bsProvinceService.getProvinceByCode(deptVo.getRegionProvince()).getName() + bsCityService.getCityByCode(deptVo.getRegionCity()).getName() + bsAreaService.getAreaByCode(deptVo.getRegionArea()).getName());
					 }
				 } else {
					 map.put("address", deptVo.getAddress());
				 }
			 }
		 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return map;
	 }

	/**
	 * 获取当前登录用户下的角色的标识
	 * @return
	 */
	@GetMapping("/getRoleFlag" )
	 public R getRoleFlag(){
		List<String> list= SecurityUtils.getRoles();
		String roleFlag=null;
		try{
		if(list!=null){
			String roleId=list.get(0);
			SysRole role=sysRoleService.getById(roleId);
			roleFlag=role.getRoleCode();
		}else{
			roleFlag="用户无角色,请添加角色";
		}
		}catch(Exception e) {
			return new R<>(e);
		}
		return new R<>(roleFlag);
	 }
	 /**
	  * 获取当前登录用户下的unitIds,用于数据权限控制
	  * 
	  * @return
	  */
	 @GetMapping("/getUserLoingUnitIds" )
	 public List<String> getUserLoingUnitIds(){
		 List<String> list=null;
		 try {
			list= userService.getUserLoingUnitIds();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return list;
	 }
	 
	/**
	       *  获取人员信息,根据人员类别
	  * @return
	  */
	 @GetMapping("/getUserInfoByPersonCate" )
	 public R getUserInfoByPersonCate(String personCate){
		 List<UserVO> list=new ArrayList<UserVO>();
		 try {
		 //网格员1
		 if(Constant.PERSON_CATE_GIRD.equals(personCate)) {
			 UserVO grid=new UserVO();
			 grid.setUserId("45522222");
			 grid.setUsername("zhangshan");
			 grid.setNickname("张三");
			 grid.setPhone("13698754875");
			 grid.setLongitude(new BigDecimal(116.533938));
			 grid.setLatitude(new BigDecimal(39.805794));
			 grid.setType(Integer.parseInt(personCate));
			 grid.setPersonCate("person_cate");
			 list.add(grid);
		 //消防员2	 
		 }else if(Constant.PERSON_CATE_FIRE.equals(personCate)) {
			 UserVO fire=new UserVO();
			 fire.setUserId("455785555");
			 fire.setUsername("lisi");
			 fire.setNickname("李四");
			 fire.setPhone("1366547146");
			 fire.setLongitude(new BigDecimal(116.537311));
			 fire.setLatitude(new BigDecimal(39.809882));
			 fire.setType(Integer.parseInt(personCate));
			 fire.setPersonCate("person_cate");
			 list.add(fire);
			 
		 //单位负责人3	 
		 }else if(Constant.PERSON_CATE_UNIT_HEAD.equals(personCate)) {
			 UserVO unit=new UserVO();
			 unit.setUserId("455786785");
			 unit.setUsername("wangwu");
			 unit.setNickname("王五");
			 unit.setPhone("1369874745");
			 unit.setLongitude(new BigDecimal(116.537411));
			 unit.setLatitude(new BigDecimal(39.809771));
			 unit.setType(Integer.parseInt(personCate));
			 unit.setPersonCate("person_cate");
			 list.add(unit);
		 //巡检人员4
		 }else if(Constant.PERSON_CATE_INSPECT.equals(personCate)) {
			 list.addAll(userService.getUserInfoByUnitIds(userService.getUserLoingUnitIds(),Constant.ORG_TYPE_CODE_WYGS));
		 }else {
			 UserVO grid=new UserVO();
			 grid.setUserId("45522222");
			 grid.setUsername("zhangshan");
			 grid.setNickname("张三");
			 grid.setPhone("13698754875");
			 grid.setLongitude(new BigDecimal(116.533938));
			 grid.setLatitude(new BigDecimal(39.805794));
			 grid.setPersonCate("person_cate");
			 grid.setType(Integer.parseInt(Constant.PERSON_CATE_GIRD));
			 list.add(grid);
			 UserVO fire=new UserVO();
			 fire.setUserId("455785555");
			 fire.setUsername("lisi");
			 fire.setNickname("李四");
			 fire.setPhone("1366547146");
			 fire.setLongitude(new BigDecimal(116.537311));
			 fire.setLatitude(new BigDecimal(39.809882));
			 fire.setType(Integer.parseInt(Constant.PERSON_CATE_FIRE));
			 fire.setPersonCate("person_cate");
			 list.add(fire);
			 UserVO unit=new UserVO();
			 unit.setUserId("455786785");
			 unit.setUsername("wangwu");
			 unit.setNickname("王五");
			 unit.setPhone("1369874745");
			 unit.setLongitude(new BigDecimal(116.537411));
			 unit.setLatitude(new BigDecimal(39.809771));
			 unit.setType(Integer.parseInt(Constant.PERSON_CATE_UNIT_HEAD));
			 unit.setPersonCate("person_cate");
			 list.add(unit);
			 list.addAll(userService.getUserInfoByUnitIds(userService.getUserLoingUnitIds(),Constant.ORG_TYPE_CODE_WYGS));
		 }
		 }catch(Exception e) {
			 return new R<>(e);
		 }
		 return new R<>(list);
	 }
	 
}
