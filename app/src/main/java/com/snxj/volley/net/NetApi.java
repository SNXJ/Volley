package com.snxj.volley.net;

import android.content.Context;

import java.util.Map;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */

public class NetApi {
    // 中赢
	public static final String SERVER_URL = "https://www.zyskcn.com/qdw/";
	public static final String IMAGE_BASE_URL = "https://oss-bj-bucket-zysk-test.img-cn-beijing.aliyuncs.com/";
    // 发版
//    public static final String SERVER_URL = "https://app.qiandaowei.com/qdw/";
//    public static final String IMAGE_BASE_URL ="https://oss-bj-bucket-zysk-1.img-cn-beijing.aliyuncs.com/";


    public static final String LOGIN_URL ="UserController/mobileLogin.act";

    public static void login(Context mContext, String method, String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener){
        NetUntil.newInstance(mContext,method).requestStrHTTPS(NetApi.SERVER_URL+url,paramsMap,clz,responseListener);
    }
}
