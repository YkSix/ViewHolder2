package com.ericluapp.libs;

import android.view.View;

public interface OnRecyclerViewItemClickListener extends View.OnClickListener {
    /**
     * @param holder The ViewHolder of the RecyclerView item.
     * @param position The position in which the item is clicked.
     */
    void onItemClick(ViewHolder holder, int position);

    /**
     * Note that the ViewHolder can be got by <code>v.getTag(R.id.tag_viewholder)</code>
     * or using SimpleOnRecyclerViewItemClickListener#getViewHolder
     */
    @Override
    void onClick(View v);
}
