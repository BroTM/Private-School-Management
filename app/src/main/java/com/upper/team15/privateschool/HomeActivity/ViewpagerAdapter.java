package com.upper.team15.privateschool.HomeActivity;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.upper.team15.privateschool.R;

/**
 * Created by hp on 21-Oct-17.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.headerimage,R.drawable.ucs_three, R.drawable.ucs_four};


    public ViewpagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.image,null);
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.image_linear);
        linearLayout.setBackgroundResource(images[position]);
        Glide.with(context).load(images[position]);
       /* ImageView imageView= (ImageView) view.findViewById(R.id.image_linear);
        imageView.setBackgroundResource(images[position]);*/
        ViewPager vp= (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp= (ViewPager) container;
        View view= (View) object;
        vp.removeView(view);

    }
}