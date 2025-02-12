package com.android.jesse.huitao.net.util;

import com.android.jesse.huitao.net.bean.NetCommonBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * @ClassName: NetCommonBeanUtil
 * @Desciption: NetCommonBean工具类，实现对泛型进行创建实例
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class NetCommonBeanUtil {

    /**
     * @param aClass     带泛型 T 的类，应该传入xxBeanListener监听器或其的子类。他的父类必须是带泛型T的
     * @param tIndex     泛型 T 所在下标
     * @param jsonObject 用来解析的 json
     * @param <T>        泛型 T
     * @return 返回泛型T的实例
     * @throws JSONException e
     */
    public static <T extends NetCommonBean> T parseItem(Class aClass, int tIndex, JSONObject jsonObject) throws JSONException {
        T t = getBean(aClass, tIndex);
        t = t.getBeanByJson(jsonObject);
        return t;
    }

    /**
     * @param aClass 带泛型 T 的类，应该传入xxBeanListener监听器或其的子类。他的父类必须是带泛型T的
     * @param tIndex 泛型 T 所在下标
     * @param <T>    泛型 T
     * @return 返回泛型T的实例
     */
    @SuppressWarnings("unchecked")
    private static <T extends NetCommonBean> T getBean(Class aClass, int tIndex) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) aClass.getGenericSuperclass()).getActualTypeArguments()[tIndex];
        T entity = null;
        try {
            // 使用newInstance创建实例的类，必须有无参构造方法
            entity = entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
