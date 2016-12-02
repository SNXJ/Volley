package com.snxj.volley.net;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.snxj.volley.untils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;
/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */

public class NetUntil {
    private static Context context;
    private static DataRequester.Method netMethod = DataRequester.Method.POST;
    private static NetUntil mInstance;

    public static NetUntil newInstance(Context mContext, String method) {
        context = mContext;
         if ("GET".equals(method)) {
            netMethod = DataRequester.Method.GET;
        }else if ("POST".equals(method)) {
             netMethod = DataRequester.Method.POST;
         }
        if (mInstance == null) {
            synchronized (NetUntil.class) {
                if (mInstance == null) {
                    mInstance = new NetUntil();
                }
            }
        }
        return mInstance;
    }
    /**
     * HTTPS  String请求
     * 默认证书
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */

    public  void requestStrHTTPS(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(paramsMap).setMethod(netMethod).setStringResponseListener(new DataRequester.StringResponseListener() {
            @Override
            public void onResponse(String response) {
                responseListener.responSuccess(new GsonUntil().GsonString(response, clz));
                resultLog(response.toString());
            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.resonError(error);
                resultLog(error.toString());
            }
        }).requestString();
    }

    /**
     * HTTPS  JSON请求
     * 默认证书
     *
     * @param url
     * @param jsonBody
     * @param clz
     * @param responseListener
     */
    public  void requestJsonHTTPS(String url, JSONObject jsonBody, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(jsonBody).setMethod(netMethod).setJsonResponseListener(new DataRequester.JsonResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.responSuccess(new GsonUntil().GsonJson(response, clz));
                resultLog(response.toString());

            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.resonError(error);
                resultLog(error.toString());
            }
        }).requestJson();
    }
    /**
     * HTTPS  JSONArray请求
     * 默认证书
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    public  void requestJsonArrayHTTPS(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(paramsMap).setJsonArrayResponseListener(new DataRequester.JsonArrayResponseListener() {
            @Override
            public void onResponse(JSONArray response) {
                responseListener.responSuccess(new GsonUntil().GsonJsonArray(response, clz));
            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.resonError(error);
                resultLog(error.toString());
            }
        }).requestJsonArray();
    }

    /**
     * http  String请求
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    public  void requestStrHTTP(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withHttp(context).setUrl(url).setMethod(netMethod).setBody(paramsMap)
                .setResponseErrorListener(new DataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.resonError(error);
                        resultLog(error.toString());
                    }
                }).setStringResponseListener(new DataRequester.StringResponseListener() {
            @Override
            public void onResponse(String response) {
                responseListener.responSuccess(new GsonUntil().GsonString(response, clz));
                resultLog(response.toString());
            }
        }).requestString();
    }

    /**
     * HTTP   JSON请求
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    public  void requestJsonHTTP(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withHttp(context).setUrl(url).setBody(paramsMap)
                .setResponseErrorListener(new DataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.resonError(error);
                        resultLog(error.toString());
                    }
                }).setJsonResponseListener(new DataRequester.JsonResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.responSuccess(new GsonUntil().GsonJson(response, clz));
                resultLog(response.toString());
            }
        }).requestJson();
    }

    /**
     * HTTP 获取图片
     * @param iv
     * @param url
     * @param dafaultRes
     */
    public void requestImage(ImageView iv, String url , int dafaultRes) {
        DataRequester.withHttp(context)
                .setUrl(url)
                .setMethod(DataRequester.Method.GET)//GET
                .setImageView(iv)
                .setDafaultImage(dafaultRes)
                .setFailImage(dafaultRes)
                .requestImage();
    }
    private  void resultLog(String str) {
        LogUtils.i("++++++++QDW+++++++++", "result=" + str);
    }


}
