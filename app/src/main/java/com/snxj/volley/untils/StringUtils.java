package com.snxj.volley.untils;

import java.net.URLEncoder;


public class StringUtils {

    public static boolean isSelect(CharSequence str) {
        if ("待选择".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /* 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equals(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static String getUrlEncodePath(String path) {
        try {
            String substring1 = path.substring(0, path.lastIndexOf('/') + 1);
            String substring = path.substring(path.lastIndexOf('/') + 1);
            path = substring1 + URLEncoder.encode(substring, "utf-8");
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileType(String path) {
        try {
            return path.substring(path.lastIndexOf('.'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDislodgeSuffix(String name) {
        try {
            if (name.lastIndexOf('.') != -1) {
                return name.substring(0, name.lastIndexOf('.'));
            } else {
                return name;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理null值数据,如果为null 返回空串
     *
     * @param data
     * @return
     */
    public static String getNotNullData(String data) {
        if (null != data) {
            return data;
        }
        return "";
    }

    /**
     * 判断字符串是否是中文
     *
     * @param strName
     * @return true 为中文
     */
    public static boolean isChinese(String strName) {
        if (strName == null) {
            return false;
        }
        String reg = "[\\u4e00-\\u9fa5]+";
        return strName.matches(reg);
    }

    /**
     * 判断字符串是否是中文
     *
     * @param strName
     * @return true 为中文
     */
    public static boolean isChinese2(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
