package com.chs.boot.common.util;

import static com.chs.boot.common.util.MyBatisUtil.isNotEmpty;

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
    public static String lastIndexString(String str, String splitStr){
        String returnString = "";
        if(isNotEmpty(str)){
            returnString = str.substring(str.lastIndexOf(splitStr)+1);
        }
        return returnString;
    }
    public static String lowerCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toLowerCase(arr[0]);
        return new String(arr);
    }

    public static String replaceLast(String str, String regex, String replacement) {
        int regexIndexOf = str.lastIndexOf(regex);
        if(regexIndexOf == -1){
            return str;
        }else{
            return str.substring(0, regexIndexOf) + str.substring(regexIndexOf).replace(regex, replacement);
        }
    }

}
