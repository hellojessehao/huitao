package com.android.jesse.huitao.model.bean;

import java.util.List;

/**
 * @Description:
 * @author: zhangshihao
 * @date: 2019/9/25 0025
 */
public class RelativeGoodsBean {

    /**
     * request_id :
     * error_response :
     * code :
     * msg :
     * sub_code :
     * sub_msg :
     * tbk_item_recommend_get_response : {"results":{"n_tbk_item":[{"num_iid":123456789423,"title":"","pict_url":"","reserve_price":"","zk_final_price":"","item_url":"","volume":123}]}}
     */

    private String request_id;
    private String error_response;
    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;
    private TbkItemRecommendGetResponseBean tbk_item_recommend_get_response;

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

    public TbkItemRecommendGetResponseBean getTbk_item_recommend_get_response() {
        return tbk_item_recommend_get_response;
    }

    public void setTbk_item_recommend_get_response(TbkItemRecommendGetResponseBean tbk_item_recommend_get_response) {
        this.tbk_item_recommend_get_response = tbk_item_recommend_get_response;
    }

    public static class TbkItemRecommendGetResponseBean {
        /**
         * results : {"n_tbk_item":[{"num_iid":123456789423,"title":"","pict_url":"","reserve_price":"","zk_final_price":"","item_url":"","volume":123}]}
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
                 * num_iid : 123456789423
                 * title :
                 * pict_url :
                 * reserve_price :
                 * zk_final_price :
                 * item_url :
                 * volume : 123
                 * nick : 男装旗舰店
                 */

                private long num_iid;
                private String title;
                private String pict_url;
                private String reserve_price;
                private String zk_final_price;
                private String item_url;
                private int volume;
                private String nick;

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

                public String getItem_url() {
                    return item_url;
                }

                public void setItem_url(String item_url) {
                    this.item_url = item_url;
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
            }
        }
    }
}
