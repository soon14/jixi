package com.pig4cloud.pigx.device.generator.service;

import java.util.Map;

public interface CommonFeignService {
    /******************************* SysDict(字典) Begin *******************************/
    /**
     * 通过类型获取字典项
     * @param type
     * @return
     */
    Map<String,String> getInterfaceDictByType(String type);
    /******************************* SysDict end *******************************/
}
