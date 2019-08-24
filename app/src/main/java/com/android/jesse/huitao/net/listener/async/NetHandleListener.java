package com.android.jesse.huitao.net.listener.async;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.jesse.huitao.net.bean.NetBaseBean;
import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.core.async.NetListener;
import com.android.jesse.huitao.net.exp.RequestErrorException;
import com.android.jesse.huitao.net.exp.RespondErrorException;
import com.android.jesse.huitao.net.listener.common.CallbackCode;
import com.android.jesse.huitao.net.util.NetBaseBeanUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: NetHandleListener
 * @Desciption: 公用网络逻辑，核心监听器。自定义监听器一般继承这个类
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class NetHandleListener implements NetListener {

    private static final String TAG = "NetHandleListener";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    callbackResult((NetRetBean) msg.obj);
                    break;
            }
        }
    };

    private void callbackResult(NetRetBean netRetBean) {
        onCommon();
        switch (netRetBean.getCallbackCode()) {
            case CODE_ERROR_HTTP_NOT_200:
            case CODE_ERROR_REQUEST_EXP:
            case CODE_ERROR_SERVER_DATA_ERROR:
            case CODE_ERROR_JSON_EXP:
            case CODE_ERROR_UNKNOWN:
                onError(netRetBean.getCallbackCode(), netRetBean);
                break;

            case CODE_SUCCESS_REQUEST:
            case CODE_SUCCESS_LOCAL:
                onSuccess(netRetBean.getCallbackCode(), netRetBean);
                break;

            default:
                break;
        }
    }

    /**
     * 将非ui线程处理结果传到ui线程去。是一个中转站。本类和其子类都可以调用这个方法
     *
     * @param netRetBean 要返回给ui线程的实体
     */
    protected void handleResult(NetRetBean netRetBean) {
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = netRetBean;
        mHandler.sendMessage(msg);
    }

    @Override
    public void sendSuccess(String result) {
        NetRetBean netRetBean = new NetRetBean();
        try {
            JSONObject jsonObject = new JSONObject(result);
            int responseCode = jsonObject.getInt("responseCode");
            int reset = jsonObject.optInt("reset",0);
            int rewardReset = jsonObject.optInt("reward_reset",0);

            int code = jsonObject.optInt("error",0);
            Log.i(TAG,"code=" + code);
            String message = jsonObject.optString("msg","");
            Log.i(TAG,"message=" + message);
            String data = jsonObject.optString("data","");
            Log.i(TAG,"data=" + data);
            String request_id = jsonObject.optString("request_id","");
            Log.i(TAG," request_id = "+request_id);
            String error_response = jsonObject.optString("error_response","");
            Log.i(TAG,"error_response = "+error_response);
            String sub_code= jsonObject.optString("sub_code","");
            Log.i(TAG,"sub_code = "+sub_code);
            String sub_msg = jsonObject.optString("sub_msg","");
            Log.i(TAG,"sub_msg = "+sub_msg);

            netRetBean.setServerData(data);
            netRetBean.setResponseCode(responseCode);
            netRetBean.setServerCode(code);
            netRetBean.setServerMsg(message);
            netRetBean.setReset(reset);
            netRetBean.setRewardReset(rewardReset);

            if (code == 0) {
                netRetBean.setCallbackCode(CallbackCode.CODE_SUCCESS_REQUEST);
                onReceivedRet(netRetBean);
                return;
            } else if(code == 2){

                return;
            } else {
                netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_SERVER_DATA_ERROR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_JSON_EXP);
        }
        handleResult(netRetBean);
    }

    @Override
    public void sendError(Exception exp) {
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

        handleResult(netRetBean);
    }

    /**
     * 子类根据业务区分，将netRetBean解析成list或者单个实体，或者解析成其它结果
     *
     * @param netRetBean server返回的数据实体，data字段将在子类中解析
     * @throws JSONException 解析json异常
     */
    abstract protected void onReceivedRet(NetRetBean netRetBean) throws JSONException;

    /**
     * ui线程中实现这个方法来得到网络请求返回的数据
     *
     * @param successCode 成功的code
     * @param netRetBean  成功的实体bean，data字段已经在子类中解析完成
     */
    abstract protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean);

    /**
     * ui线程来处理错误码和错误信息
     *
     * @param errorCode  错误的code
     * @param netRetBean 错误的实体bean，字段可能不完整（完整的话还是error吗？呵呵）
     */
    abstract protected void onError(CallbackCode errorCode, NetRetBean netRetBean);

    /**
     * 运行在ui线程
     * <p>
     * 在onSuccess和onError之前都会调用这个方法。
     * 子类可以重写，onSuccess和onError有大量重复代码时，只要在重写的onCommon方法里面执行就可以了
     */
    protected void onCommon() {

    }

    /**
     * @ClassName: NetListBeanListener
     * @Desciption: 返回是List<Bean>的网络请求Listener
     * @author: yichaohua
     * @date: 2017-12-16
     */
    abstract public static class NetListBeanListener<T extends NetBaseBean> extends NetHandleListener {

        @Override
        protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
            JSONArray array = new JSONArray(netRetBean.getServerData());
            List<T> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                T t = NetBaseBeanUtil.parseItem(getClass(), 0, object);
                list.add(t);
            }
            netRetBean.setServerObject(list);
            handleResult(netRetBean);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
            onSuccess((List<T>) netRetBean.getServerObject());
        }

        /**
         * 运行在ui线程，返回多个实体
         *
         * @param ts 当前bean的List
         */
        abstract protected void onSuccess(List<T> ts);
    }
}
