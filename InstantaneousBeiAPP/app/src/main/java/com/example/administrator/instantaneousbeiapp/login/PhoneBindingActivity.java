package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/27.
 */
public class PhoneBindingActivity extends Activity {
    ImageView backBtn;
    TextView wanchengBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phone_binding);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        wanchengBtn = (TextView) findViewById(R.id.shunbei_xuigai_wancheng_btn);

        backBtn.setOnClickListener(onClickListener);
        wanchengBtn.setOnClickListener(onClickListener);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.shunbei_xuigai_wancheng_btn:
                    Toast.makeText(PhoneBindingActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };
}
