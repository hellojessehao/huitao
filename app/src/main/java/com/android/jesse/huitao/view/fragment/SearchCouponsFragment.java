package com.android.jesse.huitao.view.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.model.bean.CouponBean;
import com.android.jesse.huitao.net.bean.NetRetBean;
import com.android.jesse.huitao.net.core.common.RequestType;
import com.android.jesse.huitao.net.helper.NetHelper;
import com.android.jesse.huitao.net.listener.async.NetSingleBeanListener;
import com.android.jesse.huitao.net.listener.common.CallbackCode;
import com.android.jesse.huitao.net.url.UrlParse;
import com.android.jesse.huitao.utils.DateUtils;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.OkgoUtils;
import com.android.jesse.huitao.utils.TaobaoUtils;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseFragment;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import butterknife.BindView;
import butterknife.OnClick;

import static com.taobao.api.Constants.CHARSET_UTF8;
import static com.taobao.api.Constants.CONTENT_ENCODING_GZIP;

/**
 * @Description: 搜券页
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class SearchCouponsFragment extends BaseFragment {

    private static final String TAG = SearchCouponsFragment.class.getSimpleName();

    @BindView(R.id.et_search)
    EditText et_search;

    @Override
    protected int getLayoutId() {
        return R.layout.search_coupons_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.btn_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_search:
                if(valide()){
//                    try{
//                        UrlParse paramParse = new UrlParse();
//                        Map<String, String> params = new HashMap<>();
//                        params.put("app_key", Constant.APPKEY_TAOBAO);
//                        params.put("format", "json");
//                        params.put("me",et_search.getText().toString());
//                        params.put("method", Constant.POPULARIZE_COUPONS_GET);
//                        params.put("sign_method", Constant.MD5);
//                        params.put("timestamp", DateUtils.getCurrentFormatedDate("yyyy-MM-dd HH:mm:ss"));
//                        params.put("v", "2.0");
//                        params.put("sign",Utils.signTopRequest(params,Constant.APPSECRET_TAOBAO,Constant.MD5));
//                        paramParse.putAllValue(params);
//                        paramParse.putValue("sign",Utils.signTopRequest(params,Constant.APPSECRET_TAOBAO,Constant.MD5));
//                        LogUtil.d(TAG+" "+Utils.getTaobaoUrl(params));
//                        UrlParse urlParse = new UrlParse(Utils.getTaobaoUrl(params));
//                        NetHelper.create()
//                                .url(urlParse.toString())
//                                .request(RequestType.REQUEST_TYPE_GET, new NetSingleBeanListener<CouponBean>() {
//
//                                    @Override
//                                    protected void onCommon() {
//                                        super.onCommon();
//                                    }
//
//                                    @Override
//                                    protected void onSuccess(CouponBean couponBean) {
//                                        LogUtil.d(TAG+" couponBean : "+couponBean.toString());
//                                    }
//
//                                    @Override
//                                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
//                                        Utils.showNetErrorMsg(netRetBean);
//                                    }
//                                });
//                    }catch (IOException ioe){
//                        ioe.printStackTrace();
//                        LogUtil.e(TAG+" "+ioe.toString());
//                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                String result = HttpUtils.getCouponInfo();
                                LogUtil.d(TAG+" result : "+result);
                            }catch (IOException ioe){
                                ioe.printStackTrace();
                                LogUtil.e(TAG+" "+ioe.toString());
                            }
                        }
                    }).start();
                }
                break;
        }
    }

    private boolean valide(){
        if(TextUtils.isEmpty(et_search.getText().toString())){
            ToastUtil.shortShow("无搜索内容");
            return false;
        }
        return true;
    }

}
