package com.pig4cloud.pigx.device.generator.feign;

import com.pig4cloud.pigx.admin.api.entity.SysDictItem;
import com.pig4cloud.pigx.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 调用其他服务的公共接口类
 */
@FeignClient(contextId = "commonFeign",value = "pigx-upms-biz")
@Service
public interface CommonFeign {



    /******************************* SysDict(字典) Begin *******************************/
    /**
     * 通过类型获取字典项
     * @param type
     * @return
     */
    @GetMapping("/dict/getInterfaceDictByType")
    List<SysDictItem> getInterfaceDictByType(@RequestParam("type") String type);
    /******************************* SysDict end *******************************/
}
