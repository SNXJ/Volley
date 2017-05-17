package com.snxj.volley.untils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;


public class NetWorkUntil {
    /**
     * 是否代理
     *
     * @param context
     * @return
     */
    public static boolean isWifiProxy(Context context) {
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    /**
     * 是否能上网
     *
     * @param context
     * @return 是否能上网
     */
    public static boolean hasConnectedNetwork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE); //"connectivity"
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 获取用户ip地址
     *
     * @return
     */
//    public static String getLocalIpAddress() {
//        try {
//
//            for (Enumeration<NetworkInterface> en = NetworkInterface
//
//                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
//
//                NetworkInterface intf = en.nextElement();
//
//                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
//
//                        .hasMoreElements(); ) {
//
//                    InetAddress inetAddress = ipAddr.nextElement();
//                    // ipv4地址
//                    if (!inetAddress.isLoopbackAddress()
//                            && InetAddressUtils.isIPv4Address(inetAddress
//                            .getHostAddress())) {
//
//                        return inetAddress.getHostAddress();
//
//                    }
//
//                }
//
//            }
//
//        } catch (Exception ex) {
//
//        }
//
//        return "";
//
//    }
}
