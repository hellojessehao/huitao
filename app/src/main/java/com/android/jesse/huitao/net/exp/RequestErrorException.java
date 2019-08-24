package com.android.jesse.huitao.net.exp;

/**
 * @ClassName: RequestErrorException
 * @Desciption: http请求时发生异常
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class RequestErrorException extends BaseException {

    public RequestErrorException(Exception e) {
        super(e);
    }

    public RequestErrorException() {
        super(ExceptionCode.EXPCODE_REQUEST_ERROR);
    }
}
