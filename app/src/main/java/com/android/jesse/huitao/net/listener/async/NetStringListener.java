package com.android.jesse.huitao.net.listener.async;


import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.listener.common.CallbackCode;

import org.json.JSONException;

/**
 * @ClassName: NetStringListener
 * @Desciption: 返回字String的网络请求Listener
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class NetStringListener extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        netRetBean.setServerObject(netRetBean.getServerData());
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        onSuccess((String) netRetBean.getServerObject());
    }

    /**
     * 运行在ui线程，返回单个实体
     *
     * @param data 当前data
     */
    abstract protected void onSuccess(String data);
}
