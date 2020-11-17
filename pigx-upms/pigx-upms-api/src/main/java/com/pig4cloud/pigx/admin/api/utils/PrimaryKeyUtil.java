package com.pig4cloud.pigx.admin.api.utils;

import java.util.UUID;

public class PrimaryKeyUtil {
    /**
     *
     * @Description:生成主键id(目前是uuid)
     * @Return:
     */
    public static String getPrimaryKey(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
