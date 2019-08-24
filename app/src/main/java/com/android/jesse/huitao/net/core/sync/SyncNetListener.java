package com.android.jesse.huitao.net.core.sync;


import com.android.jesse.huitao.net.bean.NetRetBean;

/**
 * @ClassName: SyncNetListener
 * @Desciption: 网络请求回调核心类
 * @author: yichaohua
 * @date: 2017-12-16
 */
public interface SyncNetListener {

    /**
     * http请求，数据解密部分，成功
     *
     * @param result result
     * @return NetRetBean
     */
    NetRetBean sendSuccess(String result);

    /**
     * http请求，数据解密部分，失败
     *
     * @param e e
     * @return NetRetBean
     */
    NetRetBean sendError(Exception e);
}
