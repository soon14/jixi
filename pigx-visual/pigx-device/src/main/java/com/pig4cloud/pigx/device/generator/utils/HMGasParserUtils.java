package com.pig4cloud.pigx.device.generator.utils;

public class HMGasParserUtils {
	private static final int deviceDataLength = 158;
	/**
	 * 设备协议版本编号
	 */
	private String gasDeviceComVerCode;

	/**
	 * 可燃气体浓度
	 */
	private int gasDeviceGasConCode;
	
	/**
	 * 可燃气设备命令
	 * */
	private int gasDeviceCom;
	
	/**
	 * 报警类型
	 */
	private int gasDeviceAlarmStatusCode;
 
	/**
	 * 燃气阀门状态
	 * */
	private int gasValveStatus;

	private HMGasBeanInfo ygBeanInfo;

	public HMGasParserUtils(String deviceData) {
		if (deviceData.length() == deviceDataLength) {
			// 截取 + 转换
			hexTransformation(deviceData);
		} else {
			System.out.println("数据长度："+deviceData.length());
			System.out.print("数据解析失败，数据格式异常");
		}
	}

	private void hexTransformation(String deviceData) {
		// 烟雾浓度
		gasDeviceGasConCode = HexUtils.sixteen2ten(deviceData.substring(110, 114));
		// 设备状态(设备命令)
		gasDeviceCom = HexUtils.sixteen2ten(deviceData.substring(120, 122));
		// 报警类型
		gasDeviceAlarmStatusCode = HexUtils.sixteen2ten(deviceData.substring(128, 130));
		//机械手状态
		gasValveStatus = HexUtils.sixteen2ten(deviceData.substring(144, 146));
		System.out.println("报警的状态："+gasDeviceAlarmStatusCode);
	}

	public HMGasBeanInfo getGasBeanInfo() {
		ygBeanInfo = new HMGasBeanInfo();
		initYgManufacturer();
		initYgType();
		initThreeWeight();
		initSevenWeight();
		initAlarmStatus();
		//initGasStatus();
		System.out.println(ygBeanInfo.toString());
		return ygBeanInfo;
	}

	private void initSevenWeight() {
		ygBeanInfo.setGasDeviceGasConCode(gasDeviceGasConCode);
	}

	private void initThreeWeight() {	
		ygBeanInfo.setGasDeviceComVerCode("第一版本");
		ygBeanInfo.setGasValveStatus(gasValveStatus);
	}

	private void initYgType() {
		ygBeanInfo.setGasDeviceType("可燃气体探测器");
	}

	private void initYgManufacturer() {
		ygBeanInfo.setGasManufacturer("海曼");
	}
	/**
	 * 海曼可燃气 设置设备状态对应的编码
	 * 
	 * @param deviceStatus
	 * @return 0：无报警1：GAS报警恢复2：GAS轻度泄漏3：GAS重度泄漏4：短路故障5：开路故障6：机械手故障
	 * 7：其他故障8：自检完成9：设备断电
	 * 
	 * 自定义状态：0：报警；1：故障；2：正常
	 */
	private void initAlarmStatus() {
		switch (gasDeviceAlarmStatusCode) {
		case 0:
			ygBeanInfo.setGasDeviceCom("无报警");
			ygBeanInfo.setGasDeviceAlarmStatusCode(2);
			break;
		case 1:
			ygBeanInfo.setGasDeviceCom("GAS报警恢复");
			ygBeanInfo.setGasDeviceAlarmStatusCode(2);
			break;
		case 2:
			ygBeanInfo.setGasDeviceCom("GAS轻度泄漏");
			ygBeanInfo.setGasDeviceAlarmStatusCode(0);
			break;
		case 3:
			ygBeanInfo.setGasDeviceCom("GAS重度泄漏");
			ygBeanInfo.setGasDeviceAlarmStatusCode(0);
			break;
		case 4:
			ygBeanInfo.setGasDeviceCom("短路故障");
			ygBeanInfo.setGasDeviceAlarmStatusCode(1);
			break;
		case 5:
			ygBeanInfo.setGasDeviceCom("开路故障");
			ygBeanInfo.setGasDeviceAlarmStatusCode(1);
			break;
		case 6:
			ygBeanInfo.setGasDeviceCom("机械手故障");
			ygBeanInfo.setGasDeviceAlarmStatusCode(1);
			break;
		case 7:
			ygBeanInfo.setGasDeviceCom("其他故障");
			ygBeanInfo.setGasDeviceAlarmStatusCode(1);
			break;
		case 9:
			ygBeanInfo.setGasDeviceCom("设备断电");
			ygBeanInfo.setGasDeviceAlarmStatusCode(2);
			break;
		default:
			ygBeanInfo.setGasDeviceCom("自检完成");
			ygBeanInfo.setGasDeviceAlarmStatusCode(2);
			break;
		}
	}
	
	/**
	 * 海曼可燃气 设置设备状态对应的编码
	 * 
	 * @param deviceStatus
	 * @return -0 ：正常  1：正在自检   2：正在预热  3：自检成功 4：设备故障
	 */
	private void initGasStatus() {
		switch (gasDeviceCom) {
		case 0:
			ygBeanInfo.setGasDeviceCom("正常");
			break;
		case 1:
			ygBeanInfo.setGasDeviceCom("正在自检");
			break;
		case 2:
			ygBeanInfo.setGasDeviceCom("正在预热");
			break;
		case 3:
			ygBeanInfo.setGasDeviceCom("自检成功");
			break;
		default:
			ygBeanInfo.setGasDeviceCom("设备故障");
			break;
		}
	}
}
