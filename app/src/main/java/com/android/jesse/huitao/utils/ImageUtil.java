package com.android.jesse.huitao.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: 图片处理工具类
 * @author: zhangshihao
 * @date: 2019/10/14
 */
public class ImageUtil {

    private static final String TAG = ImageUtil.class.getSimpleName();

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url 网络图片链接
     * @return 网络图片的bitmap对象
     */
    public static Bitmap getUrlBitmap(Context context, String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (Exception e) {
            LogUtil.e(TAG + "  " + e.getMessage() + "  " + e.toString());
        }
        return bitmap;

    }

}
