package com.android.jesse.huitao.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.HighMoneyTranslateBean;
import com.android.jesse.huitao.utils.ApkInfoUtils;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.UpgradeUtils;
import com.android.jesse.huitao.view.activity.SearchActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 搜券页
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class SearchCouponsFragment extends BaseFragment {

    private static final String TAG = SearchCouponsFragment.class.getSimpleName();

    @BindView(R.id.et_search)
    EditText et_search;

    private RequestHelper requestHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.search_coupons_fragment;
    }

    @Override
    protected void initEventAndData() {
        requestHelper = new RequestHelper();

    }

    @OnClick({R.id.btn_search,R.id.et_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_search:
                if(et_search.getText() != null &&
                        !TextUtils.isEmpty(et_search.getText().toString()) &&
                        !TextUtils.isEmpty(et_search.getText().toString().trim())){
                    Map<String,String> params = new HashMap<>();
                    params.put("apkey",Constant.APKEY_MIAOYQ);
                    params.put("tpwdcode",et_search.getText().toString());
                    params.put("pid",Constant.PID_TAOBAO);
                    params.put("tbname",Constant.USER_NAME_TAOBAO);
                    params.put("tpwd","1");//生成淘口令
                    params.put("extsearch","1");//淘宝官方查不到券时去第三方平台查
                    params.put("hasiteminfo","1");//输出商品信息
                    requestHelper.request(Constant.BASE_URL_MIAOYQ,Constant.HIGH_MONEY_TRANSLATE_FROM_TKL,params,onRequestListener,HighMoneyTranslateBean.class);
                }
                break;
        }
    }

    private RequestHelper.OnRequestListener<HighMoneyTranslateBean> onRequestListener = new RequestHelper.OnRequestListener<HighMoneyTranslateBean>() {
        @Override
        public void onError(String msg) {
            LogUtil.d(TAG+" request failed : "+msg);
        }

        @Override
        public void onSuccess(HighMoneyTranslateBean resultBean) {
            LogUtil.d(TAG+" request success");
        }
    };

}
