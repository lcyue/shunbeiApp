package com.example.administrator.instantaneousbeiapp.guide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GuideTransitionTwo extends Activity {
    ViewFlipper GuideTransitionTwo_ViewFlipper;
    ImageView guide_rabbit_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_transition_two_layout  );

        guide_rabbit_img = (ImageView) findViewById(R.id.guide_rabbit_img);
        GuideTransitionTwo_ViewFlipper = (ViewFlipper) findViewById(R.id.GuideTransitionTwo_ViewFlipper);

        GuideTransitionTwo_ViewFlipper.startFlipping();
    }
}
