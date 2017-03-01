package com.ericluapp.libs;

import android.view.View;

public interface OnRecyclerViewItemClickListener extends View.OnClickListener {
    void onItemClick(ViewHolder holder, int position);

    @Override
    void onClick(View v);
}
