package com.android.jesse.huitao.net.request;

import android.text.TextUtils;
import android.util.Log;

import com.android.jesse.huitao.net.core.common.RequestType;
import com.android.jesse.huitao.net.data.NetVariables;
import com.android.jesse.huitao.net.exp.RequestErrorException;
import com.android.jesse.huitao.net.exp.RespondErrorException;
import com.android.jesse.huitao.net.util.LogUtil;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @ClassName: HttpUtil
 * @Desciption: Http请求工具类
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class HttpUtil implements HttpEngine {

    private static final String TAG = "HttpUtil";
    private static final boolean DEBUG = true;

    private static final int CONNECT_TIMEOUT = 1000 * 10;
    private static final int READ_TIMEOUT = 1000 * 10;
    private static final int BUF_SIZE = 1024 * 10;

    @Override
    public String post(String requestUrl, String param) throws Exception {
        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            out = conn.getOutputStream();

            if (param != null && param.length() > 0) {
                out.write(param.getBytes());
            }
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new RespondErrorException(code);
            }
            is = conn.getInputStream();
            byte[] tmp = new byte[BUF_SIZE];
            baos = new ByteArrayOutputStream();
            int len = is.read(tmp);
            while (len != -1) {
                baos.write(tmp, 0, len);
                len = is.read(tmp);
            }
            return baos.toString("utf-8");
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String get(String requestUrl) throws Exception {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);
            conn.connect();
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new RespondErrorException(code);
            }
            is = conn.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUF_SIZE];
            int size;
            while ((size = is.read(buffer)) != -1) {
                baos.write(buffer, 0, size);
            }
            return baos.toString("utf-8");
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String request(RequestType requestType, String requestUrl, String param) throws Exception {
        if(DEBUG) {
            LogUtil.i(TAG, "requestUrl:" + requestUrl + " param:" + param);
        }else{
            Log.i(TAG, "requestUrl:" + requestUrl + " param:" + param);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            setMethod(conn,requestType);
            conn.setRequestProperty("Authorization", NetVariables.token);
            Log.i(TAG,"NetVariables.token=" + NetVariables.token);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);

            if(!(requestType == RequestType.REQUEST_TYPE_GET ||
                    requestType == RequestType.REQUEST_TYPE_DELETE)){
                conn.setDoOutput(true);
            }

            conn.connect();

            if(!(requestType == RequestType.REQUEST_TYPE_GET ||
                    requestType == RequestType.REQUEST_TYPE_DELETE)){
                out = conn.getOutputStream();

                if (param != null && param.length() > 0) {
                    out.write(param.getBytes());
                }
            }

            int code = conn.getResponseCode();
            Log.i(TAG,"ResponseCode=" + code);

            if (code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
            }else {
                is = conn.getErrorStream();
            }

            byte[] tmp = new byte[BUF_SIZE];
            baos = new ByteArrayOutputStream();
            int len = is.read(tmp);
            while (len != -1) {
                baos.write(tmp, 0, len);
                len = is.read(tmp);
            }

            String responses = baos.toString("utf-8");
            if(DEBUG) {
                LogUtil.i(TAG, "responses " + responses);
            }else{
                Log.i(TAG, "responses " + responses);
            }

            JSONObject jsonObject;
            if(TextUtils.isEmpty(responses)){
                jsonObject = new JSONObject();
            }else {
                jsonObject = new JSONObject(responses);
            }

            jsonObject.put("responseCode",code);

            return jsonObject.toString();
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String request(boolean jsonContentType, RequestType requestType, String requestUrl, String param) throws Exception {
        if(DEBUG) {
            LogUtil.i(TAG, "requestUrl:" + requestUrl + " param:" + param);
        }else{
            Log.i(TAG, "requestUrl:" + requestUrl + " param:" + param);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            setMethod(conn,requestType);
            conn.setRequestProperty("Authorization", NetVariables.token);
            Log.i(TAG,"NetVariables.token=" + NetVariables.token);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);

            if(!(requestType == RequestType.REQUEST_TYPE_GET ||
                    requestType == RequestType.REQUEST_TYPE_DELETE)){
                conn.setDoOutput(true);
            }

            if(jsonContentType){
                // 设置文件类型:
                conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            }
            conn.connect();

            if(!(requestType == RequestType.REQUEST_TYPE_GET ||
                    requestType == RequestType.REQUEST_TYPE_DELETE)){
                out = conn.getOutputStream();

                if (param != null && param.length() > 0) {
                    out.write(param.getBytes());
                }
            }

            int code = conn.getResponseCode();
            Log.i(TAG,"ResponseCode=" + code);

            if (code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
            }else {
                is = conn.getErrorStream();
            }

            byte[] tmp = new byte[BUF_SIZE];
            baos = new ByteArrayOutputStream();
            int len = is.read(tmp);
            while (len != -1) {
                baos.write(tmp, 0, len);
                len = is.read(tmp);
            }

            String responses = baos.toString("utf-8");
            if(DEBUG) {
                LogUtil.i(TAG, "responses " + responses);
            }else{
                Log.i(TAG, "responses " + responses);
            }
            JSONObject jsonObject;
            if(TextUtils.isEmpty(responses)){
                jsonObject = new JSONObject();
            }else {
                jsonObject = new JSONObject(responses);
            }

            jsonObject.put("responseCode",code);

            return jsonObject.toString();
        } catch (UnknownHostException e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("responseCode",200);
            jsonObject.put("error",800);
            jsonObject.put("msg","无法访问网络");
            return jsonObject.toString();
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setMethod(HttpURLConnection cn, RequestType requestType) throws IOException {

        switch (requestType) {
            case REQUEST_TYPE_GET:
                cn.setRequestMethod("GET");
                Log.i(TAG,"setMethod GET");
                break;
            case REQUEST_TYPE_POST:
                cn.setRequestMethod("POST");
                Log.i(TAG,"setMethod POST");
                break;
            case REQUEST_TYPE_PATCH:
                cn.setRequestMethod("PATCH");
                Log.i(TAG,"setMethod PATCH");
                break;
            case REQUEST_TYPE_HEAD:
                cn.setRequestMethod("HEAD");
                break;
            case REQUEST_TYPE_OPTIONS:
                cn.setRequestMethod("OPTIONS");
                break;
            case REQUEST_TYPE_PUT:
                cn.setRequestMethod("PUT");
                Log.i(TAG,"setMethod PUT");
                break;
            case REQUEST_TYPE_DELETE:
                Log.i(TAG,"setMethod DELETE");
                cn.setRequestMethod("DELETE");
                break;
            case REQUEST_TYPE_TRACE:
                cn.setRequestMethod("TRACE");
                break;
            default:
                Log.i(TAG,"setMethod other");
                // set a dummy POST verb
                cn.setRequestMethod("POST");
                cn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//                try {
//                    // Change protected field called "method" of public class HttpURLConnection
//                    setProtectedFieldValue(HttpURLConnection.class, "method", cn, requestType.toString());
//                } catch (Exception ex) {
//                    throw new IOException(ex);
//                }
                break;
        }
    }

    public static <T> void setProtectedFieldValue(Class<T> clazz, String fieldName, T object, Object newValue) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);

        field.setAccessible(true);
        field.set(object, newValue);
    }
}
