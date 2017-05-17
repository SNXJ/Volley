package com.snxj.volley.untils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.snxj.volley.base.BaseApplication;

import java.util.List;


public class PackageUtils {
    /**
     * 根据PackageName获取PackageInfo
     */
    public static PackageInfo getPackageInfo(String packageName) {
        Context context = BaseApplication.getApplication();
        if (null == context) {
            return null;
        }
        if (StringUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        PackageInfo info = null;
        PackageManager manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获取本应用的VersionCode
     */
    public static int getVersionVode() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionCode;
        } else {
            return -1;
        }
    }

    /**
     * 获取本应用的VersionName
     */
    public static String getVersionName() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionName;
        } else {
            return null;
        }
    }

    /**
     * 得到跳转应用市场的intent
     *
     * @param paramContext
     * @return
     */
    public static Intent getIntent(Context paramContext) {
        StringBuilder localStringBuilder = new StringBuilder()
                .append("market://details?id=");
        String str = paramContext.getPackageName();
        localStringBuilder.append(str);
        Uri localUri = Uri.parse(localStringBuilder.toString());
        return new Intent("android.intent.action.VIEW", localUri);
    }

    // 直接跳转不判断是否存在市场应用
    public static void start(Context paramContext, String paramString) {
        Uri localUri = Uri.parse(paramString);
        Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
        localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        paramContext.startActivity(localIntent);
    }

    /**
     * 判断是否有应用市场
     *
     * @param paramContext
     * @param paramIntent
     * @return
     */
    public static boolean judge(Context paramContext, Intent paramIntent) {
        List<ResolveInfo> localList = paramContext.getPackageManager().queryIntentActivities(paramIntent, PackageManager.GET_INTENT_FILTERS);
        if ((localList != null) && (localList.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }
}
