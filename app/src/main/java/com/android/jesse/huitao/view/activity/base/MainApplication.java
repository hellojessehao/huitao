package com.android.jesse.huitao.view.activity.base;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.CrashHandler;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
    }
}
