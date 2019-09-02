package com.android.jesse.huitao.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.FailBean;
import com.android.jesse.huitao.model.bean.GoodsListBean;
import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.adapter.CommonFragmentAdapter;
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

    private int pager = 1;
    private int size = 20;
    private CommonFragmentAdapter adapter;
    private GoodsListBean bannerBean;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataBeanList = (List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean>)msg.obj;
                if(!Utils.isListEmpty(dataBeanList)){
                    List<String> imageList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    for(int i=0;i<dataBeanList.size();i++){
                        imageList.add(Constant.URL_HEADER+dataBeanList.get(i).getPict_url());
                        titleList.add(Utils.getBannerTitle(dataBeanList.get(i)));
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
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setOnBannerListener(onBannerListener);

        srl_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBannerData();
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
            fragments.add(typesFragment);
        }
        adapter = new CommonFragmentAdapter(fragments,getChildFragmentManager());
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
        for(int i=0;i<tabStrArr.length;i++){
            tab_layout.getTabAt(i).setText(tabStrArr[i]);
        }
    }

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
            ToastUtil.shortShow("点击了 : "+position);
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
