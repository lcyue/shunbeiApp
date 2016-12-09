package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Date;
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
    String time;

    final int uplist = 123;
    private static final int ADD_REQUESTCODE = 1070;
    private static final int ADD_RESULTCODE = 1080;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_renmid_charge_account_delete);

        addRemind = (Button) findViewById(R.id.addRemind);
        listView = (ListView) findViewById(R.id.list);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        addRemind.setOnClickListener(onClickListener);
        back_btn.setOnClickListener(onClickListener);

        mAdapter = new remindAdapter(this,list);
        listView.setAdapter(mAdapter);

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

    public void getSharePreferences(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("timeList", Context.MODE_PRIVATE);
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case uplist:
                        mAdapter.notifyDataSetChanged();
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
                    Bundle bundle = new Bundle();
                    bundle.putString("ischeck", "fause");
                    intent.putExtras(bundle);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent,ADD_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };

    /*当需要从第二个页面获得数据返回的时候，重写该方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //请求码      ，    结果码       ， intent对象（包含绑定的数据）
        switch (resultCode){
            case ADD_RESULTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Log.i("SIGNATURE_RESULTCODE", data.toString());
                    Bundle bundle = data.getExtras();
                    time = bundle.getString("choseTime");//第二个页面新传的数据
                    MRemind addtime = new MRemind();
                    addtime.setRemindTime(""+time);
                    list.add(addtime);
                    Message message = new Message();
                    message.what = uplist;
                    handler.sendMessage(message);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }
}
