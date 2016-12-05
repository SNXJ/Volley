package com.snxj.volley.net;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

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
    private static DataRequester.Method netMethod = DataRequester.Method.POST;//默认
    private static NetUntil mInstance;

    public static NetUntil newInstance(Context mContext) {
        context = mContext;
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
     * POST https
     *
     * @param url
     * @param paramsMap
     * @param clz              实体类
     * @param responseListener
     */
    public void doPostHttps(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        netMethod = DataRequester.Method.POST;
        requestStrHTTPS(url, paramsMap, clz, responseListener);
    }

    /**
     * GET https
     *
     * @param url
     * @param paramsMap
     * @param clz              实体类
     * @param responseListener
     */
    public void doGetHttps(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        netMethod = DataRequester.Method.GET;
        requestStrHTTPS(url, paramsMap, clz, responseListener);
    }


/*********************************************只对外暴漏Https StringRequest的POST和GET方法*****************************************************************/
    /**
     * HTTPS  String请求
     * 默认证书
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */

    private void requestStrHTTPS(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(paramsMap).setMethod(netMethod).setStringResponseListener(new DataRequester.StringResponseListener() {
            @Override
            public void onResponse(String response) {
                responseListener.responSuccess(new GsonParser().GsonString(response, clz));
                resultLog(response.toString());
            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ErrorHelper.getErrorMsg(error, context), Toast.LENGTH_SHORT).show();
                responseListener.resonError(error);
                LogUtils.i("++++++++qdw+++++++++", "errorCode=" +  ErrorHelper.getErrorMsg(error,context));
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
    private void requestJsonHTTPS(String url, JSONObject jsonBody, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(jsonBody).setMethod(netMethod).setJsonResponseListener(new DataRequester.JsonResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.responSuccess(new GsonParser().GsonJson(response, clz));
                resultLog(response.toString());

            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.resonError(error);
                LogUtils.i("++++++++qdw+++++++++", "errorCode=" +  ErrorHelper.getErrorMsg(error,context));
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
    private void requestJsonArrayHTTPS(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
        DataRequester.withDefaultHttps(context).setUrl(url).setBody(paramsMap).setJsonArrayResponseListener(new DataRequester.JsonArrayResponseListener() {
            @Override
            public void onResponse(JSONArray response) {
                responseListener.responSuccess(new GsonParser().GsonJsonArray(response, clz));
            }
        }).setResponseErrorListener(new DataRequester.ResponseErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.resonError(error);
                LogUtils.i("++++++++qdw+++++++++", "errorCode=" +  ErrorHelper.getErrorMsg(error,context));
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
    private void requestStrHTTP(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
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
                responseListener.responSuccess(new GsonParser().GsonString(response, clz));
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
    private void requestJsonHTTP(String url, Map<String, String> paramsMap, final Class clz, final ResponseListener responseListener) {
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
                responseListener.responSuccess(new GsonParser().GsonJson(response, clz));
                resultLog(response.toString());
            }
        }).requestJson();
    }

    /**
     * HTTP 获取图片
     *
     * @param iv
     * @param url
     * @param dafaultRes
     */
    public void requestImage(ImageView iv, String url, int dafaultRes) {
        DataRequester.withHttp(context)
                .setUrl(url)
                .setMethod(DataRequester.Method.GET)//GET
                .setImageView(iv)
                .setDafaultImage(dafaultRes)
                .setFailImage(dafaultRes)
                .requestImage();
    }

    private void resultLog(String str) {
        LogUtils.i("++++++++qdw+++++++++", "Result=" +str );
    }


}
