package com.pig4cloud.pigx.device.generator.utils;

/**
 * 海曼一期174长度 二期236长度 设备
 */
public class HMParserUtils {
	private static final int deviceDataLength = 174;
	/**
	 * 设备协议版本编号
	 */
	private String yGDeviceComVerCode;

	/**
	 * 烟雾浓度
	 */
	private int yGDeviceSmokeConCode;
	/**
	 * 设备温度值
	 */
	private int yGDeviceTempertureConCode;
	/**
	 * 设备电量
	 */
	private int yGDeviceEleValueCode;
	/**
	 * 设备报警值
	 */
	private int yGDeviceAlarmStatusCode;

	/**
	 * 消息类型
	 */
	private String YGInfoType;

	/**
	 * 信号强度
	 */
	private String YGSignalStrength;

	private HMYGBeanInfo ygBeanInfo;

	public HMParserUtils(String deviceData) {
		if (deviceData.length() == deviceDataLength || deviceData.length() == 236) {
			// 截取 + 转换
			hexTransformation(deviceData);
		} else {
			System.out.print("数据解析失败，数据格式异常");
		}
	}

	private void hexTransformation(String deviceData) {
		// ASCII转换 字符 软件版本号
		yGDeviceComVerCode = deviceData.substring(0, 2);
		// 消息类型
		YGInfoType = deviceData.substring(2, 4);
		// 信号强度
		YGSignalStrength = deviceData.substring(14, 22);
		// 烟雾浓度
		yGDeviceSmokeConCode = HexUtils.sixteen2ten(deviceData.substring(110, 112));
		// 温度值
		yGDeviceTempertureConCode = HexUtils.sixteen2ten(deviceData.substring(118, 120));
		// 相对湿度
		deviceData.substring(126, 128);
		// 电量百分比
		yGDeviceEleValueCode = HexUtils.sixteen2ten(deviceData.substring(134, 136));
		// 经度
		deviceData.substring(136, 150);
		// 维度
		deviceData.substring(150, 164);
		// 报警状态
		yGDeviceAlarmStatusCode = HexUtils.sixteen2ten(deviceData.substring(170, 172));
	}
	public HMYGBeanInfo getYgBeanInfo() {
		ygBeanInfo = new HMYGBeanInfo();
		initYgManufacturer();
		initYgType();
		initThreeWeight();
		initSevenWeight();
		initEightWeight();
		initAlarmStatus();
		return ygBeanInfo;
	}

	private void initSevenWeight() {
		ygBeanInfo.setYGDeviceSmokeConCode(yGDeviceSmokeConCode);
		ygBeanInfo.setYGInfoType(YGInfoType);
		ygBeanInfo.setYGSignalStrength(YGSignalStrength);
		ygBeanInfo.setYGDeviceTempertureConCode(yGDeviceTempertureConCode);
	}

	private void initEightWeight() {
		ygBeanInfo.setYGDeviceEleValueCode(yGDeviceEleValueCode);
	}

	private void initThreeWeight() {
		if (yGDeviceComVerCode != null) {
			switch (yGDeviceComVerCode) {
				case "01":
					ygBeanInfo.setYGDeviceComVerCode("第一版本");
					break;
				default:
					ygBeanInfo.setYGDeviceComVerCode("未知版本");
					break;
			}
		}

	}

	private void initYgType() {
		ygBeanInfo.setYGDeviceType("烟雾报警器");
	}

	private void initYgManufacturer() {
		ygBeanInfo.setYGManufacturer("海曼");
	}
	/**
	 * 百思威 设置设备状态对应的编码
	 *
	 * @return 0：无报警1：烟雾报警2：烟雾报警解除3：温度报警4：温度报警解除5：烟感低电量报警6：烟感低电量报警解除
	 * 7：NB低电量报警8：NB低电量报警解除9：烟雾传感器故障10：烟雾传感器故障解除11：温湿度传感器故障12：温湿度传感器故障解除
	 * 13：自检测试开始14：自检测试完成15：防拆触发16：防拆恢复17：烟雾板连接断开18：烟雾板连接恢复 19 烟雾预警 20 温度预警
	 *
	 *         自定义状态：0：报警；1：故障；2：正常
	 */
	private void initAlarmStatus() {
		switch (yGDeviceAlarmStatusCode) {
			case 0:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("无报警");
				break;
			case 1:
				ygBeanInfo.setYGDeviceAlarmStatusCode(0);
				ygBeanInfo.setYGDeviceCom("烟雾报警");
				break;
			case 2:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("烟雾报警解除");
				break;
			case 3:
				ygBeanInfo.setYGDeviceAlarmStatusCode(0);
				ygBeanInfo.setYGDeviceCom("温度报警");
				break;
			case 4:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("温度报警解除");
				break;
			case 5:
				ygBeanInfo.setYGDeviceAlarmStatusCode(0);
				ygBeanInfo.setYGDeviceCom("烟感低电量报警");
				break;
			case 6:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("烟感低电量报警解除");
				break;
			case 7:
				ygBeanInfo.setYGDeviceAlarmStatusCode(0);
				ygBeanInfo.setYGDeviceCom("NB低电量报警");
				break;
			case 8:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("NB低电量报警解除");
				break;
			case 9:
				ygBeanInfo.setYGDeviceAlarmStatusCode(1);
				ygBeanInfo.setYGDeviceCom("烟雾传感器故障");
				break;
			case 10:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("烟雾传感器故障解除");
				break;
			case 11:
				ygBeanInfo.setYGDeviceAlarmStatusCode(1);
				ygBeanInfo.setYGDeviceCom("温湿度传感器故障");
				break;
			case 12:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("温湿度传感器故障解除");
				break;
			case 13:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("自检测试开始");
				break;
			case 14:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("自检测试完成");
				break;
			case 15:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("防拆触发");
				break;
			case 16:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("防拆恢复");
				break;
			case 17:
				ygBeanInfo.setYGDeviceAlarmStatusCode(1);
				ygBeanInfo.setYGDeviceCom("烟雾板连接断开");
				break;
			default:
				ygBeanInfo.setYGDeviceAlarmStatusCode(2);
				ygBeanInfo.setYGDeviceCom("烟雾板连接恢复");
				break;
		}
	}

}
