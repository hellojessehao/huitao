package com.android.jesse.huitao.view.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridRecyclerDivider extends RecyclerView.ItemDecoration {

    private int mDividerHeight = 1;
    private int colNumPerLine = 2;

    public GridRecyclerDivider(){}

    public GridRecyclerDivider(int colNumPerLine, int dividerHeight) {
        this.mDividerHeight = dividerHeight;
        this.colNumPerLine = colNumPerLine;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            if(i/colNumPerLine == 0){//第一行
                if(i%colNumPerLine == 1){//最后一列
                    outRect.set(0,mDividerHeight,mDividerHeight,mDividerHeight);
                }else{//非最后一列
                    outRect.set(mDividerHeight,mDividerHeight,mDividerHeight,mDividerHeight);
                }
            }else{//非第一行
                if(i%colNumPerLine == 1){//最后一列
                    outRect.set(0,0,mDividerHeight,mDividerHeight);
                }else{//非最后一列
                    outRect.set(mDividerHeight,0,mDividerHeight,mDividerHeight);
                }
            }

        }

    }

}