package com.example.administrator.instantaneousbeiapp.Guide;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GuideTransitionOne extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_transition_one_layout);

        //需要停顿2秒后到GuideTransitionTwo
    }
}
