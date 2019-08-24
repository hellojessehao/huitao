package com.android.jesse.huitao.net.listener.async;

import com.android.jesse.huitao.net.bean.NetBaseBean;
import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.listener.common.CallbackCode;
import com.android.jesse.huitao.net.util.NetBaseBeanUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: NetSingleBeanListener
 * @Desciption: 返回是单个Bean的网络请求Listener
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class NetSingleBeanListener<T extends NetBaseBean> extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        JSONObject object = new JSONObject(netRetBean.getServerData());
        T t = NetBaseBeanUtil.parseItem(getClass(), 0, object);
        netRetBean.setServerObject(t);
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        onSuccess((T) netRetBean.getServerObject());
    }

    /**
     * 运行在ui线程，返回单个实体
     *
     * @param t 当前bean
     */
    abstract protected void onSuccess(T t);
}
