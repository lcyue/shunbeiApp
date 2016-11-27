package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/22.
 */
public class ZhuceActivity extends Activity {
    TextView signInbtton;
    TextView registerBtton;
    TextView testGetCodeBtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_zhuce_layout);

        signInbtton = (TextView) findViewById(R.id.sign_in_btton);
        registerBtton = (TextView) findViewById(R.id.register_btton);
        testGetCodeBtton = (TextView) findViewById(R.id.test_get_code_btton);

        signInbtton.setOnClickListener(onClickListener);
        registerBtton.setOnClickListener(onClickListener);
        testGetCodeBtton.setOnClickListener(onClickListener);//获取验证的逻辑还没有写

    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.sign_in_btton:
                    intent = new Intent(ZhuceActivity.this, XugaiChenggongActivity.class);//注册成功
                    startActivity(intent);
                    break;
                case R.id.register_btton:
                    intent = new Intent(ZhuceActivity.this, ShunbeiLogin.class);//登录页面
                    startActivity(intent);
                    break;
                case R.id.test_get_code_btton:
                    break;
            }
        }
    };
}
