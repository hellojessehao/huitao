package com.android.jesse.huitao.view.fragment;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.GoodsListBean;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.adapter.TypesFragmentAdapter;
import com.android.jesse.huitao.view.custom.RecycleViewDivider;
import com.blankj.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Description: 主页各分类通用fragment
 * @author: zhangshihao
 * @date: 2019/8/29
 */
public class TypesFragment extends BaseFragment {

    private static final String TAG = TypesFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;

    private int type = Constant.TYPE_HOT;
    private RequestHelper<GoodsListBean> requestHelper;
    private RequestHelper.OnRequestListener<GoodsListBean> onRequestListener;
    private Map<String,String> bussinessMap;
    private int material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_ZONGHE;
    private TypesFragmentAdapter adapter;
    private List<GoodsListBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataBeanList;
    private int pageSize = 20;
    private int page = 1;
    private int position;

    public static TypesFragment getInstance(int type){
        TypesFragment typesFragment = new TypesFragment();
        typesFragment.setType(type);
        return typesFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.types_fragment;
    }

    @Override
    protected void initEventAndData() {
        dataBeanList = new ArrayList<>();
        adapter = new TypesFragmentAdapter(dataBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext,LinearLayoutManager.VERTICAL,SizeUtils.dp2px(1),
                getResources().getColor(R.color.color_most_shallow)));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(onScrollChangeListener);

        requestHelper = new RequestHelper<>();
        onRequestListener = new RequestHelper.OnRequestListener<GoodsListBean>() {
            @Override
            public void onError(String msg) {
                tv_no_data.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(GoodsListBean resultBean) {
                recyclerView.setVisibility(View.VISIBLE);
                tv_no_data.setVisibility(View.GONE);
                dataBeanList.addAll(resultBean.getTbk_dg_optimus_material_response().getResult_list().getMap_data());
                adapter.notifyDataSetChanged();
                //发送广播给主页面，提示数据加载完成
                Intent intent = new Intent(Constant.ACTION_RECOMMEND_LOADDATA_COMPLETE);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        };
        bussinessMap = new HashMap<>();

        getData();
    }

    private RecyclerView.OnScrollListener onScrollChangeListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(onRecyclerScrollListener != null){
                onRecyclerScrollListener.onRecyclerScroll(recyclerView,dx,dy);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

    };

    /**
     * 根据type请求相关数据
     *
     *     public static final int TYPE_HOT = 0;
     *     public static final int TYPE_BIG_DISCOUNT = 1;
     *     public static final int TYPE_NVZHUANG = 2;
     *     public static final int TYPE_NANZHUANG = 3;
     *     public static final int TYPE_XIEBAO = 4;
     *     public static final int TYPE_MEISHI = 5;
     *     public static final int TYPE_MEIZHUANG = 6;
     *     public static final int TYPE_MUYING = 7;
     *     public static final int TYPE_SHUMA = 8;
     *     public static final int TYPE_NEIYI = 9;
     *     public static final int TYPE_JIAZHUANG = 10;
     *     public static final int TYPE_YUNDONG = 11;
     */
    private void getData(){
        switch (type){
            case Constant.TYPE_HOT:
                material_id = Constant.HAOQUAN_MATERIAL_ID_ZONGHE;
                break;
            case Constant.TYPE_BIG_DISCOUNT:
                material_id = Constant.BIG_DISCOUNT_MATERIAL_ID_ZONGHE;
                break;
            case Constant.TYPE_NVZHUANG:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_NVZHUANG;
                break;
            case Constant.TYPE_NANZHUANG:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_NANZHUANG;
                break;
            case Constant.TYPE_XIEBAO:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_XIEBAO;
                break;
            case Constant.TYPE_MEISHI:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_MEISHI;
                break;
            case Constant.TYPE_MEIZHUANG:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_MEIZHUANG;
                break;
            case Constant.TYPE_MUYING:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_MUYING;
                break;
            case Constant.TYPE_SHUMA:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_SHUMA;
                break;
            case Constant.TYPE_NEIYI:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_NEIYI;
                break;
            case Constant.TYPE_JIAZHUANG:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_JIAZHUANG;
                break;
            case Constant.TYPE_YUNDONG:
                material_id = Constant.HIGH_COMMISSION_MATERIAL_ID_YUNDONG;
                break;
        }
        bussinessMap.put("adzone_id",Constant.ADZONE_ID);
        bussinessMap.put("page_no",page+"");
        bussinessMap.put("page_size",pageSize+"");
        bussinessMap.put("platform",2+"");
        bussinessMap.put("material_id",material_id+"");
        LogUtil.i(TAG+" material_id = "+material_id);
        requestHelper.request(Constant.GOODS_LIST_GET,bussinessMap,onRequestListener,GoodsListBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_RECCOMEND_REFRESH);
        intentFilter.addAction(Constant.ACTION_RECOMMEND_LOADMORE);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(refreshLoadmoreReceiver,intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(refreshLoadmoreReceiver);
    }

    private BroadcastReceiver refreshLoadmoreReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //只加载当前显示的fragment
            int position = intent.getIntExtra("currentPosition",0);
            if(position != type){
                return;
            }
            if(intent.getAction().equals(Constant.ACTION_RECCOMEND_REFRESH)){
                page = 1;
                dataBeanList.clear();
                getData();
            }else if(intent.getAction().equals(Constant.ACTION_RECOMMEND_LOADMORE)){
                page++;
                getData();
            }
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public interface OnRecyclerScrollListener{
        void onRecyclerScroll(@NonNull RecyclerView recyclerView, int dx, int dy);
    }

    private OnRecyclerScrollListener onRecyclerScrollListener;

    public OnRecyclerScrollListener getOnRecyclerScrollListener() {
        return onRecyclerScrollListener;
    }

    public void setOnRecyclerScrollListener(OnRecyclerScrollListener onRecyclerScrollListener) {
        this.onRecyclerScrollListener = onRecyclerScrollListener;
    }
}
