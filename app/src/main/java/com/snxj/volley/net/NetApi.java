package com.snxj.volley.net;

import android.content.Context;

import com.snxj.volley.R;
import com.snxj.volley.untils.NetWorkUntil;
import com.snxj.volley.untils.UIUtils;

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

    public static final String LOGIN_URL = "UserController/mobileLogin.act";

    /**
     * HTTPS POST
     *
     * @param <T>
     * @param mContext
     * @param url
     * @param paramsMap
     * @param clz              MODEL
     * @param responseListener
     */
    public static <T> void doPost(Context mContext, String url,
                                  Map<String, String> paramsMap, final Class<T> clz,
                                  final ResponseListener<T> responseListener) {
        if (NetWorkUntil.isWifiProxy(mContext)) {
            UIUtils.showToast(mContext.getResources().getString(R.string.is_wifi_proxy));
            return;
        } else {
            NetUntil.newInstance(mContext).doPostHttps(SERVER_URL + url, paramsMap, clz, responseListener);
        }
    }

    /**
     * HTTPS GET
     *
     * @param <T>
     * @param mContext
     * @param url
     * @param paramsMap
     * @param clz              MODEL
     * @param responseListener
     */
    @SuppressWarnings("unused")
    private static <T> void doGet(Context mContext, String url,
                                  Map<String, String> paramsMap, final Class<T> clz,
                                  final ResponseListener<T> responseListener) {
        if (NetWorkUntil.isWifiProxy(mContext)) {
            UIUtils.showToast(mContext.getResources().getString(R.string.is_wifi_proxy));
            return;
        } else {
            NetUntil.newInstance(mContext).doGetHttps(SERVER_URL + url, paramsMap,
                    clz, responseListener);
        }
    }

}
