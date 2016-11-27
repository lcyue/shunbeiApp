package com.example.administrator.instantaneousbeiapp.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/24.
 */
public class Statement extends Activity {
    TextView week;
    TextView year;
    TextView day;
    TextView mouth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_statement);

        week = (TextView) findViewById(R.id.week_btton);
        year = (TextView) findViewById(R.id.year_btton);
        day = (TextView) findViewById(R.id.day_btton);
        mouth = (TextView) findViewById(R.id.moth_btton);
    }
}
