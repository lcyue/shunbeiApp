package com.example.administrator.instantaneousbeiapp.login;

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
import com.example.administrator.instantaneousbeiapp.menu.RenmidChargeAccountDelete;
import com.example.administrator.instantaneousbeiapp.menu.UseAccount;

/**
 * Created by Administrator on 2016/11/27.
 */
public class RegisterActivity extends Activity {
    ImageView backBtn;
    TextView saveingBtton;
    EditText editText;
    String signature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signature);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        saveingBtton = (TextView) findViewById(R.id.saveing_btton);
        editText = (EditText) findViewById(R.id.editText);

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
                signature = editable.toString();
            }
        });
    }

    //点击事件
    private static final int SIGNATURE_RESULTCODE = 1020;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.saveing_btton:
                    if(signature != null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("signature",""+signature);//将签到情况回传给签到按钮
                        setResult(SIGNATURE_RESULTCODE,saveIntentBtn);
                        Toast.makeText(RegisterActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, "签名为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
