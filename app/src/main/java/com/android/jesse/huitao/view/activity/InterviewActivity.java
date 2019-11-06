package com.android.jesse.huitao.view.activity;

import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 招募合伙人页面
 * @author: zhangshihao
 * @date: 2019/11/6
 */
public class InterviewActivity extends BaseActivity {

    private static final String TAG = InterviewActivity.class.getSimpleName();

    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected int getLayout() {
        return R.layout.interview_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true,mContext);

        tv_title.setText("招募合伙人");
    }

    @OnClick({R.id.iv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
