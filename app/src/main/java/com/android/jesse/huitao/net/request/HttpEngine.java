package com.android.jesse.huitao.net.request;


import com.android.jesse.huitao.net.core.common.RequestType;

/**
 * @ClassName: HttpEngine
 * @Desciption: //TODO
 * @author: yichaohua
 * @date: 2017-12-16
 */
public interface HttpEngine {
    public String get(String url) throws Exception;

    public String post(String url, String param) throws Exception;

    public String request(RequestType requestType, String url, String param) throws Exception;

    public String request(boolean jsonContentType, RequestType requestType, String url, String param) throws Exception;
}
