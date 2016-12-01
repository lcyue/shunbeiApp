package com.example.administrator.instantaneousbeiapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.view.DBManager;
import com.example.administrator.instantaneousbeiapp.view.DemoSignCalendar;
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
    private DemoSignCalendar calendar;
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
        calendar = (DemoSignCalendar) findViewById(R.id.popupwindow_calendar);
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
                + calendar.getCalendarMonth() + "月");
        if (null != date) {
            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");

            calendar.showCalendar(years, month);
            calendar.setCalendarDayBgColor(date,
                    R.color.blue_cyan);
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        query();
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
                //calendar.addMark(today, 0);
                query();
                HashMap<String, Integer> bg = new HashMap<String, Integer>();

                calendar.setCalendarDayBgColor(today, R.mipmap.gou);
                btn_signIn.setText("今日已签");
                btn_signIn.setEnabled(false);
            }
        });
        //监听所选中的日期
		calendar.setOnCalendarClickListener(new DemoSignCalendar.OnCalendarClickListener() {

			public void onCalendarClick(int row, int col, String dateFormat) {
				int month = Integer.parseInt(dateFormat.substring(
						dateFormat.indexOf("-") + 1,
						dateFormat.lastIndexOf("-")));

				if (calendar.getCalendarMonth() - month == 1//跨年跳转
						|| calendar.getCalendarMonth() - month == -11) {
					calendar.lastMonth();

				} else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
						|| month - calendar.getCalendarMonth() == -11) {
					calendar.nextMonth();

				} else {
					list.add(dateFormat);
					calendar.addMarks(list, 0);
					calendar.removeAllBgColor();
					calendar.setCalendarDayBgColor(dateFormat,
							R.mipmap.gou);
					date = dateFormat;//最后返回给全局 date
				}
			}
		});

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new DemoSignCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month
                        .setText(year + "年" + month + "月");
            }
        });
    }

    public void add(String date)
    {
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
            list.add(person.dates);
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
