package com.pig4cloud.pigx.device.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;

public class ParseUtil {
	
	public static String hexToIntString(String intHexString) {
        byte[] bytes = toByteArray(intHexString);
        int imei9 = Integer.reverseBytes(ByteBuffer.wrap(bytes).getInt());
        StringBuffer imei = new StringBuffer();
        if (imei9 < 100000000) {
            imei.append("0").append(imei9);
        } else {
            imei.append(imei9);
        }
        return imei.toString();
    }


    public static String hexToByteString(String byteHexString){
        byte[] bytes = toByteArray(byteHexString);
        int cmd = bytes[0];

        return String.valueOf(cmd);
    }

    public static String hexToUEByteString(String byteHexString){
        byte[] bytes = toByteArray(byteHexString);
        int cmd = bytes[0]& 0xff;
        return String.valueOf(cmd);
    }
	
    public static String hexToShortString(String shortHexString){
        byte[] bytes = toByteArray(shortHexString);
        int cmd = Short.reverseBytes(ByteBuffer.wrap(bytes).getShort()) ;

        return String.valueOf(cmd);
    }
    
    
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}