package com.android.jesse.huitao.net.exp;

/**
 * @ClassName: RespondErrorException
 * @Desciption: http请求返回的StatusCode不为200
 * @author: yichaohua
 * @date: 2017-12-16
 */
public class RespondErrorException extends BaseException {

    private int mRespondCode;

    public RespondErrorException(int respondCode) {
        super(ExceptionCode.EXPCODE_RESPOND_ERROR);
        this.mRespondCode = respondCode;
    }

    public RespondErrorException() {
        super(ExceptionCode.EXPCODE_RESPOND_ERROR);
    }

    public int getRespondCode() {
        return mRespondCode;
    }

    public void setRespondCode(int respondCode) {
        this.mRespondCode = respondCode;
    }
}
