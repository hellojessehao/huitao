package com.android.jesse.huitao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

/**
 * @Description: 应用在线升级工具类
 * @author: zhangshihao
 * @date: 2019/9/25 0025
 */
public class UpgradeUtils {

    private static final String TAG = UpgradeUtils.class.getSimpleName();

    /**
     * 检查是否有更新版本
     */
    public static void checkUpgrade(final Context mContext){
        /** 新版本 **/
        new PgyUpdateManager.Builder()
                .setForced(false)                //设置是否强制提示更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(true)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk， 默认为true
                .setUpdateManagerListener(new UpdateManagerListener() {
                    
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        LogUtil.d("pgyer"+" there is no new version");
                    }
                    @Override
                    public void onUpdateAvailable(final AppBean appBean) {
                        //有更新回调此方法
                        LogUtil.d("pgyer"+" there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener

                        new AlertDialog.Builder(mContext)
                                .setTitle("发现新版本:" + appBean.getVersionName())
                                .setMessage(appBean.getReleaseNote())
                                .setPositiveButton(
                                        "在线升级",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                                            }
                                        })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
                        LogUtil.e("pgyer"+" check update failed " + e.toString());
                    }
                })
                //注意 ：
                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
                //想要使用蒲公英的默认下载进度的UI则不设置此方法
//                .setDownloadFileListener(new DownloadFileListener() {
//                    @Override
//                    public void downloadFailed() {
//                        //下载失败
//                        LogUtil.e("pgyer", "download apk failed");
//                    }
//
//                    @Override
//                    public void downloadSuccessful(Uri uri) {
//                        LogUtil.e("pgyer", "download apk failed");
//                        // 使用蒲公英提供的安装方法提示用户 安装apk
//                        PgyUpdateManager.installApk(uri);
//                    }
//
//                    @Override
//                    public void onProgressUpdate(Integer... integers) {
//                        LogUtil.e("pgyer", "update download apk progress" + integers);
//                    }})
                .register();
    }
}
