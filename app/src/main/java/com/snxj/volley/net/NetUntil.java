package com.snxj.volley.net;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.snxj.volley.untils.LogUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */

public class NetUntil {
    private static Context context;
    private static DataRequester.Method netMethod = DataRequester.Method.POST;// 默认
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
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     *            实体类
     * @param responseListener
     */

    public <T> void doPostHttps(String url, Map<String, String> paramsMap,
                                final Class<T> clz, final ResponseListener<T> responseListener) {
        netMethod = DataRequester.Method.POST;
        requestStrHTTPS(url, paramsMap, clz, responseListener);
    }

    /**
     * GET https
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     *            实体类
     * @param responseListener
     */
    public <T> void doGetHttps(String url, Map<String, String> paramsMap,
                               final Class<T> clz, final ResponseListener<T> responseListener) {
        netMethod = DataRequester.Method.GET;
        requestStrHTTPS(url, paramsMap, clz, responseListener);
    }

    /********************************************* 只对外暴漏Https StringRequest的POST和GET方法 *****************************************************************/
    public void doPostHttpsStr(String url, Map<String, String> paramsMap, final ResponseListener<?> responseListener) {
        netMethod = DataRequester.Method.POST;
    }
    /**
     * HTTPS String请求 默认证书
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */

    private <T> void requestStrHTTPS(String url, Map<String, String> paramsMap,
                                     final Class<T> clz, final ResponseListener<T> responseListener) {
        DataRequester
                .withDefaultHttps(context)
                .setUrl(url)
                .setBody(paramsMap)
                .setMethod(netMethod)
                .setStringResponseListener(
                        new DataRequester.StringResponseListener() {
                            @SuppressWarnings("unchecked")
                            public void onResponse(String response) {
                                resultLog(response.toString());
                                if(null ==clz){
                                    responseListener.responSuccess((T) response);

                                }else{
                                    responseListener.responSuccess(new GsonParser<T>().GsonString(response, clz));
                                }
                            }
                        })
                .setResponseErrorListener(
                        new DataRequester.ResponseErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(
                                        context,
                                        ErrorHelper.getErrorMsg(error, context),
                                        Toast.LENGTH_SHORT).show();
                                LogUtils.i(
                                        "++++qdw++++",
                                        "errorCode="
                                                + ErrorHelper.getErrorMsg(
                                                error, context));
                            }
                        }).requestString();
    }

    /**
     * HTTPS JSON请求 默认证书
     * @param <T>
     *
     * @param url
     * @param jsonBody
     * @param clz
     * @param responseListener
     */
    @SuppressWarnings("unused")
    private <T> void requestJsonHTTPS(String url, JSONObject jsonBody,
                                      final Class<T> clz, final ResponseListener<T> responseListener) {
        DataRequester
                .withDefaultHttps(context)
                .setUrl(url)
                .setBody(jsonBody)
                .setMethod(netMethod)
                .setJsonResponseListener(
                        new DataRequester.JsonResponseListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                responseListener.responSuccess(new GsonParser<T>()
                                        .GsonJson(response, clz));
                                resultLog(response.toString());

                            }
                        })
                .setResponseErrorListener(
                        new DataRequester.ResponseErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //	responseListener.resonError(error);
                                LogUtils.i(
                                        "++++qdw++++",
                                        "errorCode="
                                                + ErrorHelper.getErrorMsg(
                                                error, context));
                            }
                        }).requestJson();
    }

    /**
     * HTTPS JSONArray请求 默认证书
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    @SuppressWarnings("unused")
    private <T> void requestJsonArrayHTTPS(String url,
                                           Map<String, String> paramsMap, final Class<T> clz,
                                           final ResponseListener<List<T>> responseListener) {
        DataRequester
                .withDefaultHttps(context)
                .setUrl(url)
                .setBody(paramsMap)
                .setJsonArrayResponseListener(
                        new DataRequester.JsonArrayResponseListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                responseListener.responSuccess(new GsonParser<T>()
                                        .GsonJsonArray(response, clz));
                            }
                        })
                .setResponseErrorListener(
                        new DataRequester.ResponseErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                LogUtils.i(
                                        "++++++++qdw+++++++++",
                                        "errorCode="
                                                + ErrorHelper.getErrorMsg(
                                                error, context));
                            }
                        }).requestJsonArray();
    }

    /**
     * http String请求
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    @SuppressWarnings("unused")
    private <T> void requestStrHTTP(String url, Map<String, String> paramsMap,
                                    final Class<T> clz, final ResponseListener<T> responseListener) {
        DataRequester
                .withHttp(context)
                .setUrl(url)
                .setMethod(netMethod)
                .setBody(paramsMap)
                .setResponseErrorListener(
                        new DataRequester.ResponseErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //responseListener.resonError(error);
                                resultLog(error.toString());
                            }
                        })
                .setStringResponseListener(
                        new DataRequester.StringResponseListener() {
                            @Override
                            public void onResponse(String response) {
                                responseListener.responSuccess(new GsonParser<T>()
                                        .GsonString(response, clz));
                                resultLog(response.toString());
                            }
                        }).requestString();
    }

    /**
     * HTTP JSON请求
     * @param <T>
     *
     * @param url
     * @param paramsMap
     * @param clz
     * @param responseListener
     */
    @SuppressWarnings("unused")
    private <T> void requestJsonHTTP(String url, Map<String, String> paramsMap,
                                     final Class<T> clz, final ResponseListener<T> responseListener) {
        DataRequester
                .withHttp(context)
                .setUrl(url)
                .setBody(paramsMap)
                .setResponseErrorListener(
                        new DataRequester.ResponseErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //responseListener.resonError(error);
                                resultLog(error.toString());
                            }
                        })
                .setJsonResponseListener(
                        new DataRequester.JsonResponseListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                responseListener.responSuccess(new GsonParser<T>().GsonJson(response, clz));
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
        DataRequester.withHttp(context).setUrl(url)
                .setMethod(DataRequester.Method.GET)
                // GET
                .setImageView(iv).setDafaultImage(dafaultRes)
                .setFailImage(dafaultRes).requestImage();
    }

    private void resultLog(String str) {
        LogUtils.i("++++qdw++++", "Result=" + str);
    }

}
