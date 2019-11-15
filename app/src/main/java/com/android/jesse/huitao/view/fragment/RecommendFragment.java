package com.android.jesse.huitao.view.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.FailBean;
import com.android.jesse.huitao.model.bean.GoodsListBean;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.UpgradeUtils;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.GoodsDetailActivity;
import com.android.jesse.huitao.view.activity.SearchActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.adapter.CommonFragmentAdapter;
import com.android.jesse.huitao.view.custom.ImageViewRoundRect;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 推荐商品页面
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class RecommendFragment extends BaseFragment {

    private static final String TAG = RecommendFragment.class.getSimpleName();

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srl_refresh;
    @BindView(R.id.ll_content_container)
    LinearLayout ll_content_container;
    @BindView(R.id.view_divider)
    View view_divider;
    @BindView(R.id.iv_logo)
    ImageViewRoundRect iv_logo;

    private int pager = 1;
    private int size = 20;
    private CommonFragmentAdapter adapter;
    private GoodsListBean bannerBean;
    List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> bannerDataBeanList;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                bannerDataBeanList = (List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean>)msg.obj;
                if(!Utils.isListEmpty(bannerDataBeanList)){
                    List<String> imageList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    for(int i=0;i<bannerDataBeanList.size();i++){
                        imageList.add(Constant.URL_HEADER+bannerDataBeanList.get(i).getPict_url());
                        titleList.add(Utils.getBannerTitle(bannerDataBeanList.get(i)));
                    }
                    mBanner.setImages(imageList);
                    mBanner.setBannerTitles(titleList);
                    mBanner.start();
                    Utils.resetRefreshViewState(srl_refresh,false);
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initEventAndData() {
//        iv_logo.setType(0);
//        GlideUtil.getInstance().loadOriImg(mContext,SharedPreferencesUtil.getStringDate(Constant.AVATAR_URL),iv_logo);
        iv_logo.setType(1);
        iv_logo.setRoundRadius(0);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setOnBannerListener(onBannerListener);

        srl_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_RECOMMEND_LOADMORE);
                intent.putExtra("currentPosition",view_pager.getCurrentItem());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBannerData();
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_RECCOMEND_REFRESH);
                intent.putExtra("currentPosition",view_pager.getCurrentItem());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });
        srl_refresh.autoRefresh();
        //填充tabLayout
        String[] tabStrArr = getResources().getStringArray(R.array.recommend_category_array);
        for(int i=0;i<tabStrArr.length;i++){
            tab_layout.addTab(tab_layout.newTab(),i==0);
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<tabStrArr.length;i++){
            TypesFragment typesFragment = TypesFragment.getInstance(i);
            typesFragment.setOnRecyclerScrollListener(onRecyclerScrollListener);
            fragments.add(typesFragment);
        }
        adapter = new CommonFragmentAdapter(fragments,getChildFragmentManager());
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
        for(int i=0;i<tabStrArr.length;i++){
            tab_layout.getTabAt(i).setText(tabStrArr[i]);
        }
        LinearLayout.LayoutParams pagerParams = (LinearLayout.LayoutParams) view_pager.getLayoutParams();
        pagerParams.height = ScreenUtils.getScreenHeight();
        view_pager.setLayoutParams(pagerParams);
        view_pager.addOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            //重置位移参数
            ll_content_container.scrollTo(0,0);
            totalScroll = 0;
            totalDy = 0;
            lastDy = 0;
            jumpNextUpCase = false;
            jumpNextLowCase = false;
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    int totalScroll;
    int totalDy;
    int lastDy;
    boolean jumpNextUpCase,jumpNextLowCase;//跳过下一次dy>0 ； 跳过下一次dy<0
    int topLine = -SizeUtils.dp2px(180);//最高偏移量(banner的高度)

    private TypesFragment.OnRecyclerScrollListener onRecyclerScrollListener = new TypesFragment.OnRecyclerScrollListener() {
        @Override
        public void onRecyclerScroll(@NonNull RecyclerView recyclerView, int dx, int dy) {
            lastDy = dy;
            totalDy += dy;
            if(jumpNextLowCase && dy < 0){
                jumpNextLowCase = false;
                return;
            }else if(jumpNextUpCase && dy > 0){
                jumpNextUpCase = false;
                return;
            }
            if(dy > 0 && totalScroll > topLine){
                totalScroll -= dy;
                if(totalScroll <= topLine){
                    totalScroll = topLine;
                }
                ll_content_container.scrollTo(0,-totalScroll);
                jumpNextLowCase = true;
            }else if(dy < 0 && totalScroll < 0 && Math.abs(totalDy) < -topLine){
                totalScroll -= dy;
                if(totalScroll > 0){
                    totalScroll = 0;
                }
                ll_content_container.scrollTo(0,-totalScroll);
                jumpNextUpCase = true;
            }
        }
    };

    private void getBannerData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = HttpUtils.getGoodsList(1, 5, Constant.HIGH_COMMISSION_MATERIAL_ID_ZONGHE);
                    LogUtil.i(TAG+" getBannerData : "+response);
                    bannerBean = new Gson().fromJson(response,GoodsListBean.class);
                    List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataBeanList = bannerBean.getTbk_dg_optimus_material_response().getResult_list().getMap_data();
                    mHandler.sendMessage(mHandler.obtainMessage(0,dataBeanList));
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    LogUtil.e(TAG+" getBannerData failed : "+ioe.toString());
                }catch (JsonSyntaxException jse){
                    LogUtil.e(TAG+" getBannerData failed : parse json error : "+jse.toString());
                }
            }
        }).start();
    }

    private OnBannerListener onBannerListener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            if(Utils.isListEmpty(bannerDataBeanList)){
                return;
            }
            GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean dataBean = bannerDataBeanList.get(position);
            Intent intent = new Intent(mContext,GoodsDetailActivity.class);
            intent.putExtra("dataBean",dataBean);
            mContext.startActivity(intent);
        }
    };

    /**
     * Banner自定义图片加载器
     */
    private class GlideImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtil.getInstance().loadImg(context,(String) path,imageView);
        }
    }

    @OnClick({R.id.view_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.view_search:
                ActivityUtils.startActivity(SearchActivity.class);
                break;
        }
    }

    private BroadcastReceiver loadDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constant.ACTION_RECOMMEND_LOADDATA_COMPLETE)){
                Utils.resetRefreshViewState(srl_refresh,true);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter(Constant.ACTION_RECOMMEND_LOADDATA_COMPLETE);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(loadDataReceiver,intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(loadDataReceiver);
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
