package com.android.jesse.huitao.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.CacheHelper;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;
import com.android.jesse.huitao.view.adapter.CommonFragmentAdapter;
import com.android.jesse.huitao.view.custom.flowlayout.FlowLayout;
import com.android.jesse.huitao.view.custom.flowlayout.TagAdapter;
import com.android.jesse.huitao.view.custom.flowlayout.TagFlowLayout;
import com.android.jesse.huitao.view.fragment.SearchListFragment;
import static com.android.jesse.huitao.utils.Utils.Conditions;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 搜索页面
 * @author: zhangshihao
 * @date: 2019/9/3
 */
public class SearchActivity extends BaseActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.fl_search_history)
    TagFlowLayout fl_search_history;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rl_search_history_container)
    RelativeLayout rl_search_history_container;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rl_pager_container)
    RelativeLayout rl_pager_container;
    @BindView(R.id.ll_filter_container)
    LinearLayout ll_filter_container;
    @BindView(R.id.switch_only_coupon_goods)
    Switch switch_only_coupon_goods;
    @BindView(R.id.tv_filter_hot)
    TextView tv_filter_hot;
    @BindView(R.id.tv_selled_count_text)
    TextView tv_selled_count_text;
    @BindView(R.id.iv_up_arrow_selled_count)
    ImageView iv_up_arrow_selled_count;
    @BindView(R.id.iv_down_arrowselled_count)
    ImageView iv_down_arrowselled_count;
    @BindView(R.id.tv_price_text)
    TextView tv_price_text;
    @BindView(R.id.iv_up_arrow_price)
    ImageView iv_up_arrow_price;
    @BindView(R.id.iv_down_arrow_price)
    ImageView iv_down_arrow_price;
    @BindView(R.id.btn_search)
    TextView btn_search;

    private List<String> historyList;
    private TagAdapter tagAdapter;
    private CommonFragmentAdapter fragmentAdapter;
    private List<Fragment> fragments;
    /* 分三类： 淘宝、天猫、海外 */
    private SearchListFragment taobaoFragment;
    private SearchListFragment tmallFragment;
    private SearchListFragment overSeaFragment;
    public static final int TYPE_TAOBAO = 1;
    public static final int TYPE_TMALL = 2;
    public static final int TYPE_OVERSEA = 3;
    private int searchType = TYPE_TAOBAO;//1为同步，2为名师
    public static Conditions condition = Conditions.HOT;
    private Conditions lastCondition = Conditions.HOT;
    public static boolean is_overseas = false;//是否海外商品
    public static boolean is_tmall = false;//是否天猫商品
    public static boolean has_coupon = true;//是否有优惠券
    private InputMethodManager inputMethodManager;

    @Override
    protected int getLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true,mContext);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        condition = Conditions.HOT;
        is_overseas = false;//是否海外商品
        is_tmall = false;//是否天猫商品
        has_coupon = true;//是否有优惠券

        historyList = new ArrayList<>();
        fragments = new ArrayList<>();
        //初始化fragments
        taobaoFragment = new SearchListFragment();
        Bundle taobaoBundle = new Bundle();
        taobaoBundle.putInt("type", SearchActivity.TYPE_TAOBAO);
        taobaoFragment.setArguments(taobaoBundle);

        tmallFragment = new SearchListFragment();
        Bundle tmallBundle = new Bundle();
        tmallBundle.putInt("type", SearchActivity.TYPE_TMALL);
        tmallFragment.setArguments(tmallBundle);

        overSeaFragment = new SearchListFragment();
        Bundle overSeaBundle = new Bundle();
        overSeaBundle.putInt("type", SearchActivity.TYPE_OVERSEA);
        overSeaFragment.setArguments(overSeaBundle);

        fragments.add(taobaoFragment);
        fragments.add(tmallFragment);
        fragments.add(overSeaFragment);
        getHistory();

        //初始化控件
        //填充数据
        String[] tabStrArr = {"淘宝", "天猫","海外"};
        fragmentAdapter = new CommonFragmentAdapter(fragments, getSupportFragmentManager());
        fragmentAdapter.setTitleArr(tabStrArr);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(fragmentAdapter);
        tab_layout.setVisibility(View.GONE);
        for (int i = 0; i < tabStrArr.length; i++) {
            tab_layout.addTab(tab_layout.newTab(), i == 0);
        }
        tab_layout.setupWithViewPager(viewpager);
        for (int i = 0; i < tabStrArr.length; i++) {
            tab_layout.getTabAt(i).setText(tabStrArr[i]);
        }
        //设置监听
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refreshData(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_search.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_search.getWidth()
                        - et_search.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_search.setText("");
                    et_search.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_search, 0, 0, 0);
                    rl_pager_container.setVisibility(View.GONE);
                    ll_filter_container.setVisibility(View.GONE);
                }
                return false;
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_search, 0, R.mipmap.ic_close, 0);
                } else {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_search, 0, 0, 0);
                    rl_search_history_container.setVisibility(View.VISIBLE);
                    rl_pager_container.setVisibility(View.GONE);
                    ll_filter_container.setVisibility(View.GONE);
                    getHistory();
                }
            }
        });
        switch_only_coupon_goods.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private boolean isFirstTime = true;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && isFirstTime){
            isFirstTime = false;
            if(getIntent() != null){
                String content = getIntent().getStringExtra("content");
                if(TextUtils.isEmpty(content) ||
                        TextUtils.isEmpty(content.trim())){
                    return;
                }
                tab_layout.setVisibility(View.VISIBLE);
                rl_pager_container.setVisibility(View.VISIBLE);
                ll_filter_container.setVisibility(View.VISIBLE);
                rl_search_history_container.setVisibility(View.GONE);
                et_search.setText(content);
                btn_search.performClick();
            }
        }
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            has_coupon = isChecked;
            refreshData(null);
        }
    };

    private void getHistory(){
        historyList = CacheHelper.getSearchHistory();
        if (Utils.isListEmpty(historyList)) {
            rl_search_history_container.setVisibility(View.GONE);
        } else {
            rl_search_history_container.setVisibility(View.VISIBLE);
            tagAdapter = new TagAdapter<String>(historyList) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.listory_tag_layout,
                            fl_search_history, false);
                    tv.setText(s);
                    return tv;
                }
            };
            fl_search_history.setAdapter(tagAdapter);
            fl_search_history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    String content = historyList.get(position);
                    if(!TextUtils.isEmpty(content) && !TextUtils.isEmpty(content.trim())){
                        tab_layout.setVisibility(View.VISIBLE);
                        rl_pager_container.setVisibility(View.VISIBLE);
                        ll_filter_container.setVisibility(View.VISIBLE);
                        rl_search_history_container.setVisibility(View.GONE);
                        historyList.remove(position);
                        if(Utils.isListEmpty(Utils.checkIsItemExist(historyList,et_search.getText().toString().trim()))){//去重
                            historyList.add(0,content);
                        }
                        CacheHelper.cacheSearchHistory(historyList);
                        et_search.setText(content.trim());
                        et_search.setSelection(content.trim().length());
                        inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
                        taobaoFragment.setHasLoaded(false);
                        tmallFragment.setHasLoaded(false);
                        overSeaFragment.setHasLoaded(false);
                        if(searchType == TYPE_TAOBAO){
                            taobaoFragment.search(content.trim());
                        }else if(searchType == TYPE_TMALL){
                            tmallFragment.search(content.trim());
                        }else if(searchType == TYPE_OVERSEA){
                            overSeaFragment.search(content.trim());
                        }
                    }
                    return false;
                }
            });
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_search, R.id.iv_delete,R.id.tv_filter_hot,R.id.rl_selled_count_filter,
    R.id.rl_price_filter})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_search:
                if (TextUtils.isEmpty(et_search.getText().toString()) ||
                        TextUtils.isEmpty(et_search.getText().toString().trim())) {
                    Toast.makeText(mContext, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    break;
                }
                tab_layout.setVisibility(View.VISIBLE);
                rl_pager_container.setVisibility(View.VISIBLE);
                ll_filter_container.setVisibility(View.VISIBLE);
                rl_search_history_container.setVisibility(View.GONE);
                String content = et_search.getText().toString().trim();
                inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
                taobaoFragment.setHasLoaded(false);
                tmallFragment.setHasLoaded(false);
                overSeaFragment.setHasLoaded(false);
                if (searchType == TYPE_TAOBAO) {
                    taobaoFragment.search(content);
                } else if (searchType == TYPE_TMALL) {
                    tmallFragment.search(content);
                } else if(searchType == TYPE_OVERSEA){
                    overSeaFragment.search(content);
                }
                if(Utils.isListEmpty(Utils.checkIsItemExist(historyList,et_search.getText().toString().trim()))){//去重
                    historyList.add(0,et_search.getText().toString().trim());
                }
                CacheHelper.cacheSearchHistory(historyList);
                break;
            case R.id.iv_delete://清除搜索历史
                if (Utils.isListEmpty(historyList)) {
                    Toast.makeText(mContext, "当前无搜索历史", Toast.LENGTH_SHORT).show();
                    break;
                }
                historyList.clear();
                tagAdapter.notifyDataChanged();
                CacheHelper.cacheSearchHistory(historyList);
                rl_search_history_container.setVisibility(View.GONE);
                Toast.makeText(mContext, "已清空", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_filter_hot:
                lastCondition = condition;
                condition = Utils.Conditions.HOT;
                refreshFilterUi();
                refreshData(null);
                break;
            case R.id.rl_selled_count_filter:
                lastCondition = condition;
                if(condition == Utils.Conditions.SELL_UP){
                    condition = Utils.Conditions.SELL_DOWN;
                }else if(condition == Utils.Conditions.SELL_DOWN){
                    condition = Utils.Conditions.SELL_UP;
                }else{
                    condition = Utils.Conditions.SELL_DOWN;
                }
                refreshFilterUi();
                refreshData(null);
                break;
            case R.id.rl_price_filter:
                lastCondition = condition;
                if(condition == Utils.Conditions.PRICE_UP){
                    condition = Utils.Conditions.PRICE_DOWN;
                }else if(condition == Utils.Conditions.PRICE_DOWN){
                    condition = Utils.Conditions.PRICE_UP;
                }else{
                    condition = Utils.Conditions.PRICE_UP;
                }
                refreshFilterUi();
                refreshData(null);
                break;
        }
    }

    /**
     * 根据条件刷新UI
     */
    private void refreshFilterUi(){
        resetLastFilter();
        switch (condition){
            case HOT:
                tv_filter_hot.setTextColor(getResources().getColor(R.color.color_selected_tags));
                break;
            case SELL_UP:
                tv_selled_count_text.setTextColor(getResources().getColor(R.color.color_selected_tags));
                iv_up_arrow_selled_count.setImageResource(R.mipmap.ic_up_arrow_orange);
                break;
            case SELL_DOWN:
                tv_selled_count_text.setTextColor(getResources().getColor(R.color.color_selected_tags));
                iv_down_arrowselled_count.setImageResource(R.mipmap.ic_down_arrow_orange);
                break;
            case PRICE_UP:
                tv_price_text.setTextColor(getResources().getColor(R.color.color_selected_tags));
                iv_up_arrow_price.setImageResource(R.mipmap.ic_up_arrow_orange);
                break;
            case PRICE_DOWN:
                tv_price_text.setTextColor(getResources().getColor(R.color.color_selected_tags));
                iv_down_arrow_price.setImageResource(R.mipmap.ic_down_arrow_orange);
                break;
        }
    }

    private void resetLastFilter(){
        switch (lastCondition){
            case HOT:
                tv_filter_hot.setTextColor(getResources().getColor(R.color.color_subtext_subtitle));
                break;
            case SELL_UP:
                tv_selled_count_text.setTextColor(getResources().getColor(R.color.color_subtext_subtitle));
                iv_up_arrow_selled_count.setImageResource(R.mipmap.ic_up_arrow_gray);
                break;
            case SELL_DOWN:
                tv_selled_count_text.setTextColor(getResources().getColor(R.color.color_subtext_subtitle));
                iv_down_arrowselled_count.setImageResource(R.mipmap.ic_down_arrow_gray);
                break;
            case PRICE_UP:
                tv_price_text.setTextColor(getResources().getColor(R.color.color_subtext_subtitle));
                iv_up_arrow_price.setImageResource(R.mipmap.ic_up_arrow_gray);
                break;
            case PRICE_DOWN:
                tv_price_text.setTextColor(getResources().getColor(R.color.color_subtext_subtitle));
                iv_down_arrow_price.setImageResource(R.mipmap.ic_down_arrow_gray);
                break;
        }
    }

    /**
     * 根据条件刷新数据
     */
    private void refreshData(TabLayout.Tab tab){
        if(tab == null){
            tab = tab_layout.getTabAt(tab_layout.getSelectedTabPosition());
            taobaoFragment.setHasLoaded(false);
            tmallFragment.setHasLoaded(false);
            overSeaFragment.setHasLoaded(false);
        }
        if(tab == null){
            return;
        }
        String content = et_search.getText().toString().trim();
        inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
        if (tab.getPosition() == 0) {
            searchType = TYPE_TAOBAO;
            if (!TextUtils.isEmpty(content)) {
                is_overseas = false;
                is_tmall = false;
                taobaoFragment.search(content);
            }
        } else if (tab.getPosition() == 1) {
            searchType = TYPE_TMALL;
            if (!TextUtils.isEmpty(content)) {
                is_overseas = false;
                is_tmall = true;
                tmallFragment.search(content);
            }
        } else if(tab.getPosition() == 2){
            searchType = TYPE_OVERSEA;
            if (!TextUtils.isEmpty(content)) {
                is_overseas = true;
                is_tmall = false;
                overSeaFragment.search(content);
            }
        }
    }

}
