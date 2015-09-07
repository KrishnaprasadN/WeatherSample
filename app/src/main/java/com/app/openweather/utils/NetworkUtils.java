package com.app.openweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class NetworkUtils {

    /**
     * Is user online.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Is user connected to wifi.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return (networkInfo.getType() == ConnectivityManager.TYPE_WIFI);
        }

        return false;
    }


    /**
     * Is user connected to mobile data.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isMobileDataConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        }

        return false;
   }
}
