package com.example.administrator.instantaneousbeiapp.Guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.administrator.instantaneousbeiapp.HomePage.HomeMainActivity;
import com.example.administrator.instantaneousbeiapp.R;

/**
 * 要求第2次进入App显示这个页面，然后跳转
 * Created by Administrator on 2016/10/20.
 */
public class GuideTransitionTwo extends Activity {
    ViewFlipper GuideTransitionTwo_ViewFlipper;
    ImageView guide_rabbit_img;
    ImageView shunbei_logo_img;
    LinearLayout rootLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_transition_two_layout);

        guide_rabbit_img = (ImageView) findViewById(R.id.guide_rabbit_img);
        shunbei_logo_img = (ImageView) findViewById(R.id.shunbei_logo_img);
        rootLinearLayout = (LinearLayout) findViewById(R.id.root_LinearLayout);
        GuideTransitionTwo_ViewFlipper = (ViewFlipper) findViewById(R.id.GuideTransitionTwo_ViewFlipper);

        GuideTransitionTwo_ViewFlipper.startFlipping();

        rootLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideTransitionTwo.this, HomeMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
