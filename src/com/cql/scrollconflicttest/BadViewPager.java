package com.cql.scrollconflicttest;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BadViewPager extends ViewPager {
    
    private int lastX;
    private int lastY;

    public BadViewPager(Context context) {
        super(context);
    }

    public BadViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch(action){
            case MotionEvent.ACTION_DOWN:
                /**
                 * ACTION_DOWN 时不能拦截事件，因为如过拦截了，根据View的事件分发机制,
                 * 之后的ACTION_MOVE，ACTION_UP 都会默认交由父View处理，而不会传递到子View
                 */
                isIntercept = false;
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                lastX = x;
                lastY = y;
                
                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) (ev.getX() - lastX);
                int offsetY = (int) (ev.getY() - lastY);
                if(Math.abs(offsetX) > Math.abs(offsetY)){
                    isIntercept = true;
                }else{
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                break;
        }
        
        return isIntercept;
    }

}
