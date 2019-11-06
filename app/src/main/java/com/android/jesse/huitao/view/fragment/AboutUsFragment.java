package com.android.jesse.huitao.view.fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.BaichuanUtils;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.TaobaoUtils;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.android.jesse.huitao.view.activity.BackMoneyActivity;
import com.android.jesse.huitao.view.activity.InterviewActivity;
import com.android.jesse.huitao.view.activity.LoginActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.custom.ImageViewRoundRect;
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

    @BindView(R.id.iv_trait)
    ImageViewRoundRect iv_trait;
    @BindView(R.id.tv_name)
    TextView tv_name;

    private Dialog mLogoutDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.about_us_fragment;
    }

    @Override
    protected void initEventAndData() {
        GlideUtil.getInstance().loadOriImg(mContext,SharedPreferencesUtil.getStringDate(Constant.AVATAR_URL),iv_trait);
        tv_name.setText(SharedPreferencesUtil.getStringDate(Constant.NICKNAME));
    }

    @OnClick({R.id.ll_share,R.id.ll_back_money,R.id.ll_logout,R.id.ll_orders,R.id.ll_interview})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_share:
                WxApiUtils.showShareDialog(mContext,R.mipmap.publish_qrcode);
                break;
            case R.id.ll_back_money:
                ActivityUtils.startActivity(BackMoneyActivity.class);
                break;
            case R.id.ll_logout:
                if (mLogoutDialog == null) {
                    showLogoutConfirmDialog();
                } else {
                    if (mLogoutDialog.isShowing()) {
                        mLogoutDialog.dismiss();
                    } else {
                        mLogoutDialog.show();
                    }
                }
                break;
            case R.id.ll_orders:
                ToastUtil.shortShow("暂未开发");
                break;
            case R.id.ll_interview:
                ActivityUtils.startActivity(InterviewActivity.class);
                break;
        }
    }

    private void showLogoutConfirmDialog() {
        mLogoutDialog = DialogUtil.showSimpleHintDialogForCommonVersion(mContext, "确认退出登录吗？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_positive:
                        BaichuanUtils.logout(mContext);
                        break;
                    case R.id.tv_negative:
                        mLogoutDialog.dismiss();
                        break;
                }
            }
        });
    }

}
