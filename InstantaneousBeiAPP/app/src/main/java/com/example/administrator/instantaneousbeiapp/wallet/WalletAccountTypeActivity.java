package com.example.administrator.instantaneousbeiapp.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.menu.WalletType;
import com.example.administrator.instantaneousbeiapp.menu.WalletTypeAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class WalletAccountTypeActivity extends Activity {
    List<WalletType> list = new ArrayList<WalletType>();
    ImageView walletGuideBtn;//返回按钮
    WalletTypeAdapter walletTypeAdapter;
    Boolean once = true;
    TextView saveing_btton;
    String type;
    private static final int ACCOUNTTYPE_RESULTCODE = 1020;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wallet_account_type);
        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        saveing_btton = (TextView) findViewById(R.id.saveing_btton);

        ListView layoutWalletTypeListView = (ListView) findViewById(R.id.layout_wallet_type_listview);
        getDate();
        walletTypeAdapter = new WalletTypeAdapter(WalletAccountTypeActivity.this,list);
        layoutWalletTypeListView.setAdapter(walletTypeAdapter);

        walletGuideBtn.setOnClickListener(onClickListener);
        saveing_btton.setOnClickListener(onClickListener);
        layoutWalletTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("=======",""+position);
                for(int i = 0 ; i<list.size();i++){
                    if ((i == position) && (once = true)){
                        WalletType wallet = (WalletType) adapterView.getItemAtPosition(position);
                        wallet.setIscheck(true);
                        type = wallet.getType();
                        once = false;
                        handler.sendEmptyMessage(0);
                    }else {
                        WalletType otherwallet = (WalletType) adapterView.getItemAtPosition(i);
                        otherwallet.setIscheck(false);
                    }
                }
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            walletTypeAdapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };
    //点击事件的方法
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.wallet_guide_btn:
                    finish();
                    break;
                case R.id.saveing_btton:
                    if(type != null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("type",""+type);//将签到情况回传给签到按钮
                        setResult(ACCOUNTTYPE_RESULTCODE,saveIntentBtn);
                        Toast.makeText(WalletAccountTypeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(WalletAccountTypeActivity.this, "请选择类型", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    //    里面的数据
    public void getDate() {
        String[] str = {"现金","储蓄卡","信用卡","投资账户","货币资金","实物储值卡","网络充值账户","住房公积金"};
        int[] image = {R.mipmap.wallet_type_money,R.mipmap.wallet_type_subsistence,R.mipmap.wallet_type_dc,
                R.mipmap.wallet_type_invest_user, R.mipmap.wallet_type_currency,R.mipmap.wallet_type_entity,
                R.mipmap.wallet_type_mesh_user, R.mipmap.wallet_type_housing};
        for (int i = 0 ; i<str.length;i++){
            WalletType walletType = new WalletType();
            walletType.setImage(image[i]);
            walletType.setIscheck(false);
            walletType.setType(""+str[i]);
            list.add(walletType);
        }
    }
}
