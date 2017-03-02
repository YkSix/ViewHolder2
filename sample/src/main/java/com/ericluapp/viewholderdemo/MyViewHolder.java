package com.ericluapp.viewholderdemo;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ericluapp.libs.OnRecyclerViewItemClickListener;
import com.ericluapp.libs.ViewHolder;


public class MyViewHolder extends ViewHolder {

    public MyViewHolder(@LayoutRes int itemLayoutId, @NonNull ViewGroup parent) {
        super(itemLayoutId, parent);
    }

    public MyViewHolder(@LayoutRes int itemLayoutId, @NonNull ViewGroup parent, OnRecyclerViewItemClickListener listener) {
        super(itemLayoutId, parent, listener);
    }

    public MyViewHolder(View itemView, ViewGroup parent) {
        super(itemView, parent);
    }

    public MyViewHolder(View itemView, ViewGroup parent, OnRecyclerViewItemClickListener listener) {
        super(itemView, parent, listener);
    }

    public MyViewHolder loadUrl(@IdRes int viewId, String url) {
        Glide.with(getContext())
                .load(url)
                .into((ImageView) getView(viewId));
        return this;
    }
}
