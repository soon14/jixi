package com.pig4cloud.pigx.smartff.generator.feign;


import com.pig4cloud.pigx.smartff.generator.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "userFeign",value = "pigx-api")
@Service
public interface UserFeign {



    /**
     * 通过userIds,查询用户信息
     * @param userIds
     * @return
     */
    @GetMapping("/user/getUserByUserIds" )
    List<SysUser> getUserByUserIds(@RequestParam("userIds") String[] userIds);


    /**
     * 获取当前登录用户下的unitIds,用于数据权限控制
     *
     * @return
     */
    @GetMapping("/user/getUserLoingUnitIds" )
    List<String> getUserLoingUnitIds();

}
