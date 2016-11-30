package com.example.administrator.instantaneousbeiapp.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.menu.MenuSet;
import com.example.administrator.instantaneousbeiapp.menu.RespectInsranraneoous;
import com.example.administrator.instantaneousbeiapp.menu.UseAccount;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.register.Derive;
import com.example.administrator.instantaneousbeiapp.login.MoreData;
import com.example.administrator.instantaneousbeiapp.login.SuggestionActivity;
import com.example.administrator.instantaneousbeiapp.adapter.mMoreAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/26.
 */
public class MoreFragment extends Fragment {
    ImageView imageView;
    TextView userName;
    ListView userlist;

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

        LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
        View view1 = layoutinflater.inflate(R.layout.layout_more_list_top,null);
        userlist.addHeaderView(view1);

        ArrayList<MoreData> list = getUserListData();

        mMoreAdapter mMoreAdapter = new mMoreAdapter(getActivity(),list);
        userlist.setAdapter(mMoreAdapter);

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 1:
                        //跳用户资料
                        intent = new Intent(getActivity(), UseAccount.class);
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
                    case 4:
                        //跳数据导出
                        intent = new Intent(getActivity(), Derive.class);
                        startActivity(intent);
                        break;
                    case 5:
                        //跳关于我们
                        intent = new Intent(getActivity(), RespectInsranraneoous.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        return view;
    }


    public  ArrayList<MoreData> getUserListData(){
        ArrayList<MoreData> list = new ArrayList<MoreData>();
        //数据库没建立之前的死数据
        String[] strings = {"个人资料","个人设置","意见反馈","数据导出","关于我们"};
        for (int i = 0 ; i < strings.length ; i++){
            MoreData data = new MoreData();
            //data.setUrl("");
            data.setContent(""+strings[i]);
            list.add(data);
        }
        return list;
    }


}
