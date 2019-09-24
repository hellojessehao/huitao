package com.android.jesse.huitao.view.adapter;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.SearchListBean;
import com.android.jesse.huitao.model.bean.TklBean;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.GoodsDetailActivity;
import com.blankj.utilcode.util.SizeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 搜索列表GridView适配器
 * @author: zhangshihao
 * @date: 2019/9/9
 */
public class SearchListRecyclerAdapter extends RecyclerView.Adapter<SearchListRecyclerAdapter.ViewHolder> {

    private static final String TAG = SearchListRecyclerAdapter.class.getSimpleName();

    private List<SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> dataBeanList;
    private Context mContext;
    private ClipboardManager clipboardManager;
    private Dialog useCouponDialog;

    public SearchListRecyclerAdapter(Context mContext, List<SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.search_list_grid_adapter,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean dataBean = dataBeanList.get(position);
        GlideUtil.getInstance().loadImg(mContext,dataBean.getPict_url(),viewHolder.iv_cover);
        viewHolder.tv_title.setText(dataBean.getTitle());
        viewHolder.tv_shop_name.setText(dataBean.getShop_title());
        //制造中划线
        viewHolder.tv_ori_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        viewHolder.tv_ori_price.setText(dataBean.getCoupon_start_fee() + "");
        viewHolder.tv_selled_count.setText(dataBean.getVolume() + "人已买");

        viewHolder.tv_discount_price.setText("￥"+Utils.getDiscountPrice(dataBean.getCoupon_start_fee(),dataBean.getCoupon_amount()));
        viewHolder.tv_coupon_value.setText(dataBean.getCoupon_amount() + "元");

        viewHolder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,GoodsDetailActivity.class);
                intent.putExtra("dataBean",dataBean);
                mContext.startActivity(intent);
            }
        });
        viewHolder.ll_get_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
//                params.put("user_id",Constant.TB_USER_ID); 不确定是不是这个userid，暂时不传
                params.put("text", dataBean.getTitle() + "\n" + dataBean.getItem_description());
                params.put("url", Constant.URL_SAFE_HEADER + dataBean.getCoupon_share_url());
                params.put("logo", dataBean.getPict_url());
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList==null?0:dataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView iv_cover;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_shop_name)
        TextView tv_shop_name;
        @BindView(R.id.tv_ori_price)
        TextView tv_ori_price;
        @BindView(R.id.tv_selled_count)
        TextView tv_selled_count;
        @BindView(R.id.tv_discount_price)
        TextView tv_discount_price;
        @BindView(R.id.tv_coupon_value)
        TextView tv_coupon_value;
        @BindView(R.id.ll_get_coupon)
        LinearLayout ll_get_coupon;
        @BindView(R.id.rl_container)
        RelativeLayout rl_container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SizeUtils.dp2px(280));
            itemView.setLayoutParams(layoutParams);
        }
    }

}
