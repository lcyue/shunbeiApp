package com.example.administrator.instantaneousbeiapp.Guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class GuideMainTransition extends Activity {
    ViewFlipper viewFlipper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shunbei_ryx);
        viewFlipper = (ViewFlipper) findViewById(R.id.guide_flipper);
        textView= (TextView) findViewById(R.id.guide_btn);

        viewFlipper.startFlipping();

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
                Intent intent = new Intent(GuideMainTransition.this,GuideTransitionOne.class);
                startActivity(intent);
            }
        });
    }
}
