package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class WalletDepositCardActivity extends Activity {
    ImageView walletGuideBtn;//返回按钮
    ImageView walletInstallWhite;//选择页面
    Spinner mSpinner;
    private ArrayAdapter<String> mAdapter ;
    private String [] mStringArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_deposit_card_layout); //消费的详细页面

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        walletInstallWhite = (ImageView) findViewById(R.id.wallet_install_white);
        init();
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

    private void init(){
        mSpinner=(Spinner) findViewById(R.id.spinner);
        mStringArray=getResources().getStringArray(R.array.month);
        //使用自定义的ArrayAdapter
        mAdapter = new TestArrayAdapter(WalletDepositCardActivity.this,mStringArray);
        //设置下拉列表风格(这句不些也行)
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        //监听Item选中事件
        mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
    }

    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
            Toast.makeText(WalletDepositCardActivity.this, "选中了:"+mStringArray[position], Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

    }
}
