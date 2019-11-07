package com.android.jesse.huitao.view.activity.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
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
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    public static Context mContext;
    public static MainApplication app;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

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
        JPushInterface.init(this);     		// 初始化 JPush
    }

}
