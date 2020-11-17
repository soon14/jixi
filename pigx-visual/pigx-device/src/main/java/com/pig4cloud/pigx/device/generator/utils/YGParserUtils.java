package com.pig4cloud.pigx.device.generator.utils;

public class YGParserUtils {


    private static final int deviceDataLength = 24;
    /**
     * 设备厂商编号
     */
    private int yGManufacturerCode;
    /**
     * 设备类型
     */
    private String yGDeviceTypeCode;
    /**
     * 设备型号编号
     */
    private String yGDeviceModleCode;
    /**
     * 设备协议版本编号
     */
    private String yGDeviceComVerCode;
    /**
     * 设备报警器软件版本
     */
    private String yGDeviceSofVerCode;
    /**
     * 服务器成功收到本地信息标志 1:成功收到对方信息
     */
    private String yGDeviceUpSucFlagCode;
    /**
     * 本地成功收到服务器信息标志 1:成功收到对方信息
     */
    private String yGDeviceDownSucFlagCode;
    /**
     * 烟感设备命令
     */
    private String yGDeviceComCode;
    /**
     * 烟感参数命令 上行命令
     */
    private String yGDeviceParametermComCode;
    /**
     * 烟感参数值 配合烟感参数命令
     */
    private String yGDeviceParametermValueCode;
    /**
     * 烟雾浓度
     */
    private int yGDeviceSmokeConCode;
    /**
     * 设备电量
     */
    private int yGDeviceEleValueCode;

    /**
     * 报警序列
     */

    private int yGDeviceAlaSequenceCode;
    /**
     * 使用时间
     */
    private int yGUserTime;

    private YGBeanInfo ygBeanInfo;
    private int tenWeight;
    private int sixWeight;

    public YGParserUtils(String deviceData) {
        if (deviceData.length() == deviceDataLength) {
            //截取 + 转换
            hexTransformation(deviceData);
        } else {
            System.out.print("数据解析失败，数据格式异常");
        }
    }

    private void hexTransformation(String deviceData) {
        //ASCII转换 字符   8013840400000000000d0012
        yGManufacturerCode = Integer.parseInt(deviceData.substring(0, 2));

        //16转2
        String twoWeight = HexUtils.sixteen2binary(deviceData.substring(2, 4));
        yGDeviceTypeCode = HexUtils.getHex(twoWeight, 0, 4);
        yGDeviceModleCode = HexUtils.getHex(twoWeight, 4, 8);

        //16转2
        String threeWeight = HexUtils.sixteen2binary(deviceData.substring(4, 6));
        yGDeviceComVerCode = HexUtils.getHex(threeWeight, 0, 2);
        yGDeviceSofVerCode = HexUtils.getHex(threeWeight, 3, 8);

        //16转2
        String fourWeight = HexUtils.sixteen2binary(deviceData.substring(6, 8));
        yGDeviceUpSucFlagCode = HexUtils.getHex(fourWeight, 0, 1);
        yGDeviceDownSucFlagCode = HexUtils.getHex(fourWeight, 1, 2);
        yGDeviceComCode = HexUtils.getHex(fourWeight, 2, 8);

        //16转2
        String fiveWeight = HexUtils.sixteen2binary(deviceData.substring(8, 10));
        yGDeviceParametermComCode = HexUtils.getHex(fiveWeight, 4, 8);

        //16转2
        sixWeight = HexUtils.sixteen2ten(deviceData.substring(10, 14));
        //yGDeviceParametermValueCode = sixWeight;

        //16转2
        yGDeviceSmokeConCode = HexUtils.sixteen2ten(deviceData.substring(14, 16));

        yGDeviceEleValueCode = HexUtils.sixteen2ten(deviceData.substring(16, 18));

        //16转10
        yGDeviceAlaSequenceCode = HexUtils.sixteen2ten(deviceData.substring(18, 20));

        //16转10
        yGUserTime = HexUtils.sixteen2ten(deviceData.substring(20, 24));


    }

    public YGBeanInfo getYgBeanInfo() {
        ygBeanInfo = new YGBeanInfo();
        initYgManufacturer();
        initYgType();
        initYgDeviceVer();
        initThreeWeight();
        initFourWeight();
        initFiveWeight();
        initSixWeight();
        initSevenWeight();
        initEightWeight();
        initNineWeight();
        initTenWeight();
        System.out.println(ygBeanInfo.toString());
        return ygBeanInfo;
    }

    private void initSevenWeight() {
        ygBeanInfo.setYGDeviceSmokeCon(yGDeviceSmokeConCode);
    }

    private void initEightWeight() {
        ygBeanInfo.setYGDeviceEleValue(yGDeviceEleValueCode);
    }

    private void initSixWeight() {
        ygBeanInfo.setYGDeviceParametermValue(sixWeight);
    }

    private void initNineWeight() {
        ygBeanInfo.setYGDeviceAlaSequence(yGDeviceAlaSequenceCode);
    }

    private void initFiveWeight() {
        if (yGDeviceParametermComCode != null) {
            switch (yGDeviceParametermComCode) {
                case "0000":
                    ygBeanInfo.setYGDeviceParametermCom("无命令");
                    break;
                case "0001":
                    ygBeanInfo.setYGDeviceParametermCom("修改心跳包时间");
                    break;
                case "0010":
                    ygBeanInfo.setYGDeviceParametermCom("获取空气校准值");
                    break;
                case "0011":
                    ygBeanInfo.setYGDeviceParametermCom("获取烟雾校准值");
                    break;
                default:
                    ygBeanInfo.setYGDeviceParametermCom("未知命令");
                    break;
            }
        }
    }

    private void initTenWeight() {
        ygBeanInfo.setYGUserTime(yGUserTime);
    }

    private void initFourWeight() {
        if (yGDeviceUpSucFlagCode != null) {
            switch (yGDeviceUpSucFlagCode) {
                case "1":
                    ygBeanInfo.setYGDeviceUpSucFlag("服务器成功收到本地信息");
                    break;
                default:
                    ygBeanInfo.setYGDeviceUpSucFlag("");
                    break;
            }
        }
        if (yGDeviceDownSucFlagCode != null) {
            switch (yGDeviceDownSucFlagCode) {
                case "1":
                    ygBeanInfo.setYGDeviceDownSucFlag("本地成功收到服务器信息标志");
                    break;
                default:
                    ygBeanInfo.setYGDeviceDownSucFlag("");
                    break;
            }
        }
        if (yGDeviceComCode != null) {
            switch (yGDeviceComCode) {
                case "000001":
                    ygBeanInfo.setYGDeviceCom("设备开机");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "000010":
                    ygBeanInfo.setYGDeviceCom("设备心跳");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "000011":
                    ygBeanInfo.setYGDeviceCom("烟雾报警");
                    ygBeanInfo.setDeviceCode(2);
                    break;
                case "000100":
                    ygBeanInfo.setYGDeviceCom("测试报警");
                    ygBeanInfo.setDeviceCode(2);
                    break;
                case "000101":
                    ygBeanInfo.setYGDeviceCom("低电压报警");
                    ygBeanInfo.setDeviceCode(3);
                    break;
                case "000110":
                    ygBeanInfo.setYGDeviceCom("烟雾消警");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "000111":
                    ygBeanInfo.setYGDeviceCom("静音命令");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "001000":
                    ygBeanInfo.setYGDeviceCom("设备入网");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "001001":
                    ygBeanInfo.setYGDeviceCom("设备消网");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "001011":
                    ygBeanInfo.setYGDeviceCom("常连接心跳");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "001100":
                    ygBeanInfo.setYGDeviceCom("底座低电压");
                    ygBeanInfo.setDeviceCode(1);
                    break;
                case "001101":
                    ygBeanInfo.setYGDeviceCom("探头失联");
                    ygBeanInfo.setDeviceCode(3);
                    break;
                default:
                    ygBeanInfo.setYGDeviceCom("未知通讯命令");
                    ygBeanInfo.setDeviceCode(1);
                    break;
            }
        }
    }

    private void initThreeWeight() {
        if (yGDeviceComVerCode != null) {
            switch (yGDeviceComVerCode) {
                case "01":
                    ygBeanInfo.setYGDeviceComVer("第一版本");
                    break;
                case "10":
                    ygBeanInfo.setYGDeviceComVer("第二版本");
                    break;
                case "11":
                    ygBeanInfo.setYGDeviceComVer("第三版本");
                    break;
                default:
                    ygBeanInfo.setYGDeviceComVer("未知版本");
                    break;
            }
        }
        //报警器软件版本(服务器平台不做解析)
        if (yGDeviceSofVerCode != null) {
            ygBeanInfo.setYGDeviceSofVer("");
        }


    }

    private void initYgDeviceVer() {
        if (yGDeviceModleCode != null) {
            switch (yGDeviceModleCode) {
                case "0001":
                    ygBeanInfo.setYGDeviceModel("PW-507 9V 电信版本，一体化烟感，NB05-01 模组");
                    break;
                case "0010":
                    ygBeanInfo.setYGDeviceModel("PW-507 9V 移动版本，一体化烟感，NB08-01 模组");
                    break;
                case "0011":
                    ygBeanInfo.setYGDeviceModel("PW-201 3.6V 电信版本，无线底座，NB05-01 模组");
                    break;
                case "0100":
                    ygBeanInfo.setYGDeviceModel("PW-201 3.6V 移动版本，无线底座，NB08-01 模组");
                    break;
                case "0101":
                    ygBeanInfo.setYGDeviceModel("PW-201 3.6V 移动版本，无线底座，M5310 模组");
                    break;
                case "0110":
                    ygBeanInfo.setYGDeviceModel("PW-507 3.6V 电信版本，一体化烟感，NB05-01 模组");
                    break;
                case "0111":
                    ygBeanInfo.setYGDeviceModel(" PW-507 3.6V 移动版本，一体化烟感，NB08-01 模组");
                    break;
                case "1000":
                    ygBeanInfo.setYGDeviceModel("PW-201 3.6V 全网通版本，无线底座，NB86-G 模组");
                    break;
                case "1001":
                    ygBeanInfo.setYGDeviceModel("PW-507 3.6V 移动版本，一体化烟感，M5310 模组");
                    break;
                default:
                    ygBeanInfo.setYGDeviceModel("未知型号");
                    break;


            }
        }

    }

    private void initYgType() {
        if (yGDeviceTypeCode != null) {
            switch (yGDeviceTypeCode) {
                case "0001":
                    ygBeanInfo.setYGDeviceType("烟雾报警器");
                    break;
                case "0010":
                    ygBeanInfo.setYGDeviceType("瓦斯报警器");
                    break;
                default:
                    ygBeanInfo.setYGDeviceType("未知设备类型");
                    break;
            }
        }
    }


    private void initYgManufacturer() {
        switch (yGManufacturerCode) {
            case 80:
                ygBeanInfo.setYGManufacturer("百思威");
                break;
            default:
                ygBeanInfo.setYGManufacturer("未知设备厂商");
                break;
        }
    }

}