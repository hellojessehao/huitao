package com.android.jesse.huitao.view.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.utils.LogUtil;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.Utils;
import com.android.jesse.huitao.view.activity.base.BaseActivity;
import com.android.jesse.huitao.view.adapter.PicsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 全屏浏览图片的页面
 * @author: zhangshihao
 * @date: 2019/9/25 0025
 */
//TODO:增加双指缩放
public class PicsBrowseActivity extends BaseActivity {

    private static final String TAG = PicsBrowseActivity.class.getSimpleName();

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_current_index)
    TextView tv_current_index;
    @BindView(R.id.tv_total_count)
    TextView tv_total_count;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    public static final String LIST_KEY = "imgUrlList";
    public static final String INDEX_KEY = "currentIndex";

    private int currentIndex = 0;
    private List<String> imgUrlList;
    private PicsPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.pics_browse_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true, mContext);
        imgUrlList = new ArrayList<>();

        Intent intent = getIntent();
        if(intent != null){
            imgUrlList = intent.getStringArrayListExtra(LIST_KEY);
            currentIndex = intent.getIntExtra(INDEX_KEY,0);

            if(Utils.isListEmpty(imgUrlList)){
                LogUtil.e(TAG+" imgUrlList is empty");
                return;
            }
            adapter = new PicsPagerAdapter(mContext,imgUrlList);
            viewpager.setAdapter(adapter);
            viewpager.setCurrentItem(currentIndex);
            tv_total_count.setText(imgUrlList.size()+"");
            tv_current_index.setText(""+(currentIndex+1));
            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentIndex = position;
                    tv_current_index.setText(""+(currentIndex+1));
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
