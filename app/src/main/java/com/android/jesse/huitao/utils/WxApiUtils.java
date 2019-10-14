package com.android.jesse.huitao.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.view.activity.base.MainApplication;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.net.URL;

/**
 * @ClassName: WxApiUtils
 * @Desciption: 微信api工具类
 * @author: yichaohua
 * @date: 2018-04-17
 */
public class WxApiUtils {

    public static final int SHARE_TYPE_SESSION = 0;//分享到聊天界面
    public static final int SHARE_TYPE_TIME_LINE = 1;//分享到朋友圈

    private static IWXAPI api;

    public static void regToWx(Context context) {
        if(api == null){
            api = WXAPIFactory.createWXAPI(context, Constant.WECHAT_APP_ID, true);
        }
        api.registerApp(Constant.WECHAT_APP_ID);
    }

    /**
     * 跳转到微信首页
     */
    public static void gotoWeChatMp(Context context){
        /*
        JumpToBizProfile.Req req = new JumpToBizProfile.Req();
        req.toUserName = "gh_6a3c0217b291";//公众号原始ID
        req.profileType = JumpToBizProfile.JUMP_TO_NORMAL_BIZ_PROFILE;
        req.extMsg = "";//若为服务号或订阅号则本字段为空，硬件号则填写相关的硬件二维码串
        return ShuShanApplication.getInstance().getWxApi().sendReq(req);*/
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtil.show("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }

    /**
     * 分享本地图片资源到微信
     */
    public static boolean sharePic(Context context, int shareType, int resourceId) {
        if(!ApkInfoUtils.hasInstalledApp(context,"com.tencent.mm")){
            ToastUtil.shortShow("请客官先安装微信喔~");
            return false;
        }
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resourceId);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage mediaMsg = new WXMediaMessage();
        mediaMsg.mediaObject = imgObj;
        //设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
        bmp.recycle();
        mediaMsg.thumbData = ImageUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img" + String.valueOf(System.currentTimeMillis());
        req.message = mediaMsg;

        switch (shareType) {
            case SHARE_TYPE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case SHARE_TYPE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        return api.sendReq(req);
    }

    /**
     * 分享bitmap到微信
     */
    public static boolean sharePic(Context context, int shareType, Bitmap bmp) {
        if(!ApkInfoUtils.hasInstalledApp(context,"com.tencent.mm")){
            ToastUtil.shortShow("请客官先安装微信喔~");
            return false;
        }
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage mediaMsg = new WXMediaMessage();
        mediaMsg.mediaObject = imgObj;
        //设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
        //bmp.recycle();
        mediaMsg.thumbData = ImageUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img" + String.valueOf(System.currentTimeMillis());
        req.message = mediaMsg;

        switch (shareType) {
            case SHARE_TYPE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case SHARE_TYPE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        return api.sendReq(req);
    }

    /**
     * 分享网络图片到微信
     */
    public static void sharePic(final Context context,final int shareType ,final String url){
        if(!ApkInfoUtils.hasInstalledApp(context,"com.tencent.mm")){
            ToastUtil.shortShow("请客官先安装微信喔~");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = ImageUtil.getUrlBitmap(context,url);
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage mediaMsg = new WXMediaMessage();
                mediaMsg.mediaObject = imgObj;
                //设置缩略图
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp,50,50,true);
                bmp.recycle();
                mediaMsg.thumbData = ImageUtils.bitmap2Bytes(thumbBmp,Bitmap.CompressFormat.PNG);

                //构造一个req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "img"+String.valueOf(System.currentTimeMillis());
                req.message = mediaMsg;
                req.scene = shareType;
                api.sendReq(req);
            }
        }).start();
    }

}
