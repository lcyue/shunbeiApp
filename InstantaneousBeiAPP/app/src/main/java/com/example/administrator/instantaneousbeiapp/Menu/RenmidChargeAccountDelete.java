package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.adapter.mMoreAdapter;
import com.example.administrator.instantaneousbeiapp.login.MoreData;
import com.example.administrator.instantaneousbeiapp.view.SwipeListLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/22.
 */
public class RenmidChargeAccountDelete extends Activity {

    Button addRemind;
    ListView listView;
    ImageView back_btn;
    private Set<SwipeListLayout> sets = new HashSet();
    remindAdapter mAdapter;
    MRemind data;
    ArrayList<MRemind> list = new ArrayList<MRemind>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_renmid_charge_account_delete);

        addRemind = (Button) findViewById(R.id.addRemind);
        listView = (ListView) findViewById(R.id.list);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        addRemind.setOnClickListener(onClickListener);
        back_btn.setOnClickListener(onClickListener);

        ArrayList<MRemind> list =getListData();
        mAdapter = new remindAdapter(this,list);
        listView.setAdapter(mAdapter);
        if(mAdapter!=null){
            Message message = new Message();
            message.what = uplist;
            handler.sendMessage(message);
        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });
    }

    public  ArrayList<MRemind> getListData(){
        list = new ArrayList<MRemind>();
        //数据库没建立之前的死数据
        String[] strings = {"06:30","07:30","08:30"};
        for (int i = 0 ; i < strings.length ; i++){
            data = new MRemind();
            data.setRemindTime(""+strings[i]);
            list.add(data);
        }
        return list;
    }
    final int uplist = 123;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Intent intent;
            switch (message.what){
                case uplist:
                    intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        data = new MRemind();
                        Serializable serializable = bundle.getSerializable("time");
                        String time = (String) serializable;
                        data.setRemindTime("" + time);
//                        list.add(data);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.addItem(data);
                    }
                    break;
            }
            return true;
        }
    });

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.addRemind:
                    intent = new Intent(RenmidChargeAccountDelete.this,RenmidChargeAccoutSave.class);
                    startActivity(intent);
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };
}
