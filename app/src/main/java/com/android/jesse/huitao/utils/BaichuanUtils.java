package com.android.jesse.huitao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.view.activity.HomePageActivity;
import com.android.jesse.huitao.view.activity.LoginActivity;
import com.blankj.utilcode.util.ActivityUtils;

import java.util.HashMap;
import java.util.Map;

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
                new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.what == 0){
                            ((Activity)context).finish();//关闭登录页
                        }
                    }
                }.sendEmptyMessageDelayed(0,2000);
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
                ActivityUtils.startActivity(LoginActivity.class);
                ActivityUtils.finishOtherActivities(LoginActivity.class);
                Toast.makeText(context, "已退出登录", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.shortShow("退出登录失败，请重试~");
            }
        });
    }

    /*
    <br>// 以code的方式打开电商页面（eg：详情--detail，店铺--shop）

// 页面实例
AlibcBasePage page = new AlibcDetailPage(itemId);
AlibcBasePage page = new AlibcShopPage(shopId);
//展示参数配置
AlibcShowParams showParams = new AlibcShowParams();
showParams.setOpenType(openType);
showParams.setClientType(clientType);
showParams.setBackUrl(BACK_URL);
showParams.setNativeOpenFailedMode(nativeOpenFailedMode);
// showParam各参数介绍
1、OpenType（页面打开方式）： 枚举值（Auto和Native），Native表示唤端，Auto表示不做设置
2、clientType表示唤端类型：taobao---唤起淘宝客户端；tmall---唤起天猫客户端
3、BACK_URL（小把手）：唤端返回的scheme
(如果不传默认将不展示小把手；如果想展示小把手，可以自己传入自定义的scheme，
或者传入百川提供的默认scheme："alisdk://")
4、AlibcFailModeType（唤端失败模式）： 枚举值如下
  AlibcNativeFailModeNONE：不做处理；
  AlibcNativeFailModeJumpBROWER：跳转浏览器；
  AlibcNativeFailModeJumpDOWNLOAD：跳转下载页；
  AlibcNativeFailModeJumpH5：应用内webview打开）
（注：AlibcNativeFailModeJumpBROWER不推荐使用）
5、degradeUrl（降级url）：可自行设置降级url，如果唤端失败且设置了降级url，则加载该url

// taokeParams（淘客）参数配置：配置aid或pid的方式分佣
参数说明：
pid
unionId
subPid
adzoneId
extraParams
（注：1、如果走adzoneId的方式分佣打点，需要在extraParams中显式传入taokeAppkey，否则打点失败；
     2、如果是打开店铺页面(shop)，需要在extraParams中显式传入sellerId，否则同步打点转链失败）
AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
taokeParams.setPid("mm_112883640_11584347_72287650277");
//taokeParams.setAdzoneid("29932014");
//adzoneid是需要taokeAppkey参数才可以转链成功&店铺页面需要卖家id（sellerId），具体设置方式如下：
taokeParams.extraParams.put("taokeAppkey", "xxxxx")
taokeParams.extraParams.put("sellerId", "xxxxx")

//自定义参数
Map<String, String> trackParams = new HashMap<>();


AlibcTrade.openByBizCode(FeatureActivity.this, page, null, new WebViewClient(),
               new WebChromeClient(), "detail", showParams, taokeParams,
                         trackParams, new AlibcTradeCallback() {
 @Override
 public void onTradeSuccess(AlibcTradeResult tradeResult) {
    // 交易成功回调（其他情形不回调）
    AlibcLogger.i(TAG, "open detail page success");
 }
 @Override
 public void onFailure(int code, String msg) {
    // 失败回调信息
    AlibcLogger.e(TAG, "code=" + code + ", msg=" + msg);
    if (code == -1) {
       Toast.makeText(FeatureActivity.this, "唤端失败，失败模式为none",
                      Toast.LENGTH_SHORT).show();
    }
 }
});
// 以显示传入url的方式打开页面（第二个参数是套件名称）
AlibcTrade.openByUrl(FeatureActivity.this, "", url, null,
                     new WebViewClient(), new WebChromeClient(), showParams,
                     taokeParams, trackParams, new AlibcTradeCallback() {
 @Override
 public void onTradeSuccess(AlibcTradeResult tradeResult) {
 AlibcLogger.i(TAG, "request success");
 }
 @Override
 public void onFailure(int code, String msg) {
    AlibcLogger.e(TAG, "code=" + code + ", msg=" + msg);
    if (code == -1) {
      Toast.makeText(FeatureActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
 }
});
     */

    /**
     * 跳转商品详情页
     */
    public static void gotoDetailPage(final Activity activity, String itemId){
        AlibcBasePage page = new AlibcDetailPage(itemId);
        //展示参数配置
        AlibcShowParams showParams = new AlibcShowParams();
        showParams.setOpenType(OpenType.Auto);
//        showParams.setClientType();
        showParams.setBackUrl("huitao://back");
//        showParams.setNativeOpenFailedMode("alisdk:\/\/");
// showParam各参数介绍
//        1、OpenType（页面打开方式）： 枚举值（Auto和Native），Native表示唤端，Auto表示不做设置
//        2、clientType表示唤端类型：taobao---唤起淘宝客户端；tmall---唤起天猫客户端
//        3、BACK_URL（小把手）：唤端返回的scheme
//                (如果不传默认将不展示小把手；如果想展示小把手，可以自己传入自定义的scheme，
//                        或者传入百川提供的默认scheme："alisdk://")
        AlibcTaokeParams taokeParams = new AlibcTaokeParams("","","");
        taokeParams.setAdzoneid(Constant.ADZONE_ID);
        Map<String,String> extraParams = new HashMap<>();
        extraParams.put("taokeAppkey", Constant.APPKEY_TAOBAO);
        taokeParams.extraParams = extraParams;
        //自定义参数
        Map<String, String> trackParams = new HashMap<>();
        trackParams.put("nickname",SharedPreferencesUtil.getStringDate(Constant.NICKNAME));

        AlibcTrade.openByBizCode(activity, page, null, new WebViewClient(),
                new WebChromeClient(), "detail", showParams, taokeParams,
                trackParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult tradeResult) {
                        // 交易成功回调（其他情形不回调）
                        ToastUtil.shortShow("跳转中……");
                        LogUtil.i(TAG+" open detail page success");
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        // 失败回调信息
                        LogUtil.e(TAG, "code=" + code + ", msg=" + msg);
                        ToastUtil.show("跳转失败，code="+code+",客官可以通过“问题反馈”反馈给我们噢~");
                    }
                });
    }

    /**
     * 跳转店铺页
     */
    public static void gotoShopPage(Activity activity,String shopId){
        AlibcBasePage page = new AlibcShopPage(shopId);
        //展示参数配置
        AlibcShowParams showParams = new AlibcShowParams();
        showParams.setOpenType(OpenType.Auto);
//        showParams.setClientType();
        showParams.setBackUrl("huitao://back");
//        showParams.setNativeOpenFailedMode("alisdk:\/\/");
// showParam各参数介绍
//        1、OpenType（页面打开方式）： 枚举值（Auto和Native），Native表示唤端，Auto表示不做设置
//        2、clientType表示唤端类型：taobao---唤起淘宝客户端；tmall---唤起天猫客户端
//        3、BACK_URL（小把手）：唤端返回的scheme
//                (如果不传默认将不展示小把手；如果想展示小把手，可以自己传入自定义的scheme，
//                        或者传入百川提供的默认scheme："alisdk://")
        AlibcTaokeParams taokeParams = new AlibcTaokeParams("","","");
        taokeParams.setAdzoneid(Constant.ADZONE_ID);
        Map<String,String> extraParams = new HashMap<>();
        extraParams.put("taokeAppkey", Constant.APPKEY_TAOBAO);
        extraParams.put("sellerId",shopId);
        taokeParams.extraParams = extraParams;
        //自定义参数
        Map<String, String> trackParams = new HashMap<>();
        trackParams.put("nickname",SharedPreferencesUtil.getStringDate(Constant.NICKNAME));

        AlibcTrade.openByBizCode(activity, page, null, new WebViewClient(),
                new WebChromeClient(), "shop", showParams, taokeParams,
                trackParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult tradeResult) {
                        // 交易成功回调（其他情形不回调）
                        ToastUtil.shortShow("跳转中……");
                        LogUtil.i(TAG+" open detail page success");
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        // 失败回调信息
                        LogUtil.e(TAG, "code=" + code + ", msg=" + msg);
                        ToastUtil.show("跳转失败，code="+code+",客官可以通过“问题反馈”反馈给我们噢~");
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
