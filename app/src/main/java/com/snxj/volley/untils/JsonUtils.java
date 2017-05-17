package com.snxj.volley.untils;

import com.google.gson.Gson;

public class JsonUtils {
    /**
     * 将封装好的bean对象 转换为json字符串
     *
     * @param obj bean对象
     * @return json 字符串 2015/12/16
     * @author sxj
     */
    public static String bean2JsonString(Object obj) {

        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
