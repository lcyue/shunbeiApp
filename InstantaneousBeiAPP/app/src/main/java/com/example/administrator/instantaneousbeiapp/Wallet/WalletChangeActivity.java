package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class WalletChangeActivity extends Activity {
    RelativeLayout walletTypeRetivlayout;//选择类型的按钮
    ImageView walletGuideBtn;//返回按钮
    RelativeLayout walletColorbtn;//点击选择按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_change_layout);//更改账户页面

        walletTypeRetivlayout = (RelativeLayout) findViewById(R.id.wallet_type_retivlayout);
        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        walletColorbtn = (RelativeLayout) findViewById(R.id.wallet_color_btn);

        walletTypeRetivlayout.setOnClickListener(onClickListener);
        walletGuideBtn.setOnClickListener(onClickListener);
        walletColorbtn.setOnClickListener(onClickListener);
    }

    //    点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.wallet_type_retivlayout:
                    intent = new Intent(WalletChangeActivity.this, WalletAccountType.class);
                    startActivity(intent);
                    break;
                case R.id.wallet_guide_btn:
                    finish();
                    break;
                case R.id.wallet_color_btn:
                    intent = new Intent(WalletChangeActivity.this, WalletSelectColour.class);
                    startActivity(intent);
                    break;
            }

        }
    };
}
