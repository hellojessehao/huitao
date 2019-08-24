package com.android.jesse.huitao.net.util;

import android.content.Context;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.net.bean.NetRetBean;

/**
 * @ClassName: NetToastUtil
 * @Desciption: 网络出错Toast工具类
 * @author: yichaohua
 * @date: 2018-01-03
 */
public class NetToastUtil {
    /**
     * 解析错误码
     */
    public static void requestError(Context context, NetRetBean netRetBean) {
        switch (netRetBean.getCallbackCode()) {
            case CODE_ERROR_JSON_EXP:
                serverError(context);
                break;
            case CODE_ERROR_SERVER_DATA_ERROR:
            case CODE_ERROR_HTTP_NOT_200:
            case CODE_ERROR_REQUEST_EXP:
            case CODE_ERROR_UNKNOWN:
            default:
//                requestError(context);
                ToastUtil.longPrompt(context, netRetBean.getServerMsg());
                break;
        }
    }

    /**
     * 服务器返回数据异常
     */
    private static void serverError(Context context) {
        ToastUtil.longPrompt(context, R.string.request_failed_server);
    }

    /**
     * 请求出错，可能是 网络请求发生异常 或者 网络未打开
     */
    private static void requestError(Context context) {
        if (DeviceUtil.isNetConnect(context)) {
            netError(context);
        } else {
            noOpenNet(context);
        }
    }

    /**
     * 网络请求发生异常
     */
    private static void netError(Context context) {
        ToastUtil.longPrompt(context, R.string.request_failed);
    }


    /**
     * 网络未打开
     */
    private static void noOpenNet(Context context) {
        ToastUtil.longPrompt(context, R.string.no_open_network);
    }
}
