package com.android.jesse.huitao.net.demo.app;

import android.app.Application;
import android.content.Context;

import com.android.jesse.huitao.net.demo.util.PackageUtil;
import com.android.jesse.huitao.net.url.UrlManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DemoApplication
 * @Desciption: 例子：定义一个Application
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class DemoApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        initNet();
    }

    private void initNet() {
        /**
         * 设置网络请求公共参数。注意以下设置的参数为只是get请求的参数
         */
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("app_version_name", PackageUtil.getAppVersionName(context));
        paramMap.put("app_version_code", String.valueOf(PackageUtil.getAppVersionCode(context)));
        UrlManager.getInstance().setCommonParam(paramMap);
    }
}
