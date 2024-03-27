package com.cvmaster.fileidlerm.utils;

/**
 * @ClassName:StringUtils
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/11/011 22:32
 * @Version:1.0
 **/
public class StringUtils {

    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

}
