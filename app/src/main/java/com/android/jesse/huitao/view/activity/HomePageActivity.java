package com.android.jesse.huitao.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.BaichuanUtils;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.SharedPreferencesUtil;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;
import com.android.jesse.huitao.view.custom.TabItemView;
import com.android.jesse.huitao.view.fragment.AboutUsFragment;
import com.android.jesse.huitao.view.fragment.RecommendFragment;
import com.android.jesse.huitao.view.fragment.SearchCouponsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 主页面
 * @author: zhangshihao
 * @date: 2019/8/19
 */
public class HomePageActivity extends BaseActivity {

    private static final String TAG = HomePageActivity.class.getSimpleName();

    @BindView(R.id.recommend_tab)
    TabItemView recommend_tab;
    @BindView(R.id.search_tab)
    TabItemView search_tab;
    @BindView(R.id.aboutus_tab)
    TabItemView aboutus_tab;

    private List<TabItemView> tabList;
    private final int RECOMMEND_TAB_INDEX = 0;
    private final int SEARCH_COUPONS_TAB_INDEX = 1;
    private final int ABOUT_US_TAB_INDEX = 2;
    private int currentSelectIndex = RECOMMEND_TAB_INDEX;//默认选中项目序号
    private FragmentManager fragmentManager;

    private RecommendFragment recommendFragment;
    private SearchCouponsFragment searchCouponsFragment;
    private AboutUsFragment aboutUsFragment;
    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final int REQUEST_PERMISSIONS = 110;
    private Dialog gotoTaobaoDialog;
    private Dialog guideDialog;

    @Override
    protected int getLayout() {
        return R.layout.home_page_activity;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getBooleanExtra(Constant.FROM_NOTIFICATION, false)) {
            gotoTaobaoDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "提示", "淘口令复制成功，是否现在进入淘宝？", "现在就去", "稍等",
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
                                    gotoTaobaoDialog.dismiss();
                                    break;
                                case R.id.tv_negative:
                                    gotoTaobaoDialog.dismiss();
                                    break;
                            }
                        }
                    });
        }
    }

    @TargetApi(23)
    @Override
    protected void initEventAndData() {
        if(!SharedPreferencesUtil.getBooleanDate(Constant.SPKEY_NOT_FIRST_INTO_HOMEPAGE)){
            guideDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "新手指南", "找到想要购买的商品，点击“领券”后跳转淘宝，即可使用优惠券买下它了~", "知道了", "待会儿问客服", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferencesUtil.setBooleanDate(Constant.SPKEY_NOT_FIRST_INTO_HOMEPAGE,true);
                    switch (v.getId()){
                        case R.id.tv_positive:
                            guideDialog.dismiss();
                            break;
                        case R.id.tv_negative:
                            guideDialog.dismiss();
                            break;
                    }
                }
            });
        }
        if (getIntent() != null && getIntent().getBooleanExtra(Constant.FROM_NOTIFICATION, false)) {
            gotoTaobaoDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "提示", "淘口令复制成功，是否现在进入淘宝？", "现在就去", "稍等",
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
                                    gotoTaobaoDialog.dismiss();
                                    break;
                                case R.id.tv_negative:
                                    gotoTaobaoDialog.dismiss();
                                    break;
                            }
                        }
                    });
        }
        //初始化变量
        fragmentManager = getSupportFragmentManager();
        tabList = new ArrayList<>();
        tabList.add(recommend_tab);
        tabList.add(search_tab);
        tabList.add(aboutus_tab);
        //设置初始fragment
        if (recommendFragment == null) {
            recommendFragment = new RecommendFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_content, recommendFragment).commit();
        //设置监听
        recommend_tab.setOnClickListener(onTabClickListener);
        search_tab.setOnClickListener(onTabClickListener);
        aboutus_tab.setOnClickListener(onTabClickListener);
        if (Utils.isHigherThanM()) {
            if (!checkPermissions()) {
                requestPermissions(permissions, REQUEST_PERMISSIONS);
            }
        }
    }

    private boolean checkPermissions() {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public View.OnClickListener onTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TabItemView) {
                if (currentSelectIndex == ((TabItemView) v).getIndex()) {
                    return;
                }
                currentSelectIndex = ((TabItemView) v).getIndex();
                switch (((TabItemView) v).getIndex()) {
                    case RECOMMEND_TAB_INDEX:
                        recommend_tab.setSelect(!recommend_tab.isSelect());
                        //切换fragment
                        if (recommendFragment == null) {
                            recommendFragment = new RecommendFragment();
                        }
                        fragmentManager.beginTransaction().replace(R.id.fl_content, recommendFragment).commit();
                        break;
                    case SEARCH_COUPONS_TAB_INDEX:
                        search_tab.setSelect(!search_tab.isSelect());
                        //切换fragment
                        if (searchCouponsFragment == null) {
                            searchCouponsFragment = new SearchCouponsFragment();
                        }
                        fragmentManager.beginTransaction().replace(R.id.fl_content, searchCouponsFragment).commit();
                        break;
                    case ABOUT_US_TAB_INDEX:
                        aboutus_tab.setSelect(!aboutus_tab.isSelect());
                        //切换fragment
                        if (aboutUsFragment == null) {
                            aboutUsFragment = new AboutUsFragment();
                        }
                        fragmentManager.beginTransaction().replace(R.id.fl_content, aboutUsFragment).commit();
                        break;
                }
                resetSelectState();
            }
        }
    };

    /**
     * 除当前序号对应的tab和中间凸出的tab外，
     * 其他tab都设置为未选中状态
     */
    private void resetSelectState() {
        for (int i = 0; i < tabList.size(); i++) {
            if (i == currentSelectIndex) {
                continue;
            }
            tabList.get(i).setSelect(false);
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, REQUEST_PERMISSIONS);
                }
            }
        }
    }

    private long lastMills, currentMills;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            currentMills = System.currentTimeMillis();
            if (lastMills > 0 && currentMills - lastMills <= 1500) {
                finish();
                return true;
            } else {
                Toast.makeText(mContext, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                lastMills = currentMills;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lastMills = 0;
                    }
                }, 1500);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
