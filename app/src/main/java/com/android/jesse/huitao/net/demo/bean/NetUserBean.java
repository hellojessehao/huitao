package com.android.jesse.huitao.net.demo.bean;

import com.android.jesse.huitao.net.bean.NetBaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: NetUserBean
 * @Desciption: 例子：自定义Bean类型
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class NetUserBean extends NetBaseBean {
    private String id;
    private String name;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.optString("id");
        this.name = jsonObject.optString("name");

        /**
         * 本类使用fastjson和gson会出现下面的情况，因为他们没有提供在当前类中去解析当前类
         * 即 fromJson(jsonString, getClass(), this) 这样的方法
         * 而是解析后返回一个新的对象，所以就会出现下面这样尴尬的情况
         */

//        NetUserBean netUserBean = new Gson().fromJson(jsonObject.toString(), NetUserBean.class);
//        this.id = netUserBean.getId();
//        this.name = netUserBean.getName();

//        NetUserBean netUserBean = JSON.parseObject(jsonObject.toString(), NetUserBean.class);
//        this.id = netUserBean.getId();
//        this.name = netUserBean.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NetUserBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
