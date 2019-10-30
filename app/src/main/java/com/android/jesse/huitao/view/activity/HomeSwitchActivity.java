package com.android.jesse.huitao.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

/**
 * @Description: APP启动页
 * @author: zhangshihao
 * @date: 2019/9/29
 */
public class HomeSwitchActivity extends BaseActivity {

    private static final String TAG = HomeSwitchActivity.class.getSimpleName();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(!SharedPreferencesUtil.getBooleanDate(Constant.IS_LOGIN)){
                startActivity(new Intent(mContext,LoginActivity.class));
            }else{
                startActivity(new Intent(mContext,HomePageActivity.class));
            }

            finish();
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.home_switch_activity;
    }

    @Override
    protected void initEventAndData() {
        mHandler.sendEmptyMessageDelayed(0,2000);
    }
}
