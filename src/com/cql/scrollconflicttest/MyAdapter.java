package com.cql.scrollconflicttest;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyAdapter extends PagerAdapter {

    private List<View> views;
    
    public MyAdapter(List<View> mList) {
        views = mList;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(position>=views.size()){
            position--;
        }
        container.removeView(views.get(position));
    }
    
    @Override
    public int getItemPosition(Object object) {
        if (getCount() > 0) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}
