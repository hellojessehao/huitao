package com.android.jesse.huitao.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: NetBaseBean
 * @Desciption: 网络请求解析实体基类。子类必须有无参构造方法
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class NetBaseBean {

    /**
     * 子类实现这个方法，在其中解析json
     *
     * @param jsonObject 将要解析的JsonObject
     * @throws JSONException
     */
    abstract public void initByJson(JSONObject jsonObject) throws JSONException;

}
