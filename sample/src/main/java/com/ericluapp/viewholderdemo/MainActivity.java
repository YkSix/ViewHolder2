package com.ericluapp.viewholderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ericluapp.libs.OnRecyclerViewItemClickListener;
import com.ericluapp.libs.SimpleOnRecyclerViewItemClickListener;
import com.ericluapp.libs.ViewHolder;

public class MainActivity extends AppCompatActivity {

    public static final String[] IMAGES = new String[] {
            "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg",
            "https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
            "https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg",
            "https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg",
            "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg",
            "https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg",
            "https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg",
            "https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg",
            "https://lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s1024/Bee%252520and%252520Flower.jpg",
            "https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg"
    };

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

    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private OnRecyclerViewItemClickListener mListener;

        public MyAdapter(OnRecyclerViewItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(R.layout.item_simple, parent, mListener);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setText(R.id.btn_button, position + "");
            holder.setOnClickListener(R.id.btn_button, mListener);
            holder.loadUrl(R.id.iv_image, IMAGES[position % IMAGES.length]);
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }
}
