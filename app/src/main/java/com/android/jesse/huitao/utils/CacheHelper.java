package com.android.jesse.huitao.utils;

import android.support.annotation.Nullable;

import com.android.jesse.huitao.model.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 缓存工具类
 * @author: zhangshihao
 * @date: 2019/9/3
 */
public class CacheHelper {

    private static final String TAG = CacheHelper.class.getSimpleName();

    public static List<String> getSearchHistory() {
        List<String> historyList = new ArrayList<>();
        String json = SharedPreferencesUtil.getStringDate(Constant.SPKEY_SEARCH_HISTORY, null);
        LogUtil.d(TAG+" getSearchHistory : json = "+json);
        try{
            new JSONArray(json);
        }catch (Exception e){
            LogUtil.e(TAG+" getSearchHistory : "+e.toString());
            e.printStackTrace();
            return historyList;
        }
        List<String> resultList = new Gson().fromJson(json,new TypeToken<List<String>>(){}.getType());
        if(Utils.isListEmpty(resultList)){
            return historyList;
        }
        return Utils.getEightItems(resultList);
    }

    public static void cacheSearchHistory(List<String> historyList) {
        SharedPreferencesUtil.setStringDate(Constant.SPKEY_SEARCH_HISTORY,new Gson().toJson(historyList));
    }

}
