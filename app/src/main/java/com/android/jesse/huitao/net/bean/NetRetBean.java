package com.android.jesse.huitao.net.bean;

import com.android.jesse.huitao.net.listener.common.CallbackCode;

import java.util.Map;

/**
 * @ClassName: NetRetBean
 * @Desciption: 网络请求返回实体类
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class NetRetBean {

    /**
     * http请求返回的状态码，成功为200
     */
    private int responseCode = -1;

    /**
     * 返回码，具体说明请看{@link com.android.jesse.huitao.net.listener.common.CallbackCode}
     */
    private CallbackCode mCallbackCode;

    /**
     * 请求过程中发生异常，包括请求时，解析时。可能为null
     */
    private Exception mException;

    /**
     * 服务端的返回码，是服务端返回的数据中解析“code”字段得到的。可能为null
     */
    private int mServerCode = -1;

    /**
     * 服务端返回的消息，是服务端返回的数据中解析“msg”字段得到的。可能为null
     */
    private String mServerMsg;

    /**
     * 服务端返回是否需要同步用户信息，是服务端返回的数据中解析“reset”字段得到的。可能为null
     */
    private int reset = 0;

    /**
     * 服务端返回是否有版本跟新，是服务端返回的数据中解析“rewardReset”字段得到的。可能为null
     */
    private int rewardReset = 0;

    /**
     * 服务端返回的时间，是服务端返回的数据中解析“time”字段得到的。可能为null
     */
    private String mServerTime;

    /**
     * 服务端返回的数据，是服务端返回的数据中解析“data”字段得到的。可能为null
     */
    private String mServerData;

    /**
     * 服务端返回的数据解析成的bean，是用mServerData字段进行json解析得到的。可能为null
     */
    private Object mServerObject;

    private String request_id;

    private String error_response;

    private String sub_code;

    private String sub_msg;

    /**
     * 自定义监听器时使用。服务端返回的数据解析，可能有多个bean，是用mServerData字段进行json解析得到的。可能为null
     */
    private Map<String, Object> mServerObjectMap;

    public NetRetBean() {

    }

    public boolean isSuccess() {
        return mCallbackCode == CallbackCode.CODE_SUCCESS_REQUEST;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public CallbackCode getCallbackCode() {
        return mCallbackCode;
    }

    public void setCallbackCode(CallbackCode callbackCode) {
        this.mCallbackCode = callbackCode;
    }

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        this.mException = exception;
    }

    public int getServerCode() {
        return mServerCode;
    }

    public void setServerCode(int serverCode) {
        this.mServerCode = serverCode;
    }

    public String getServerMsg() {
        return mServerMsg;
    }

    public void setServerMsg(String serverMsg) {
        this.mServerMsg = serverMsg;
    }

    public String getServerTime() {
        return mServerTime;
    }

    public void setServerTime(String serverTime) {
        this.mServerTime = serverTime;
    }

    public String getServerData() {
        return mServerData;
    }

    public void setServerData(String serverData) {
        this.mServerData = serverData;
    }

    public Object getServerObject() {
        return mServerObject;
    }

    public void setServerObject(Object object) {
        this.mServerObject = object;
    }

    public Map<String, Object> getServerObjectMap() {
        return mServerObjectMap;
    }

    public void setServerObjectMap(Map<String, Object> serverObjectMap) {
        this.mServerObjectMap = serverObjectMap;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }

    public int getRewardReset() {
        return rewardReset;
    }

    public void setRewardReset(int rewardReset) {
        this.rewardReset = rewardReset;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getError_response() {
        return error_response;
    }

    public void setError_response(String error_response) {
        this.error_response = error_response;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    @Override
    public String toString() {
        return "NetRetBean{" +
                "responseCode=" + responseCode +
                ", mCallbackCode=" + mCallbackCode +
                ", mException=" + mException +
                ", mServerCode=" + mServerCode +
                ", mServerMsg='" + mServerMsg + '\'' +
                ", reset=" + reset +
                ", rewardReset=" + rewardReset +
                ", mServerTime='" + mServerTime + '\'' +
                ", mServerData='" + mServerData + '\'' +
                ", mServerObject=" + mServerObject +
                ", request_id='" + request_id + '\'' +
                ", error_response='" + error_response + '\'' +
                ", sub_code='" + sub_code + '\'' +
                ", sub_msg='" + sub_msg + '\'' +
                ", mServerObjectMap=" + mServerObjectMap +
                '}';
    }
}
