package com.example.administrator.instantaneousbeiapp.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/25.
 */
public class UseAccount extends Activity {
    ImageView walletGuideBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_use_accout);

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);

        walletGuideBtn.setOnClickListener(onClickListener);
    }
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
