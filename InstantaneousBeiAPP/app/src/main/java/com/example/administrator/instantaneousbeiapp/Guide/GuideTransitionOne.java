package com.example.administrator.instantaneousbeiapp.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GuideTransitionOne extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_transition_one_layout);
        imageView = (ImageView) findViewById(R.id.guide_image);

        ScaleAnimator();

    }

    public void ScaleAnimator(){//利用动画停顿2秒后到GuideTransitionTwo

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,0,0,0);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.start();
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(GuideTransitionOne.this,GuideTransitionTwo.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(scaleAnimation);
    }




}
