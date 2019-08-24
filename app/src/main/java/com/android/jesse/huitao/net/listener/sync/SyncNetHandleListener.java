package com.android.jesse.huitao.net.listener.sync;


import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.core.sync.SyncNetListener;
import com.android.jesse.huitao.net.exp.RequestErrorException;
import com.android.jesse.huitao.net.exp.RespondErrorException;
import com.android.jesse.huitao.net.listener.common.CallbackCode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: SyncNetHandleListener
 * @Desciption: 公用网络逻辑，核心监听器。自定义监听器一般继承这个类
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class SyncNetHandleListener implements SyncNetListener {

    /**
     * 处理NetRetBean并返回。是一个中转站。本类和其子类都可以调用这个方法
     *
     * @param netRetBean netRetBean
     * @return netRetBean
     */
    protected NetRetBean handleResult(NetRetBean netRetBean) {
        return netRetBean;
    }

    @Override
    public NetRetBean sendSuccess(String result) {
        NetRetBean netRetBean = new NetRetBean();
        try {
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("error");
            String message = jsonObject.getString("msg");
            String time = jsonObject.getString("time");
            String data = jsonObject.getString("data");
            netRetBean.setServerCode(code);
            netRetBean.setServerMsg(message);
            netRetBean.setServerTime(time);
            netRetBean.setServerData(data);
            if (code == 0) {
                netRetBean.setCallbackCode(CallbackCode.CODE_SUCCESS_REQUEST);
                netRetBean = onReceivedRet(netRetBean);
            } else {
                netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_SERVER_DATA_ERROR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_JSON_EXP);
        }
        return handleResult(netRetBean);
    }

    @Override
    public NetRetBean sendError(Exception exp) {
        exp.printStackTrace();

        NetRetBean netRetBean = new NetRetBean();
        netRetBean.setException(exp);

        try {
            throw exp;
        } catch (RespondErrorException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_HTTP_NOT_200);
        } catch (RequestErrorException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_REQUEST_EXP);
        } catch (JSONException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_JSON_EXP);
        } catch (Exception e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_UNKNOWN);
        }

        return handleResult(netRetBean);
    }

    /**
     * 子类根据业务区分，将netRetBean解析成list或者单个实体，或者解析成其它结果
     *
     * @param netRetBean server返回的数据实体，data字段将在子类中解析
     * @return 解析后的netRetBean
     * @throws JSONException 解析json异常
     */
    abstract protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException;
}
