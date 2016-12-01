package com.example.administrator.instantaneousbeiapp.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.view.DBManager;
import com.example.administrator.instantaneousbeiapp.view.SignCalendar;
import com.example.administrator.instantaneousbeiapp.view.sqlit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MyCalendard extends Activity {
    private Button btn_signIn;
    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private TextView popupwindow_calendar_month;
    private SignCalendar calendar;
    private List<String> list = new ArrayList<String>(); //设置标记列表
    DBManager dbManager;
    boolean isinput = false;
    private String date1 = null;//单天日期
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calendar);

        // 初始化DBManager
        dbManager = new DBManager(this);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date1 = formatter.format(curDate);

        back_btn = (ImageView) findViewById(R.id.back_btn);
        popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        calendar = (SignCalendar) findViewById(R.id.popupwindow_calendar);
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
                + calendar.getCalendarMonth() + "月");
        if (null != date) {
            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");

            calendar.showCalendar(years, month);
        }
        add("2016-11-30");
        Log.i("list1","========="+list.toString());
        query();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntentBtn = getIntent();
                backIntentBtn.putExtra("Text",""+btn_signIn.getText());//将签到情况回传给签到按钮
                setResult(1001,backIntentBtn);
                finish();
            }
        });

        if(isinput){
            btn_signIn.setText("今日已签");
            btn_signIn.setEnabled(false);
        }
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today= calendar.getThisday();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           /* calendar.removeAllMarks();
           list.add(df.format(today));
           calendar.addMarks(list, 0);*/
                //将当前日期标示出来
                add(df.format(today));
                Log.i("list2","========="+list.toString());
                //calendar.addMark(today, 0);
                query();
                HashMap<String, Integer> bg = new HashMap<String, Integer>();

                calendar.setCalendarDayBgColor(today, R.mipmap.icon_qindao);
                btn_signIn.setText("今日已签");
                btn_signIn.setEnabled(false);
            }
        });

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new SignCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month
                        .setText(year + "年" + month + "月");
            }
        });
    }

    public void add(String date) {
        ArrayList<sqlit> persons = new ArrayList<sqlit>();
        sqlit person1 = new sqlit(date,"true");
        persons.add(person1);
        dbManager.add(persons);
    }

    public void query()
    {
        List<sqlit> persons = dbManager.query();
        for (sqlit person : persons)
        {
            list.add(person.date);
            if(date1.equals(person.getDate())){
                isinput=true;
            }
        }
        calendar.addMarks(list, 0);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        dbManager.closeDB();// 释放数据库资源
    }
}
