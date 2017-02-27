package com.ericluapp.android.viewholderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ericluapp.android.libs.OnRecyclerViewItemClickListener;
import com.ericluapp.android.libs.SimpleOnRecyclerViewItemClickListener;
import com.ericluapp.android.libs.ViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;

    private OnRecyclerViewItemClickListener mListener = new SimpleOnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(ViewHolder holder, int position) {
            Toast.makeText(MainActivity.this, "click item: " + position, Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onClick(View v) {
            ViewHolder holder = getViewHolder(v);
            switch (v.getId()) {
                case R.id.btn_button: {
                    Toast.makeText(MainActivity.this, "click button: " + holder.getAdapterPosition(), Toast.LENGTH_SHORT)
                            .show();
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMyAdapter = new MyAdapter(mListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);
    }

    private static class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private OnRecyclerViewItemClickListener mListener;

        public MyAdapter(OnRecyclerViewItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(R.layout.item_simple, parent, mListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setText(R.id.tv_index, position + "");
            holder.setText(R.id.btn_button, position + "");
            holder.setOnClickListener(R.id.btn_button, mListener);
            holder.setImageResource(R.id.iv_image, R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }
}
