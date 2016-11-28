package com.example.administrator.instantaneousbeiapp.register;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/30.
 */
public class Derive extends Activity {
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_derive);

        backBtn = (ImageView) findViewById(R.id.back_btn);

        backBtn.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };

}
