package com.example.administrator.instantaneousbeiapp.jizhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BijiShouruActivity extends Activity {
    TextView jizhang_add_btn;
    TextView jizhang_back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_bianjishouru_layout);

        jizhang_add_btn= (TextView) findViewById(R.id.jizhang_add_btn);
        jizhang_back_btn= (TextView) findViewById(R.id.jizhang_back_btn);

        jizhang_add_btn.setOnClickListener(onClickListener);
        jizhang_back_btn.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.jizhang_add_btn:
                    intent = new Intent(BijiShouruActivity.this,TianjiaoshouruActivity.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_back_btn:
                    finish();
                    break;
            }
        }
    };
}
