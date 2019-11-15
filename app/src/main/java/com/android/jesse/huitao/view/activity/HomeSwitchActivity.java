package com.android.jesse.huitao.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * @Description: APP启动页
 * @author: zhangshihao
 * @date: 2019/9/29
 */
public class HomeSwitchActivity extends Activity {

    private static final String TAG = HomeSwitchActivity.class.getSimpleName();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(!SharedPreferencesUtil.getBooleanDate(Constant.IS_LOGIN)){
                startActivity(new Intent(HomeSwitchActivity.this,LoginActivity.class));
            }else{
                startActivity(new Intent(HomeSwitchActivity.this,HomePageActivity.class));
            }

            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getInstance().setStatusBar(true,this);//设置沉浸式状态栏
        setContentView(R.layout.home_switch_activity);

        mHandler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
