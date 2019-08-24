package com.android.jesse.huitao.net.exp;

/**
 * @ClassName: ExceptionCode
 * @Desciption: 自定义异常码
 * @author: yichaohua
 * @date: 2017-12-16
 */
public enum ExceptionCode {
    /**
     * http请求的过程中发生了异常
     */
    EXPCODE_REQUEST_ERROR,


    /**
     * http请求返回的状态码不为200
     */
    EXPCODE_RESPOND_ERROR,
}
