package com.android.jesse.huitao.net.token;

import com.android.jesse.huitao.net.data.NetVariables;

/**
 * @ClassName: TokenUtil
 * @Desciption: //TODO
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class TokenUtil {

    private static boolean isWaitToken = true;

    public static void setWaitToken(boolean isWaitToken) {
        TokenUtil.isWaitToken = isWaitToken;
    }

    /**
     * 此方法只能在非ui线程中调用，慎用
     */
    public static void waitToken() {
        while (isWaitToken) {
            if (NetVariables.token != null && !NetVariables.token.equals("")) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
