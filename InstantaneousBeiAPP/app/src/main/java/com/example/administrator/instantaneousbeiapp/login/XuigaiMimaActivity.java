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
public class XuigaiMimaActivity extends Activity {
    TextView shunbei_xuigai_wancheng_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_xuigaimima_layout);

        shunbei_xuigai_wancheng_btn = (TextView) findViewById(R.id.shunbei_xuigai_wancheng_btn);
        shunbei_xuigai_wancheng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XuigaiMimaActivity.this,XugaiChenggongActivity.class);
                startActivity(intent);
            }
        });
    }
}
