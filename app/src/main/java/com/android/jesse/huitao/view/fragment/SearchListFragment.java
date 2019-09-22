package com.android.jesse.huitao.view.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.SearchListBean;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.SearchActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.android.jesse.huitao.view.adapter.SearchListRecyclerAdapter;
import com.android.jesse.huitao.view.custom.OffsetRecyclerDivider;
import com.blankj.utilcode.util.SizeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.android.jesse.huitao.utils.Utils.Conditions;

/**
 * @Description: 商品列表页面
 * @author: zhangshihao
 * @date: 2019/9/3
 */
public class SearchListFragment extends BaseFragment {

    private static final String TAG = SearchListFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srl_refresh;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;

    private int size = 20;//每页数据条数
    private int page = 1;//页码数
    private List<SearchListBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> dataBeanList;
    private SearchListRecyclerAdapter adapter;
    private RequestHelper<SearchListBean> requestHelper;
    private Map<String,String> bussinessMap;
    private String lastContent;
    private String content;

    @Override
    protected int getLayoutId() {
        return R.layout.goods_list_fragment;
    }

    @Override
    protected void initEventAndData() {
        dataBeanList = new ArrayList<>();
        requestHelper = new RequestHelper<>();
        bussinessMap = new HashMap<>();
        adapter = new SearchListRecyclerAdapter(mContext,dataBeanList);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        OffsetRecyclerDivider divider = new OffsetRecyclerDivider();
        divider.setBottom(SizeUtils.dp2px(5));
        divider.setLeft(SizeUtils.dp2px(5));
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);
        srl_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                dataBeanList.clear();
                getData();
            }
        });
    }

    private void getData(){
        bussinessMap.put("adzone_id",Constant.ADZONE_ID);
        bussinessMap.put("page_no",page+"");
        bussinessMap.put("page_size",size+"");
        bussinessMap.put("platform",2+"");
        if(SearchActivity.condition != Conditions.HOT){
            bussinessMap.put("sort",Utils.getSort(SearchActivity.condition));
        }
        bussinessMap.put("q",content);
        bussinessMap.put("is_overseas",SearchActivity.is_overseas+"");
        bussinessMap.put("is_tmall",SearchActivity.is_tmall+"");
        bussinessMap.put("has_coupon",SearchActivity.has_coupon+"");
        bussinessMap.put("need_free_shipment","true");//包邮
//        bussinessMap.put("material_id",Constant.HIGH_COMMISSION_MATERIAL_ID_ZONGHE+"");
//        bussinessMap.put("include_good_rate",true+"");//好评率高于平均
//        bussinessMap.put("include_rfd_rate",true+"");//退款率低于平均
        requestHelper.request(Constant.GOODS_SEARCH,bussinessMap,onRequestListener,SearchListBean.class);
    }

    public void search(String content){
        if (TextUtils.isEmpty(content)) {
            return;
        }
        this.content = content;
        srl_refresh.autoRefresh();
        lastContent = content;
    }

    private RequestHelper.OnRequestListener<SearchListBean> onRequestListener = new RequestHelper.OnRequestListener<SearchListBean>() {
        @Override
        public void onError(String msg) {
            Utils.resetRefreshViewState(srl_refresh,false);
            Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
            tv_no_data.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        @Override
        public void onSuccess(SearchListBean resultBean) {
            LogUtil.e(TAG+" onSuccess : code = "+resultBean.getCode()+" ,errorResponse = "+resultBean.getError_response()+" ,subCode = "+resultBean.getSub_code());
            Utils.resetRefreshViewState(srl_refresh,resultBean.getTbk_dg_material_optional_response().getResult_list().getMap_data().size()>=size);
            recyclerView.setVisibility(View.VISIBLE);
            tv_no_data.setVisibility(View.GONE);
            dataBeanList.addAll(resultBean.getTbk_dg_material_optional_response().getResult_list().getMap_data());
            adapter.notifyDataSetChanged();
        }
    };

}
