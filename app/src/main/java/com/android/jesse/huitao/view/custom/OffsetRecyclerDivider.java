package com.android.jesse.huitao.view.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class OffsetRecyclerDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = OffsetRecyclerDivider.class.getSimpleName();

    private int bottom;
    private int left;

    public OffsetRecyclerDivider() {
    }

    public OffsetRecyclerDivider(int left, int bottom) {
        this.left = left;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(left, 0, left, bottom);
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}