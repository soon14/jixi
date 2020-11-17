package com.pig4cloud.pigx.api.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * json处理类
 *
 */
public class StrToJson {
	
	/**
	 * 把json格式的字符转成 json对象
	 * @param str
	 * @return
	 */
	public static JSONObject strToJsonObj(String str){
		JSONObject JSONObj = JSONObject.parseObject(str);
		return JSONObj;
	}
	
	/**
	 * json字符串转Map, 经测试发现，json对象JSONObject 就是map,不用再转
	 * @param str
	 * @return
	 */
	public static Map<String,Object> jsonStrToMap(String str){
		
		return JSONObject.parseObject(str);
	}

}
