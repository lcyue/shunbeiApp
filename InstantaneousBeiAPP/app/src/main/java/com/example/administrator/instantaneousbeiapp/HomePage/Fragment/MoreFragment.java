package com.example.administrator.instantaneousbeiapp.HomePage.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.Menu.MenuSet;
import com.example.administrator.instantaneousbeiapp.Menu.RespectInsranraneoous;
import com.example.administrator.instantaneousbeiapp.Menu.UseAccount;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.Register.Derive;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletRemindActivity;
import com.example.administrator.instantaneousbeiapp.login.MoreData;
import com.example.administrator.instantaneousbeiapp.login.SuggestionActivity;
import com.example.administrator.instantaneousbeiapp.login.mMoreAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class MoreFragment extends android.support.v4.app.Fragment {
    ImageView imageView;
    TextView userName;
    ListView userlist;
    ListView datalist;
    List<MoreData> list = new ArrayList<MoreData>();
    List<MoreData> dalist = new ArrayList<MoreData>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_more, null);
        imageView = (ImageView) view.findViewById(R.id.more_img);
        userName = (TextView) view.findViewById(R.id.more_user_name);
        userlist = (ListView) view.findViewById(R.id.more_user_list);
        datalist = (ListView) view.findViewById(R.id.more_data_list);

        getUserListData();
        mMoreAdapter mMoreAdapter = new mMoreAdapter(getActivity(),list);
        userlist.setAdapter(mMoreAdapter);

        getDataListData();
        mMoreAdapter mMoreAdapter1 = new mMoreAdapter(getActivity(),dalist);
        datalist.setAdapter(mMoreAdapter1);


        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        //跳用户资料
                        intent = new Intent(getActivity(), UseAccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //跳记账提醒
                        intent = new Intent(getActivity(), WalletRemindActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //跳设置
                        intent = new Intent(getActivity(), MenuSet.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳意见反馈
                        intent = new Intent(getActivity(), SuggestionActivity.class);
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
                        intent = new Intent(getActivity(), Derive.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //跳关于我们
                        intent = new Intent(getActivity(), RespectInsranraneoous.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Log.i("adapterView", "=====>" + adapterView);

        }
    };
    public void getUserListData(){
        //数据库没建立之前的死数据
        String[] strings = {"个人资料","记账提醒","个人设置","意见反馈"};
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
