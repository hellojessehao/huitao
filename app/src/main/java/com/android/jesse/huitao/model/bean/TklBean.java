package com.android.jesse.huitao.model.bean;

/**
 * @Description: 生成淘口令bean
 * @author: zhangshihao
 * @date: 2019/9/2
 */
public class TklBean extends BaseResponseBean {

    /**
     * request_id :
     * error_response :
     * code :
     * msg :
     * sub_code :
     * sub_msg :
     * tbk_tpwd_create_response : {"data":{"model":"￥AADPOKFz￥"}}
     */

    private String request_id;
    private String error_response;
    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;
    private TbkTpwdCreateResponseBean tbk_tpwd_create_response;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getError_response() {
        return error_response;
    }

    public void setError_response(String error_response) {
        this.error_response = error_response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public TbkTpwdCreateResponseBean getTbk_tpwd_create_response() {
        return tbk_tpwd_create_response;
    }

    public void setTbk_tpwd_create_response(TbkTpwdCreateResponseBean tbk_tpwd_create_response) {
        this.tbk_tpwd_create_response = tbk_tpwd_create_response;
    }

    public static class TbkTpwdCreateResponseBean {
        /**
         * data : {"model":"￥AADPOKFz￥"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * model : ￥AADPOKFz￥
             */

            private String model;

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }
        }
    }
}
