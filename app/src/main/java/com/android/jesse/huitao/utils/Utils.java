package com.android.jesse.huitao.utils;

import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.GoodsListBean;
import com.android.jesse.huitao.net.bean.NetRetBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.taobao.api.Constants;
import com.taobao.api.internal.util.StringUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static com.blankj.utilcode.util.ObjectUtils.isNotEmpty;
import static com.taobao.api.Constants.SIGN_METHOD_HMAC;
import static com.taobao.api.Constants.SIGN_METHOD_MD5;

/**
 * @Description: 公用工具类
 * @author: zhangshihao
 * @date: 2019/8/24
 */
public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    private static String CHARSET_UTF8 = "utf-8";

    public static Map<String, String> generatePublicParamsForTaobao(String method) {
        Map<String, String> params = new HashMap<>();
        params.put("app_key", Constant.APPKEY_TAOBAO);
        params.put("format", "json");
        params.put("method", method);
        params.put("sign_method", Constant.MD5);
        params.put("timestamp", DateUtils.getCurrentFormatedDate("yyyy-MM-dd HH:mm:ss"));
        params.put("v", "2.0");
        return params;
    }

    public static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }
        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            LogUtil.d(TAG + " query = " + query.toString());
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
        LogUtil.d(TAG + " sign result = " + byte2hex(bytes) + " ,md5Utils : " + MD5(query.toString()));
//        return byte2hex(bytes);
        return MD5(query.toString());
    }

    public final static String MD5(String pwd) {
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = pwd.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public static byte[] encryptMD5(String data) throws IOException {
        return data.getBytes(Constants.CHARSET_UTF8);
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static void showNetErrorMsg(NetRetBean netRetBean) {
        ToastUtil.shortShow(netRetBean.getSub_code() + " : " + netRetBean.getSub_msg());
    }

    public static String getTaobaoUrl(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constant.BASE_URL_TAOBAO)
                .append("?");
        for (String key : map.keySet()) {
            stringBuilder.append(key)
                    .append("=")
                    .append(map.get(key))
                    .append("&");
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return "";
    }

    /**
     * 刷新SmartRefreshLayout状态
     *
     * @param refreshLayout
     * @param hasMore       是否还有更多
     */
    public static void resetRefreshViewState(SmartRefreshLayout refreshLayout, boolean hasMore) {
        if (refreshLayout == null) {
            return;
        }
        if (refreshLayout.getState() == RefreshState.Refreshing) {
            refreshLayout.finishRefresh();
        } else if (refreshLayout.getState() == RefreshState.Loading) {
            if (hasMore) {
                refreshLayout.finishLoadMore();
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    //判断列表是否为空
    public static boolean isListEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }

    //获取banner标题
    public static String getBannerTitle(String title, int needMoney, int subMoney) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title.substring(0, 10));
        stringBuilder.append("(满")
                .append(needMoney)
                .append("减")
                .append(subMoney)
                .append(")");
        return stringBuilder.toString();
    }

    //获取banner标题
    public static String getBannerTitle(GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean dataBean) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dataBean.getTitle().substring(0, 12));
        stringBuilder.append("(满");
        String startPrice = dataBean.getCoupon_start_fee();
        stringBuilder.append(startPrice.substring(0,startPrice.indexOf(".")));
        stringBuilder.append("减")
                .append(dataBean.getCoupon_amount())
                .append(")");
        return stringBuilder.toString();
    }

    /**
     * 拿出列表的前8个item放在另外一个列表里
     */
    public static List<String> getEightItems(List<String> oriList){
        List<String> resultList = new ArrayList<>();
        if(oriList.size() > 8){
            for(int i=0;i<8;i++){
                resultList.add(oriList.get(i));
            }
        }else{
            return oriList;
        }
        return resultList;
    }

    /**
     * 检查要加入列表的Item是否已经存在，如果存在则
     * 将列表中当前已经存在的Item放到第一位
     */
    public static List<String> checkIsItemExist(List<String> oriList,String content){
        int existPosition = -1;
        for(int i=0;i<oriList.size();i++){
            if(oriList.get(i).equals(content)){
                existPosition = i;
            }
        }
        if(existPosition > 0){
            oriList.remove(existPosition);
            oriList.add(0,content);
            return oriList;
        }else{
            return null;//代表不存在一样的内容
        }
    }

    public enum Conditions{
        HOT,
        SELL_UP,//销量升序 （从小到大）
        SELL_DOWN,//销量升序 （从大到小）
        PRICE_UP,//价格升序 （从小到大）
        PRICE_DOWN//价格降序 （从大到小）
    }

    /**
     * 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
     */
    public static String getSort(Conditions condition){
        String sort = "";
        switch (condition){
            case SELL_UP:
                sort = "total_sales_asc";
                break;
            case SELL_DOWN:
                sort = "total_sales_des";
                break;
            case PRICE_UP:
                sort = "price_asc";
                break;
            case PRICE_DOWN:
                sort = "price_des";
                break;
        }
        return sort;
    }

}
