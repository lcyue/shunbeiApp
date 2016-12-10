package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class WalletChangeActivity extends Activity {
    LinearLayout walletTypeRetivlayout;//选择类型的按钮
    ImageView walletGuideBtn;//返回按钮
    LinearLayout walletColorbtn;//点击选择按钮
    TextView account_name;
    TextView account_type;
    private static final int ACCOUNTTYPE_REQUESTCODE = 1010;
    private static final int ACCOUNTTYPE_RESULTCODE = 1020;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_change_layout);//更改账户页面

        walletTypeRetivlayout = (LinearLayout) findViewById(R.id.wallet_type_retivlayout);
        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        walletColorbtn = (LinearLayout) findViewById(R.id.wallet_color_btn);
        account_name = (TextView) findViewById(R.id.account_name);
        account_type = (TextView) findViewById(R.id.account_type);

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
                    intent = new Intent(WalletChangeActivity.this, WalletAccountTypeActivity.class);
                    startActivityForResult(intent,ACCOUNTTYPE_REQUESTCODE);//第二个参数为请求码
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
    String type;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case ACCOUNTTYPE_RESULTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Bundle bundle = data.getExtras();
                    type = bundle.getString("type");//第二个页面传的数据
                    account_name.setText(type);
                    account_type.setText(type);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }
}
