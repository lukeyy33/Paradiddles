package com.lukewaugh.paradiddles;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

class DoubleSwipeAdapter extends PagerAdapter {
    private int [] doubles = {R.drawable.splash, R.drawable.triplets};
    private Context context;


    public DoubleSwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return doubles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView  = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.SwipeImageView);

        imageView.setImageResource(doubles[position]);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Destroy item to free heap memory
        container.removeView((LinearLayout)object);
    }
}
