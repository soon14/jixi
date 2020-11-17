package com.pig4cloud.pigx.device.generator.utils;

public class Mes6698 {

	/**
	 * 6698消息解析
	 * 
	 * @param data
	 * @return
	 */
	public FusaierEntity parse(String data) {
		
		data = convertHexToString(data);

		StringBuffer stringBuffer = new StringBuffer(data);
		// 命令号
		String cmd = ParseUtil.hexToByteString(stringBuffer.substring(36, 38));
		if (cmd.equals("113")) {// 命令113 特殊
			return null;
		}
		FusaierEntity fusaierEntity = new FusaierEntity();
		// 流水号
		fusaierEntity.setFlow(ParseUtil.hexToShortString(stringBuffer.substring(4, 8)));
		// 协议版本
		fusaierEntity.setEdition(new StringBuffer().append(stringBuffer.substring(10, 12))
				.append(stringBuffer.substring(8, 10)).toString());
		// 数据体
		fusaierEntity.setDataBody(stringBuffer.substring(12, 24));
		fusaierEntity.setData1(ParseUtil.hexToByteString(stringBuffer.substring(12, 14)));
		fusaierEntity.setData2(ParseUtil.hexToUEByteString(stringBuffer.substring(14, 16)));
		fusaierEntity.setData3(ParseUtil.hexToByteString(stringBuffer.substring(16, 18)));
		fusaierEntity.setData4(ParseUtil.hexToByteString(stringBuffer.substring(18, 20)));
		fusaierEntity.setData5(ParseUtil.hexToByteString(stringBuffer.substring(20, 22)));
		fusaierEntity.setData6(ParseUtil.hexToByteString(stringBuffer.substring(22, 24)));
		// 公司编号、设备类型号
		String device = stringBuffer.substring(32, 34);
		if ((!device.equals("07")) && (!device.equals("08"))) {
			System.out.println("[6698消息入库]设备类型不符异常,device={}" + device);
			return null;
		}
		String brand = ParseUtil.hexToByteString(stringBuffer.substring(34, 36));
		if (brand.length() < 2) {
			brand = new StringBuffer().append("0").append(brand).toString();
		}
		// 设备类型
		fusaierEntity.setDevice(device);
		// 厂商
		fusaierEntity.setBrand(brand);
		// imei
		String address = new StringBuffer().append("0").append(brand).append("0").append(device).append(ParseUtil.hexToIntString(stringBuffer.substring(24, 32))).toString();
		if (address.length() != 15) {
			System.out.println("[6698消息入库]imei长度异常,IMEI={}" + address);
			return null;
		}
		String check = new StringBuffer().append(address).substring(6, 7);
		if (!check.equals("0")) {
			System.out.println("[6698消息入库]imei校检异常,IMEI={}" + address);
			return null;
		}
		// 地址号
		fusaierEntity.setImeiF(address);
		// 命令号
		fusaierEntity.setCmd(cmd);
		// 事件 //todo 补全
		fusaierEntity.setEvent(getEventByNbiotCmd(cmd));
		fusaierEntity.setDeviceStatusCode(getDeviceStatusCode(cmd));

		return fusaierEntity;

	}

	// Event 类型工具
	public static String getEventByNbiotCmd(String cmd) {
		switch (cmd) {
		case "0":
			return Event98Enum.RESERVE.getEvent();
		case "1":
			return Event98Enum.CONTROL_COMMAND.getEvent();
		case "2":
			return Event98Enum.SEND_DATA.getEvent();
		case "3":
			return Event98Enum.CONFIRM.getEvent();
		case "4":
			return Event98Enum.REQUEST.getEvent();
		case "5":
			return Event98Enum.ANSWER.getEvent();
		case "6":
			return Event98Enum.NEGATED.getEvent();
		case "7":
			return Event98Enum.FIRE_ALARM.getEvent();
		case "8":
			return Event98Enum.FIRE_ALARM_RECOVERY.getEvent();
		case "9":
			return Event98Enum.FIRE_FAULT.getEvent();
		case "10":
			return Event98Enum.FIRE_FAULT_RECOVERY.getEvent();
		case "11":
			return Event98Enum.FIRE_UNDERVOLTAGE.getEvent();
		case "12":
			return Event98Enum.FIRE_UNDERVOLTAGE_RECOVERY.getEvent();
		case "13":
			return Event98Enum.FIRE_TEST.getEvent();
		case "14":
			return Event98Enum.GAS_ALARM.getEvent();
		case "15":
			return Event98Enum.GAS_ALARM_RECOVERY.getEvent();
		case "16":
			return Event98Enum.GAS_TEST.getEvent();
		case "17":
			return Event98Enum.NORMAL_STATE.getEvent();
		case "18":
			return Event98Enum.REMOVE_ALARM.getEvent();
		case "19":
			return Event98Enum.REMOVE_ALARM_RECOVERY.getEvent();
		case "110":
			return Event98Enum.DEMOLITION_ALARM.getEvent();
		case "111":
			return Event98Enum.DEMOLITION_ALARM_RECOVERY.getEvent();
		case "112":
			return Event98Enum.SILENCING.getEvent();
		case "113":
			return Event98Enum.UPLOAD_IMSI.getEvent();

		}
		return "";
	}
	
	// DeviceStatusCode 类型工具
		public static String getDeviceStatusCode(String cmd) {
			switch (cmd) {
			case "0":
				return "1";
			case "1":
				return "1";
			case "2":
				return "1";
			case "3":
				return "1";
			case "4":
				return "1";
			case "5":
				return "1";
			case "6":
				return "1";
			case "7":
				return "2";
			case "8":
				return "1";
			case "9":
				return "3";
			case "10":
				return "1";
			case "11":
				return "2";
			case "12":
				return "1";
			case "13":
				return "2";
			case "14":
				return "2";
			case "15":
				return "1";
			case "16":
				return "2";
			case "17":
				return "1";
			case "18":
				return "2";
			case "19":
				return "1";
			case "110":
				return "2";
			case "111":
				return "1";
			case "112":
				return "1";
			case "113":
				return "1";
			}
			return "1";
		}

	public static String convertHexToString(String hex){
		 
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
	 
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
	 
		  return sb.toString();
		  }
	
}
