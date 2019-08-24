package com.android.jesse.huitao.model.bean;

import com.android.jesse.huitao.net.bean.NetBaseBean;
import com.android.jesse.huitao.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Description:
 * @author: zhangshihao
 * @date: 2019/8/24
 */
public class NetTaobaoBaseBean extends NetBaseBean {

    private static final String TAG = NetTaobaoBaseBean.class.getSimpleName();

    protected String request_id;//平台颁发的每次请求访问的唯一标识
    protected String error_response;//请求访问失败时返回的根节点
    protected String code;//请求失败返回的错误码
    protected String msg;//请求失败返回的错误信息
    protected String sub_code;//请求失败返回的子错误码
    protected String sub_msg;//请求失败返回的子错误信息

    @Override
    public void initByJson(JSONObject jsonObject) {
        if(jsonObject == null){
            LogUtil.e(TAG+" jsonObject is null");
            return;
        }
        request_id = jsonObject.optString("request_id");
        error_response = jsonObject.optString("error_response");
        code = jsonObject.optString("code");
        msg = jsonObject.optString("msg");
        sub_code = jsonObject.optString("sub_code");
        sub_msg = jsonObject.optString("sub_msg");
    }
}
