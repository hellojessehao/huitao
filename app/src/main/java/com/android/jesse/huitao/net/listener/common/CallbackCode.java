package com.android.jesse.huitao.net.listener.common;

/**
 * @ClassName: CallbackCode
 * @Desciption: 返回码
 * @author: yichaohua
 * @date: 2017-12-16
 */
public enum CallbackCode {

    /**
     * 网络请求数据成功并解析成功
     */
    CODE_SUCCESS_REQUEST,

    /**
     * 网络请求失败返回本地保存的数据（未实现）
     */
    CODE_SUCCESS_LOCAL,

    /**
     * 网络请求失败，未知错误
     */
    CODE_ERROR_UNKNOWN,

    /**
     * 网络请求失败，返回码不为200
     */
    CODE_ERROR_HTTP_NOT_200,

    /**
     * 网络请求失败，请求过程当中异常了
     */
    CODE_ERROR_REQUEST_EXP,

    /**
     * 服务器返回非成功数据，返回的code非0
     */
    CODE_ERROR_SERVER_DATA_ERROR,

    /**
     * toke失效，需要重新登录
     */
    CODE_ERROR_HTTP_UNAUTHORIZED,

    /**
     * 解析json出错
     */
    CODE_ERROR_JSON_EXP,
}
