package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/12/7.
 */
public class ActivityBrithDay extends Activity {

    ImageView backBtn;
    TextView saveingBtton;
    EditText editText;
    String brithday;

    private static final int BRITH_RESULTCODE = 1040;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_brithday);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        saveingBtton = (TextView) findViewById(R.id.saveing_btton);
        editText = (EditText) findViewById(R.id.brithday);

        backBtn.setOnClickListener(onClickListener);
        saveingBtton.setOnClickListener(onClickListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                brithday = editable.toString();
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.saveing_btton:
                    if(brithday != null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("brithday",""+brithday);//将签到情况回传给签到按钮
                        setResult(BRITH_RESULTCODE,saveIntentBtn);
                        Toast.makeText(ActivityBrithDay.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ActivityBrithDay.this, "生日为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
