package com.android.jesse.huitao.view.activity.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.jesse.huitao.utils.CrashHandler;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.WxApiUtils;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import java.util.Map;

/**
 * @Description: Application类
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    public static Context mContext;
    public static MainApplication app;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    public static synchronized MainApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        app = this;
        //初始化sp
        SharedPreferencesUtil.initSharedPreferences(mContext);
        //初始化崩溃监听器，崩溃日志将存于 mnt/sdcard/tbcrash 目录下
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        //注册蒲公英日志上传
        PgyCrashManager.register(); //推荐使用
        //注册微信开放平台
        WxApiUtils.regToWx(this);
        //友盟初始化
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(this, "5db25ceb3fc1951b4e0006b0", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "6c7542b5e95e5daabe16aa0584b34f0e");
        //注册友盟推送服务
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LogUtil.i(TAG+" 注册成功：deviceToken：-------->  " + deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                LogUtil.e(TAG,"注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
        //注册用户点击监听器
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage uMessage) {//点击消息调用
                super.launchApp(context, uMessage);
                Log.d(TAG, "launchApp : extra = " + uMessage.extra);
                dealExtra(uMessage.extra);
            }

        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        UmengMessageHandler umengMessageHandler = new UmengMessageHandler(){

            @Override
            public void dealWithNotificationMessage(Context context, UMessage uMessage) {//收到消息就会调用
                super.dealWithNotificationMessage(context, uMessage);
                Log.d(TAG,"dealWithNotificationMessage : extra = "+uMessage.extra);
                dealCustomMessage(uMessage.extra);
            }

        };
        mPushAgent.setMessageHandler(umengMessageHandler);
    }

    private void dealCustomMessage(Map<String,String> extra){
        String cate = "";
        String mid = "";
        String oid = "";
        String cateKey = "cate";
        String midKey = "mid";
        String oidKey = "oid";
        if(extra.containsKey(cateKey)){
            cate = extra.get(cateKey);
        }
        if(extra.containsKey(midKey)){
            mid = extra.get(midKey);
        }
        if(extra.containsKey(oidKey)){
            oid = extra.get(oidKey);
        }
        Log.d(TAG,"dealCustomMessage : cate = "+cate+", mid = "+mid+", oid = "+oid);
        Bundle midBundle = new Bundle();
        midBundle.putString("mid",mid);
        midBundle.putString("oid",oid);
//        if(!TextUtils.isEmpty(cate)){
//            switch (cate){
//                case PushConstants.CATE_CHECK_FEED_BACK_MSG://检查结果反馈
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(1, midBundle));
//                    break;
//            }
//        }
    }

    private void dealExtra(Map<String, String> extra) {
        String cate = "";
        String mid = "";
        String oid = "";
        String cateKey = "cate";
        String midKey = "mid";
        String oidKey = "oid";
        if (extra.containsKey(cateKey)) {
            cate = extra.get(cateKey);
        }
        if (extra.containsKey(midKey)) {
            mid = extra.get(midKey);
        }
        if (extra.containsKey(oidKey)) {
            oid = extra.get(oidKey);
        }
        Log.d(TAG, "cate = " + cate + ", mid = " + mid + ", oid = " + oid);
        Bundle midBundle = new Bundle();
        midBundle.putString("mid", mid);
        midBundle.putString("oid", oid);
//        if (!TextUtils.isEmpty(cate)) {
//            switch (cate) {
//                case PushConstants.CATE_SYS_MSG://系统消息推送
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(0, midBundle));
//                    Log.d(TAG, "send sys msg");
//                    break;
//                case PushConstants.CATE_CHECK_FEED_BACK_MSG://检查结果反馈
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(1, midBundle));
//                    break;
//                case PushConstants.CATE_CHECK_ACCEPT_MSG://作业检查接单
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(2, midBundle));
//                    break;
//                case PushConstants.CATE_GUIDE_NOW_ACCEPT://实时作业辅导接单
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(3, midBundle));
//                    break;
//                case PushConstants.CATE_PRE_GUIDE_ACCEPT://预约作业辅导接单
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(4, midBundle));
//                    break;
//                case PushConstants.CATE_CANCEL_INDENT://取消订单
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(5, midBundle));
//                    Log.d(TAG, "send cancel msg");
//                    break;
//                case PushConstants.CATE_GUIDE_CANCEL://预约作业辅导已接单取消作业
//                    dealPushHandler.sendMessage(dealPushHandler.obtainMessage(6, midBundle));
//                    Log.d(TAG, "send job cancel msg");
//                    break;
//            }
//        }
    }

    @SuppressLint("HandlerLeak")
    private Handler dealPushHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handler msg : what = " + msg.what);
//            switch (msg.what) {
//                case 0://系统消息
//                    Log.d(TAG, "jump to sys msg detail");
//                    Intent sysIntent = new Intent(mContext, SystemMsgDetailActivity.class);
//                    sysIntent.putExtra("url", ((Bundle) msg.obj).getString("oid"));
//                    sysIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(sysIntent);
//                    break;
//                case 1://检查结果-跳作业检查结果页
//                    Log.d(TAG, "jump to check result page");
//                    Intent checkResultIntent = new Intent(mContext, JobCheckResultActivity.class);
//                    checkResultIntent.putExtra("oid", ((Bundle) msg.obj).getString("oid"));
//                    checkResultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(checkResultIntent);
//                    break;
//                case 2://作业检查接单
//                    Log.d(TAG, "jump to indent detail");
//                    Intent checkAcceptIntent = new Intent(mContext, TutorshipDetailsActivity.class);
//                    checkAcceptIntent.putExtra("oid", ((Bundle) msg.obj).getString("oid"));
//                    checkAcceptIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(checkAcceptIntent);
//                    break;
//                case 3://实时作业辅导接单
//                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.ACTION_REFRESH_HOMEPAGE));
//                    break;
//                case 4://预约作业辅导接单
//                    Log.d(TAG, "jump to indent detail by yuyue accept");
//                    Intent yuyueAcceptIntent = new Intent(mContext, TutorshipDetailsActivity.class);
//                    yuyueAcceptIntent.putExtra("oid", ((Bundle) msg.obj).getString("oid"));
//                    yuyueAcceptIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(yuyueAcceptIntent);
//                    break;
//                case 5://取消订单
//                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.ACTION_REFRESH_ORDERS));
//                    break;
//                case 6://取消作业
//                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.ACTION_REFRESH_HOMEPAGE));
//                    break;
//            }
        }
    };

}
