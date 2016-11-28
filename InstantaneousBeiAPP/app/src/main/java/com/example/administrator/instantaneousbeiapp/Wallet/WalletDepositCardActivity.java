package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class WalletDepositCardActivity extends Activity {
    ImageView walletGuideBtn;//返回按钮
    ImageView walletInstallWhite;//选择页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_deposit_card_layout); //消费的详细页面

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        walletInstallWhite = (ImageView) findViewById(R.id.wallet_install_white);

        walletGuideBtn.setOnClickListener(onClickListener);
        walletInstallWhite.setOnClickListener(onClickListener);
    }

    //    点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.wallet_guide_btn:
                    finish();
                    break;
                case R.id.wallet_install_white:
                    Intent intent = new Intent(WalletDepositCardActivity.this, WalletChangeActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
