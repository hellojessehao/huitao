package com.android.jesse.huitao.model.bean;

/**
 * @Description: 请求失败返回数据
 * @author: zhangshihao
 * @date: 2019/9/1
 */
public class FailBean {

    /**
     * error_response : {"code":15,"msg":"Remote service error","sub_code":"30001","sub_msg":"非法的物料id","request_id":"oj5nfpwgso4y"}
     * {"error_response":{"code":15,"msg":"Remote service error","sub_code":"20000","sub_msg":"口令跳转url不支持口令转换","request_id":"pqc4j80jcv3e"}}
     */

    private ErrorResponseBean error_response;

    public ErrorResponseBean getError_response() {
        return error_response;
    }

    public void setError_response(ErrorResponseBean error_response) {
        this.error_response = error_response;
    }

    public static class ErrorResponseBean {
        /**
         * code : 15
         * msg : Remote service error
         * sub_code : 30001
         * sub_msg : 非法的物料id
         * request_id : oj5nfpwgso4y
         */

        private int code;
        private String msg;
        private String sub_code;
        private String sub_msg;
        private String request_id;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSub_code() {
            return sub_code;
        }

        public void setSub_code(String sub_code) {
            this.sub_code = sub_code;
        }

        public String getSub_msg() {
            return sub_msg;
        }

        public void setSub_msg(String sub_msg) {
            this.sub_msg = sub_msg;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }
    }
}
