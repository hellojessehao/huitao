package com.android.jesse.huitao.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.android.jesse.huitao.model.bean.BaseResponseBean;
import com.android.jesse.huitao.model.bean.ParameterizedTypeImpl;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


/**
 * @Description: 接口请求工具类
 * @author: zhangshihao
 * @date: 2019/9/2
 */
public class RequestHelper<E> {

    private static final String TAG = RequestHelper.class.getSimpleName();

    public void request(String requestUrl,Map<String,String> bussinessParams,OnRequestListener<E> onRequestListener,Class aClass){
        new Thread(new RequestRunnable<E>(requestUrl,bussinessParams,onRequestListener,aClass)).start();
    }

    public void request(String baseUrl,String apiAddress,Map<String,String> params,OnRequestListener<E> onRequestListener,Class aClass){
        new Thread(new CommonRequestRunnable<E>(params,baseUrl+apiAddress,onRequestListener,aClass)).start();
    }

    /**
     * 通用请求类
     */
    public class CommonRequestRunnable<E> implements Runnable{

        private Map<String,String> bussinessMap;
        private String requestUrl;
        private Class aClass;

        public CommonRequestRunnable(Map<String, String> bussinessMap, String requestUrl,OnRequestListener<E> onRequestListener,Class aClass) {
            this.bussinessMap = bussinessMap;
            this.requestUrl = requestUrl;
            this.onRequestListener = onRequestListener;
            this.aClass = aClass;
        }

        @Override
        public void run() {
            if(bussinessMap == null){
                LogUtil.e(TAG+" RequestRunnable : bussinessMap is null");
                return;
            }
            try{
                String result = HttpUtils.callApi(new URL(requestUrl),bussinessMap);
                LogUtil.i(TAG+" "+result);
                mHandler.sendMessage(mHandler.obtainMessage(0,result));
            }catch (MalformedURLException mue){
                mue.printStackTrace();
                LogUtil.e(TAG+" RequestRunnable error : "+mue.toString());
                if(onRequestListener != null){
                    onRequestListener.onError("occur MalformedURLException");
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
                LogUtil.e(TAG+" RequestRunnable error : "+ioe.toString());
                if(onRequestListener != null){
                    onRequestListener.onError("occur IOException");
                }
            }
        }

        @SuppressLint("HandlerLeak")
        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    try {
                        String result = (String) msg.obj;
                        onRequestListener.onSuccess((E)(new Gson().fromJson(result,aClass)));
                    }catch (Exception e){
                        e.printStackTrace();
                        LogUtil.e(TAG+" json parse error : "+e.toString());
                        onRequestListener.onError("json parse error");
                    }
                }
            }
        };

        private OnRequestListener<E> onRequestListener;

        public OnRequestListener<E> getOnRequestListener() {
            return onRequestListener;
        }

        public void setOnRequestListener(OnRequestListener<E> onRequestListener) {
            this.onRequestListener = onRequestListener;
        }

    }

    /**
     * 淘宝API专用请求类
     */
    public class RequestRunnable<E> implements Runnable{

        private Map<String,String> bussinessMap;
        private String requestUrl;
        private Class aClass;

        public RequestRunnable(String requestUrl,Map<String,String> bussinessMap,OnRequestListener<E> onRequestListener,Class aClass){
            this.requestUrl = requestUrl;
            this.bussinessMap = bussinessMap;
            this.onRequestListener = onRequestListener;
            this.aClass = aClass;
        }

        @Override
        public void run() {
            if(bussinessMap == null){
                LogUtil.e(TAG+" RequestRunnable : bussinessMap is null");
                return;
            }
            try{
                String result = HttpUtils.callApi(new URL(HttpUtils.serverUrl), HttpUtils.createParamsMap(requestUrl,bussinessMap));
                LogUtil.i(TAG+" "+result);
                mHandler.sendMessage(mHandler.obtainMessage(0,result));
            }catch (MalformedURLException mue){
                mue.printStackTrace();
                LogUtil.e(TAG+" RequestRunnable error : "+mue.toString());
                if(onRequestListener != null){
                    onRequestListener.onError("occur MalformedURLException");
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
                LogUtil.e(TAG+" RequestRunnable error : "+ioe.toString());
                if(onRequestListener != null){
                    onRequestListener.onError("occur IOException");
                }
            }
        }

        @SuppressLint("HandlerLeak")
        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    try {
                        String result = (String) msg.obj;
                        onRequestListener.onSuccess((E)(new Gson().fromJson(result,aClass)));
                    }catch (Exception e){
                        e.printStackTrace();
                        LogUtil.e(TAG+" json parse error : "+e.toString());
                        onRequestListener.onError("json parse error");
                    }
                }
            }
        };

        private OnRequestListener<E> onRequestListener;

        public OnRequestListener<E> getOnRequestListener() {
            return onRequestListener;
        }

        public void setOnRequestListener(OnRequestListener<E> onRequestListener) {
            this.onRequestListener = onRequestListener;
        }
    }

    public interface OnRequestListener<E>{
        void onError(String msg);
        void onSuccess(E resultBean);
    }

}
