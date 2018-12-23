package com.example.lehieudut.samplemvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utilities for checking connection.
 */
public final class ConnectionUtil {

    private ConnectionUtil() {
        // no instance
    }

    /**
     * Get the network info.
     */
    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity.
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = ConnectionUtil.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity to a Wifi network.
     */
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = ConnectionUtil.getNetworkInfo(context);
        return (info != null && info.isConnected()
                && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network.
     */
    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = ConnectionUtil.getNetworkInfo(context);
        return (info != null && info.isConnected()
                && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }
}
