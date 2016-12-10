package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.view.PatterView;


/**
 * Created by Administrator on 2016/11/27.
 */
public class GesturesPasswordActivity extends Activity implements PatterView.OnPatterChangeListener {
    ImageView returnBtton;
    PatterView lockPatternView;
    Button create_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gestures_password);

        returnBtton = (ImageView) findViewById(R.id.return_btton);
        lockPatternView = (PatterView) findViewById(R.id.lockPatternView);
        create_password= (Button) findViewById(R.id.create_password);

        returnBtton.setOnClickListener(onClickListener);
        create_password.setOnClickListener(onClickListener);
        lockPatternView.setPatterChangeListener(this);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.return_btton:
                    finish();
                    break;
                case R.id.create_password:
                    break;
            }
        }
    };

    @Override
    public void onPatterChange(String passwordStr) {
        if(!TextUtils.isEmpty(passwordStr)){
            Toast.makeText(GesturesPasswordActivity.this, ""+passwordStr, Toast.LENGTH_SHORT).show();
            create_password.setText("确认密码");
        }else {
            create_password.setText("创建手势密码");
        }
    }

    @Override
    public void onPatterStart(boolean isStart) {
        if(isStart){
            create_password.setText("创建手势密码");
        }
    }

}
