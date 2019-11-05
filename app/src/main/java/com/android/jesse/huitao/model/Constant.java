package com.android.jesse.huitao.model;

/**
 * @Description: 放置全局变量
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class Constant {
    public static final String ABC = "abc";

    //@接口地址{
    public static final String BASE_URL_TAOBAO = "http://gw.api.taobao.com/router/rest?";
    public static final String CREATE_TAO_WORDS = "taobao.tbk.tpwd.create";//生成淘口令
    public static final String POPULARIZE_COUPONS_GET = "taobao.tbk.coupon.get";//推广券获取
    public static final String TKL_CONVERT = "taobao.tbk.tpwd.convert";//邀约制接口 暂无权限
    public static final String GOODS_LIST_GET = "taobao.tbk.dg.optimus.material";//淘宝客-推广者-物料精选
    public static final String GOODS_SEARCH = "taobao.tbk.dg.material.optional";//淘宝客【推广者】物料搜索
    public static final String SHOP_SEARCH = "taobao.tbk.shop.get";//根据关键词搜索店铺
    public static final String GOODS_DETAIL_GET = "taobao.tbk.item.info.get";//淘宝客-公用-淘宝客商品详情查询(简版)
    public static final String RELATIVE_RECOMMEND = "taobao.tbk.item.recommend.get";//淘宝客-公用-商品关联推荐
    //@}

    //@第三方平台参数{
    public static final String APPKEY_TAOBAO = "27821806";
    public static final String APPSECRET_TAOBAO = "154972c8b8e369d1232e7a5acfd74584";
    public static final String TB_USER_ID = "783511823";
    public static final String PGYER_APP_KEY = "1fbe0631ff1ab55d8045d85a874a1c09";
    public static final String WECHAT_APP_ID = "wxe427ebd5fa3dadd7";
    public static final String UMENG_APP_KEY = "5db25ceb3fc1951b4e0006b0";
    public static final String UMENG_MESSAGE_SECRET = "6c7542b5e95e5daabe16aa0584b34f0e";
    //@}

    //淘客相关参数@{
    public static final String ADZONE_ID = "109339400190";//推广位id
    //好券
    public static final int HAOQUAN_MATERIAL_ID_ZONGHE = 3756;//综合
    //高佣
    public static final int HIGH_COMMISSION_MATERIAL_ID_ZONGHE = 13366;//综合
    public static final int HIGH_COMMISSION_MATERIAL_ID_SHUMA = 13369;//数码
    public static final int HIGH_COMMISSION_MATERIAL_ID_NVZHUANG = 13367;//女装
    public static final int HIGH_COMMISSION_MATERIAL_ID_NANZHUANG = 13372;//男装
    public static final int HIGH_COMMISSION_MATERIAL_ID_XIEBAO = 13370;//鞋包
    public static final int HIGH_COMMISSION_MATERIAL_ID_MEISHI = 13375;//美食
    public static final int HIGH_COMMISSION_MATERIAL_ID_MEIZHUANG = 13371;//美妆
    public static final int HIGH_COMMISSION_MATERIAL_ID_MUYING = 13374;//母婴
    public static final int HIGH_COMMISSION_MATERIAL_ID_NEIYI = 13373;//内衣
    public static final int HIGH_COMMISSION_MATERIAL_ID_JIAZHUANG = 13368;//家装
    public static final int HIGH_COMMISSION_MATERIAL_ID_YUNDONG = 13376;//运动
    //大额券
    public static final int BIG_DISCOUNT_MATERIAL_ID_ZONGHE = 9660;//综合
    //@}

    //推荐分类@{
    /**
     *         <item>热门</item>
     *         <item>大折扣</item>
     *         <item>女装</item>
     *         <item>男装</item>
     *         <item>鞋包</item>
     *         <item>美食</item>
     *         <item>美妆</item>
     *         <item>母婴</item>
     *         <item>数码家电</item>
     *         <item>内衣</item>
     *         <item>家装家纺</item>
     *         <item>户外运动</item>
     */
    public static final int TYPE_HOT = 0;
    public static final int TYPE_BIG_DISCOUNT = 1;
    public static final int TYPE_NVZHUANG = 2;
    public static final int TYPE_NANZHUANG = 3;
    public static final int TYPE_XIEBAO = 4;
    public static final int TYPE_MEISHI = 5;
    public static final int TYPE_MEIZHUANG = 6;
    public static final int TYPE_MUYING = 7;
    public static final int TYPE_SHUMA = 8;
    public static final int TYPE_NEIYI = 9;
    public static final int TYPE_JIAZHUANG = 10;
    public static final int TYPE_YUNDONG = 11;
    //@}
    //网络@{
    public static final String URL_HEADER = "http:";
    public static final String URL_SAFE_HEADER = "https:";//调用生成淘口令接口时，生成淘口令的链接头部需要使用https
    //@}
    public static final String HMAC = "hmac";
    public static final String MD5 = "md5";
    //SPKEY@{
    public static final String SPKEY_SEARCH_HISTORY = "spkey_search_history";
    //@}
    //客服账号@{
    public static final String SERVICE_QQ = "983934995";
    public static final String SERVICE_WECHAT = "android_jesse";
    //@}
    //APP全局变量@{
    public static final String IS_LOGIN = "is_login";
    public static final String NICKNAME = "user_nickname";
    public static final String AVATAR_URL = "avatar_url";
    public static final String OPEN_ID_TAOBAO = "open_id_taobao";
    //@}
}
