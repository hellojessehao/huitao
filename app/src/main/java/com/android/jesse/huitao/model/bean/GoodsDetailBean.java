package com.android.jesse.huitao.model.bean;

import java.util.List;

/**
 * @Description: 商品详情数据
 * @author: zhangshihao
 * @date: 2019/9/22 0022
 */
public class GoodsDetailBean {
    /**
     * request_id :
     * error_response :
     * code :
     * msg :
     * sub_code :
     * sub_msg :
     * tbk_item_info_get_response : {"results":{"n_tbk_item":[{"cat_name":"女装","num_iid":123,"title":"连衣裙","pict_url":"http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg","small_images":{"string":["http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg"]},"reserve_price":"102.00","zk_final_price":"88.00","user_type":1,"provcity":"杭州","item_url":"http://detail.m.tmall.com/item.htm?id=xxx","seller_id":123,"volume":1,"nick":"xx旗舰店","cat_leaf_name":"情趣内衣","is_prepay":true,"shop_dsr":23,"ratesum":13,"i_rfd_rate":true,"h_good_rate":true,"h_pay_rate30":true,"free_shipment":true,"material_lib_type":"1"}]}}
     */
    private String request_id;
    private String error_response;
    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;
    private TbkItemInfoGetResponseBean tbk_item_info_get_response;

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

    public TbkItemInfoGetResponseBean getTbk_item_info_get_response() {
        return tbk_item_info_get_response;
    }

    public void setTbk_item_info_get_response(TbkItemInfoGetResponseBean tbk_item_info_get_response) {
        this.tbk_item_info_get_response = tbk_item_info_get_response;
    }

    public static class TbkItemInfoGetResponseBean {
        /**
         * results : {"n_tbk_item":[{"cat_name":"女装","num_iid":123,"title":"连衣裙","pict_url":"http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg","small_images":{"string":["http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg"]},"reserve_price":"102.00","zk_final_price":"88.00","user_type":1,"provcity":"杭州","item_url":"http://detail.m.tmall.com/item.htm?id=xxx","seller_id":123,"volume":1,"nick":"xx旗舰店","cat_leaf_name":"情趣内衣","is_prepay":true,"shop_dsr":23,"ratesum":13,"i_rfd_rate":true,"h_good_rate":true,"h_pay_rate30":true,"free_shipment":true,"material_lib_type":"1"}]}
         */

        private ResultsBean results;

        public ResultsBean getResults() {
            return results;
        }

        public void setResults(ResultsBean results) {
            this.results = results;
        }

        public static class ResultsBean {
            private List<NTbkItemBean> n_tbk_item;

            public List<NTbkItemBean> getN_tbk_item() {
                return n_tbk_item;
            }

            public void setN_tbk_item(List<NTbkItemBean> n_tbk_item) {
                this.n_tbk_item = n_tbk_item;
            }

            public static class NTbkItemBean {
                /**
                 * cat_name : 女装
                 * num_iid : 123
                 * title : 连衣裙
                 * pict_url : http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg
                 * small_images : {"string":["http://gi4.md.alicdn.com/bao/uploaded/i4/xxx.jpg"]}
                 * reserve_price : 102.00
                 * zk_final_price : 88.00
                 * user_type : 1
                 * provcity : 杭州
                 * item_url : http://detail.m.tmall.com/item.htm?id=xxx
                 * seller_id : 123
                 * volume : 1
                 * nick : xx旗舰店
                 * cat_leaf_name : 情趣内衣
                 * is_prepay : true
                 * shop_dsr : 23
                 * ratesum : 13
                 * i_rfd_rate : true
                 * h_good_rate : true
                 * h_pay_rate30 : true
                 * free_shipment : true
                 * material_lib_type : 1
                 */

                private String cat_name;
                private long num_iid;
                private String title;
                private String pict_url;
                private SmallImagesBean small_images;
                private String reserve_price;
                private String zk_final_price;
                private int user_type;
                private String provcity;
                private String item_url;
                private int seller_id;
                private int volume;
                private String nick;
                private String cat_leaf_name;
                private boolean is_prepay;
                private int shop_dsr;
                private int ratesum;
                private boolean i_rfd_rate;
                private boolean h_good_rate;
                private boolean h_pay_rate30;
                private boolean free_shipment;
                private String material_lib_type;

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
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

                public int getSeller_id() {
                    return seller_id;
                }

                public void setSeller_id(int seller_id) {
                    this.seller_id = seller_id;
                }

                public int getVolume() {
                    return volume;
                }

                public void setVolume(int volume) {
                    this.volume = volume;
                }

                public String getNick() {
                    return nick;
                }

                public void setNick(String nick) {
                    this.nick = nick;
                }

                public String getCat_leaf_name() {
                    return cat_leaf_name;
                }

                public void setCat_leaf_name(String cat_leaf_name) {
                    this.cat_leaf_name = cat_leaf_name;
                }

                public boolean isIs_prepay() {
                    return is_prepay;
                }

                public void setIs_prepay(boolean is_prepay) {
                    this.is_prepay = is_prepay;
                }

                public int getShop_dsr() {
                    return shop_dsr;
                }

                public void setShop_dsr(int shop_dsr) {
                    this.shop_dsr = shop_dsr;
                }

                public int getRatesum() {
                    return ratesum;
                }

                public void setRatesum(int ratesum) {
                    this.ratesum = ratesum;
                }

                public boolean isI_rfd_rate() {
                    return i_rfd_rate;
                }

                public void setI_rfd_rate(boolean i_rfd_rate) {
                    this.i_rfd_rate = i_rfd_rate;
                }

                public boolean isH_good_rate() {
                    return h_good_rate;
                }

                public void setH_good_rate(boolean h_good_rate) {
                    this.h_good_rate = h_good_rate;
                }

                public boolean isH_pay_rate30() {
                    return h_pay_rate30;
                }

                public void setH_pay_rate30(boolean h_pay_rate30) {
                    this.h_pay_rate30 = h_pay_rate30;
                }

                public boolean isFree_shipment() {
                    return free_shipment;
                }

                public void setFree_shipment(boolean free_shipment) {
                    this.free_shipment = free_shipment;
                }

                public String getMaterial_lib_type() {
                    return material_lib_type;
                }

                public void setMaterial_lib_type(String material_lib_type) {
                    this.material_lib_type = material_lib_type;
                }

                public static class SmallImagesBean {
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
    /*
    {
        "request_id":"",
            "error_response":"",
            "code":"",
            "msg":"",
            "sub_code":"",
            "sub_msg":"",
            "tbk_item_info_get_response":{
        "results":{
            "n_tbk_item":[
            {
                "cat_name":"女装",
                    "num_iid":123,
                    "title":"连衣裙",
                    "pict_url":"http:\/\/gi4.md.alicdn.com\/bao\/uploaded\/i4\/xxx.jpg",
                    "small_images":{
                "string":[
                "http:\/\/gi4.md.alicdn.com\/bao\/uploaded\/i4\/xxx.jpg"
                        ]
            },
                "reserve_price":"102.00",
                    "zk_final_price":"88.00",
                    "user_type":1,
                    "provcity":"杭州",
                    "item_url":"http:\/\/detail.m.tmall.com\/item.htm?id=xxx",
                    "seller_id":123,
                    "volume":1,
                    "nick":"xx旗舰店",
                    "cat_leaf_name":"情趣内衣",
                    "is_prepay":true,
                    "shop_dsr":23,
                    "ratesum":13,
                    "i_rfd_rate":true,
                    "h_good_rate":true,
                    "h_pay_rate30":true,
                    "free_shipment":true,
                    "material_lib_type":"1"
            }
            ]
        }
    }
    }
    */

}
