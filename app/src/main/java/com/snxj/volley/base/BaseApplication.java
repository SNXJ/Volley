package com.snxj.volley.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.snxj.volley.net.SSLCertificateValidation;
import com.snxj.volley.net.SelfSSLSocketFactory;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;
import com.tinkerpatch.sdk.server.callback.RollbackCallBack;
import com.tinkerpatch.sdk.server.callback.TinkerPatchRequestCallback;
import com.tinkerpatch.sdk.tinker.callback.ResultCallBack;
import com.tinkerpatch.sdk.tinker.service.TinkerServerResultService;

import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author Sheng XiaoJie
 * @date   2016/12/2
 * @desc
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance ;
    /**获取主线程的上下文对象 */
    private static BaseApplication context;
    /** 获取主线程ID */
    private static int mMainThreadId;
    /** 获取到主线程的Handler */
    private static Handler mainThreadHandler;
    /** 创建http请求队列 */
    private RequestQueue mRequestQueueWithHttp ;
    /** 创建自定义证书的Https请求队列 */
    private RequestQueue mRequestQueueWithSelfCertifiedSsl ;
    /** 创建默认证书的Https请求队列 */
    private RequestQueue mRequestQueueWithDefaultSsl ;


    /**通过单例模式获取对象*/
    public static BaseApplication getInstance(){
        return mInstance ;
    }
    /**对外暴露上下文对象 */
    public static BaseApplication getApplication() {
        return context;
    }
    /**对外暴露主线程的ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**对外暴露主线程的handler */
    public static Handler getMainThreadHandler() {
        return mainThreadHandler;
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
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        mInstance = this ;
        initTinker();
    }
    private static final String TAG = "volly_base_applicatoon";
    private ApplicationLike tinkerApplicationLike;

    /**
     * Tinker
     */
    private void initTinker() {
        tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
        simpleSample();

    }
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
    }

    /**
     * 简单调用
     */
    private void simpleSample() {
        //开始检查是否有补丁，这里配置的是每隔访问3小时服务器是否有更新。
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true);

        TinkerPatch.with().fetchPatchUpdate(true);//每次调用
    }
    /**
     * 在这里给出TinkerPatch的所有接口解释
     * 更详细的解释请参考:http://tinkerpatch.com/Docs/api
     */
    private void useSample() {
        TinkerPatch.init(tinkerApplicationLike)
                //是否自动反射Library路径,无须手动加载补丁中的So文件
                //注意,调用在反射接口之后才能生效,你也可以使用Tinker的方式加载Library
                .reflectPatchLibrary()
                //向后台获取是否有补丁包更新,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchPatchUpdate(false)
                //设置访问后台补丁包更新配置的时间间隔,默认为3个小时
                .setFetchPatchIntervalByHours(3)
                //向后台获得动态配置,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchDynamicConfig(new ConfigRequestCallback() {
                    @Override
                    public void onSuccess(HashMap<String, String> hashMap) {

                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                }, false)
                //设置访问后台动态配置的时间间隔,默认为3个小时
                .setFetchDynamicConfigIntervalByHours(3)
                //设置当前渠道号,对于某些渠道我们可能会想屏蔽补丁功能
                //设置渠道后,我们就可以使用后台的条件控制渠道更新
                .setAppChannel("default")
                //屏蔽部分渠道的补丁功能
                .addIgnoreAppChannel("googleplay")
                //设置tinkerpatch平台的条件下发参数
                .setPatchCondition("test", "1")
                //设置补丁合成成功后,锁屏重启程序
                //默认是等应用自然重启
                .setPatchRestartOnSrceenOff(true)
                //我们可以通过ResultCallBack设置对合成后的回调
                //例如弹框什么
                .setPatchResultCallback(new ResultCallBack() {
                    @Override
                    public void onPatchResult(PatchResult patchResult) {
                        Log.i(TAG, "onPatchResult callback here");
                    }
                })
                //设置收到后台回退要求时,锁屏清除补丁
                //默认是等主进程重启时自动清除
                .setPatchRollbackOnScreenOff(true)
                //我们可以通过RollbackCallBack设置对回退时的回调
                .setPatchRollBackCallback(new RollbackCallBack() {
                    @Override
                    public void onPatchRollback() {
                        Log.i(TAG, "onPatchRollback callback here");
                    }
                });
    }

    /**
     * 自定义Tinker类的高级用法, 一般不推荐使用
     * 更详细的解释请参考:http://tinkerpatch.com/Docs/api
     */
    private void complexSample() {
        TinkerPatch.Builder builder = new TinkerPatch.Builder(tinkerApplicationLike);
        //修改tinker的构造函数,自定义类
        builder.listener(new DefaultPatchListener(this))
                .loadReporter(new DefaultLoadReporter(this))
                .patchReporter(new DefaultPatchReporter(this))
                .resultServiceClass(TinkerServerResultService.class)
                .upgradePatch(new UpgradePatch())
                .patchRequestCallback(new TinkerPatchRequestCallback());

        TinkerPatch.init(builder.build());
    }
}
