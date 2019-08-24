package com.android.jesse.huitao.view.activity;

import android.content.Intent;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

/**
 * 中转页面
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        startActivity(new Intent(mContext,HomePageActivity.class));
        finish();
    }
}
