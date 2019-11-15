package com.android.jesse.huitao.view.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.CrashHandler;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * @Description: Application类
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = MainApplication.class.getSimpleName();

    public static Context mContext;
    public static MainApplication app;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    private int countActivity = 0;//前台界面数

    public static synchronized MainApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        app = this;
        //初始化sp
        SharedPreferencesUtil.initSharedPreferences(mContext);
        //初始化崩溃监听器，崩溃日志将存于 mnt/sdcard/tbcrash 目录下
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        //注册蒲公英日志上传
        PgyCrashManager.register(); //推荐使用
        //注册微信开放平台
        WxApiUtils.regToWx(this);
        //初始化百川SDK
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                LogUtil.d(TAG+" 百川初始化成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                LogUtil.e(TAG+" 百川初始化失败 : code = "+code+",msg = "+msg);
            }
        });
        //初始化JPush
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化JPush
        //监听Activity周期
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (countActivity == 0) {
            LogUtil.d(TAG + " APP running in foreground");
            SharedPreferencesUtil.setBooleanDate(Constant.SPKEY_APP_FOREGROUND, true);
            Intent intent = new Intent(Constant.ACTION_FOREGROUND_STATE_CHANGED);
            intent.putExtra(Constant.KEY_IS_FOREGROUND,true);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
        countActivity++;
        LogUtil.d(TAG + " onActivityResumed : countActivity = " + countActivity);
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        countActivity--;
        LogUtil.i(TAG + " onActivityStopped : countActivity=" + countActivity);
        if (countActivity <= 0) {
            LogUtil.d(TAG + " APP running in background");
            SharedPreferencesUtil.setBooleanDate(Constant.SPKEY_APP_FOREGROUND, false);
            Intent intent = new Intent(Constant.ACTION_FOREGROUND_STATE_CHANGED);
            intent.putExtra(Constant.KEY_IS_FOREGROUND,false);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
