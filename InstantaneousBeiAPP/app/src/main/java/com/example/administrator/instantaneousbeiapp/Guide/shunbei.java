package com.example.administrator.instantaneousbeiapp.Guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.administrator.instantaneousbeiapp.R;

public class shunbei extends AppCompatActivity implements View.OnTouchListener{
    TextView guide_btn;
    private float startX;
    ViewFlipper guide_flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shunbei_ryx);
        guide_flipper = (ViewFlipper) findViewById(R.id.guide_flipper);
        guide_btn = (TextView) findViewById(R.id.guide_btn);

        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shunbei.this,GuideTransitionTwo.class);
                startActivity(intent);
            }
        });

        guide_flipper.setOnTouchListener(this);
        //guide_flipper.startFlipping();

    }


    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                break;
            //手指滑动
            case MotionEvent.ACTION_MOVE:
                //向右滑动
                if (motionEvent.getX() - startX > 300) {

                    guide_flipper.showNext();
                }
                //向左滑动
                if (startX - motionEvent.getX() > 300) {
                    guide_flipper.showPrevious();
                }
                break;
            //手指离开
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
