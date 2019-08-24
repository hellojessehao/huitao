package com.android.jesse.huitao.net.listener.sync;

import com.android.jesse.huitao.net.bean.NetRetBean;

import org.json.JSONException;

/**
 * @ClassName: SyncNetStringListener
 * @Desciption: 返回字String的网络请求Listener
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class SyncNetStringListener extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        netRetBean.setServerObject(netRetBean.getServerData());
        return handleResult(netRetBean);
    }
}
