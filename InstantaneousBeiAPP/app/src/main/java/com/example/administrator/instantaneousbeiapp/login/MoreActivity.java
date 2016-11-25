package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

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
    }

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
