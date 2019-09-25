package com.android.jesse.huitao.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.jesse.huitao.utils.GlideUtil;
import com.android.jesse.huitao.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: zhangshihao
 * @date: 2019/9/25 0025
 */
public class PicsPagerAdapter extends PagerAdapter {

    private static final String TAG = PicsPagerAdapter.class.getSimpleName();

    private List<String> imgUrlList;
    private List<ImageView> imageViewList;
    private Context mContext;

    public PicsPagerAdapter(Context context,List<String> imgUrlList){
        mContext = context;
        this.imgUrlList = imgUrlList;
        if(!Utils.isListEmpty(imgUrlList)){
            imageViewList = new ArrayList<>();
            for(int i=0;i<imgUrlList.size();i++){
                ImageView imageView = new ImageView(mContext);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                GlideUtil.getInstance().loadOriImg(mContext,imgUrlList.get(i),imageView);
                imageViewList.add(imageView);
            }
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mContext instanceof Activity){
//                    ((Activity) mContext).finish();
//                }
//            }
//        });
        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViewList.get(position));
    }


    @Override
    public int getCount() {
        return imageViewList==null?0:imageViewList.size();
    }

    private OnImageClickListener onImageClickListener;

    public OnImageClickListener getOnImageClickListener() {
        return onImageClickListener;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener{
        void onImageClick(int position);
    }

}
