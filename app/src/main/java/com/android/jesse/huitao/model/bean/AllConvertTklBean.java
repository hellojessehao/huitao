package com.android.jesse.huitao.model.bean;

/**
 * @Description: 订单侠-万能高佣转链接口数据bean
 * @author: zhangshihao
 * @date: 2019/11/15
 */
public class AllConvertTklBean {

    /**
     * code : 200
     * msg : 请求成功【success】
     * data : {"category_id":50002766,"coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=ERrdCjEC%2BZAGQASttHIRqYl7%2F8DSkiA1evgn4PHiXSqRFz8q1QnJ3h%2B1G42%2FkpiVjNL2c7ZP8LBmNwWApekjrb9fwBwwUiql9%2BAYBumJvWjt0stvlaC75nYefz8NXcoYTJnbK5InWzloKXCUZd8BcHFf5RMoZqfAYwOD23XOnRHymNXcL2pzBMxE1I69YrVlcv3%2BIxFWPRo%3D&traceId=0b0b45cd15555567673714179e&union_lens=lensId:0bb698e5_0bca_16a2e689719_3025&xId=sMmthYV8WSLEznR8sF6uU9Aji2Q5Y1retWvU2e9S3IR8rxvDYYQYQOyuGP8Q78TaDjdAaaRpRwQ0jl5ns25HyO","coupon_end_time":"2019-04-19","coupon_info":"满168元减20元","coupon_remain_count":891426,"coupon_start_time":"2019-04-16","coupon_total_count":1000000,"coupon_type":1,"item_id":540702875061,"item_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3Duas5Cwf5iJFw4vFB6t2Z2ueEDrYVVa64K7Vc7tFgwiHLWlSKdGSYDiRvIO7AjsmKlovu%2FCElQOv%2BqN5xItGbDIU8BKtK9THKAyttkDnyUMf1M2eenISXJGu6Riu1FeF%2BaKxloahyuS%2FNEPXytV9ALq8XLr9cF0l0AOJ7n9hIYuSUmgZvWjGCm1FzQeSXzIFOsQdmUFNDbWyF53HItdrIJKJn5AyUbPoV&union_lens=lensId:0bb698e5_0bca_16a2e689719_3025&xId=sMmthYV8WSLEznR8sF6uU9Aji2Q5Y1retWvU2e9S3IR8rxvDYYQYQOyuGP8Q78TaDjdAaaRpRwQ0jl5ns25HyO","max_commission_rate":"0.45","coupon":"20","itemInfo":{"title":"【三只松鼠_坚果大礼包2188g/12袋】每日坚果干果整箱礼盒送礼","pict_url":"主图","reserve_price":"360","zk_final_price":"188","qh_final_price":168,"qh_final_commission":0.756},"coupon_tpwd":"￥wmpGYZ4kvZP￥","item_tpwd":"￥m4ZnYZ4kxdp￥"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 50002766
         * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=ERrdCjEC%2BZAGQASttHIRqYl7%2F8DSkiA1evgn4PHiXSqRFz8q1QnJ3h%2B1G42%2FkpiVjNL2c7ZP8LBmNwWApekjrb9fwBwwUiql9%2BAYBumJvWjt0stvlaC75nYefz8NXcoYTJnbK5InWzloKXCUZd8BcHFf5RMoZqfAYwOD23XOnRHymNXcL2pzBMxE1I69YrVlcv3%2BIxFWPRo%3D&traceId=0b0b45cd15555567673714179e&union_lens=lensId:0bb698e5_0bca_16a2e689719_3025&xId=sMmthYV8WSLEznR8sF6uU9Aji2Q5Y1retWvU2e9S3IR8rxvDYYQYQOyuGP8Q78TaDjdAaaRpRwQ0jl5ns25HyO
         * coupon_end_time : 2019-04-19
         * coupon_info : 满168元减20元
         * coupon_remain_count : 891426
         * coupon_start_time : 2019-04-16
         * coupon_total_count : 1000000
         * coupon_type : 1
         * item_id : 540702875061
         * item_url : https://s.click.taobao.com/t?e=m%3D2%26s%3Duas5Cwf5iJFw4vFB6t2Z2ueEDrYVVa64K7Vc7tFgwiHLWlSKdGSYDiRvIO7AjsmKlovu%2FCElQOv%2BqN5xItGbDIU8BKtK9THKAyttkDnyUMf1M2eenISXJGu6Riu1FeF%2BaKxloahyuS%2FNEPXytV9ALq8XLr9cF0l0AOJ7n9hIYuSUmgZvWjGCm1FzQeSXzIFOsQdmUFNDbWyF53HItdrIJKJn5AyUbPoV&union_lens=lensId:0bb698e5_0bca_16a2e689719_3025&xId=sMmthYV8WSLEznR8sF6uU9Aji2Q5Y1retWvU2e9S3IR8rxvDYYQYQOyuGP8Q78TaDjdAaaRpRwQ0jl5ns25HyO
         * max_commission_rate : 0.45
         * coupon : 20
         * itemInfo : {"title":"【三只松鼠_坚果大礼包2188g/12袋】每日坚果干果整箱礼盒送礼","pict_url":"主图","reserve_price":"360","zk_final_price":"188","qh_final_price":168,"qh_final_commission":0.756}
         * coupon_tpwd : ￥wmpGYZ4kvZP￥
         * item_tpwd : ￥m4ZnYZ4kxdp￥
         */

        private int category_id;
        private String coupon_click_url;
        private String coupon_end_time;
        private String coupon_info;
        private int coupon_remain_count;
        private String coupon_start_time;
        private int coupon_total_count;
        private int coupon_type;
        private long item_id;
        private String item_url;
        private String max_commission_rate;
        private String coupon;
        private ItemInfoBean itemInfo;
        private String coupon_tpwd;
        private String item_tpwd;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCoupon_click_url() {
            return coupon_click_url;
        }

        public void setCoupon_click_url(String coupon_click_url) {
            this.coupon_click_url = coupon_click_url;
        }

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

        public int getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(int coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public int getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(int coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public int getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(int coupon_type) {
            this.coupon_type = coupon_type;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public String getMax_commission_rate() {
            return max_commission_rate;
        }

        public void setMax_commission_rate(String max_commission_rate) {
            this.max_commission_rate = max_commission_rate;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public ItemInfoBean getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(ItemInfoBean itemInfo) {
            this.itemInfo = itemInfo;
        }

        public String getCoupon_tpwd() {
            return coupon_tpwd;
        }

        public void setCoupon_tpwd(String coupon_tpwd) {
            this.coupon_tpwd = coupon_tpwd;
        }

        public String getItem_tpwd() {
            return item_tpwd;
        }

        public void setItem_tpwd(String item_tpwd) {
            this.item_tpwd = item_tpwd;
        }

        public static class ItemInfoBean {
            /**
             * title : 【三只松鼠_坚果大礼包2188g/12袋】每日坚果干果整箱礼盒送礼
             * pict_url : 主图
             * reserve_price : 360
             * zk_final_price : 188
             * qh_final_price : 168
             * qh_final_commission : 0.756
             */

            private String title;
            private String pict_url;
            private String reserve_price;
            private String zk_final_price;
            private float qh_final_price;
            private double qh_final_commission;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }

            public String getReserve_price() {
                return reserve_price;
            }

            public void setReserve_price(String reserve_price) {
                this.reserve_price = reserve_price;
            }

            public String getZk_final_price() {
                return zk_final_price;
            }

            public void setZk_final_price(String zk_final_price) {
                this.zk_final_price = zk_final_price;
            }

            public float getQh_final_price() {
                return qh_final_price;
            }

            public void setQh_final_price(int qh_final_price) {
                this.qh_final_price = qh_final_price;
            }

            public double getQh_final_commission() {
                return qh_final_commission;
            }

            public void setQh_final_commission(double qh_final_commission) {
                this.qh_final_commission = qh_final_commission;
            }
        }
    }
}
