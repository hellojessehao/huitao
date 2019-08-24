package com.android.jesse.huitao.net.helper;


import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.core.async.NetExcutor;
import com.android.jesse.huitao.net.core.async.NetListener;
import com.android.jesse.huitao.net.core.common.RequestType;
import com.android.jesse.huitao.net.core.sync.SyncNetExcutor;
import com.android.jesse.huitao.net.core.sync.SyncNetListener;
import com.android.jesse.huitao.net.data.NetConstants;
import com.android.jesse.huitao.net.request.HttpEngine;
import com.android.jesse.huitao.net.request.HttpUtil;

/**
 * @ClassName: NetHelper
 * @Desciption: 网络请求工具类
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class NetHelper {
    private HttpEngine mHttpEngine;
    private String mUrl;
    private String mParam;
    private boolean mIsWaitForToken;
    private NetListener mNetListener;
    private SyncNetListener mSyncNetListener;

    private NetHelper() {
        this.mIsWaitForToken = NetConstants.defaultWaitForToken;
    }

    /**
     * 创建实例
     * @return 返回一个实例
     */
    public static NetHelper create() {
        return new NetHelper();
    }

    /**
     * 这里可以指定默认用哪个网络请求引擎。
     * 当前默认用的是系统自带的HttpURLConnection
     * 开发者也可以根据自己的需要，修改成okhttp、retrofit
     *
     * @param httpEngine 网络请求引擎
     * @return this
     */
    public NetHelper httpEngine(HttpEngine httpEngine) {
        this.mHttpEngine = httpEngine;
        return this;
    }

    /**
     * 设置url
     * @param url url
     * @return this
     */
    public NetHelper url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置参数
     * @param param param
     * @return this
     */
    public NetHelper param(String param) {
        this.mParam = param;
        return this;
    }

    /**
     * 是否等待token
     * @param isWaitForToken isWaitForToken
     * @return this
     */
    public NetHelper isWaitForToken(boolean isWaitForToken) {
        this.mIsWaitForToken = isWaitForToken;
        return this;
    }

    /**
     * 发起get异步请求
     * @param netListener 异步请求监听器
     */
    public void get(NetListener netListener) {
        this.mNetListener = netListener;
        handleRequest(false, RequestType.REQUEST_TYPE_GET);
    }

    /**
     * 发起post异步请求
     * @param netListener 异步请求监听器
     */
    public void post(NetListener netListener) {
        this.mNetListener = netListener;
        handleRequest(false,RequestType.REQUEST_TYPE_POST);
    }

    /**
     * 发起request异步请求
     * @param requestType 请求类型
     * @param netListener 异步请求监听器
     */
    public void request(RequestType requestType,NetListener netListener) {
        this.mNetListener = netListener;
        handleRequest(false,requestType);
    }

    /**
     * 发起request异步请求
     * @param jsonContentType   Content-Type是否为application/json
     * @param requestType 请求类型
     * @param netListener 异步请求监听器
     */
    public void request(boolean jsonContentType,RequestType requestType,NetListener netListener) {
        this.mNetListener = netListener;
        handleRequest(jsonContentType,requestType);
    }

    /**
     * 异步请求
     * @param jsonContentType   Content-Type是否为application/json
     * @param requestType requestType
     */
    private void handleRequest(boolean jsonContentType,RequestType requestType) {
        NetExcutor netExcutor = new NetExcutor();

        if (mHttpEngine == null) {
            mHttpEngine = new HttpUtil();
        }
        netExcutor.setHttpEngine(mHttpEngine);

        if (mUrl == null || mUrl.equals("")) {
            return;
        }
        netExcutor.setUrl(mUrl);

        netExcutor.setParams(mParam);

        netExcutor.setIsWaitForToken(mIsWaitForToken);

        netExcutor.setNetListener(mNetListener);

//        switch (requestType) {
//            case REQUEST_TYPE_GET:
//                netExcutor.get();
//                break;
//            case REQUEST_TYPE_POST:
//                netExcutor.post();
//                break;
//        }

        netExcutor.request(jsonContentType,requestType);
    }

    /**
     * 发起get同步请求
     * @param syncNetListener syncNetListener
     * @return NetRetBean
     */
    public NetRetBean syncGet(SyncNetListener syncNetListener) {
        this.mSyncNetListener = syncNetListener;
        return syncRequest(RequestType.REQUEST_TYPE_GET);
    }

    /**
     * 发起post同步请求
     * @param syncNetListener syncNetListener
     * @return NetRetBean
     */
    public NetRetBean syncPost(SyncNetListener syncNetListener) {
        this.mSyncNetListener = syncNetListener;
        return syncRequest(RequestType.REQUEST_TYPE_POST);
    }

    /**
     * 同步请求
     * @param requestType requestType
     * @return NetRetBean
     */
    private NetRetBean syncRequest(RequestType requestType) {
        NetRetBean netRetBean = null;
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();

        if (mHttpEngine == null) {
            mHttpEngine = new HttpUtil();
        }
        syncNetExcutor.setHttpEngine(mHttpEngine);

        if (mUrl == null || mUrl.equals("")) {
            return null;
        }
        syncNetExcutor.setUrl(mUrl);

        syncNetExcutor.setParams(mParam);

        syncNetExcutor.setIsWaitForToken(mIsWaitForToken);

        syncNetExcutor.setSyncNetListener(mSyncNetListener);

        switch (requestType) {
            case REQUEST_TYPE_GET:
                netRetBean = syncNetExcutor.get();
                break;
            case REQUEST_TYPE_POST:
                netRetBean = syncNetExcutor.post();
                break;
        }

        return netRetBean;
    }
}
