package com.android.jesse.huitao.view.fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.net.util.DeviceUtil;
import com.android.jesse.huitao.utils.ApkInfoUtils;
import com.android.jesse.huitao.utils.BaichuanUtils;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.TaobaoUtils;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.android.jesse.huitao.view.activity.ActivitiesActivity;
import com.android.jesse.huitao.view.activity.BackMoneyActivity;
import com.android.jesse.huitao.view.activity.ChatGroupActivity;
import com.android.jesse.huitao.view.activity.InterviewActivity;
import com.android.jesse.huitao.view.activity.LoginActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.custom.ImageViewRoundRect;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.pgyersdk.feedback.PgyerFeedbackManager;

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
    @BindView(R.id.tv_activities)
    TextView tv_activities;
    @BindView(R.id.tv_version_name)
    TextView tv_version_name;

    private Dialog mLogoutDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.about_us_fragment;
    }

    @Override
    protected void initEventAndData() {
        tv_version_name.setText("v"+ApkInfoUtils.getVersionName(mContext));
        GlideUtil.getInstance().loadOriImg(mContext,SharedPreferencesUtil.getStringDate(Constant.AVATAR_URL),iv_trait);
        tv_name.setText(SharedPreferencesUtil.getStringDate(Constant.NICKNAME));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        SpannableString spannableString = new SpannableString(tv_activities.getText().toString());
        spannableString.setSpan(colorSpan,tv_activities.getText().toString().indexOf("("),tv_activities.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_activities.setText(spannableString);
    }

    @OnClick({R.id.ll_share,R.id.ll_back_money,R.id.ll_logout,R.id.ll_orders,R.id.ll_interview,
    R.id.ll_fallback,R.id.ll_join_chatgroup,R.id.ll_activities})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_activities:
                ActivityUtils.startActivity(ActivitiesActivity.class);
                break;
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
                ToastUtil.shortShow("暂未开放");
                break;
            case R.id.ll_interview:
                ActivityUtils.startActivity(InterviewActivity.class);
                break;
            case R.id.ll_fallback:
                new PgyerFeedbackManager.PgyerFeedbackBuilder()
                        .setShakeInvoke(false)       //fasle 则不触发摇一摇，最后需要调用 invoke 方法
                        // true 设置需要调用 register 方法使摇一摇生效
                        .setDisplayType(PgyerFeedbackManager.TYPE.DIALOG_TYPE)   //设置以Dialog 的方式打开
                        .setColorDialogTitle("#FF0000")    //设置Dialog 标题的字体颜色，默认为颜色为#ffffff
                        .setColorTitleBg("#FF0000")        //设置Dialog 标题栏的背景色，默认为颜色为#2E2D2D
                        .setBarBackgroundColor("#FF0000")      // 设置顶部按钮和底部背景色，默认颜色为 #2E2D2D
                        .setBarButtonPressedColor("#FF0000")        //设置顶部按钮和底部按钮按下时的反馈色 默认颜色为 #383737
                        .setColorPickerBackgroundColor("#FF0000")   //设置颜色选择器的背景色,默认颜色为 #272828
                        .setMoreParam("KEY1","VALUE1") //自定义的反馈数据
                        .setMoreParam("KEY2","VALUE2") //自定义的反馈数据
                        .builder()
                        .invoke();                  //激活直接显示的方式
                break;
            case R.id.ll_join_chatgroup://加入群聊
                ActivityUtils.startActivity(ChatGroupActivity.class);
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
