package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class RenmidChargeAccoutSave extends Activity {
    TimePicker timepicker;
    ImageView back_btn;
    TextView saveing_btton;
    String time;
    Boolean change = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_renmid_charge_accout_save);

        back_btn = (ImageView) findViewById(R.id.back_btn);
        saveing_btton = (TextView) findViewById(R.id.saveing_btton);
        timepicker = (TimePicker) findViewById(R.id.timepicker);

        back_btn.setOnClickListener(onClickListener);
        saveing_btton.setOnClickListener(onClickListener);

        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                time = (hourOfDay + ":" + minute);
                change = true;
            }
        });
    }
    private static final int ADD_RESULTCODE = 1080;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.saveing_btton:
                    //需要将timepicker的时间存下来即time
                    if(change){
//                        intent = new Intent(RenmidChargeAccoutSave.this,RenmidChargeAccountDelete.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("time", ""+time);
//                        intent.putExtras(bundle);
//                        startActivityForResult(intent,1001);//第二个参数为请求码,1000在签到那里用了
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("choseTime",""+time);//将签到情况回传给签到按钮
                        setResult(ADD_RESULTCODE,saveIntentBtn);
                        Toast.makeText(RenmidChargeAccoutSave.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                        //list.add(time);
                    }else {
                        Toast.makeText(RenmidChargeAccoutSave.this, "请选择时间", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };
        List<String> list = new ArrayList<String>();

    //按保存后数据保存到本地
    public void saveSharePreferences(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("timeList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        //editor.putStringSet("timeList",""+list);
        editor.commit();
    }

}
