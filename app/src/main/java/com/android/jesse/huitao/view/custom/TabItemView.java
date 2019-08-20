package com.android.jesse.huitao.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

/**
 * @Description: 底部导航栏itemView
 * @author: zhangshihao
 * @date: 2019/7/24
 */
public class TabItemView extends LinearLayout {

    private static final String TAG = TabItemView.class.getSimpleName();

    private ImageView imageView;
    private TextView textView;
    private boolean isSelect = false;
    private Drawable selectedImgResId,unselectedImgResId;
    private int selectedTextColor,unselectedTextColor;
    private float tabItemWidth,tabItemHeight;
    private float imgWidth,imgHeight;
    private String tabName;
    private int index = 0;

    public TabItemView(Context context) {
        super(context);
        init(context,null,0);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs, int defStyleAttr){
        imageView = new ImageView(context);
        textView = new TextView(context);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabItemView);
        isSelect = ta.getBoolean(R.styleable.TabItemView_defaultSelected,false);
        selectedImgResId = ta.getDrawable(R.styleable.TabItemView_selectedImgResId);
        unselectedImgResId = ta.getDrawable(R.styleable.TabItemView_unselectedImgResId);
        selectedTextColor = ta.getInt(R.styleable.TabItemView_selectedTextColor,Color.BLUE);
        unselectedTextColor = ta.getInt(R.styleable.TabItemView_unselectedTextColor,Color.BLACK);
        tabItemWidth = ta.getDimension(R.styleable.TabItemView_tabItemWidth,ScreenUtils.getScreenWidth()/5);
        tabItemHeight = ta.getDimension(R.styleable.TabItemView_tabItemHeight,SizeUtils.dp2px(60));
        imgWidth = ta.getDimension(R.styleable.TabItemView_imgWidth,ViewGroup.LayoutParams.WRAP_CONTENT);
        imgHeight = ta.getDimension(R.styleable.TabItemView_imgHeight,ViewGroup.LayoutParams.WRAP_CONTENT);
        tabName = ta.getString(R.styleable.TabItemView_tabName);
        float textSize = ta.getDimension(R.styleable.TabItemView_textSize,9);
        index = ta.getInteger(R.styleable.TabItemView_tabIndex,-1);
        ta.recycle();  //注意回收
        //配置ImageView
        imageView.setImageDrawable(isSelect?selectedImgResId:unselectedImgResId);
        if(imgWidth > 0 && imgHeight > 0){
            LayoutParams imgParams = new LayoutParams((int) imgWidth,(int) imgHeight);
            imageView.setLayoutParams(imgParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        addView(imageView);
        //配置TextView
        textView.setText(tabName);
        textView.setTextSize(textSize);
        textView.setTextColor(isSelect?selectedTextColor:unselectedTextColor);
        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0,SizeUtils.dp2px(2),0,0);
        textView.setLayoutParams(textParams);
        addView(textView);
    }

    /**
     * 根据select状态改变UI
     */
    public void resetSelectState(){
        imageView.setImageDrawable(isSelect?selectedImgResId:unselectedImgResId);
        textView.setTextColor(isSelect?selectedTextColor:unselectedTextColor);
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        if(isSelect == select){
            return;
        }
        isSelect = select;
        resetSelectState();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
