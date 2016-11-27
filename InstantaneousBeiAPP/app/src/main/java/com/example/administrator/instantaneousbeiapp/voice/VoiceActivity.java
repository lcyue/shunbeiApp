package com.example.administrator.instantaneousbeiapp.voice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class VoiceActivity extends Activity {
    ImageView yuyin_btn;
    LinearLayout yuyin_linearlayout;
    TextView yuyin_test;
    TextView yuyin_test1;
    ImageView returnBtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_voice_layout);

        yuyin_btn = (ImageView) findViewById(R.id.yuyin_btn);
        yuyin_linearlayout = (LinearLayout) findViewById(R.id.yuyin_linearlayout);
        yuyin_test = (TextView) findViewById(R.id.yuyin_test);
        yuyin_test1 = (TextView) findViewById(R.id.yuyin_test1);
        returnBtton = (ImageView) findViewById(R.id.return_btton);

        yuyin_btn.setOnClickListener(onClickListener);
        returnBtton.setOnClickListener(onClickListener);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.yuyin_btn:
                    if(yuyin_linearlayout.getVisibility()==View.GONE){
                        yuyin_linearlayout.setVisibility(View.VISIBLE);
                        yuyin_test.setVisibility(View.VISIBLE);
                        yuyin_test1.setVisibility(View.GONE);
                    }else {
                        yuyin_linearlayout.setVisibility(View.GONE);
                        yuyin_test1.setVisibility(View.VISIBLE);
                        yuyin_test.setVisibility(View.GONE);
                    }
                    break;
                case R.id.return_btton:
                    finish();
                    break;
            }
        }
    };

}
