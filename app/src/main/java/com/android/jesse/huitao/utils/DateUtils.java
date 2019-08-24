package com.android.jesse.huitao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @author: zhangshihao
 * @date: 2019/8/24
 */
public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    /**
     * 获取格式化后的日期字符串
     * @param mills 日期毫秒数
     * @param formatRule 格式化规则
     * @return 格式化后的日期字符串
     */
    public static String getFormatedDate(long mills,String formatRule){
        String formatedDate = "";
        Date date = new Date(mills);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatRule);
        formatedDate = simpleDateFormat.format(date);
        return formatedDate;
    }

    public static String getCurrentFormatedDate(String formatRule){
        return getFormatedDate(System.currentTimeMillis(),formatRule);
    }

}
