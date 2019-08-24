package com.android.jesse.huitao.net.core.async;

/**
 * @ClassName: NetListener
 * @Desciption: 网络请求回调核心类
 * @author: yichaohua
 * @date: 2017-12-16
 */
public interface NetListener {

    /**
     * http请求，数据解密部分，成功
     *
     * @param result result
     */
    void sendSuccess(String result);

    /**
     * http请求，数据解密部分，失败
     *
     * @param e e
     */
    void sendError(Exception e);
}
