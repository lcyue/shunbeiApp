package com.example.administrator.instantaneousbeiapp.Wallet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.instantaneousbeiapp.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class WalletAccountType extends Activity {
    List<HashMap<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wallet_account_type);

        ListView layoutWalletTypeListView = (ListView) findViewById(R.id.layout_wallet_type_listview);
        String[] from = {"wallettypeimg", "wallettypetext",
                "wallettypecontenttext", "wallettypetcancelimg"};
        int[] to = {R.id.layout_wallet_type_img, R.id.layout_wallet_type_text,
                R.id.layout_wallet_type_con, R.id.layout_wallet_type_cancel_img};
        getDate();
        SimpleAdapter walletTypeAdapter = new SimpleAdapter(WalletAccountType.this,list,
                R.layout.layout_wallet_account_type_text, from, to);

        layoutWalletTypeListView.setAdapter(walletTypeAdapter);
    }

    public void getDate() {
        list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("wallettypeimg", R.mipmap.wallet_type_money);
        map.put("wallettypetext", "现金");
        map.put("wallettypecontenttext", null);
        map.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map);

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("wallettypeimg", R.mipmap.wallet_type_subsistence);
        map1.put("wallettypetext", "储蓄卡");
        map1.put("wallettypecontenttext", null);
        map1.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map1);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("wallettypeimg", R.mipmap.wallet_type_dc);
        map2.put("wallettypetext", "信用卡");
        map2.put("wallettypecontenttext", null);
        map2.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("wallettypeimg", R.mipmap.wallet_type_invest_user);
        map3.put("wallettypetext", "投资账户");
        map3.put("wallettypecontenttext","（资金账户、证券账户等）");
        map3.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map3);

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("wallettypeimg", R.mipmap.wallet_type_currency);
        map4.put("wallettypetext", "货币资金");
        map4.put("wallettypecontenttext", null);
        map4.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map4);

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("wallettypeimg", R.mipmap.wallet_type_entity);
        map5.put("wallettypetext", "实物储值卡");
        map5.put("wallettypecontenttext", "（购物卡、公交卡等）");
        map5.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map5);

        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("wallettypeimg", R.mipmap.wallet_type_mesh_user);
        map6.put("wallettypetext","网络充值账户");
        map6.put("wallettypecontenttext", "（支付宝、微信钱包等）");
        map6.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map6);

        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("wallettypeimg", R.mipmap.wallet_type_housing);
        map7.put("wallettypetext", "住房公积金");
        map7.put("wallettypecontenttext", null);
        map7.put("wallettypetcancelimg", R.mipmap.ico_cancel);
        list.add(map7);

    }
}
