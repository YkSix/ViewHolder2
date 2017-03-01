package com.ericluapp.libs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;


/**
 * A better ViewHolder pattern for RecyclerView
 * 
 * @author ericluapp@gmail.com (Eric Lu)
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private int mLayoutId;
    private Object mTag;

    private static class OnViewHolderItemClickListener implements View.OnClickListener {
        private WeakReference<OnRecyclerViewItemClickListener> mListener;
        public OnViewHolderItemClickListener(OnRecyclerViewItemClickListener listener) {
            mListener = new WeakReference<OnRecyclerViewItemClickListener>(listener);
        }

        @Override
        public void onClick(View v) {
            OnRecyclerViewItemClickListener listener = mListener.get();

            ViewHolder holder = (ViewHolder) v.getTag(R.id.tag_viewholder);
            if (holder == null || listener == null) {
                return;
            }

            final int position = holder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) {
                return;
            }

            if (listener != null) {
                listener.onItemClick(holder, position);
            }
        }
    }

    public ViewHolder(View itemView, ViewGroup parent, OnRecyclerViewItemClickListener listener) {
        super(itemView);
        mViews = new SparseArray<View>();
        itemView.setTag(R.id.tag_viewholder, this);

        if (listener != null) {
            itemView.setOnClickListener(new OnViewHolderItemClickListener(listener));
        }
    }

    public ViewHolder(@LayoutRes int itemLayoutId, @NonNull ViewGroup parent) {
        this(itemLayoutId, parent, null);
    }

    public ViewHolder(@LayoutRes int itemLayoutId, @NonNull ViewGroup parent, OnRecyclerViewItemClickListener listener) {
        this(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false), parent, listener);
        mLayoutId = itemLayoutId;
    }

    public ViewHolder(View itemView, ViewGroup parent) {
        this(itemView, parent, null);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return itemView;
    }

    public ViewHolder setText(@IdRes int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setText(@IdRes int viewId, @StringRes int resId, Object... formatArgs) {
        TextView tv = getView(viewId);
        tv.setText(tv.getContext().getString(resId, formatArgs));
        return this;
    }

    public ViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(itemView.getResources().getColor(textColorRes));
        return this;
    }

    public ViewHolder setAlpha(@IdRes int viewId, @FloatRange(from=0.0, to=1.0) float value) {
        getView(viewId).setAlpha(value);
        return this;
    }

    public ViewHolder setVisible(@IdRes int viewId, boolean visible) {
        return setVisible(viewId, visible, View.GONE);
    }

    public ViewHolder setVisible(@IdRes int viewId, boolean visible, int visibilityWhenGone) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : visibilityWhenGone);
        return this;
    }

    public ViewHolder setSelected(@IdRes int viewId, boolean selected) {
        getView(viewId).setSelected(selected);
        return this;
    }

    public ViewHolder linkify(@IdRes int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(@IdRes int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(@IdRes int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public ViewHolder setEnabled(@IdRes int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    public ViewHolder setOnClickListener(@IdRes int viewId,
                                           View.OnClickListener listener) {
        View view = getView(viewId);
        view.setTag(R.id.tag_viewholder, this);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnClickListener(@IdRes int viewId,
                                           View.OnClickListener listener,
                                           Bundle data) {
        View view = getView(viewId);
        view.setTag(R.id.tag_viewholder, this);
        view.setTag(R.id.tag_bundle_data, data);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(@IdRes int viewId,
                                           View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(@IdRes int viewId,
                                               View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    public Context getContext() {
        return getConvertView().getContext();
    }

    public Resources getResources() {
        return getConvertView().getResources();
    }

    /**
     * @return true if {@link #getItemViewType()} is {@link RecyclerView#INVALID_TYPE}
     */
    public boolean isInvalidType() {
        return getItemViewType() == RecyclerView.INVALID_TYPE;
    }

    /**
     * TODO: How about combining with setTag using a single variable ?
     */
    public void setBundle(Bundle bundle) {
        getConvertView().setTag(R.id.tag_bundle_data, bundle);
    }

    public Bundle getBundle() {
        return (Bundle) getConvertView().getTag(R.id.tag_bundle_data);
    }

    /**
     * TODO: How about combining with setBundle using a single variable ?
     */
    public void setTag(Object tag) {
        mTag = tag;
    }

    public Object getTag() {
        return mTag;
    }

    public Object getTag(@IdRes int viewId) {
        View view = getView(viewId);
        return view.getTag();
    }

    public Object getTag(@IdRes int viewId, int key) {
        View view = getView(viewId);
        return view.getTag(key);
    }

}
