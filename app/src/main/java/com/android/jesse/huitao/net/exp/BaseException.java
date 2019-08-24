package com.android.jesse.huitao.net.exp;

/**
 * @ClassName: BaseException
 * @Desciption: 自定义通用异常
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class BaseException extends Exception {

    private ExceptionCode mCode;

    public BaseException() {

    }

    public BaseException(Exception e) {
        super(e);
    }

    public BaseException(ExceptionCode code) {
        this.mCode = code;
    }

    public ExceptionCode getCode() {
        return mCode;
    }

    public void setCode(ExceptionCode code) {
        this.mCode = code;
    }
}
