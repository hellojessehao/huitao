package com.android.jesse.huitao.view.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.GoodsDetailBean;
import com.android.jesse.huitao.model.bean.GoodsListBean;
import com.android.jesse.huitao.model.bean.RelativeGoodsBean;
import com.android.jesse.huitao.model.bean.SearchListBean;
import com.android.jesse.huitao.model.bean.TklBean;
import com.android.jesse.huitao.utils.DateUtils;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;
import com.android.jesse.huitao.view.adapter.RelativeGoodsRecyclerAdapter;
import com.android.jesse.huitao.view.custom.OffsetRecyclerDivider;
import com.android.jesse.huitao.view.fragment.RecommendFragment;
import com.blankj.utilcode.util.SizeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 商品详情页
 * @author: zhangshihao
 * @date: 2019/9/22 0022
 */
public class GoodsDetailActivity extends BaseActivity {

    private static final String TAG = GoodsDetailActivity.class.getSimpleName();

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_discount_price)
    TextView tv_discount_price;
    @BindView(R.id.tv_ori_price_text)
    TextView tv_ori_price_text;
    @BindView(R.id.tv_selled_count)
    TextView tv_selled_count;
    @BindView(R.id.tv_coupon_value)
    TextView tv_coupon_value;
    @BindView(R.id.tv_valide_date_start)
    TextView tv_valide_date_start;
    @BindView(R.id.tv_valide_date_end)
    TextView tv_valide_date_end;
    @BindView(R.id.tv_recommend_reason)
    TextView tv_recommend_reason;
    @BindView(R.id.tv_seller_name)
    TextView tv_seller_name;
    @BindView(R.id.tv_get_coupon)
    TextView tv_get_coupon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;

    private Object dataBean;
    private final int TYPE_GOODS_LIST = 0;
    private final int TYPE_SEARCH_LIST = 1;
    private int dataType = TYPE_GOODS_LIST;
    private GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean goodsListBean;
    private SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean searchListBean;
    private ClipboardManager clipboardManager;
    private Dialog useCouponDialog;
    private long itemId = -1;//商品id
    RequestHelper requestHelper;

    @Override
    protected int getLayout() {
        return R.layout.goods_detail_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true, mContext);
        requestHelper = new RequestHelper();
        if (getIntent() != null) {
            clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setImageLoader(new GlideImageLoader());
            mBanner.setOnBannerListener(onBannerListener);

            dataBean = getIntent().getSerializableExtra("dataBean");
            if(dataBean != null){
                if (dataBean instanceof GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean) {
                    dataType = TYPE_GOODS_LIST;
                    GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean mapDataBean = (GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean) dataBean;
                    goodsListBean = mapDataBean;
                    itemId = mapDataBean.getItem_id();

                    List<String> imageUrlList = new ArrayList<>();
                    for (int i = 0; i < mapDataBean.getSmall_images().getString().size(); i++) {
                        imageUrlList.add(Constant.URL_HEADER + mapDataBean.getSmall_images().getString().get(i));
                        LogUtil.i(TAG + " url : " + Constant.URL_HEADER + mapDataBean.getSmall_images().getString().get(i));
                    }
                    mBanner.setImages(imageUrlList);
                    mBanner.start();

                    tv_title.setText(mapDataBean.getTitle());
                    tv_discount_price.setText("￥" + Utils.getDiscountPrice(mapDataBean.getCoupon_start_fee(), mapDataBean.getCoupon_amount()));
                    SpannableString spannableString = new SpannableString("原价:￥" + mapDataBean.getCoupon_start_fee());
                    spannableString.setSpan(new StrikethroughSpan(), 0, tv_ori_price_text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    tv_ori_price_text.setText(spannableString);
                    tv_selled_count.setText(mapDataBean.getVolume() + "人已抢");
                    tv_coupon_value.setText("￥" + mapDataBean.getCoupon_amount());
                    String startTime = DateUtils.getFormatedDate(Long.parseLong(mapDataBean.getCoupon_start_time()), "yyyy-MM-dd");
                    String endTime = DateUtils.getFormatedDate(Long.parseLong(mapDataBean.getCoupon_end_time()), "yyyy-MM-dd");
                    tv_valide_date_start.setText(startTime);
                    tv_valide_date_end.setText(endTime);
                    tv_seller_name.setText(mapDataBean.getShop_title());
                    if (TextUtils.isEmpty(mapDataBean.getItem_description())) {
                        tv_recommend_reason.setVisibility(View.GONE);
                    } else {
                        SpannableString span = new SpannableString("推荐理由:" + mapDataBean.getItem_description());
                        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_subtext_subtitle)),0,5,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        tv_recommend_reason.setText(span);
                    }
                } else if (dataBean instanceof SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean) {
                    dataType = TYPE_SEARCH_LIST;
                    SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean mapDataBean = (SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean) dataBean;
                    searchListBean = mapDataBean;
                    itemId = mapDataBean.getItem_id();

                    mBanner.setImages(mapDataBean.getSmall_images().getString());
                    mBanner.start();

                    tv_title.setText(mapDataBean.getTitle());
                    tv_discount_price.setText("￥" + Utils.getDiscountPrice(mapDataBean.getCoupon_start_fee(), mapDataBean.getCoupon_amount()));
                    SpannableString spannableString = new SpannableString("原价:￥" + mapDataBean.getCoupon_start_fee());
                    spannableString.setSpan(new StrikethroughSpan(), 0, tv_ori_price_text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    tv_ori_price_text.setText(spannableString);
                    tv_selled_count.setText(mapDataBean.getVolume() + "人已抢");
                    tv_coupon_value.setText("￥" + mapDataBean.getCoupon_amount());
                    tv_valide_date_start.setText(mapDataBean.getCoupon_start_time());
                    tv_valide_date_end.setText(mapDataBean.getCoupon_end_time());
                    tv_seller_name.setText(mapDataBean.getShop_title());
                    if (TextUtils.isEmpty(mapDataBean.getItem_description())) {
                        tv_recommend_reason.setVisibility(View.GONE);
                    } else {
                        SpannableString span = new SpannableString("推荐理由:" + mapDataBean.getItem_description());
                        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_subtext_subtitle)),0,5,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        tv_recommend_reason.setText(span);
                    }
                }
                //获取关联推荐的商品
                LogUtil.i(TAG+" itemId = "+itemId);
                if(itemId >= 0){
                    Map<String,String> bussinessMap = new HashMap<>();
                    bussinessMap.put("fields","num_iid,title,pict_url,reserve_price,zk_final_price,item_url,volume,nick");
                    bussinessMap.put("num_iid",itemId+"");
                    bussinessMap.put("count","40");
                    bussinessMap.put("platform","2");

                    requestHelper.request(Constant.RELATIVE_RECOMMEND,bussinessMap,onGetRelativeGoodsListener,RelativeGoodsBean.class);
                }else{
                    LogUtil.e(TAG+" id < 0 ,无关联商品");
                }
            }else{
                itemId = getIntent().getLongExtra("itemId",-1);
                if(itemId >= 0){
                    //TODO:调用商品详情接口
                }else{
                    ToastUtil.shortShow("抱歉客官……没有找到该商品详情~\n去看看别的商品吧~");
                    tv_get_coupon.setEnabled(false);
                }
            }

        }
    }

    private RequestHelper.OnRequestListener<RelativeGoodsBean> onGetRelativeGoodsListener = new RequestHelper.OnRequestListener<RelativeGoodsBean>() {
        @Override
        public void onError(String msg) {
            tv_no_data.setVisibility(View.VISIBLE);
        }

        @Override
        public void onSuccess(RelativeGoodsBean resultBean) {
            if(resultBean == null){
                LogUtil.e(TAG+" get RelativeGoods : resultBean is null");
                return;
            }
            if(Utils.isListEmpty(resultBean.getTbk_item_recommend_get_response().getResults().getN_tbk_item())){
                LogUtil.e(TAG+" get RelativeGoods : dataBeanList is empty");
                return;
            }
            RelativeGoodsRecyclerAdapter adapter = new RelativeGoodsRecyclerAdapter(mContext,resultBean.getTbk_item_recommend_get_response().getResults().getN_tbk_item());
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            OffsetRecyclerDivider divider = new OffsetRecyclerDivider();
            divider.setBottom(SizeUtils.dp2px(5));
            divider.setLeft(SizeUtils.dp2px(5));
            recyclerView.addItemDecoration(divider);
            recyclerView.setAdapter(adapter);
        }
    };

    private OnBannerListener onBannerListener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            Intent intent = new Intent(mContext,PicsBrowseActivity.class);
            intent.putExtra(PicsBrowseActivity.INDEX_KEY,position);
            switch (dataType){
                case TYPE_GOODS_LIST:
                    List<String> imageUrlList = new ArrayList<>();
                    for (int i = 0; i < goodsListBean.getSmall_images().getString().size(); i++) {
                        imageUrlList.add(Constant.URL_HEADER + goodsListBean.getSmall_images().getString().get(i));
                        LogUtil.i(TAG + " url : " + Constant.URL_HEADER + goodsListBean.getSmall_images().getString().get(i));
                    }
                    intent.putExtra(PicsBrowseActivity.LIST_KEY,new ArrayList<>(imageUrlList));
                    break;
                case TYPE_SEARCH_LIST:
                    intent.putExtra(PicsBrowseActivity.LIST_KEY,new ArrayList<>(searchListBean.getSmall_images().getString()));
                    break;
            }
            startActivity(intent);
        }
    };

    /**
     * Banner自定义图片加载器
     */
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtil.getInstance().loadImg(context, (String) path, imageView);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_get_coupon})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_coupon:
                switch (dataType){
                    case TYPE_GOODS_LIST:
                        if(goodsListBean == null){
                            break;
                        }
                        Map<String, String> params = new HashMap<>();
//                params.put("user_id",Constant.TB_USER_ID); 不确定是不是这个userid，暂时不传
                        params.put("text", goodsListBean.getTitle() + "\n" + goodsListBean.getItem_description());
                        params.put("url", Constant.URL_SAFE_HEADER + goodsListBean.getCoupon_share_url());
                        params.put("logo", goodsListBean.getPict_url());
                        new RequestHelper<TklBean>().request(Constant.CREATE_TAO_WORDS, params, new RequestHelper.OnRequestListener<TklBean>() {
                            @Override
                            public void onError(String msg) {
                                ToastUtil.shortShow("淘口令获取失败，请重试~");
                            }

                            @Override
                            public void onSuccess(TklBean resultBean) {
                                if (clipboardManager == null) {
                                    clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                }
                                ClipData clipData = ClipData.newPlainText("coupons", resultBean.getTbk_tpwd_create_response().getData().getModel());
                                clipboardManager.setPrimaryClip(clipData);
                                useCouponDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "优惠券复制成功", "打开淘宝即可使用优惠券，是否现在进入淘宝？", "现在就去", "稍等",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                switch (v.getId()) {
                                                    case R.id.tv_positive:
                                                        PackageInfo packageInfo;
                                                        try {
                                                            packageInfo = mContext.getPackageManager().getPackageInfo(
                                                                    "com.taobao.taobao", 0);
                                                        } catch (PackageManager.NameNotFoundException e) {
                                                            packageInfo = null;
                                                            e.printStackTrace();
                                                        }
                                                        if (packageInfo == null) {
                                                            ToastUtil.shortShow("请客官先安装淘宝~");
                                                        } else {
                                                            Intent intent = new Intent();
                                                            //com.taobao.taobao/com.taobao.tao.TBMainActivity 淘宝首页地址
                                                            intent.setClassName("com.taobao.taobao", "com.taobao.tao.TBMainActivity");
                                                            mContext.startActivity(intent);
                                                            ToastUtil.shortShow("正在前往淘宝。。。");
                                                        }
                                                        useCouponDialog.dismiss();
                                                        break;
                                                    case R.id.tv_negative:
                                                        useCouponDialog.dismiss();
                                                        break;
                                                }
                                            }
                                        });
                            }
                        }, TklBean.class);
                        break;
                    case TYPE_SEARCH_LIST:
                        if(searchListBean == null){
                            break;
                        }
                        Map<String, String> searchParams = new HashMap<>();
//                searchParams.put("user_id",Constant.TB_USER_ID); 不确定是不是这个userid，暂时不传
                        searchParams.put("text", searchListBean.getTitle() + "\n" + searchListBean.getItem_description());
                        searchParams.put("url", Constant.URL_SAFE_HEADER + searchListBean.getCoupon_share_url());
                        searchParams.put("logo", searchListBean.getPict_url());
                        new RequestHelper<TklBean>().request(Constant.CREATE_TAO_WORDS, searchParams, new RequestHelper.OnRequestListener<TklBean>() {
                            @Override
                            public void onError(String msg) {
                                ToastUtil.shortShow("淘口令获取失败，请重试~");
                            }

                            @Override
                            public void onSuccess(TklBean resultBean) {
                                if (clipboardManager == null) {
                                    clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                }
                                ClipData clipData = ClipData.newPlainText("coupons", resultBean.getTbk_tpwd_create_response().getData().getModel());
                                clipboardManager.setPrimaryClip(clipData);
                                useCouponDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "优惠券复制成功", "打开淘宝即可使用优惠券，是否现在进入淘宝？", "现在就去", "稍等",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                switch (v.getId()) {
                                                    case R.id.tv_positive:
                                                        PackageInfo packageInfo;
                                                        try {
                                                            packageInfo = mContext.getPackageManager().getPackageInfo(
                                                                    "com.taobao.taobao", 0);
                                                        } catch (PackageManager.NameNotFoundException e) {
                                                            packageInfo = null;
                                                            e.printStackTrace();
                                                        }
                                                        if (packageInfo == null) {
                                                            ToastUtil.shortShow("请客官先安装淘宝~");
                                                        } else {
                                                            Intent intent = new Intent();
                                                            //com.taobao.taobao/com.taobao.tao.TBMainActivity 淘宝首页地址
                                                            intent.setClassName("com.taobao.taobao", "com.taobao.tao.TBMainActivity");
                                                            mContext.startActivity(intent);
                                                            ToastUtil.shortShow("正在前往淘宝。。。");
                                                        }
                                                        useCouponDialog.dismiss();
                                                        break;
                                                    case R.id.tv_negative:
                                                        useCouponDialog.dismiss();
                                                        break;
                                                }
                                            }
                                        });
                            }
                        }, TklBean.class);
                        break;
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

}
