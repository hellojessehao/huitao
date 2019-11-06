package com.android.jesse.huitao.view.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.android.jesse.huitao.view.activity.BackMoneyActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 关于我们页面
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class AboutUsFragment extends BaseFragment {

    private static final String TAG = AboutUsFragment.class.getSimpleName();

    private ClipboardManager clipboardManager;

    @Override
    protected int getLayoutId() {
        return R.layout.about_us_fragment;
    }

    @Override
    protected void initEventAndData() {
        clipboardManager = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @OnClick({R.id.ll_share,R.id.ll_back_money})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_share:
                WxApiUtils.showShareDialog(mContext,R.mipmap.publish_qrcode);
                break;
            case R.id.ll_back_money:
                ActivityUtils.startActivity(BackMoneyActivity.class);
                break;
        }
    }

}
