package com.pig4cloud.pigx.device.generator.utils;

import com.alibaba.fastjson.JSON;
import com.dahantc.api.commons.EncryptUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CallUtil
 * @Description: 大汉打电话接口
 */
public class CallUtil {
	private static String account = "dh82191";

	
	private static String password = "0ewWS73U";

	/**
	 * 文本外呼 接口地址 请求方式 post
	 */
	private static String url = "http://voice.3tong.net/json/voiceSms/SubmitVoc";

	public static void call(String text,String phone, String uuid) {
		StringBuffer sbPhone = new StringBuffer();
		sbPhone.append(phone);
		// 密码加密
		String sig = EncryptUtil.MD5Encode(password);

		// 1.创建httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.创建httppost请求
		HttpPost post = new HttpPost(url);
		List<Map<String, Object>> formparams = new ArrayList<Map<String, Object>>();
		// 按照参数格式要求组装
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map.put("account", account);
		map.put("password", sig);
		map1.put("msgid", uuid);
		map1.put("callee", sbPhone.toString());
		map1.put("text", text);
		// 外呼类型：0-文本呼叫
		map1.put("calltype", 0);
		// 放音模式：0-只播放文本
		map1.put("playmode", 0);
		// 循环播放次数: 默认1次，最多3次，int类型
		map1.put("playtimes", 2);

		formparams.add(map1);
		map.put("data", formparams);
		// map格式转成json字符串
		String str = JSON.toJSONString(map);
		CloseableHttpResponse response = null;
		try {
			StringEntity strEntity = new StringEntity(str, "utf-8");
			post.setEntity(strEntity);
			response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String backData = EntityUtils.toString(entity, "UTF-8");
			System.out.println("Response content: " + backData);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				httpclient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				response.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}