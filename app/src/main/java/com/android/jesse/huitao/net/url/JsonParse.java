package com.android.jesse.huitao.net.url;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * @ClassName: JsonParse
 * @Desciption: Json工具类，对post请求参数的构造提供基本方法单元
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class JsonParse {

    private JSONObject mJsonObject;
    private boolean mUseCommonJson = true;

    public JsonParse() {
        this.mJsonObject = new JSONObject();
    }

    public JsonParse(JSONObject jsonObject) {
        this.mJsonObject = jsonObject;
    }

    public boolean isUseCommonJson() {
        return mUseCommonJson;
    }

    public void setUseCommonJson(boolean useCommonJson) {
        this.mUseCommonJson = useCommonJson;
    }

    public JsonParse put(String key, String value) {
        try {
            mJsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String toString() {
        if (mUseCommonJson) {
            addCommonJson();
        }
        return mJsonObject.toString();
    }

    private void addCommonJson() {
        JSONObject jsonObject = JsonManager.getInstance().getCommonJson();
        if (jsonObject != null) {
            Iterator<?> it = jsonObject.keys();
            for (; it.hasNext(); ) {
                try {
                    String key = it.next().toString();
                    String value = jsonObject.getString(key);
                    mJsonObject.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
