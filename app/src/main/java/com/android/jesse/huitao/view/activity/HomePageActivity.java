package com.android.jesse.huitao.view.activity;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TabHost;

import com.android.jesse.huitao.R;
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
    private int currentSelectIndex = SEARCH_COUPONS_TAB_INDEX;//默认选中项目序号
    private FragmentManager fragmentManager;

    private RecommendFragment recommendFragment;
    private SearchCouponsFragment searchCouponsFragment;
    private AboutUsFragment aboutUsFragment;

    @Override
    protected int getLayout() {
        return R.layout.home_page_activity;
    }

    @Override
    protected void initEventAndData() {
        //初始化变量
        fragmentManager = getSupportFragmentManager();
        tabList = new ArrayList<>();
        tabList.add(recommend_tab);
        tabList.add(search_tab);
        tabList.add(aboutus_tab);
        //设置初始fragment
        if(searchCouponsFragment == null){
            searchCouponsFragment = new SearchCouponsFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_content, searchCouponsFragment).commit();
        //设置监听
        recommend_tab.setOnClickListener(onTabClickListener);
        search_tab.setOnClickListener(onTabClickListener);
        aboutus_tab.setOnClickListener(onTabClickListener);
    }

    public View.OnClickListener onTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TabItemView) {
                if (currentSelectIndex == ((TabItemView) v).getIndex()) {
                    return;
                }
                currentSelectIndex = ((TabItemView) v).getIndex();
                switch (((TabItemView) v).getIndex()){
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

}
