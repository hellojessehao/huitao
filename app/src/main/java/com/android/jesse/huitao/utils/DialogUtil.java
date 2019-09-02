package com.android.jesse.huitao.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

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
