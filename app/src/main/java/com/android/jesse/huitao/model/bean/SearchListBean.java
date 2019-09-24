package com.android.jesse.huitao.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 搜索列表数据
 * @author: zhangshihao
 * @date: 2019/9/9
 */
public class SearchListBean {

    /**
     * request_id :
     * error_response :
     * code :
     * msg :
     * sub_code :
     * sub_msg :
     * tbk_dg_material_optional_response : {"total_results":1212,"result_list":{"map_data":[{"coupon_start_time":"2017-10-29","coupon_end_time":"2017-10-29","info_dxjh":"{\"19013551\":\"2850\",\"74510538\":\"2550\"}","tk_total_sales":"11","tk_total_commi":"323","coupon_id":"d62db1ab8d9546b1bf0ff49bda5fc33b","num_iid":556633720749,"title":"毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款","pict_url":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","small_images":{"string":["https://img.alicdn.com/i4/3077291146/TB2NA3poDnI8KJjSszgXXc8ApXa_!!3077291146.jpg"]},"reserve_price":"102.00","zk_final_price":"88.00","user_type":1,"provcity":"杭州","item_url":"https://item.taobao.com/item.htm?id=564591813536","include_mkt":"false","include_dxjh":"false","commission_rate":"1550表示15.5%","volume":123,"seller_id":232332,"coupon_total_count":22323,"coupon_remain_count":111,"coupon_info":"满299元减20元","commission_type":"MKT表示营销计划，SP表示定向计划，COMMON表示通用计划","shop_title":"xx旗舰店","shop_dsr":13,"coupon_share_url":"uland.xxx","url":"s.click.xxx","level_one_category_name":"女装","level_one_category_id":20,"category_name":"连衣裙","category_id":162201,"short_title":"xxsd","white_image":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","oetime":"2018-08-21 11:23:43","ostime":"2018-08-21 11:23:43","jdd_num":10,"jdd_price":"5","uv_sum_pre_sale":23,"x_id":"uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc","coupon_start_fee":"29.00","coupon_amount":"10.00","item_description":"季凉被 全棉亲肤","nick":"旗舰店","orig_price":"25","total_stock":5555,"sell_num":1111,"stock":4444,"tmall_play_activity_info":"前n件x折","item_id":5678899993,"real_post_fee":"0.00"}]}}
     */

    private String request_id;
    private String error_response;
    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;
    private TbkDgMaterialOptionalResponseBean tbk_dg_material_optional_response;

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

    public TbkDgMaterialOptionalResponseBean getTbk_dg_material_optional_response() {
        return tbk_dg_material_optional_response;
    }

    public void setTbk_dg_material_optional_response(TbkDgMaterialOptionalResponseBean tbk_dg_material_optional_response) {
        this.tbk_dg_material_optional_response = tbk_dg_material_optional_response;
    }

    public static class TbkDgMaterialOptionalResponseBean {
        /**
         * total_results : 1212
         * result_list : {"map_data":[{"coupon_start_time":"2017-10-29","coupon_end_time":"2017-10-29","info_dxjh":"{\"19013551\":\"2850\",\"74510538\":\"2550\"}","tk_total_sales":"11","tk_total_commi":"323","coupon_id":"d62db1ab8d9546b1bf0ff49bda5fc33b","num_iid":556633720749,"title":"毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款","pict_url":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","small_images":{"string":["https://img.alicdn.com/i4/3077291146/TB2NA3poDnI8KJjSszgXXc8ApXa_!!3077291146.jpg"]},"reserve_price":"102.00","zk_final_price":"88.00","user_type":1,"provcity":"杭州","item_url":"https://item.taobao.com/item.htm?id=564591813536","include_mkt":"false","include_dxjh":"false","commission_rate":"1550表示15.5%","volume":123,"seller_id":232332,"coupon_total_count":22323,"coupon_remain_count":111,"coupon_info":"满299元减20元","commission_type":"MKT表示营销计划，SP表示定向计划，COMMON表示通用计划","shop_title":"xx旗舰店","shop_dsr":13,"coupon_share_url":"uland.xxx","url":"s.click.xxx","level_one_category_name":"女装","level_one_category_id":20,"category_name":"连衣裙","category_id":162201,"short_title":"xxsd","white_image":"https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg","oetime":"2018-08-21 11:23:43","ostime":"2018-08-21 11:23:43","jdd_num":10,"jdd_price":"5","uv_sum_pre_sale":23,"x_id":"uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc","coupon_start_fee":"29.00","coupon_amount":"10.00","item_description":"季凉被 全棉亲肤","nick":"旗舰店","orig_price":"25","total_stock":5555,"sell_num":1111,"stock":4444,"tmall_play_activity_info":"前n件x折","item_id":5678899993,"real_post_fee":"0.00"}]}
         */

        private int total_results;
        private ResultListBean result_list;

        public int getTotal_results() {
            return total_results;
        }

        public void setTotal_results(int total_results) {
            this.total_results = total_results;
        }

        public ResultListBean getResult_list() {
            return result_list;
        }

        public void setResult_list(ResultListBean result_list) {
            this.result_list = result_list;
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
                 * coupon_start_time : 2017-10-29
                 * coupon_end_time : 2017-10-29
                 * info_dxjh : {"19013551":"2850","74510538":"2550"}
                 * tk_total_sales : 11
                 * tk_total_commi : 323
                 * coupon_id : d62db1ab8d9546b1bf0ff49bda5fc33b
                 * num_iid : 556633720749
                 * title : 毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款
                 * pict_url : https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg
                 * small_images : {"string":["https://img.alicdn.com/i4/3077291146/TB2NA3poDnI8KJjSszgXXc8ApXa_!!3077291146.jpg"]}
                 * reserve_price : 102.00
                 * zk_final_price : 88.00
                 * user_type : 1
                 * provcity : 杭州
                 * item_url : https://item.taobao.com/item.htm?id=564591813536
                 * include_mkt : false
                 * include_dxjh : false
                 * commission_rate : 1550表示15.5%
                 * volume : 123
                 * seller_id : 232332
                 * coupon_total_count : 22323
                 * coupon_remain_count : 111
                 * coupon_info : 满299元减20元
                 * commission_type : MKT表示营销计划，SP表示定向计划，COMMON表示通用计划
                 * shop_title : xx旗舰店
                 * shop_dsr : 13
                 * coupon_share_url : uland.xxx
                 * url : s.click.xxx
                 * level_one_category_name : 女装
                 * level_one_category_id : 20
                 * category_name : 连衣裙
                 * category_id : 162201
                 * short_title : xxsd
                 * white_image : https://img.alicdn.com/bao/uploaded/i4/745957850/TB1WzSRmV9gSKJjSspbXXbeNXXa_!!0-item_pic.jpg
                 * oetime : 2018-08-21 11:23:43
                 * ostime : 2018-08-21 11:23:43
                 * jdd_num : 10
                 * jdd_price : 5
                 * uv_sum_pre_sale : 23
                 * x_id : uESS0mv8JvfJRMKlIgCD5At9VuHVBXoqBRihfQlo4ybLiKygRlqiN4eoxoZDfe38uSogWy6GB971jD2N8tLuuc
                 * coupon_start_fee : 29.00
                 * coupon_amount : 10.00
                 * item_description : 季凉被 全棉亲肤
                 * nick : 旗舰店
                 * orig_price : 25
                 * total_stock : 5555
                 * sell_num : 1111
                 * stock : 4444
                 * tmall_play_activity_info : 前n件x折
                 * item_id : 5678899993
                 * real_post_fee : 0.00
                 */

                private String coupon_start_time;
                private String coupon_end_time;
                private String info_dxjh;
                private String tk_total_sales;
                private String tk_total_commi;
                private String coupon_id;
                private long num_iid;
                private String title;
                private String pict_url;
                private SmallImagesBean small_images;
                private String reserve_price;
                private String zk_final_price;
                private int user_type;
                private String provcity;
                private String item_url;
                private String include_mkt;
                private String include_dxjh;
                private String commission_rate;
                private int volume;
                private long seller_id;
                private int coupon_total_count;
                private int coupon_remain_count;
                private String coupon_info;
                private String commission_type;
                private String shop_title;
                private int shop_dsr;
                private String coupon_share_url;
                private String url;
                private String level_one_category_name;
                private int level_one_category_id;
                private String category_name;
                private int category_id;
                private String short_title;
                private String white_image;
                private String oetime;
                private String ostime;
                private int jdd_num;
                private String jdd_price;
                private int uv_sum_pre_sale;
                private String x_id;
                private String coupon_start_fee;
                private String coupon_amount;
                private String item_description;
                private String nick;
                private String orig_price;
                private int total_stock;
                private int sell_num;
                private int stock;
                private String tmall_play_activity_info;
                private long item_id;
                private String real_post_fee;

                public String getCoupon_start_time() {
                    return coupon_start_time;
                }

                public void setCoupon_start_time(String coupon_start_time) {
                    this.coupon_start_time = coupon_start_time;
                }

                public String getCoupon_end_time() {
                    return coupon_end_time;
                }

                public void setCoupon_end_time(String coupon_end_time) {
                    this.coupon_end_time = coupon_end_time;
                }

                public String getInfo_dxjh() {
                    return info_dxjh;
                }

                public void setInfo_dxjh(String info_dxjh) {
                    this.info_dxjh = info_dxjh;
                }

                public String getTk_total_sales() {
                    return tk_total_sales;
                }

                public void setTk_total_sales(String tk_total_sales) {
                    this.tk_total_sales = tk_total_sales;
                }

                public String getTk_total_commi() {
                    return tk_total_commi;
                }

                public void setTk_total_commi(String tk_total_commi) {
                    this.tk_total_commi = tk_total_commi;
                }

                public String getCoupon_id() {
                    return coupon_id;
                }

                public void setCoupon_id(String coupon_id) {
                    this.coupon_id = coupon_id;
                }

                public long getNum_iid() {
                    return num_iid;
                }

                public void setNum_iid(long num_iid) {
                    this.num_iid = num_iid;
                }

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

                public SmallImagesBean getSmall_images() {
                    return small_images;
                }

                public void setSmall_images(SmallImagesBean small_images) {
                    this.small_images = small_images;
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

                public int getUser_type() {
                    return user_type;
                }

                public void setUser_type(int user_type) {
                    this.user_type = user_type;
                }

                public String getProvcity() {
                    return provcity;
                }

                public void setProvcity(String provcity) {
                    this.provcity = provcity;
                }

                public String getItem_url() {
                    return item_url;
                }

                public void setItem_url(String item_url) {
                    this.item_url = item_url;
                }

                public String getInclude_mkt() {
                    return include_mkt;
                }

                public void setInclude_mkt(String include_mkt) {
                    this.include_mkt = include_mkt;
                }

                public String getInclude_dxjh() {
                    return include_dxjh;
                }

                public void setInclude_dxjh(String include_dxjh) {
                    this.include_dxjh = include_dxjh;
                }

                public String getCommission_rate() {
                    return commission_rate;
                }

                public void setCommission_rate(String commission_rate) {
                    this.commission_rate = commission_rate;
                }

                public int getVolume() {
                    return volume;
                }

                public void setVolume(int volume) {
                    this.volume = volume;
                }

                public long getSeller_id() {
                    return seller_id;
                }

                public void setSeller_id(long seller_id) {
                    this.seller_id = seller_id;
                }

                public int getCoupon_total_count() {
                    return coupon_total_count;
                }

                public void setCoupon_total_count(int coupon_total_count) {
                    this.coupon_total_count = coupon_total_count;
                }

                public int getCoupon_remain_count() {
                    return coupon_remain_count;
                }

                public void setCoupon_remain_count(int coupon_remain_count) {
                    this.coupon_remain_count = coupon_remain_count;
                }

                public String getCoupon_info() {
                    return coupon_info;
                }

                public void setCoupon_info(String coupon_info) {
                    this.coupon_info = coupon_info;
                }

                public String getCommission_type() {
                    return commission_type;
                }

                public void setCommission_type(String commission_type) {
                    this.commission_type = commission_type;
                }

                public String getShop_title() {
                    return shop_title;
                }

                public void setShop_title(String shop_title) {
                    this.shop_title = shop_title;
                }

                public int getShop_dsr() {
                    return shop_dsr;
                }

                public void setShop_dsr(int shop_dsr) {
                    this.shop_dsr = shop_dsr;
                }

                public String getCoupon_share_url() {
                    return coupon_share_url;
                }

                public void setCoupon_share_url(String coupon_share_url) {
                    this.coupon_share_url = coupon_share_url;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
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

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public String getShort_title() {
                    return short_title;
                }

                public void setShort_title(String short_title) {
                    this.short_title = short_title;
                }

                public String getWhite_image() {
                    return white_image;
                }

                public void setWhite_image(String white_image) {
                    this.white_image = white_image;
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

                public String getCoupon_start_fee() {
                    return coupon_start_fee;
                }

                public void setCoupon_start_fee(String coupon_start_fee) {
                    this.coupon_start_fee = coupon_start_fee;
                }

                public String getCoupon_amount() {
                    return coupon_amount;
                }

                public void setCoupon_amount(String coupon_amount) {
                    this.coupon_amount = coupon_amount;
                }

                public String getItem_description() {
                    return item_description;
                }

                public void setItem_description(String item_description) {
                    this.item_description = item_description;
                }

                public String getNick() {
                    return nick;
                }

                public void setNick(String nick) {
                    this.nick = nick;
                }

                public String getOrig_price() {
                    return orig_price;
                }

                public void setOrig_price(String orig_price) {
                    this.orig_price = orig_price;
                }

                public int getTotal_stock() {
                    return total_stock;
                }

                public void setTotal_stock(int total_stock) {
                    this.total_stock = total_stock;
                }

                public int getSell_num() {
                    return sell_num;
                }

                public void setSell_num(int sell_num) {
                    this.sell_num = sell_num;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getTmall_play_activity_info() {
                    return tmall_play_activity_info;
                }

                public void setTmall_play_activity_info(String tmall_play_activity_info) {
                    this.tmall_play_activity_info = tmall_play_activity_info;
                }

                public long getItem_id() {
                    return item_id;
                }

                public void setItem_id(long item_id) {
                    this.item_id = item_id;
                }

                public String getReal_post_fee() {
                    return real_post_fee;
                }

                public void setReal_post_fee(String real_post_fee) {
                    this.real_post_fee = real_post_fee;
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
            }
        }
    }
}
