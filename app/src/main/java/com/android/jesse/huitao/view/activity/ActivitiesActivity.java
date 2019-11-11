package com.android.jesse.huitao.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 活动页面
 * @author: zhangshihao
 * @date: 2019/11/9 0009
 */
public class ActivitiesActivity extends BaseActivity {

    private static final String TAG = ActivitiesActivity.class.getSimpleName();

    @BindView(R.id.tv_title)
    TextView tv_title;

    private ClipboardManager clipboardManager;

    @Override
    protected int getLayout() {
        return R.layout.activities_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true,mContext);

        tv_title.setText("活动");
    }

    @OnClick({R.id.tv_desc,R.id.iv_back})
    public void onclick(View v){
        switch (v.getId()){
            case R.id.tv_desc:
                if(clipboardManager == null){
                    clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                }
                clipboardManager.setPrimaryClip(ClipData.newPlainText(Constant.SERVICE_WECHAT,Constant.SERVICE_WECHAT));
                ToastUtil.shortShow("客服微信已复制，去添加吧~");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
