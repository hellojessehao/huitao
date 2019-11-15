package com.android.jesse.huitao.view.fragment;

import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.AllConvertTklBean;
import com.android.jesse.huitao.model.bean.HighMoneyTranslateBean;
import com.android.jesse.huitao.utils.ApkInfoUtils;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.RequestHelper;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
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
    @BindView(R.id.switch_set_homepage)
    Switch switch_set_homepage;
    @BindView(R.id.switch_search_clipboard)
    Switch switch_search_clipboard;

    private RequestHelper requestHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.search_coupons_fragment;
    }

    @Override
    protected void initEventAndData() {
        requestHelper = new RequestHelper();

        switch_set_homepage.setChecked(SharedPreferencesUtil.getBooleanDate(Constant.KEY_IS_SET_SEARCH_HOMEPAGE));
        switch_search_clipboard.setChecked(SharedPreferencesUtil.getBooleanDate(Constant.KEY_IS_SEARCH_CLIPBOARD));
        switch_set_homepage.setOnCheckedChangeListener(onCheckedChangeListener);
        switch_search_clipboard.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @OnClick({R.id.btn_search,R.id.et_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_search:
                if(et_search.getText() != null &&
                        !TextUtils.isEmpty(et_search.getText().toString()) &&
                        !TextUtils.isEmpty(et_search.getText().toString().trim())){
                    DialogUtil.showWaitDialog(mContext,"玩命加载中……");
                    Map<String,String> params = new HashMap<>();
                    params.put("apikey",Constant.APIKEY_DINGDX);
                    params.put("content",et_search.getText().toString());
                    params.put("tpwd","true");
                    params.put("itemInfo","true");
                    requestHelper.request(Constant.BASE_URL_DINGDX,Constant.ALL_TKL_CONVERT_HIGH_MONEY_API,params,onRequestListener,AllConvertTklBean.class);
                }
                break;
        }
    }

    private Switch.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.switch_set_homepage:
                    if(isChecked){
                        SharedPreferencesUtil.setBooleanDate(Constant.KEY_IS_SET_SEARCH_HOMEPAGE,true);
                        LogUtil.d(TAG+" KEY_IS_SET_SEARCH_HOMEPAGE : true ");
                    }else{
                        SharedPreferencesUtil.setBooleanDate(Constant.KEY_IS_SET_SEARCH_HOMEPAGE,false);
                        LogUtil.d(TAG+" KEY_IS_SET_SEARCH_HOMEPAGE : false ");
                    }
                    break;
                case R.id.switch_search_clipboard:
                    if(isChecked){
                        SharedPreferencesUtil.setBooleanDate(Constant.KEY_IS_SEARCH_CLIPBOARD,true);
                        LogUtil.d(TAG+" KEY_IS_SEARCH_CLIPBOARD : true ");
                    }else{
                        SharedPreferencesUtil.setBooleanDate(Constant.KEY_IS_SEARCH_CLIPBOARD,false);
                        LogUtil.d(TAG+" KEY_IS_SEARCH_CLIPBOARD : false ");
                    }
                    break;
            }
        }
    };

    private RequestHelper.OnRequestListener<AllConvertTklBean> onRequestListener = new RequestHelper.OnRequestListener<AllConvertTklBean>() {
        @Override
        public void onError(String msg) {
            LogUtil.e(TAG+" msg : "+msg);
            DialogUtil.dismissWaitDialog();
            try{
                Looper.loop();
                ToastUtil.shortShow("啊噢~没有找到优惠券~");
                Looper.loop();
            }catch (Exception e){
                e.printStackTrace();
                ToastUtil.shortShow("啊噢~没有找到优惠券~");
            }
        }

        @Override
        public void onSuccess(AllConvertTklBean resultBean) {
            DialogUtil.dismissWaitDialog();
            if(resultBean.getCode() != 200){
                ToastUtil.show(resultBean.getMsg());
                return;
            }
            DialogUtil.showTklDisplayDialog(mContext,resultBean);
        }
    };

}
