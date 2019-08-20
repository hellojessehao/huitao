package com.android.jesse.huitao.utils;

import android.content.Context;

import net.grandcentrix.tray.AppPreferences;

import java.util.ArrayList;

public class SharedPreferencesUtil {
	/**
	 * 初始化一个叫vqs_shared_data的偏好设置
	 */
	public static void initSharedPreferences(Context context){
		appPreferences = new AppPreferences(context);
	}
	
	private static AppPreferences appPreferences;
	/**
	 * 保存单条数据
	 */
	public static void setStringDate(String key, String value) {
		appPreferences.put(key, value);
	}
	public static void setBooleanDate(String key, boolean value) {
		appPreferences.put(key, value);
	}
	public static void setIntDate(String key, int value) {
		appPreferences.put(key, value);
	}
	/**
	 * 批量保存数据
	 */
	public static void setAllDate(ArrayList<String> keys,
			ArrayList<String> values) {
		for (int i = 0; i < keys.size(); i++) {
			appPreferences.put(keys.get(i), values.get(i));
		}
	}
	/**
	 * 批量载入数据
	 */
	public static ArrayList<String> getAllDate(ArrayList<String> keys) {
		ArrayList<String> values = new ArrayList<String>();
		for (String key : keys) {
			String value = appPreferences.getString(key, "");
			values.add(value);
		}
		return values;
	}

	/**
	 * 获取字符串数据
	 */
	
	public static String getStringDate(String key) {
		return appPreferences.getString(key, "");
	}
	public static String getStringDate(String key,String defaul) {
		return appPreferences.getString(key, defaul);
	}
	public static boolean getBooleanDate(String key) {
		return  appPreferences.getBoolean(key, false);
	}
	public static boolean getBooleanDates(String key) {
		return  appPreferences.getBoolean(key,true);
	}
	public static int getIntDate(String key) {
		return appPreferences.getInt(key, 0);
	}
	
	public static void setLongDate(String key,long value) {
		appPreferences.put(key, value);
		
	}
	public static long getLongDate(String showRecommentInstallTime) {
		return appPreferences.getLong(showRecommentInstallTime, 0);
	}

	public static long getLongDate(String showRecommentInstallTime,int defaul) {
		return appPreferences.getLong(showRecommentInstallTime, defaul);
	}

	public static boolean getIsFirstDown(String key) {
		return  appPreferences.getBoolean(key,false);
	}
	public static void setIsFirstDown(String key, boolean value) {
		appPreferences.put(key, value);
	}
	public static boolean getIsFirstDowns(String key) {
		return  appPreferences.getBoolean(key,false);
	}
	public static void setIsFirstDowns(String key, boolean value) {
		appPreferences.put(key, value);
	}
}
