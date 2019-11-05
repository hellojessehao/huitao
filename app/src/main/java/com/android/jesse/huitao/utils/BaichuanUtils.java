package com.android.jesse.huitao.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.view.activity.HomePageActivity;
import com.android.jesse.huitao.view.activity.LoginActivity;
import com.blankj.utilcode.util.ActivityUtils;

/**
 * @Description: 阿里百川SDK4.0.0.2工具类
 * @author: zhangshihao
 * @date: 2019/10/27 0027
 */
public class BaichuanUtils {

    private static final String TAG = BaichuanUtils.class.getSimpleName();

    /**
     * 使用淘宝账号登录
     */
    public static void login(final Context context) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.showLogin(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {
                // 参数说明：
                // loginResult(0--登录初始化成功；1--登录初始化完成；2--登录成功)
                // openId：用户id
                // userNick: 用户昵称
                LogUtil.i(TAG+" 获取淘宝用户信息: " + AlibcLogin.getInstance().getSession());
                //填充用户信息@{
                Session session = AlibcLogin.getInstance().getSession();
                SharedPreferencesUtil.setStringDate(Constant.AVATAR_URL,session.avatarUrl);
                SharedPreferencesUtil.setStringDate(Constant.OPEN_ID_TAOBAO,openId);
                SharedPreferencesUtil.setStringDate(Constant.NICKNAME,userNick);
                //@}
                SharedPreferencesUtil.setBooleanDate(Constant.IS_LOGIN,true);
                context.startActivity(new Intent(context,HomePageActivity.class));
            }

            @Override
            public void onFailure(int code, String msg) {
                // code：错误码  msg： 错误信息
                LogUtil.e(TAG+" 淘宝登录失败 ： code = "+code+",msg = "+msg);
            }
        });
    }

    /**\
     * 登出
     */
    public static void logout(final Context context) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.logout(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {
                // 参数说明：
                // loginResult(3--登出成功)
                // openId：用户id
                // userNick: 用户昵称
                SharedPreferencesUtil.setBooleanDate(Constant.IS_LOGIN,false);
                ActivityUtils.finishOtherActivities(LoginActivity.class);
                ActivityUtils.startActivity(LoginActivity.class);
            }

            @Override
            public void onFailure(int code, String msg) {
                // code：错误码  msg： 错误信息
            }
        });
    }

    public interface OnLoginOrOutListener{
        void onLogin(Session session);
        void onLogout(int loginResult);
    }

    private OnLoginOrOutListener onLoginOrOutListener;

    public OnLoginOrOutListener getOnLoginOrOutListener() {
        return onLoginOrOutListener;
    }

    public void setOnLoginOrOutListener(OnLoginOrOutListener onLoginOrOutListener) {
        this.onLoginOrOutListener = onLoginOrOutListener;
    }
}
