package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class WalletSelectColour extends Activity {
    ImageView walletGuideBtn;//返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wallet_select_colour);

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);

        walletGuideBtn.setOnClickListener(onClickListener);
    }

    //    点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.wallet_guide_btn:
                    finish();
                    break;
            }
        }
    };
}
