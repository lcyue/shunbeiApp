package com.example.administrator.instantaneousbeiapp.detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/27.
 */
public class DemoArcMenu extends ViewGroup implements View.OnClickListener{

    private static final int POS_LEFT_BTN =0;
    private static final int POS_CENTER_BTN =1;
    private static final int POS_RIGHT_BTN =2;

    private Position mPosition = Position.LETT_BTN;
    private int mRadius;

    /**
     * 菜单的状态
     */
    private Status mCurrentStaus = Status.CLOSE;

    /**
     * 菜单的主按钮
     */
    private View mCButton;

    private OnMenuItemClickListener menuItemClickListener;

    private enum Status{OPEN,CLOSE};

    /**
     * 菜单位置枚举
     */
    public enum Position{LETT_BTN,CENTER_BTN,RIGHT_BTN,};

    /**
     * 点击子菜单项的回调接口
     */
    public interface OnMenuItemClickListener{
        void onClick(View view,int pos);
    }

    public OnMenuItemClickListener getMenuItemClickListener() {
        return menuItemClickListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }

    public DemoArcMenu(Context context) {
        this(context,null);
    }

    public DemoArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DemoArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,
                getResources().getDisplayMetrics());
        //获取自定义属性的值
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DemoArcMenu,
                defStyleAttr,0);

        int pos = array.getInt(R.styleable.DemoArcMenu_position,1);
        switch (pos){
            case POS_LEFT_BTN:
                mPosition = Position.LETT_BTN;
                break;
            case POS_CENTER_BTN:
                mPosition = Position.CENTER_BTN;
                break;
            case POS_RIGHT_BTN:
                mPosition = Position.RIGHT_BTN;
                break;
        }

        mRadius = (int) array.getDimension(R.styleable.DemoArcMenu_radius,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                100,getResources().getDisplayMetrics()));

        array.recycle();

        Log.i("TAG","position ==" +mPosition+",radius=="+mRadius);
    }



    @Override
    protected void onLayout(boolean changed, int i, int i1, int i2, int i3) {
        if (changed){
            layoutCButton();

            int count = getChildCount();
            for (int k = 0 ; k<count - 1;k++){
                View child = (View) getChildAt(k+1);
                child.setVisibility(GONE);
                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();

                int cl = (getMeasuredWidth() -cWidth)/2-(int)(mRadius*Math.cos(Math.PI/(count - 2)*k));
                int ct = (getMeasuredHeight() - cHeight)/2 -(int)(mRadius*Math.sin(Math.PI/(count - 2)*k));

                if (mPosition == Position.RIGHT_BTN){
                    ct = getMeasuredHeight()- cHeight - ct;
                }

                if (mPosition == Position.LETT_BTN){
                    cl = getMeasuredHeight()- cHeight - cl;
                }

                child.layout(cl,ct,cl+cWidth,ct+cHeight);

            }
        }
    }
    int width;
    int height;
    private void layoutCButton() {
        mCButton = (View) getChildAt(0);
        mCButton.setOnClickListener(this);

        int l = getMeasuredWidth()/2;
        int t = getMeasuredHeight()/2;

        width = mCButton.getMeasuredWidth();
        height = mCButton.getMeasuredHeight();

        switch (mPosition){
            case LETT_BTN:
                l=0;
                t=(getMeasuredHeight() - height)/2;
                break;
            case CENTER_BTN:
                l=(getMeasuredWidth() -width)/2;
                t=(getMeasuredHeight() - height)/2;
                break;
            case RIGHT_BTN:
                l=getMeasuredWidth() -width;
                t=(getMeasuredHeight() - height)/2;
                break;
        }

        mCButton.layout(l,t,l+width,t+width);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0 ; i<count ; i++){
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    public void onClick(View view) {
        rotateCButton(view,0f,360f,300);
        toggleMenu(300);
    }

    public void toggleMenu(int duration) {
        int count = getChildCount();
        for (int i = 0 ; i<count-1;i++){
            final View childView = getChildAt(i+1);
            childView.setVisibility(VISIBLE);

            int cl = (int)(mRadius*Math.cos(Math.PI/(count - 2)*i));
            int ct = (int)(mRadius*Math.sin(Math.PI/(count - 2)*i));

            AnimationSet anumset = new AnimationSet(true);
            Animation tranAnim = null;

            if (mCurrentStaus == Status.CLOSE){
                tranAnim = new TranslateAnimation(cl,0,ct,0);
                childView.setClickable(true);
                childView.setFocusable(true);
            }else {
                tranAnim = new TranslateAnimation(0,cl,0,cl);
                childView.setClickable(false);
                childView.setFocusable(false);
            }
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(duration);

            RotateAnimation anim = new RotateAnimation(0,720, Animation.RELATIVE_TO_SELF,
                    0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            anim.setDuration(duration*2);
            anim.setFillAfter(true);

            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mCurrentStaus == Status.CLOSE){
                        childView.setVisibility(GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //anumset.addAnimation(tranAnim);
            anumset.addAnimation(anim);
            childView.startAnimation(anumset);

            changeStatus();
        }
    }

    public void changeStatus() {
        mCurrentStaus = (mCurrentStaus == Status.CLOSE?Status.OPEN:Status.CLOSE);
    }

    public void rotateCButton(View view, float start, float end, int duration) {
        RotateAnimation anim = new RotateAnimation(start,end, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);
    }
}
