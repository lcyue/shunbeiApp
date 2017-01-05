package com.example.administrator.instantaneousbeiapp.homepage.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class StatementFragment extends Fragment {
    RadioButton weekBtton;
    RadioButton mothBtton;
    RadioButton yearBtton;
    RadioButton dayBtton;
    ImageView transform;
    TextView total_expenditure_text;
    List<Statementlist> list;
    ListView total_listview;
    TextView total_expenditure_context_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statement, null);//报表的视图转化

        weekBtton = (RadioButton) view.findViewById(R.id.week_btton);
        mothBtton = (RadioButton) view.findViewById(R.id.moth_btton);
        yearBtton = (RadioButton) view.findViewById(R.id.year_btton);
        dayBtton = (RadioButton) view.findViewById(R.id.day_btton);
        transform = (ImageView) view.findViewById(R.id.transform);
        total_expenditure_text = (TextView) view.findViewById(R.id.total_expenditure_text);
        total_listview = (ListView) view.findViewById(R.id.total_listview);
        total_expenditure_context_text = (TextView) view.findViewById(R.id.total_expenditure_context_text);

        weekBtton.setOnClickListener(onClickListener);
        mothBtton.setOnClickListener(onClickListener);
        yearBtton.setOnClickListener(onClickListener);
        dayBtton.setOnClickListener(onClickListener);
        transform.setOnClickListener(onClickListener);

//        list = getData();
        //StatementBasedapter statementBasedapter = new StatementBasedapter(StatementFragment.this,list);
        //total_listview.setAdapter(statementBasedapter);
        return view;
    }
//    public List<Statementlist> getData(){
//        list = new ArrayList<Statementlist>();
//        for(int i =0;i<list.size();i++){
//            Statementlist statementlist = new Statementlist();
//            statementlist.setText_type();
//            statementlist.setText_number();
//            statementlist.setText_percentage();
//        }
//        return list;
//    }

    int num;
    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.week_btton:
                    num = 2 ;
                    weekBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    weekBtton.setChecked(true);
                    break;
                case R.id.moth_btton:
                    num = 1 ;
                    mothBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setChecked(true);
                    break;
                case R.id.year_btton:
                    num = 0 ;
                    yearBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setChecked(true);
                    new Thread(){
                        @Override
                        public void run() {
                            yearxpendsum();
                        }
                    }.start();
                    total_expenditure_context_text.setText(""+sum_year);
                    break;
                case R.id.day_btton:
                    num = 3 ;
                    dayBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setChecked(true);
                    break;
                case R.id.transform:
                    if(num == 0){
                        if(total_expenditure_text.getText().toString().equals("总支出")){
                            total_expenditure_text.setText("总收入");
                            new Thread(){
                                @Override
                                public void run() {
                                    yearincomesum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+income_sum_year);
                            Log.i("total_expenditure","==============================="+income_sum_year);
                        }else {
                            total_expenditure_text.setText("总支出");
                            new Thread(){
                                @Override
                                public void run() {
                                    yearxpendsum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+sum_year);
                            Log.i("total_expenditure","==============================="+sum_year);
                        }
                    }else if(num == 1){
                        if(total_expenditure_text.getText().toString().equals("总支出")){
                            total_expenditure_text.setText("总收入");
                            new Thread(){
                                @Override
                                public void run() {
                                    mothincomesum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+expend_mouth);
                            Log.i("total_expenditure","==============================="+expend_mouth);
                        }else {
                            total_expenditure_text.setText("总支出");
                            new Thread(){
                                @Override
                                public void run() {
                                    mothexpendsum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+sum_mouth);
                            Log.i("total_expenditure","==============================="+sum_mouth);
                        }

                    }else if(num == 2){

                    }else if(num == 3){
                        if(total_expenditure_text.getText().toString().equals("总支出")){
                            total_expenditure_text.setText("总收入");
                            new Thread(){
                                @Override
                                public void run() {
                                    incomesum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+income_sum_money);
                        }else {
                            total_expenditure_text.setText("总支出");
                            new Thread(){
                                @Override
                                public void run() {
                                    expendsum();
                                }
                            }.start();
                            total_expenditure_context_text.setText(""+expend_sum_money);
                        }
                    }
                    break;
            }
        }
    };


    //得到ID
    public void getSharePreferences(){
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("shunbei", Context.MODE_PRIVATE);
        user_login_id = sharedPreferences1.getInt("user_id",user_id);
    }

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR); //获取年份
    int month = c.get(Calendar.MONTH);//获取月
    int day = c.get(Calendar.DATE);//获取日
    int Week = c.get(Calendar.DAY_OF_WEEK);//获取周

    int user_id = 0;
    int status;
    String message;
    int user_login_id;
    double income_sum_money;
    double expend_sum_money;
    double sum_mouth;
    double sum_year;
    double income_sum_year;
    double expend_mouth;

    //每天总收入接口
    public void incomesum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/incomesum?"+"user_login_id="+user_login_id+"&income_date="+day;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("incomesummoney");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    income_sum_money = jsonObject1.getDouble("income_sum_money");    //每天收入总数
                }
            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //每天总支出接口
    public void expendsum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/expendsum?"+"user_login_id="+user_login_id+"&income_date="+day;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("expendsummoney");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    expend_sum_money = jsonObject1.getDouble("expend_sum_money");    //每天支出总数
                }

            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //每月总支出接口
    public void mothexpendsum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/mothexpendsum?"+"user_login_id="+user_login_id+"&expend_month="+month;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString(
                        "message");
                JSONArray jsonArray = jsonObject.getJSONArray("expendmonthdata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    sum_mouth = jsonObject1.getDouble("sum(expend_sum_money)");    //每月支出总数
                }
            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //每月总收入接口
    public void mothincomesum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/mothincomesum?"+"user_login_id="+user_login_id+"&income_month="+month;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString(
                        "message");
                JSONArray jsonArray = jsonObject.getJSONArray("monthdata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    expend_mouth = jsonObject1.getDouble("sum(income_sum_money)");    //每月收入总数
                }
            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //每年总支出接口
    public void yearxpendsum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/yearxpendsum?"+"user_login_id="+user_login_id+"&expend_year="+year;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("expendyeardata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    sum_year = jsonObject1.getDouble("sum(expend_sum_money)");//每年支出总数
                }
                Log.i("=========",""+sum_year);
            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //每年总收入接口
    public void yearincomesum(){
        getSharePreferences();
        String httpurl = "http://192.168.7.5/index.php/home/index/yearincomesum?"+"user_login_id="+user_login_id+"&income_year="+year;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();
                Log.i("data====>",""+data);

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("yeardata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    income_sum_year = jsonObject1.getDouble("sum(income_sum_money)"); //每年收入总数
                }
                Log.i("income_sum_year","============================="+income_sum_year);
            }else {
                Log.i("getResponseCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
