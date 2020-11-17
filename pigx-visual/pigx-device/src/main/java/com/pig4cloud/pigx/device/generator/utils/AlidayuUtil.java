package com.pig4cloud.pigx.device.generator.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;

public class AlidayuUtil {
	// 路径
	static String url;
	// appkeyid
	
	private static final String appKey = "LTAIhT7EXwIk96eK";
	// sercret
	private static final String secret = "AcyoI67eTGDAsP8LEHmrt0CFx7mM20";
	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";
	// 短信签名
	private static final String signName = "东霖消防";
	
	/**
	 * @Title: aliSendMsgAlarm  
	 * @Description: 发送报警短信
	 * @param phone
	 * @return
	 * @throws ClientException
	 */
	public static String aliSendMsgAlarm(DmDevice dmdevice, String alarmTime, String phone) throws ClientException{
		StringBuffer sbPhone = new StringBuffer();
		sbPhone.append(phone);
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		/**阿里固定写法*/
		IClientProfile profile = DefaultProfile.getProfile("cn-beijing", appKey, secret);
		DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		/**end*/
		SendSmsRequest request = new SendSmsRequest();
		// 组装请求对象-具体描述见控制台-文档部分内容
		//SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(sbPhone.toString());
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_172604231");
		//request.setTemplateCode(Resoult.Z_C_S_J_H);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		StringBuffer str = new StringBuffer();
		str.append("{\"networkUnitName\":\"");
	    str.append(dmdevice.getNetworkUnitName());
		str.append("\",");
		str.append("\"buildName\":\"");
		str.append(dmdevice.getBuildName());
		str.append("\",");
		str.append("\"countyName\":\"");
		str.append(dmdevice.getCountyName());
		str.append("\",");
		str.append("\"position\":\"");
		str.append(dmdevice.getPosition());
		str.append("\",");
		str.append("\"deviceName\":\"");
	    str.append(dmdevice.getName());
		str.append("\",");
	    str.append("\"alarmTime\":\"");
	    str.append(alarmTime);
	    str.append("\"}");
		request.setTemplateParam(str.toString());
		System.out.println(str.toString());
		request.setOutId("yourOutId");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		System.out.println(sendSmsResponse.getBizId());
		System.out.println(sendSmsResponse.getMessage());
		System.out.println(sendSmsResponse.getCode());
		return sendSmsResponse.getCode();
	}
}
