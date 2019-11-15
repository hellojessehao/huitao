package com.android.jesse.huitao.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.AllConvertTklBean;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

import app.dinus.com.loadingdrawable.LoadingView;
import app.dinus.com.loadingdrawable.render.circle.jump.CollisionLoadingRenderer;

/**
 * @Description: 对话框工具类
 * @author: zhangshihao
 * @date: 2019/4/20
 */
public class DialogUtil {

    private static final String TAG = DialogUtil.class.getSimpleName();

    private static Dialog mDownloadDialog;

    //退出提示dialog
    public static void showExitHintDialog(final Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (context instanceof Activity) {
                            ((Activity) context).finish();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true);
        builder.show();
    }

    /**
     * 大众版通用提示框
     */
    public static Dialog showHintDialogForCommonVersion(Context context, String msg, String positiveBtnText,
                                                        String negativeBtnText, View.OnClickListener onClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hint_dialog_for_common_version);
        TextView tv_hint_content = dialog.findViewById(R.id.tv_hint_content);
        tv_hint_content.setText(msg);
        TextView tv_positive = dialog.findViewById(R.id.tv_positive);
        tv_positive.setText(positiveBtnText);
        TextView tv_negative = dialog.findViewById(R.id.tv_negative);
        tv_negative.setText(negativeBtnText);
        tv_positive.setOnClickListener(onClickListener);
        tv_negative.setOnClickListener(onClickListener);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(30);
            window.setAttributes(layoutParams);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * 大众版通用提示框
     */
    public static Dialog showHintDialogForBroadcastReceiver(Context context, String msg, String positiveBtnText,
                                                            String negativeBtnText, View.OnClickListener onClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hint_dialog_for_common_version);
        TextView tv_hint_content = dialog.findViewById(R.id.tv_hint_content);
        tv_hint_content.setText(msg);
        TextView tv_positive = dialog.findViewById(R.id.tv_positive);
        tv_positive.setText(positiveBtnText);
        TextView tv_negative = dialog.findViewById(R.id.tv_negative);
        tv_negative.setText(negativeBtnText);
        tv_positive.setOnClickListener(onClickListener);
        tv_negative.setOnClickListener(onClickListener);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(30);
            window.setAttributes(layoutParams);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            }else{
                window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * 大众版通用提示框 可定义标题
     */
    public static Dialog showHintDialogForCommonVersion(Context context, String title, String msg, String positiveBtnText,
                                                        String negativeBtnText, View.OnClickListener onClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hint_dialog_for_common_version);
        TextView tv_hint = dialog.findViewById(R.id.tv_hint);
        tv_hint.setText(title);
        TextView tv_hint_content = dialog.findViewById(R.id.tv_hint_content);
        tv_hint_content.setText(msg);
        TextView tv_positive = dialog.findViewById(R.id.tv_positive);
        tv_positive.setText(positiveBtnText);
        TextView tv_negative = dialog.findViewById(R.id.tv_negative);
        tv_negative.setText(negativeBtnText);
        tv_positive.setOnClickListener(onClickListener);
        tv_negative.setOnClickListener(onClickListener);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(30);
            window.setAttributes(layoutParams);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * 简单版操作提示框 用于清除缓存 退出登录等
     */
    public static Dialog showSimpleHintDialogForCommonVersion(Context context, String msg, View.OnClickListener onClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.simple_hint_dialog_for_common_version);
        TextView tv_msg = dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        TextView tv_positive = dialog.findViewById(R.id.tv_positive);
        tv_positive.setOnClickListener(onClickListener);
        TextView tv_negative = dialog.findViewById(R.id.tv_negative);
        tv_negative.setOnClickListener(onClickListener);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(30);
            window.setAttributes(layoutParams);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * 弹出淘口令展示对话框
     */
    public static Dialog showTklDisplayDialog(final Context context, AllConvertTklBean tklBean){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.tkl_display_dialog);
        final AllConvertTklBean.DataBean dataBean = tklBean.getData();
        final AllConvertTklBean.DataBean.ItemInfoBean itemInfoBean = dataBean.getItemInfo();
        ImageView iv_pict = dialog.findViewById(R.id.iv_pict);
        GlideUtil.getInstance().loadOriImg(context,itemInfoBean.getPict_url(),iv_pict);
        TextView tv_title = dialog.findViewById(R.id.tv_title);
        tv_title.setText(itemInfoBean.getTitle());
        TextView tv_discount_price = dialog.findViewById(R.id.tv_discount_price);
        tv_discount_price.setText("券后价：￥"+itemInfoBean.getQh_final_price());
        TextView tv_ori_price = dialog.findViewById(R.id.tv_ori_price);
        tv_ori_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//加中划线
        tv_ori_price.setText("原价：￥"+itemInfoBean.getReserve_price());
        TextView tv_back_money = dialog.findViewById(R.id.tv_back_money);
        tv_back_money.setText("返"+Utils.saveOnePositionAfterDot((float) itemInfoBean.getQh_final_commission()*Constant.BACK_MONEY_RATE) +"元");
        TextView tv_back_rate = dialog.findViewById(R.id.tv_back_rate);
        tv_back_rate.setText("("+(Float.parseFloat(dataBean.getMax_commission_rate())*Constant.BACK_MONEY_RATE)+"%)");//计算返现比例

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_close:
                        dialog.dismiss();
                        break;
                    case R.id.btn_goto_use:
                        BaichuanUtils.gotoDetailPage((Activity) context,Long.toString(dataBean.getItem_id()));
                        ToastUtil.shortShow("跳转中……");
                        LogUtil.i(TAG+" itemId = "+dataBean.getItem_id());
                        dialog.dismiss();
                        break;
                    case R.id.btn_only_copy:
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboardManager.setPrimaryClip(ClipData.newPlainText("tkl",dataBean.getCoupon_tpwd()));
                        ToastUtil.shortShow("淘口令已复制");
                        LogUtil.i(TAG+" tkl = "+dataBean.getCoupon_tpwd());
//                        dialog.dismiss();
                        break;
                }
            }
        };
        Button btn_goto_use = dialog.findViewById(R.id.btn_goto_use);
        btn_goto_use.setOnClickListener(onClickListener);
        Button btn_only_copy = dialog.findViewById(R.id.btn_only_copy);
        btn_only_copy.setOnClickListener(onClickListener);
        TextView tv_close = dialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(onClickListener);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    private static Dialog loadingDialog;

    /**
     * 等待弹窗无提示版本
     */
    public static void showWaitDialog(Context context){
        if(loadingDialog == null){
            loadingDialog = new Dialog(context);
            loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingDialog.setContentView(R.layout.animation_loading_dialog);
            loadingDialog.setCanceledOnTouchOutside(false);
            Window window = loadingDialog.getWindow();
            if(window != null){
//                window.setDimAmount(0);
                window.setBackgroundDrawableResource(android.R.color.transparent);
            }
        }
        loadingDialog.show();
    }

    /**
     * 等待弹窗带提示版本 hint ： 提示信息
     */
    public static void showWaitDialog(Context context,String hint){
        if(loadingDialog == null){
            loadingDialog = new Dialog(context);
            loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingDialog.setContentView(R.layout.animation_loading_dialog);
            loadingDialog.setCanceledOnTouchOutside(false);
            Window window = loadingDialog.getWindow();
            if(window != null){
//                window.setDimAmount(0);
                window.setBackgroundDrawableResource(android.R.color.transparent);
            }
        }
        if(!TextUtils.isEmpty(hint)){
            TextView tv_loading_hint = loadingDialog.findViewById(R.id.tv_loading_hint);
            tv_loading_hint.setVisibility(View.VISIBLE);
            tv_loading_hint.setText(hint);
        }
        loadingDialog.show();
    }

    public static void dismissWaitDialog(){
        if(loadingDialog == null || !loadingDialog.isShowing()){
            return;
        }
        loadingDialog.dismiss();
    }

    public static void dismissDownloadDialog(Context context) {
        if (mDownloadDialog != null && mDownloadDialog.isShowing()
                && context instanceof Activity && !((Activity) context).isFinishing()) {
            mDownloadDialog.dismiss();
            mDownloadDialog = null;
        }
    }

    public interface ButtonCallBack {
        void onPositive();

        void onNegative();
    }

}
