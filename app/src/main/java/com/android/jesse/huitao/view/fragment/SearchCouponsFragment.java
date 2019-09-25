package com.android.jesse.huitao.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.utils.ApkInfoUtils;
import com.android.jesse.huitao.utils.HttpUtils;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.UpgradeUtils;
import com.android.jesse.huitao.view.activity.SearchActivity;
import com.android.jesse.huitao.view.activity.base.BaseFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 搜券页
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class SearchCouponsFragment extends BaseFragment {

    private static final String TAG = SearchCouponsFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.search_coupons_fragment;
    }

    @Override
    protected void initEventAndData() {
        //检查版本更新
        UpgradeUtils.checkUpgrade(mContext);
    }

    @OnClick({R.id.btn_search,R.id.et_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_search:
            case R.id.et_search:
//                if(valide()){
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try{
//                                String result = HttpUtils.convertTkl(et_search.getText().toString());
//                                LogUtil.d(TAG+" result : "+result);
//                            }catch (IOException ioe){
//                                ioe.printStackTrace();
//                                LogUtil.e(TAG+" "+ioe.toString());
//                            }
//                        }
//                    }).start();
//                }
//                if(valide()){
//                    Intent intent = new Intent(mContext,SearchActivity.class);
//                    intent.putExtra("content",et_search.getText().toString());
//                    startActivity(intent);
//                }

                startActivity(new Intent(mContext,SearchActivity.class));
                break;
        }
    }

//    private boolean valide(){
//        if(TextUtils.isEmpty(et_search.getText().toString())){
//            ToastUtil.shortShow("无搜索内容");
//            return false;
//        }
//        return true;
//    }

}
