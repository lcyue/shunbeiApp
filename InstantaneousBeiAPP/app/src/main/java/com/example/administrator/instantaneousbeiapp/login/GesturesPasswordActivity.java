package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/27.
 */
public class GesturesPasswordActivity extends Activity {
    ImageView returnBtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gestures_password);

        returnBtton = (ImageView) findViewById(R.id.return_btton);

        returnBtton.setOnClickListener(onClickListener);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.return_btton:
                    finish();
                    break;
            }
        }
    };
}
