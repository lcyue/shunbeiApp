package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class RenmidChargeAccount extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_menu_renmid_charge_account);
        ListView menuListView = (ListView) findViewById(R.id.menu_listview);

        LayoutInflater layoutInflater = LayoutInflater.from(RenmidChargeAccount.this);
        View view = layoutInflater.inflate(R.layout.layout_menu_renmid_charge_account_bottm, null);

        List<HashMap<String, String>> list = getDate();
        String from[] = {"menutime"};
        int to[] ={R.id.menu_time};
        SimpleAdapter simpleAdapter = new SimpleAdapter(RenmidChargeAccount.this, list,
                R.layout.layout_menu_renmid_charge_account,
                from,to);

        menuListView.setAdapter(simpleAdapter);
        menuListView.addFooterView(view);
    }
    public  List<HashMap<String, String>> getDate(){
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 2; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("menutime", "08:30");
            list.add(map);
        }
        return list;
    }
}
