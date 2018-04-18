package com.example.fpp_109.damihubschool;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomPagerAdapter extends PagerAdapter {
    private LayoutInflater mLayIn;

    private int[] mResources= {
            R.drawable.satu,
            R.drawable.dua,
            R.drawable.tiga

    };

    CustomPagerAdapter(Context context){
        mLayIn = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return mResources.length;
    }
    public boolean isViewFromObject(View view,Object object) {
        return view == object;
    }
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayIn.inflate(R.layout.pager_item, container,false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);
        container.addView(itemView);
        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
