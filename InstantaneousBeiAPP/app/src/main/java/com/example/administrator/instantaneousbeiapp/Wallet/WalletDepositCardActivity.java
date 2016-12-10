package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/20.
 */
public class WalletDepositCardActivity extends Activity {
    ImageView walletGuideBtn;//返回按钮
    ImageView walletInstallWhite;//选择页面
    Spinner mSpinner;
    TextView money;
    TextView card_type;
    private ArrayAdapter<String> mAdapter ;
    private String [] mStringArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_deposit_card_layout); //消费的详细页面

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        walletInstallWhite = (ImageView) findViewById(R.id.wallet_install_white);
        money = (TextView) findViewById(R.id.money);
        card_type = (TextView) findViewById(R.id.card_type);

        init();
        walletGuideBtn.setOnClickListener(onClickListener);
        walletInstallWhite.setOnClickListener(onClickListener);
        getMyIntent();
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

    public void getMyIntent(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String type = bundle.getString("type");
            String money = bundle.getString("money");
            if (type != null){
                this.card_type.setText(type);
            }
            if (money != null){
                this.money.setText(money);
            }
        }
    }
}
