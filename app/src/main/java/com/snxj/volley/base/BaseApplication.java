package com.snxj.volley.base;

import android.app.Application;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.snxj.volley.net.SSLCertificateValidation;
import com.snxj.volley.net.SelfSSLSocketFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance ;
    /**获取主线程的上下文对象 */
    private static BaseApplication context;
    /** 创建http请求队列 */
    private RequestQueue mRequestQueueWithHttp ;
    /** 创建自定义证书的Https请求队列 */
    private RequestQueue mRequestQueueWithSelfCertifiedSsl ;
    /** 创建默认证书的Https请求队列 */
    private RequestQueue mRequestQueueWithDefaultSsl ;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        mInstance = this ;
//Tinker
       // TinkerPatch.init(TinkerPatchApplicationLike.getTinkerPatchApplicationLike());
    }

    /**通过单例模式获取对象*/
    public static BaseApplication getInstance(){
        return mInstance ;
    }
    /* 对外暴露上下文对象 */
    public static BaseApplication getApplication() {
        return context;
    }
    /**
     * 获取http请求队列
     * @return
     */
    public RequestQueue getRequestQueueWithHttp(){
        if(mRequestQueueWithHttp == null){
            //创建普通的request
            mRequestQueueWithHttp = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueueWithHttp ;
    }
    /**
     * 获取默认证书https请求队列
     * @return
     */
    public RequestQueue getRequestQueueWithDefaultSsl(){
        if(mRequestQueueWithDefaultSsl == null){
            Network network = new BasicNetwork(new HurlStack());
            Cache cache = new DiskBasedCache(getCacheDir(),1024 * 1024) ;
            mRequestQueueWithDefaultSsl = new RequestQueue(cache,network) ;
            mRequestQueueWithDefaultSsl.start();
            SSLCertificateValidation.trustAllCertificate();
        }
        return mRequestQueueWithDefaultSsl ;
    }

    /**
     * 获取自定义证书请求队列
     * @return
     */
    public RequestQueue getRequestQueueWithSelfCertifiedSsl(){

        if(mRequestQueueWithSelfCertifiedSsl == null){
            SSLSocketFactory sslSocketFactory = SelfSSLSocketFactory.getSSLSocketFactory(getApplicationContext());
            Network network = new BasicNetwork(new HurlStack(null,sslSocketFactory));
            Cache cache = new DiskBasedCache(getCacheDir(),1024 * 1024) ;
            mRequestQueueWithSelfCertifiedSsl = new RequestQueue(cache,network) ;
            mRequestQueueWithSelfCertifiedSsl.start();

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // 当URL的主机名和服务器的标识主机名不匹配默认返回true
                    return true;
                }
            });
        }
        return mRequestQueueWithSelfCertifiedSsl ;
    }

}
