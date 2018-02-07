
package com.cql.scrollconflicttest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private BadViewPager mViewPager;
    
    private List<View> mList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews(false);
    }
    
    private void initViews(boolean flag){
        
        mViewPager = (BadViewPager) findViewById(R.id.view_pager);
        mList = new ArrayList<View>();
        
        if(flag){
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(30);
            tv.setText("Page0");
            
            TextView tv2 = new TextView(this);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTextSize(30);
            tv2.setText("Page1");
            
            TextView tv3 = new TextView(this);
            tv3.setGravity(Gravity.CENTER);
            tv3.setTextSize(30);
            tv3.setText("Page2");
            
            TextView tv4 = new TextView(this);
            tv4.setGravity(Gravity.CENTER);
            tv4.setTextSize(30);
            tv4.setText("Page3");
            
            mList.add(tv);
            mList.add(tv2);
            mList.add(tv3);
            mList.add(tv4);
            
        }else{
            for(int j=0;j<4;j++){
                ListView mListView = new ListView(this);
                List<String> datas = new ArrayList<String>();
                for(int i=0;i<30;i++){
                    datas.add("data"+i);
                }
                mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas));
                mList.add(mListView);
            }
        }
        
        mViewPager.setAdapter(new MyAdapter(mList));
        mViewPager.setOffscreenPageLimit(1);
    }
}
