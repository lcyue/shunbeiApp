package com.example.administrator.mysideslip;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyHorizontalScrollViewOfSideSlip extends HorizontalScrollView{
    private LinearLayout linearLayout;
    private ViewGroup Menu;
    private ViewGroup Content;
    private int ScreenWidth;
    private int MenuRightPadding = 50;
    private int MenuWidth;

    private boolean onlyone =false;
    private boolean isOpen;

    public MyHorizontalScrollViewOfSideSlip(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        ScreenWidth = outMetrics.widthPixels;
        //把dp转化成px
        MenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子view的宽和高，设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!onlyone){
            linearLayout = (LinearLayout) getChildAt(0);
            Menu = (ViewGroup) linearLayout.getChildAt(0);
            Content = (ViewGroup) linearLayout.getChildAt(1);
            MenuWidth = Menu.getLayoutParams().width = ScreenWidth - MenuRightPadding;
            Content.getLayoutParams().width = ScreenWidth;
            onlyone = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    //通过设置偏移量，将menu隐藏
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            this.scrollTo(MenuWidth,0);
        }

    }

    //设置触摸滑动显示
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX >= MenuWidth / 2){
                    this.smoothScrollTo(MenuWidth,0);
                    isOpen = false;
                }else {
                    this.smoothScrollTo(0,0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void openMenu(){
        if (isOpen)
            return;
        this.smoothScrollTo(0,0);
        isOpen = true;
    }

    public void closeMenu(){
        if (!isOpen)
            return;
        this.smoothScrollTo(MenuWidth,0);
        isOpen = false;
    }

    public void togleMenu(){
        if (isOpen){
            closeMenu();
        }else {
            openMenu();
        }

    }
}
