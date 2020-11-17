package com.pig4cloud.pigx.device.generator.utils;

//EventEnum 事件枚举
public enum Event98Enum {
  RESERVE("Reserve", "0"),
  CONTROL_COMMAND("控制命令", "1"),
  SEND_DATA("发送数据", "2"),
  CONFIRM("确认", "3"),
  REQUEST("请求", "4"),
  ANSWER("应答", "5"),
  NEGATED("否认", "6"),
  FIRE_ALARM("烟感探测器火警", "7"),
  FIRE_ALARM_RECOVERY("烟感探测器火警恢复", "8"),
  FIRE_FAULT("烟感探测器故障", "9"),
  FIRE_FAULT_RECOVERY("烟感探测器故障恢复", "10"),
  FIRE_UNDERVOLTAGE("烟感探测器电池欠压", "11"),
  FIRE_UNDERVOLTAGE_RECOVERY("烟感探测器欠压恢复", "12"),
  FIRE_TEST("烟感探测器测试报警", "13"),
  GAS_ALARM("燃气探测器报警", "14"),
  GAS_ALARM_RECOVERY("燃气探测器报警恢复", "15"),
  GAS_TEST("燃气探测器测试报警", "16"),
  NORMAL_STATE("正常状态", "17"),
  REMOVE_ALARM("火灾探测报警器拆除报警", "18"),
  REMOVE_ALARM_RECOVERY("火灾探测报警器拆除报警恢复", "19"),
  DEMOLITION_ALARM("火灾探测报警器拆除报警", "110"),
  DEMOLITION_ALARM_RECOVERY("火灾探测报警器拆除报警恢复", "111"),
  SILENCING("火灾探测报警器消音", "112"),
  UPLOAD_IMSI("上传ICCID码", "113"),
  ;
  private String event;

  private String cmd;

  Event98Enum(String event, String cmd) {
      this.event = event;
      this.cmd = cmd;
  }

public String getEvent() {
	return event;
}

public void setEvent(String event) {
	this.event = event;
}

public String getCmd() {
	return cmd;
}

public void setCmd(String cmd) {
	this.cmd = cmd;
}

}
