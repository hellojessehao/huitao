package com.android.jesse.huitao.view.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.view.activity.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 关于我们页面
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class AboutUsFragment extends BaseFragment {

    private static final String TAG = AboutUsFragment.class.getSimpleName();

    @BindView(R.id.tv_qq)
    TextView tv_qq;
    @BindView(R.id.tv_wechat)
    TextView tv_wechat;

    private ClipboardManager clipboardManager;

    @Override
    protected int getLayoutId() {
        return R.layout.about_us_fragment;
    }

    @Override
    protected void initEventAndData() {
        clipboardManager = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);

        tv_qq.setText("QQ："+Constant.SERVICE_QQ);
        tv_wechat.setText("微信："+Constant.SERVICE_WECHAT);
    }

    @OnClick({R.id.tv_qq,R.id.tv_wechat})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_qq:
                ClipData qqClip = ClipData.newPlainText("serviceQQ",Constant.SERVICE_QQ);
                clipboardManager.setPrimaryClip(qqClip);
                ToastUtil.shortShow("官方QQ号已复制到剪切板");
                break;
            case R.id.tv_wechat:
                ClipData wechatClip = ClipData.newPlainText("serviceWechat",Constant.SERVICE_WECHAT);
                clipboardManager.setPrimaryClip(wechatClip);
                ToastUtil.shortShow("官方微信号已复制到剪切板");
                break;
        }
    }

}
