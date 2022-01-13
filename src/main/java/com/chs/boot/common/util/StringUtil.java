package com.chs.boot.common.util;

public class StringUtil {
    public static String getNewLineString() {
        return "\n";
    }
    private static String getTabString(int index) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        for (int inx = 0; inx < index; inx++) {
            returnStringBuilder.append("\t");
        }
        return returnStringBuilder.toString();
    }
}
