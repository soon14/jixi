package com.pig4cloud.pigx.device.generator.utils;

import java.sql.Timestamp;

public class FusaierEntity {

	private int id;
	private String flow;//流水号
	private String edition;//协议版本
	private String dataBody;//数据体
	private String data1;//信号强度
	private String data2;//烟雾浓度
	private String data3;//报警灵敏度
	private String data4;//电池电压
	private String data5;//心跳间隔
	private String data6;//温度值
	private String remark;//备注
	private String imeiF;//设备编号
	private String device;//设备类型 07指6698
	private String brand;//公司编号10指福赛尔电信
	private String cmd;//命令号
	private String event;//事件 113事件指上传卡的iccid
	private Timestamp time;//时间
	
	private String deviceStatusCode; 

public String getDeviceStatusCode() {
		return deviceStatusCode;
	}

	public void setDeviceStatusCode(String deviceStatusCode) {
		this.deviceStatusCode = deviceStatusCode;
	}

public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getDataBody() {
		return dataBody;
	}

	public void setDataBody(String dataBody) {
		this.dataBody = dataBody;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getData5() {
		return data5;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}

	public String getData6() {
		return data6;
	}

	public void setData6(String data6) {
		this.data6 = data6;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImeiF() {
		return imeiF;
	}

	public void setImeiF(String imeiF) {
		this.imeiF = imeiF;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "FusaierEntity [id=" + id + ", flow=" + flow + ", edition=" + edition + ", dataBody=" + dataBody
				+ ", data1=" + data1 + ", data2=" + data2 + ", data3=" + data3 + ", data4=" + data4 + ", data5=" + data5
				+ ", data6=" + data6 + ", remark=" + remark + ", imeiF=" + imeiF + ", device=" + device + ", brand="
				+ brand + ", cmd=" + cmd + ", event=" + event + ", time=" + time + "]";
	}
	
}
