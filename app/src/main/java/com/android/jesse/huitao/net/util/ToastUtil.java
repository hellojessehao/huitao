package com.android.jesse.huitao.net.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: ToastUtil
 * @Desciption: 吐司工具类
 * @author: yichaohua
 * @date: 2018-01-03
 */
public class ToastUtil {
    public static void prompt(Context context, String prompt) {
        Toast.makeText(context, prompt, Toast.LENGTH_SHORT).show();
    }

    public static void prompt(Context context, int strId) {
        prompt(context, context.getString(strId));
    }

    public static void longPrompt(Context context, String prompt) {
        Toast.makeText(context, prompt, Toast.LENGTH_LONG).show();
    }

    public static void longPrompt(Context context, int strId) {
        longPrompt(context, context.getString(strId));
    }
}
