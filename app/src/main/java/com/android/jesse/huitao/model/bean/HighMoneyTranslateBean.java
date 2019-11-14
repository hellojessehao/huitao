package com.android.jesse.huitao.model.bean;


/**
 * @Description: 喵有券平台高佣转链（淘口令版）返回数据bean
 * @author: zhangshihao
 * @date: 2019/11/14
 */
public class HighMoneyTranslateBean {


    /**
     * result : {"data":{"coupon_end_time":"2018-07-19","coupon_info":"满130元减25元","coupon_remain_count":"97000","coupon_start_time":"2018-07-15","coupon_type":"3","click_url":"","category_id":"16","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=8rvGkWBc8bMGQASttHIRqeISwO2LYyDDB3KnYDrZgFzoEnMr9v wai7tBIKWCr05a7E1JQXqpN8SMYg4guO wpQ5wfGz/u NFY/YQj1mGycGmY4hFfhtzGuFqp8TFaHM52ttWb//2DNQGD4FAxIvBw==","item_id":"568706761354","max_commission_rate":"9.50"}}
     * request_id : yezbtfb5pma
     * code : 200
     */

    private ResultBean result;
    private String request_id;
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * data : {"coupon_end_time":"2018-07-19","coupon_info":"满130元减25元","coupon_remain_count":"97000","coupon_start_time":"2018-07-15","coupon_type":"3","click_url":"","category_id":"16","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=8rvGkWBc8bMGQASttHIRqeISwO2LYyDDB3KnYDrZgFzoEnMr9v wai7tBIKWCr05a7E1JQXqpN8SMYg4guO wpQ5wfGz/u NFY/YQj1mGycGmY4hFfhtzGuFqp8TFaHM52ttWb//2DNQGD4FAxIvBw==","item_id":"568706761354","max_commission_rate":"9.50"}
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
             * coupon_end_time : 2018-07-19
             * coupon_info : 满130元减25元
             * coupon_remain_count : 97000
             * coupon_start_time : 2018-07-15
             * coupon_type : 3
             * click_url :
             * category_id : 16
             * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=8rvGkWBc8bMGQASttHIRqeISwO2LYyDDB3KnYDrZgFzoEnMr9v wai7tBIKWCr05a7E1JQXqpN8SMYg4guO wpQ5wfGz/u NFY/YQj1mGycGmY4hFfhtzGuFqp8TFaHM52ttWb//2DNQGD4FAxIvBw==
             * item_id : 568706761354
             * max_commission_rate : 9.50
             */

            private String coupon_end_time;
            private String coupon_info;
            private String coupon_remain_count;
            private String coupon_start_time;
            private String coupon_type;
            private String click_url;
            private String category_id;
            private String coupon_click_url;
            private String item_id;
            private String max_commission_rate;

            public String getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(String coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public String getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(String coupon_info) {
                this.coupon_info = coupon_info;
            }

            public String getCoupon_remain_count() {
                return coupon_remain_count;
            }

            public void setCoupon_remain_count(String coupon_remain_count) {
                this.coupon_remain_count = coupon_remain_count;
            }

            public String getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(String coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public String getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(String coupon_type) {
                this.coupon_type = coupon_type;
            }

            public String getClick_url() {
                return click_url;
            }

            public void setClick_url(String click_url) {
                this.click_url = click_url;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCoupon_click_url() {
                return coupon_click_url;
            }

            public void setCoupon_click_url(String coupon_click_url) {
                this.coupon_click_url = coupon_click_url;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getMax_commission_rate() {
                return max_commission_rate;
            }

            public void setMax_commission_rate(String max_commission_rate) {
                this.max_commission_rate = max_commission_rate;
            }
        }
    }
}
