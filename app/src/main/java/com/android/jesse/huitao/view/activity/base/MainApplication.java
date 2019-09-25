package com.android.jesse.huitao.view.activity.base;

import android.app.Application;
import android.content.Context;

import com.android.jesse.huitao.utils.CrashHandler;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * @Description: Application类
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    public static Context mContext;
    public static MainApplication app;

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
    }
}
