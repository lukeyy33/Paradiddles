package com.lukewaugh.paradiddles;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class TripletsSwipeAdapter extends PagerAdapter {
    private int [] triplets = {R.drawable.test, R.drawable.triplets};

    private Context context;
    private LayoutInflater layoutInflater;

    public TripletsSwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return triplets.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view  = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);

        imageView.setImageResource(triplets[position]);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Destroy item to free heap memory
        container.removeView((LinearLayout)object);
    }
}
