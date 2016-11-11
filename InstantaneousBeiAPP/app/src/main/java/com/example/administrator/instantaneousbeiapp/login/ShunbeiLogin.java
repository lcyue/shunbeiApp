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
public class ShunbeiLogin extends Activity {
    TextView shunbei_login_btn;
    TextView shunbei_zhuce_btn;
    TextView shunbei_xuigai_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_login_layout);

        shunbei_login_btn = (TextView) findViewById(R.id.shunbei_login_btn);
        shunbei_zhuce_btn = (TextView) findViewById(R.id.shunbei_zhuce_btn);
        shunbei_xuigai_btn = (TextView) findViewById(R.id.shunbei_xuigai_btn);

        shunbei_xuigai_btn.setOnClickListener(onClickListener);
        shunbei_login_btn.setOnClickListener(onClickListener);
        shunbei_zhuce_btn.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
//                case R.id.shunbei_login_btn:
//                    intent = new Intent(ShunbeiLogin.this, com.example.administrator.instantaneousbeiapp.homepage.HomePageAcivity.class);
//                    startActivity(intent);
//                    break;
                case R.id.shunbei_zhuce_btn:
                    intent = new Intent(ShunbeiLogin.this,ZhuceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.shunbei_xuigai_btn:
                    intent = new Intent(ShunbeiLogin.this,XuigaiMimaActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
