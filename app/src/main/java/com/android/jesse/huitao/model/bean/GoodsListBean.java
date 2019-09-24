package com.android.jesse.huitao.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 商品列表bean
 * @author: zhangshihao
 * @date: 2019/9/1
 */
public class GoodsListBean extends BaseResponseBean{


    /**
     * request_id :
     * error_response :
     * code :
     * msg :
     * sub_code :
     * sub_msg :
     * tbk_dg_optimus_material_response : {"result_list":{"map_data":[{"coupon_amount":40,"small_images":{"string":[""]},"shop_title":"魔黛娅内衣旗舰店","category_id":162201,"coupon_start_fee":"69","item_id":556633720749,"coupon_total_count":30000,"user_type":1,"zk_final_price":"79.9","coupon_remain_count":1936,"commission_rate":"5.50","coupon_start_time":"2017-10-29","title":"毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款","item_description":"","seller_id":745957850,"volume":3837,"coupon_end_time":"2017-11-02","coupon_click_url":"//uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956etpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8","pict_url":"//img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","click_url":"//item.taobao.com/item.htm?id=556633720749&scm=1007.16190.92234.0&pvid=41362290-fa0b-4252-b172-6afc9c00e2c8&app_pvid=0ab0fac715095507006577956e","stock":1,"sell_num":1,"total_stock":1,"oetime":"","ostime":"","jdd_num":1,"jdd_price":"","orig_price":"","level_one_category_name":"","level_one_category_id":1,"category_name":"","white_image":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","short_title":"sss","word_list":{"word_map_data":[{"url":"https://uland.taobao.com/semm/tbxxxx","word":"孕妇装"}]},"tmall_play_activity_info":"前n件x折","uv_sum_pre_sale":21,"x_id":"uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc","new_user_price":"1.99","coupon_info":"满299减30元","coupon_share_url":"//uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956e;tpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8","nick":"秉迪数码专营店","reserve_price":"2.99","ju_online_end_time":"1559750399000","ju_online_start_time":"1559232000000","maochao_play_end_time":"1559750399000","maochao_play_start_time":"1559232000000","maochao_play_conditions":"2,3","maochao_play_discounts":"500,450","maochao_play_discount_type":"2","maochao_play_free_post_fee":"1","multi_coupon_zk_rate":"0.41234","price_after_multi_coupon":"9.9","multi_coupon_item_count":"3"}]},"is_default":"false"}
     */

    private String request_id;
    private String error_response;
    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;
    private TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response;

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

    public TbkDgOptimusMaterialResponseBean getTbk_dg_optimus_material_response() {
        return tbk_dg_optimus_material_response;
    }

    public void setTbk_dg_optimus_material_response(TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response) {
        this.tbk_dg_optimus_material_response = tbk_dg_optimus_material_response;
    }

    public static class TbkDgOptimusMaterialResponseBean {
        /**
         * result_list : {"map_data":[{"coupon_amount":40,"small_images":{"string":[""]},"shop_title":"魔黛娅内衣旗舰店","category_id":162201,"coupon_start_fee":"69","item_id":556633720749,"coupon_total_count":30000,"user_type":1,"zk_final_price":"79.9","coupon_remain_count":1936,"commission_rate":"5.50","coupon_start_time":"2017-10-29","title":"毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款","item_description":"","seller_id":745957850,"volume":3837,"coupon_end_time":"2017-11-02","coupon_click_url":"//uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956etpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8","pict_url":"//img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","click_url":"//item.taobao.com/item.htm?id=556633720749&scm=1007.16190.92234.0&pvid=41362290-fa0b-4252-b172-6afc9c00e2c8&app_pvid=0ab0fac715095507006577956e","stock":1,"sell_num":1,"total_stock":1,"oetime":"","ostime":"","jdd_num":1,"jdd_price":"","orig_price":"","level_one_category_name":"","level_one_category_id":1,"category_name":"","white_image":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","short_title":"sss","word_list":{"word_map_data":[{"url":"https://uland.taobao.com/semm/tbxxxx","word":"孕妇装"}]},"tmall_play_activity_info":"前n件x折","uv_sum_pre_sale":21,"x_id":"uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc","new_user_price":"1.99","coupon_info":"满299减30元","coupon_share_url":"//uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956e;tpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8","nick":"秉迪数码专营店","reserve_price":"2.99","ju_online_end_time":"1559750399000","ju_online_start_time":"1559232000000","maochao_play_end_time":"1559750399000","maochao_play_start_time":"1559232000000","maochao_play_conditions":"2,3","maochao_play_discounts":"500,450","maochao_play_discount_type":"2","maochao_play_free_post_fee":"1","multi_coupon_zk_rate":"0.41234","price_after_multi_coupon":"9.9","multi_coupon_item_count":"3"}]}
         * is_default : false
         */

        private ResultListBean result_list;
        private String is_default;

        public ResultListBean getResult_list() {
            return result_list;
        }

        public void setResult_list(ResultListBean result_list) {
            this.result_list = result_list;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public static class ResultListBean {
            private List<MapDataBean> map_data;

            public List<MapDataBean> getMap_data() {
                return map_data;
            }

            public void setMap_data(List<MapDataBean> map_data) {
                this.map_data = map_data;
            }

            public static class MapDataBean implements Serializable {
                /**
                 * coupon_amount : 40
                 * small_images : {"string":[""]}
                 * shop_title : 魔黛娅内衣旗舰店
                 * category_id : 162201
                 * coupon_start_fee : 69
                 * item_id : 556633720749
                 * coupon_total_count : 30000
                 * user_type : 1
                 * zk_final_price : 79.9
                 * coupon_remain_count : 1936
                 * commission_rate : 5.50
                 * coupon_start_time : 2017-10-29
                 * title : 毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款
                 * item_description :
                 * seller_id : 745957850
                 * volume : 3837
                 * coupon_end_time : 2017-11-02
                 * coupon_click_url : //uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956etpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8
                 * pict_url : //img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg
                 * click_url : //item.taobao.com/item.htm?id=556633720749&scm=1007.16190.92234.0&pvid=41362290-fa0b-4252-b172-6afc9c00e2c8&app_pvid=0ab0fac715095507006577956e
                 * stock : 1
                 * sell_num : 1
                 * total_stock : 1
                 * oetime :
                 * ostime :
                 * jdd_num : 1
                 * jdd_price :
                 * orig_price :
                 * level_one_category_name :
                 * level_one_category_id : 1
                 * category_name :
                 * white_image : https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg
                 * short_title : sss
                 * word_list : {"word_map_data":[{"url":"https://uland.taobao.com/semm/tbxxxx","word":"孕妇装"}]}
                 * tmall_play_activity_info : 前n件x折
                 * uv_sum_pre_sale : 21
                 * x_id : uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc
                 * new_user_price : 1.99
                 * coupon_info : 满299减30元
                 * coupon_share_url : //uland.taobao.com/coupon/edetail?e=pR6YtnFKK%2B8GQASttHIRqcEWOmlidB03Pf45HLyCqA8dRAklSM5tEQ36hBQToU3M3MmLjFwLsqgZxcV7BPtHQDd2Naqom0e0&mt=1&app_pvid=0ab0fac715095507006577956e&ptl=app_pvid:0ab0fac715095507006577956e;tpp_pvid:41362290-fa0b-4252-b172-6afc9c00e2c8
                 * nick : 秉迪数码专营店
                 * reserve_price : 2.99
                 * ju_online_end_time : 1559750399000
                 * ju_online_start_time : 1559232000000
                 * maochao_play_end_time : 1559750399000
                 * maochao_play_start_time : 1559232000000
                 * maochao_play_conditions : 2,3
                 * maochao_play_discounts : 500,450
                 * maochao_play_discount_type : 2
                 * maochao_play_free_post_fee : 1
                 * multi_coupon_zk_rate : 0.41234
                 * price_after_multi_coupon : 9.9
                 * multi_coupon_item_count : 3
                 */

                private int coupon_amount;
                private SmallImagesBean small_images;
                private String shop_title;
                private int category_id;
                private String coupon_start_fee;
                private long item_id;
                private int coupon_total_count;
                private int user_type;
                private String zk_final_price;
                private int coupon_remain_count;
                private String commission_rate;
                private String coupon_start_time;
                private String title;
                private String item_description;
                private long seller_id;
                private int volume;
                private String coupon_end_time;
                private String coupon_click_url;
                private String pict_url;
                private String click_url;
                private int stock;
                private int sell_num;
                private int total_stock;
                private String oetime;
                private String ostime;
                private int jdd_num;
                private String jdd_price;
                private String orig_price;
                private String level_one_category_name;
                private int level_one_category_id;
                private String category_name;
                private String white_image;
                private String short_title;
                private WordListBean word_list;
                private String tmall_play_activity_info;
                private int uv_sum_pre_sale;
                private String x_id;
                private String new_user_price;
                private String coupon_info;
                private String coupon_share_url;
                private String item_url;
                private String url;
                private String nick;
                private String reserve_price;
                private String ju_online_end_time;
                private String ju_online_start_time;
                private String maochao_play_end_time;
                private String maochao_play_start_time;
                private String maochao_play_conditions;
                private String maochao_play_discounts;
                private String maochao_play_discount_type;
                private String maochao_play_free_post_fee;
                private String multi_coupon_zk_rate;
                private String price_after_multi_coupon;
                private String multi_coupon_item_count;

                public int getCoupon_amount() {
                    return coupon_amount;
                }

                public void setCoupon_amount(int coupon_amount) {
                    this.coupon_amount = coupon_amount;
                }

                public SmallImagesBean getSmall_images() {
                    return small_images;
                }

                public void setSmall_images(SmallImagesBean small_images) {
                    this.small_images = small_images;
                }

                public String getShop_title() {
                    return shop_title;
                }

                public void setShop_title(String shop_title) {
                    this.shop_title = shop_title;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public String getCoupon_start_fee() {
                    return coupon_start_fee;
                }

                public void setCoupon_start_fee(String coupon_start_fee) {
                    this.coupon_start_fee = coupon_start_fee;
                }

                public long getItem_id() {
                    return item_id;
                }

                public void setItem_id(long item_id) {
                    this.item_id = item_id;
                }

                public int getCoupon_total_count() {
                    return coupon_total_count;
                }

                public void setCoupon_total_count(int coupon_total_count) {
                    this.coupon_total_count = coupon_total_count;
                }

                public int getUser_type() {
                    return user_type;
                }

                public void setUser_type(int user_type) {
                    this.user_type = user_type;
                }

                public String getZk_final_price() {
                    return zk_final_price;
                }

                public void setZk_final_price(String zk_final_price) {
                    this.zk_final_price = zk_final_price;
                }

                public int getCoupon_remain_count() {
                    return coupon_remain_count;
                }

                public void setCoupon_remain_count(int coupon_remain_count) {
                    this.coupon_remain_count = coupon_remain_count;
                }

                public String getCommission_rate() {
                    return commission_rate;
                }

                public void setCommission_rate(String commission_rate) {
                    this.commission_rate = commission_rate;
                }

                public String getCoupon_start_time() {
                    return coupon_start_time;
                }

                public void setCoupon_start_time(String coupon_start_time) {
                    this.coupon_start_time = coupon_start_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getItem_description() {
                    return item_description;
                }

                public void setItem_description(String item_description) {
                    this.item_description = item_description;
                }

                public long getSeller_id() {
                    return seller_id;
                }

                public void setSeller_id(long seller_id) {
                    this.seller_id = seller_id;
                }

                public int getVolume() {
                    return volume;
                }

                public void setVolume(int volume) {
                    this.volume = volume;
                }

                public String getCoupon_end_time() {
                    return coupon_end_time;
                }

                public void setCoupon_end_time(String coupon_end_time) {
                    this.coupon_end_time = coupon_end_time;
                }

                public String getCoupon_click_url() {
                    return coupon_click_url;
                }

                public void setCoupon_click_url(String coupon_click_url) {
                    this.coupon_click_url = coupon_click_url;
                }

                public String getPict_url() {
                    return pict_url;
                }

                public void setPict_url(String pict_url) {
                    this.pict_url = pict_url;
                }

                public String getClick_url() {
                    return click_url;
                }

                public void setClick_url(String click_url) {
                    this.click_url = click_url;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public int getSell_num() {
                    return sell_num;
                }

                public void setSell_num(int sell_num) {
                    this.sell_num = sell_num;
                }

                public int getTotal_stock() {
                    return total_stock;
                }

                public void setTotal_stock(int total_stock) {
                    this.total_stock = total_stock;
                }

                public String getOetime() {
                    return oetime;
                }

                public void setOetime(String oetime) {
                    this.oetime = oetime;
                }

                public String getOstime() {
                    return ostime;
                }

                public void setOstime(String ostime) {
                    this.ostime = ostime;
                }

                public int getJdd_num() {
                    return jdd_num;
                }

                public void setJdd_num(int jdd_num) {
                    this.jdd_num = jdd_num;
                }

                public String getJdd_price() {
                    return jdd_price;
                }

                public void setJdd_price(String jdd_price) {
                    this.jdd_price = jdd_price;
                }

                public String getOrig_price() {
                    return orig_price;
                }

                public void setOrig_price(String orig_price) {
                    this.orig_price = orig_price;
                }

                public String getLevel_one_category_name() {
                    return level_one_category_name;
                }

                public void setLevel_one_category_name(String level_one_category_name) {
                    this.level_one_category_name = level_one_category_name;
                }

                public int getLevel_one_category_id() {
                    return level_one_category_id;
                }

                public void setLevel_one_category_id(int level_one_category_id) {
                    this.level_one_category_id = level_one_category_id;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getWhite_image() {
                    return white_image;
                }

                public void setWhite_image(String white_image) {
                    this.white_image = white_image;
                }

                public String getShort_title() {
                    return short_title;
                }

                public void setShort_title(String short_title) {
                    this.short_title = short_title;
                }

                public WordListBean getWord_list() {
                    return word_list;
                }

                public void setWord_list(WordListBean word_list) {
                    this.word_list = word_list;
                }

                public String getTmall_play_activity_info() {
                    return tmall_play_activity_info;
                }

                public void setTmall_play_activity_info(String tmall_play_activity_info) {
                    this.tmall_play_activity_info = tmall_play_activity_info;
                }

                public int getUv_sum_pre_sale() {
                    return uv_sum_pre_sale;
                }

                public void setUv_sum_pre_sale(int uv_sum_pre_sale) {
                    this.uv_sum_pre_sale = uv_sum_pre_sale;
                }

                public String getX_id() {
                    return x_id;
                }

                public void setX_id(String x_id) {
                    this.x_id = x_id;
                }

                public String getNew_user_price() {
                    return new_user_price;
                }

                public void setNew_user_price(String new_user_price) {
                    this.new_user_price = new_user_price;
                }

                public String getCoupon_info() {
                    return coupon_info;
                }

                public void setCoupon_info(String coupon_info) {
                    this.coupon_info = coupon_info;
                }

                public String getCoupon_share_url() {
                    return coupon_share_url;
                }

                public void setCoupon_share_url(String coupon_share_url) {
                    this.coupon_share_url = coupon_share_url;
                }

                public String getItem_url() {
                    return item_url;
                }

                public void setItem_url(String item_url) {
                    this.item_url = item_url;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getNick() {
                    return nick;
                }

                public void setNick(String nick) {
                    this.nick = nick;
                }

                public String getReserve_price() {
                    return reserve_price;
                }

                public void setReserve_price(String reserve_price) {
                    this.reserve_price = reserve_price;
                }

                public String getJu_online_end_time() {
                    return ju_online_end_time;
                }

                public void setJu_online_end_time(String ju_online_end_time) {
                    this.ju_online_end_time = ju_online_end_time;
                }

                public String getJu_online_start_time() {
                    return ju_online_start_time;
                }

                public void setJu_online_start_time(String ju_online_start_time) {
                    this.ju_online_start_time = ju_online_start_time;
                }

                public String getMaochao_play_end_time() {
                    return maochao_play_end_time;
                }

                public void setMaochao_play_end_time(String maochao_play_end_time) {
                    this.maochao_play_end_time = maochao_play_end_time;
                }

                public String getMaochao_play_start_time() {
                    return maochao_play_start_time;
                }

                public void setMaochao_play_start_time(String maochao_play_start_time) {
                    this.maochao_play_start_time = maochao_play_start_time;
                }

                public String getMaochao_play_conditions() {
                    return maochao_play_conditions;
                }

                public void setMaochao_play_conditions(String maochao_play_conditions) {
                    this.maochao_play_conditions = maochao_play_conditions;
                }

                public String getMaochao_play_discounts() {
                    return maochao_play_discounts;
                }

                public void setMaochao_play_discounts(String maochao_play_discounts) {
                    this.maochao_play_discounts = maochao_play_discounts;
                }

                public String getMaochao_play_discount_type() {
                    return maochao_play_discount_type;
                }

                public void setMaochao_play_discount_type(String maochao_play_discount_type) {
                    this.maochao_play_discount_type = maochao_play_discount_type;
                }

                public String getMaochao_play_free_post_fee() {
                    return maochao_play_free_post_fee;
                }

                public void setMaochao_play_free_post_fee(String maochao_play_free_post_fee) {
                    this.maochao_play_free_post_fee = maochao_play_free_post_fee;
                }

                public String getMulti_coupon_zk_rate() {
                    return multi_coupon_zk_rate;
                }

                public void setMulti_coupon_zk_rate(String multi_coupon_zk_rate) {
                    this.multi_coupon_zk_rate = multi_coupon_zk_rate;
                }

                public String getPrice_after_multi_coupon() {
                    return price_after_multi_coupon;
                }

                public void setPrice_after_multi_coupon(String price_after_multi_coupon) {
                    this.price_after_multi_coupon = price_after_multi_coupon;
                }

                public String getMulti_coupon_item_count() {
                    return multi_coupon_item_count;
                }

                public void setMulti_coupon_item_count(String multi_coupon_item_count) {
                    this.multi_coupon_item_count = multi_coupon_item_count;
                }

                public static class SmallImagesBean implements Serializable{
                    private List<String> string;

                    public List<String> getString() {
                        return string;
                    }

                    public void setString(List<String> string) {
                        this.string = string;
                    }
                }

                public static class WordListBean implements Serializable{
                    private List<WordMapDataBean> word_map_data;

                    public List<WordMapDataBean> getWord_map_data() {
                        return word_map_data;
                    }

                    public void setWord_map_data(List<WordMapDataBean> word_map_data) {
                        this.word_map_data = word_map_data;
                    }

                    public static class WordMapDataBean {
                        /**
                         * url : https://uland.taobao.com/semm/tbxxxx
                         * word : 孕妇装
                         */

                        private String url;
                        private String word;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public String getWord() {
                            return word;
                        }

                        public void setWord(String word) {
                            this.word = word;
                        }
                    }
                }
            }
        }
    }
}
