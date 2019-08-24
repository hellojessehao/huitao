package com.android.jesse.huitao.net.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: LogUtil
 * @Desciption: //TODO
 * @author: yichaohua
 * @date: 2018-03-22
 */
public class LogUtil {
    //可以全局控制是否打印log日志
    private static boolean isPrintLog = true;
    private static int LOG_MAXLENGTH = 2000;

    private static final String basePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "log" + File.separator;
    private static String fileName = null;
    private static boolean isAppend = true;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void v(String msg) {
        v("LogUtil", msg);
    }

    public static void v(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.v(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.v(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void d(String msg) {
        d("LogUtil", msg);
    }
    public static void d(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.d(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.d(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void i(String msg) {
        i("LogUtil", msg);
    }

    public static void i(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.i(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.i(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void w(String msg) {
        w("LogUtil", msg);
    }

    public static void w(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.w(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.w(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void e(String msg) {
        e("LogUtil", msg);
    }
    public static void e(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    /**
     * 获取有类名与方法名的logString
     *
     * @param rawMessage
     * @return
     */
    private static String createMessage(String rawMessage) {
        /**
         * Throwable().getStackTrace()获取的是程序运行的堆栈信息，也就是程序运行到此处的流程，以及所有方法的信息
         * 这里我们为什么取2呢？0是代表createMessage方法信息，1是代表上一层方法的信息，这里我们
         * 取它是上两层方法的信息
         */
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String fullClassName = stackTraceElement.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        return className + "." + stackTraceElement.getMethodName() + "(): " + rawMessage;
    }

    /**
     * 程序每次运行创建一个新的txt，以时间为文件名
     * @param type  log的类型
     * @param context   log的内容
     */
    public static void writeLog2SDCard(String type, String context) {
        if (fileName == null){
            fileName = sdf.format(new Date(System.currentTimeMillis())) + ".txt";
        }

        File file = new File(basePath + fileName);
        String dateStr = sdf.format(new Date(System.currentTimeMillis()));
        context = dateStr + "==" + type + "==" + context;

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend)));
            bufferedWriter.write(context);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            if (!isAppend) {
                isAppend = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
