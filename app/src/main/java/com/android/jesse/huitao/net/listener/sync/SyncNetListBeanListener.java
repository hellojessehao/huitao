package com.android.jesse.huitao.net.listener.sync;

import com.android.jesse.huitao.net.bean.NetBaseBean;
import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.util.NetBaseBeanUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SyncNetListBeanListener
 * @Desciption: 返回是List<Bean>的网络请求Listener
 * <p>
 * 由于去参数化(擦拭法)，也只有在
 * 超类（调用 getGenericSuperclass 方法）
 * 或者成员变量（调用 getGenericType 方法）
 * 或者方法（调用 getGenericParameterTypes 方法）
 * 像这些有方法返回 ParameterizedType 类型的时候才能反射成功
 * <p>
 * 所以使用的时候，必须使用他的子类，而不能直接使用本类
 * <p>
 * @author: yichaohua
 * @date: 2017-12-16
 */
abstract public class SyncNetListBeanListener<T extends NetBaseBean> extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        JSONArray array = new JSONArray(netRetBean.getServerData());
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            T t = NetBaseBeanUtil.parseItem(getClass(), 0, object);
            list.add(t);
        }
        netRetBean.setServerObject(list);
        return handleResult(netRetBean);
    }
}
