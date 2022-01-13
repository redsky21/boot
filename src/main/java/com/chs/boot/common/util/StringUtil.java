package com.chs.boot.common.util;

public class StringUtil {

    public static String getNewLineString() {
        return "\n";
    }

    public static String getTabString(int index) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        for (int inx = 0; inx < index; inx++) {
            returnStringBuilder.append("\t");
        }
        return returnStringBuilder.toString();
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public static String lowerCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toLowerCase(arr[0]);
        return new String(arr);
    }
}
