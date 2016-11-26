package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.Menu.RespectInsranraneoous;
import com.example.administrator.instantaneousbeiapp.Menu.UseAccount;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.Register.Derive;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletRemindActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public class MoreActivity extends Activity {
    ImageView imageView;
    TextView userName;
    ListView userlist;
    ListView datalist;
    List<MoreData> list = new ArrayList<MoreData>();
    List<MoreData> dalist = new ArrayList<MoreData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_more);

        imageView = (ImageView) findViewById(R.id.more_img);
        userName = (TextView) findViewById(R.id.more_user_name);
        userlist = (ListView) findViewById(R.id.more_user_list);
        datalist = (ListView) findViewById(R.id.more_data_list);

        getUserListData();
        mMoreAdapter mMoreAdapter = new mMoreAdapter(this,list);
        userlist.setAdapter(mMoreAdapter);

        getDataListData();
        mMoreAdapter mMoreAdapter1 = new mMoreAdapter(this,dalist);
        datalist.setAdapter(mMoreAdapter1);


        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        //跳用户资料
                        intent = new Intent(MoreActivity.this, UseAccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //跳记账提醒
                        intent = new Intent(MoreActivity.this, WalletRemindActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //跳选择颜色
                        intent = new Intent(MoreActivity.this, UseAccount.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳意见反馈
                        intent = new Intent(MoreActivity.this, SuggestionActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        datalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        //跳数据导出
                        intent = new Intent(MoreActivity.this, Derive.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //跳关于我们
                        intent = new Intent(MoreActivity.this, RespectInsranraneoous.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Log.i("adapterView", "=====>" + adapterView);
            Log.i("view", "=====>" + view);
            switch (view.getId()){
                case R.id.more_user_list:
                    switch (position){
                        case 0:
                            Intent intent = new Intent(MoreActivity.this,ZhuceActivity.class);
                            startActivity(intent);
                            break;
                    }

                    break;
                case R.id.more_data_list:

                    break;
            }


        }
    };
    public void getUserListData(){
        //数据库没建立之前的死数据
        String[] strings = {"个人资料","记账提醒","背景皮肤","意见反馈"};
        for (int i = 0 ; i < 4 ; i++){
            MoreData data = new MoreData();
            //data.setUrl("");
            data.setContent(""+strings[i]);
            list.add(data);
        }
    }

    public void getDataListData(){
        //数据库没建立之前的死数据
        String[] strings = {"数据导出","关于我们"};
        for (int i = 0 ; i < 2 ; i++){
            MoreData data = new MoreData();
            //data.setUrl("");
            data.setContent(""+strings[i]);
            dalist.add(data);
        }
    }
}
