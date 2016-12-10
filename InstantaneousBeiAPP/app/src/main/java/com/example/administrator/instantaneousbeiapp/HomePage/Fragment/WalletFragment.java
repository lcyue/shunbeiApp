package com.example.administrator.instantaneousbeiapp.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.adapter.WalletTypeAdapter;
import com.example.administrator.instantaneousbeiapp.mvc.WalletTypeItem;
import com.example.administrator.instantaneousbeiapp.wallet.WalletDepositCardActivity;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/1.
 */
public class WalletFragment extends Fragment {
    ListView walletTypelistView;
    ArrayList<WalletTypeItem> list;
    TextView wallet_type_head;
    private static final int CASH_REQUESTCODE = 1010;//请求码
    private static final int CASH_RESULTCODE = 1020;//现金返回码
    private static final int DEPOSIT_CARD_RESULTCODE = 1030;//储蓄卡返回码
    private static final int BLUE_RESULTCODE = 1040;//信用卡返回码
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet_remind_layout, null);//钱包页面的视图化
        walletTypelistView = (ListView) view.findViewById(R.id.wallet_type_listview);

        ArrayList<WalletTypeItem> list = getData();
        WalletTypeAdapter walletTypeAdapter = new WalletTypeAdapter(getActivity(), list);

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view1 = layoutInflater.inflate(R.layout.wallet_type_head, null);
        wallet_type_head = (TextView) view1.findViewById(R.id.wallet_type_head);
        wallet_type_head.setText(""+Allmoney);

        walletTypelistView.setAdapter(walletTypeAdapter);
        walletTypelistView.addHeaderView(view1);
        walletTypelistView.setOnItemClickListener(onItemClickListener);

        return view;
    }

    //    点击事件
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long item) {
            if (i>0){
                Intent intent = new Intent(getActivity(), WalletDepositCardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", ""+string[i-1]);
                bundle.putString("money",""+b);
                intent.putExtras(bundle);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                startActivityForResult(intent,CASH_REQUESTCODE);//第二个参数为请求码
            }
        }
    };

    String[] string={"现金","储蓄卡","信用卡","支付宝"};;
    float Allmoney = 0;
    float b = 0;
    //适配器
    public ArrayList<WalletTypeItem> getData() {
        list = new ArrayList<WalletTypeItem>();
        for (int i = 0; i < 4; i++) {
            WalletTypeItem walletTypeItem = new WalletTypeItem();
            walletTypeItem.setType(""+string[i]);
            walletTypeItem.setBalance("余额");
            float a = (float) (Math.random()*5000+1000);
            b = (float)((Math.round(a*100))/100);
            walletTypeItem.setMoney(b);
            walletTypeItem.setColor("");
            Allmoney += b;
            list.add(walletTypeItem);
        }
        return list;
    }
}
