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
public class ActivityNickname extends Activity{
    ImageView backBtn;
    TextView saveingBtton;
    EditText editText;
    String nickname;

    private static final int NICKNAME_RESULTCODE = 1060;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nickname);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        saveingBtton = (TextView) findViewById(R.id.saveing_btton);
        editText = (EditText) findViewById(R.id.nickname);

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
                nickname = editable.toString();
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
                    if(nickname != null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("nickname",""+nickname);//将签到情况回传给签到按钮
                        setResult(NICKNAME_RESULTCODE,saveIntentBtn);
                        Toast.makeText(ActivityNickname.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ActivityNickname.this, "生日为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
