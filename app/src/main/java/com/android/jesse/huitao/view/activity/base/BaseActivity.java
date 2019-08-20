package com.android.jesse.huitao.view.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.jesse.huitao.utils.ScreenManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        System.gc();
    }
}
