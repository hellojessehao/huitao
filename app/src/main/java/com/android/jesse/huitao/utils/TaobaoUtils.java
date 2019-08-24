package com.android.jesse.huitao.utils;

import com.android.jesse.huitao.model.Constant;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

/**
 * @Description: 淘宝接口调用工具类
 * @author: zhangshihao
 * @date: 2019/8/23
 */
public class TaobaoUtils {

    private static final String TAG = TaobaoUtils.class.getSimpleName();

    public static TaobaoClient getDefaultClient(){
        return new DefaultTaobaoClient(Constant.BASE_URL_TAOBAO,Constant.APPKEY_TAOBAO,Constant.APPSECRET_TAOBAO);
    }

}
