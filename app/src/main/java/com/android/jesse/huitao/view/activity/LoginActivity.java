package com.android.jesse.huitao.view.activity;

import android.view.View;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.utils.BaichuanUtils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import butterknife.OnClick;

/**
 * @Description: 登录页面
 * @author: zhangshihao
 * @date: 2019/10/27 0027
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected int getLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.ll_tb_login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_tb_login:
                BaichuanUtils.login(mContext);
                break;
        }
    }

}
