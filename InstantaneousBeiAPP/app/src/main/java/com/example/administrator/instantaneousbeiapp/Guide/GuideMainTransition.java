package com.example.administrator.instantaneousbeiapp.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.login.ShunbeiLogin;

/**
 * Created by Administrator on 2016/11/10.
 */
public class GuideMainTransition extends Activity {
    ViewFlipper viewFlipper;
    TextView textView;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shunbei_ryx);
        viewFlipper = (ViewFlipper) findViewById(R.id.guide_flipper);
        textView= (TextView) findViewById(R.id.guide_btn);

        viewFlipper.startFlipping();
        gestureDetector = new GestureDetector(this,gestureListener);

        viewFlipper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (view instanceof FrameLayout){
                    int m = viewFlipper.getDisplayedChild();
                    if (m == 3) {
                        viewFlipper.stopFlipping();
                    }
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideMainTransition.this,ShunbeiLogin.class);
                startActivity(intent);
            }
        });
    }

    GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            //viewFlipper.stopFlipping();
            Log.i("onDown===","onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.i("onFling=========","onFling");
            float start = motionEvent.getX();
            float end = motionEvent1.getX();
            float move  = end - start;
            if (move > 0){
                viewFlipper.stopFlipping();
                viewFlipper.showPrevious();
                viewFlipper.startFlipping();
            }else {
                viewFlipper.stopFlipping();
                viewFlipper.showNext();
                viewFlipper.startFlipping();
            }
            return false;
        }
    };



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
