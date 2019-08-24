package com.android.jesse.huitao.utils;


import android.util.Log;

/**
 * Created by codeest on 2016/8/3.
 */
public class LogUtil {

    public static boolean isDebug = /*BuildConfig.DEBUG*/true;
    private static final String TAG = "zsh";

    public static void e(String tag, String o) {
        if(isDebug) {
            Log.e(tag, o);
        }
    }

    public static void w(String tag, String o) {
        if(isDebug) {
            Log.w(tag, o);
        }
    }

    public static void w(String o) {
        LogUtil.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Log.d(TAG,msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Log.i(TAG,msg);
        }
    }

    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }
}
