<<<<<<< HEAD
package com.example.administrator.instantaneousbeiapp.Homepage.Fragment;
=======
package com.example.administrator.instantaneousbeiapp.homePage.Fragment;
>>>>>>> 37280b3161b9597bce3c01053b7d6f0cd9e05f7d

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
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.wallet.WalletDepositCardActivity;
import com.example.administrator.instantaneousbeiapp.adapter.WalletTypeAdapter;
import com.example.administrator.instantaneousbeiapp.mvc.WalletTypeItem;
import java.util.ArrayList;

<<<<<<< HEAD

import com.example.administrator.instantaneousbeiapp.Wallet.WalletChangeActivity;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletDepositCardActivity;


=======
>>>>>>> 37280b3161b9597bce3c01053b7d6f0cd9e05f7d

/**
 * Created by Administrator on 2016/11/1.
 */
public class WalletFragment extends Fragment {
    ListView walletTypelistView;
    ArrayList<WalletTypeItem> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet_remind_layout, null);//钱包页面的视图化
        walletTypelistView = (ListView) view.findViewById(R.id.wallet_type_listview);

        ArrayList<WalletTypeItem> list = getData();
        WalletTypeAdapter walletTypeAdapter = new WalletTypeAdapter(getActivity(), list);

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view1 = layoutInflater.inflate(R.layout.wallet_type_head, null);

        walletTypelistView.setAdapter(walletTypeAdapter);
        walletTypelistView.addHeaderView(view1);
        walletTypelistView.setOnItemClickListener(onItemClickListener);

        return view;
    }

    //    点击事件
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long item) {
            Log.i("onItemClick", "i====>" + i);
            Log.i("onItemClick", "item=====>" + item);

            Intent intent = new Intent(getActivity(), WalletDepositCardActivity.class);
            startActivity(intent);

        }
    };


    //适配器
    public ArrayList<WalletTypeItem> getData() {
        list = new ArrayList<WalletTypeItem>();
        for (int i = 0; i < 4; i++) {
            WalletTypeItem walletTypeItem = new WalletTypeItem();
            walletTypeItem.setType("hahah");
            walletTypeItem.setBalance("hahha余额");
            walletTypeItem.setMoney(500.00);
            walletTypeItem.setColor("");
            list.add(walletTypeItem);
        }
        return list;
    }
}
