package com.android.jesse.huitao.view.activity.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.Utils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * @Description: activity基类
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        ScreenManager.getInstance().setStatusBar(true,mContext);//设置沉浸式状态栏
        initEventAndData();
    }

    protected abstract void initEventAndData();

    protected abstract int getLayout();

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        if(SharedPreferencesUtil.getBooleanDate(Constant.KEY_IS_SEARCH_CLIPBOARD)){
            LogUtil.i(TAG+" searching clipBoard content");
            Utils.searchClipboardContent(mContext);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        System.gc();
    }
}
