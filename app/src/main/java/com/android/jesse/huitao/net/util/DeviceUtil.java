package com.android.jesse.huitao.net.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ClassName: DeviceUtil
 * @Desciption: //TODO
 * @author: yichaohua
 * @date: 2018-01-03
 */
public class DeviceUtil {
    public static boolean isNetConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo != null && networkinfo.isAvailable()) {
            return true;
        }

        return false;
    }
}
