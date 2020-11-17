package com.pig4cloud.pigx.device.generator.service.impl;

import com.pig4cloud.pigx.admin.api.entity.SysDictItem;
import com.pig4cloud.pigx.device.generator.feign.CommonFeign;
import com.pig4cloud.pigx.device.generator.service.CommonFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonFeignServiceImpl implements CommonFeignService {
    @Autowired
    private CommonFeign commonFeign;

    /******************************* SysDict(字典) Begin *******************************/
    /**
     * 通过类型获取字典项
     * @return
     */
    @Override
    public Map<String,String> getInterfaceDictByType(String type) {
        // 通过字典类型查询字典信息
        Map<String,String> sysDictMap = new HashMap<>();
        for(SysDictItem sysDictItem : commonFeign.getInterfaceDictByType(type)){
            sysDictMap.put(sysDictItem.getValue(),sysDictItem.getLabel());
        }
        return sysDictMap;
    }
    /******************************* SysDict end *******************************/
}
