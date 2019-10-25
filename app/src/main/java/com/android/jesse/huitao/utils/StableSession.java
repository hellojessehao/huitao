package com.android.jesse.huitao.utils;

import java.util.HashMap;

/**
 * @Description: 免序列化对象传递工具类(使用强引用不会被回收)
 * @author: zhangshihao
 * @date: 2019/5/18
 */
public class StableSession {

    private static StableSession instance;

    //private HashMap<String,Object> map = new HashMap<String,Object>();
    //为了内存回收没有引用的对象，启用弱引用weakHashMap
    private HashMap<String,Object> map = new HashMap<String,Object>();

    public static StableSession getSession(){

        if (instance == null) {
            synchronized (StableSession.class) {
                if (instance == null) {
                    instance = new StableSession();
                }
            }
        }
        return instance;
    }

    public void put(String key, Object obj){
        map.put(key, obj);
    }


    public Object get(String key){

        if(map.containsKey(key)){

            return map.remove(key);
        }

        return null;
    }

    //获取数据但不删除
    public Object request(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    public void replace(String key, Object obj){
        if(map.containsKey(key)) {
            map.remove(key);
        }
        map.put(key, obj);
    }

    public void reSetSession(){
        map.clear();
    }


}
