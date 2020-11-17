package com.pig4cloud.pigx.device.generator.utils;

public class HexUtils {

    public static int sixteen2ten(String sixteenNum) {
        if (sixteenNum == null) {
            return 0;
        }
        Integer tenHexNum = Integer.valueOf(sixteenNum, 16);
        return tenHexNum;
    }

    public static String sixteen2binary(String sixteenNum) {

        if (sixteenNum == null || sixteenNum.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < sixteenNum.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(sixteenNum
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static String getHex(String hex, int starPosi, int endPosi) {
        if (hex != null && starPosi >= 0 && endPosi >= 0) {
            String substring = hex.substring(starPosi, endPosi);
            return substring;
        }
        return "";
    }
}
