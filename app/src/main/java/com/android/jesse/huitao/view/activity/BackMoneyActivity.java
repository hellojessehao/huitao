package com.android.jesse.huitao.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 返现页面
 * @author: zhangshihao
 * @date: 2019/11/6
 */
public class BackMoneyActivity extends BaseActivity {

    private static final String TAG = BackMoneyActivity.class.getSimpleName();

    @BindView(R.id.tv_title)
    TextView tv_title;

    private ClipboardManager clipboardManager;

    @Override
    protected int getLayout() {
        return R.layout.back_money_activity;
    }

    @Override
    protected void initEventAndData() {
        tv_title.setText("返现");

        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @OnClick({R.id.iv_back,R.id.ll_copy_wx,R.id.ll_copy_qq})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_copy_wx:
                ClipData wxData = ClipData.newPlainText(Constant.SERVICE_WECHAT,Constant.SERVICE_WECHAT);
                clipboardManager.setPrimaryClip(wxData);
                ToastUtil.shortShow("微信号复制成功，去添加吧~");
                break;
            case R.id.ll_copy_qq:
                ClipData qqData = ClipData.newPlainText(Constant.SERVICE_QQ,Constant.SERVICE_QQ);
                clipboardManager.setPrimaryClip(qqData);
                ToastUtil.shortShow("QQ号复制成功，去添加吧~");
                break;
        }
    }

}
