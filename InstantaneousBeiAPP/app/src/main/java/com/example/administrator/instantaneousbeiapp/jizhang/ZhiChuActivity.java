package com.example.administrator.instantaneousbeiapp.jizhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.voice.VoiceActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ZhiChuActivity extends Activity implements AdapterView.OnItemClickListener{
    ImageView jizhang_back_btn;
    TextView jizhang_shouru_btn;
    TextView jizhang_zhichu_btn;
    TextView jizhang_xuanzhezhanghu_btn;
    TextView jizhang_time_btn;
    TextView jizhang_money_btn;
    ImageView jizhang_mine_btn;
    ImageView jizhang_bianji_btn;
    LinearLayout jisuanqi;        //计算器
    LinearLayout shunbei_beizhu;  //输入备注
    LinearLayout jizhang_xuanzhezhanghu;        //选择账户
    LinearLayout xuanzheshijian;  //选择时间
    LinearLayout xuanzhechengyuan;  //选择成员
    ImageView jizhang_gou;
    ImageView jizhang_gou1;
    ImageView jizhang_gou2;
    ImageView jizhang_gou3;
    ImageView jizhang_yu_yin;
    LinearLayout one;
    LinearLayout two;
    LinearLayout three;
    LinearLayout fore;
    GridView gridView;
    ImageView jizhang_xuanzhe_image;
    TextView jizhang_xuanzhe_text;
    TextView one_btn;
    TextView two_btn;
    TextView three_btn;
    TextView four_btn;
    TextView five_btn;
    TextView six_btn;
    TextView seven_btn;
    TextView eight_btn;
    TextView nine_btn;
    TextView zore_btn;
    TextView clear_btn;
    ImageView delete_btn;
    TextView subtract_btn;
    TextView sum_btn;
    TextView ok_btn;
    TextView dot_btn;
    List<HashMap<String,Object>> list;
    int[] image ={R.mipmap.salary,R.mipmap.job,R.mipmap.red_packet,R.mipmap.alimony,R.mipmap.pin,R.mipmap.investment,
            R.mipmap.bonus, R.mipmap.apply,R.mipmap.cash,R.mipmap.refund,R.mipmap.alipay,R.mipmap.rest,R.mipmap.compile};
    String[] text={"一般","餐饮","交通","酒水饮料","水果","零食",
            "买菜","衣服","生活用品","话费","房租","医疗", "编辑"};

    //声明两个参数。接收tvResult前后的值
    double num1=0,num2=0;
    double Result=0;//计算结果
    int op=0;//判断操作数，
    boolean isClickEqu=false;//判断是否按了“=”按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_zhichu_layout);

        jizhang_back_btn = (ImageView) findViewById(R.id.jizhang_back_btn);
        jizhang_shouru_btn= (TextView) findViewById(R.id.jizhang_shouru_btn);
        jizhang_zhichu_btn= (TextView) findViewById(R.id.jizhang_zhichu_btn);
        jizhang_xuanzhezhanghu_btn= (TextView) findViewById(R.id.jizhang_xuanzhezhanghu_btn);
        jizhang_time_btn= (TextView) findViewById(R.id.jizhang_time_btn);
        jizhang_mine_btn= (ImageView) findViewById(R.id.jizhang_mine_btn);
        jizhang_bianji_btn= (ImageView) findViewById(R.id.jizhang_bianji_btn);
        jizhang_xuanzhe_image = (ImageView) findViewById(R.id.jizhang_xuanzhe_image);
        jizhang_xuanzhe_text = (TextView) findViewById(R.id.jizhang_xuanzhe_text);
        shunbei_beizhu = (LinearLayout) findViewById(R.id.shunbei_beizhu);
        jisuanqi = (LinearLayout) findViewById(R.id.jisuanqi);
        jizhang_xuanzhezhanghu = (LinearLayout) findViewById(R.id.jizhang_xuanzhezhanghu);
        xuanzheshijian = (LinearLayout) findViewById(R.id.xuanzheshijian);
        xuanzhechengyuan = (LinearLayout) findViewById(R.id.xuanzhechengyuan);
        jizhang_xuanzhe_image = (ImageView) findViewById(R.id.jizhang_xuanzhe_image);
        jizhang_xuanzhe_text = (TextView) findViewById(R.id.jizhang_xuanzhe_text);
        gridView= (GridView) findViewById(R.id.gridview);
        jizhang_money_btn = (TextView) findViewById(R.id.jizhang_money_btn);
        jizhang_gou= (ImageView) findViewById(R.id.jizhang_gou);
        jizhang_gou1= (ImageView) findViewById(R.id.jizhang_gou1);
        jizhang_gou2= (ImageView) findViewById(R.id.jizhang_gou2);
        jizhang_gou3= (ImageView) findViewById(R.id.jizhang_gou3);
        jizhang_yu_yin = (ImageView) findViewById(R.id.jizhang_yuyin_btn);
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        three = (LinearLayout) findViewById(R.id.three);
        fore = (LinearLayout) findViewById(R.id.fore);
        one_btn = (TextView) findViewById(R.id.one_btn);
        two_btn = (TextView) findViewById(R.id.two_btn);
        three_btn = (TextView) findViewById(R.id.three_btn);
        four_btn = (TextView) findViewById(R.id.four_btn);
        five_btn = (TextView) findViewById(R.id.five_btn);
        six_btn = (TextView) findViewById(R.id.six_btn);
        seven_btn = (TextView) findViewById(R.id.seven_btn);
        eight_btn = (TextView) findViewById(R.id.eight_btn);
        nine_btn = (TextView) findViewById(R.id.nine_btn);
        one_btn = (TextView) findViewById(R.id.one_btn);
        zore_btn = (TextView) findViewById(R.id.zore_btn);
        clear_btn = (TextView) findViewById(R.id.clear_btn);
        delete_btn = (ImageView) findViewById(R.id.delete_btn);
        subtract_btn = (TextView) findViewById(R.id.subtract_btn);
        sum_btn = (TextView) findViewById(R.id.sum_btn);
        ok_btn = (TextView) findViewById(R.id.ok_btn);
        dot_btn = (TextView) findViewById(R.id.dot_btn);

        /**
         * 简单适配器
         */
        list = getData();//获取数据源
        String[] from ={"shunbei_image","shunbei_text"};
        int[] to = {R.id.shunbei_image,R.id.shunbei_text};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.gridview_layout,from,to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);//设置点击事件

        jizhang_bianji_btn.setOnClickListener(onClickListener);
        jizhang_back_btn.setOnClickListener(onClickListener);
        jizhang_mine_btn.setOnClickListener(onClickListener);
        jizhang_time_btn.setOnClickListener(onClickListener);
        jizhang_xuanzhezhanghu_btn.setOnClickListener(onClickListener);
        jizhang_shouru_btn.setOnClickListener(onClickListener);
        jizhang_zhichu_btn.setOnClickListener(onClickListener);
        jizhang_money_btn.setOnClickListener(onClickListener);
        jizhang_yu_yin.setOnClickListener(onClickListener);
        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        fore.setOnClickListener(onClickListener);
        one_btn.setOnClickListener(onClickListener);
        two_btn.setOnClickListener(onClickListener);
        three_btn.setOnClickListener(onClickListener);
        four_btn.setOnClickListener(onClickListener);
        five_btn.setOnClickListener(onClickListener);
        six_btn.setOnClickListener(onClickListener);
        seven_btn.setOnClickListener(onClickListener);
        eight_btn.setOnClickListener(onClickListener);
        nine_btn.setOnClickListener(onClickListener);
        one_btn.setOnClickListener(onClickListener);
        zore_btn.setOnClickListener(onClickListener);
        clear_btn.setOnClickListener(onClickListener);
        delete_btn.setOnClickListener(onClickListener);
        subtract_btn.setOnClickListener(onClickListener);
        sum_btn.setOnClickListener(onClickListener);
        ok_btn.setOnClickListener(onClickListener);
        dot_btn.setOnClickListener(onClickListener);
    }
    //数据源
    public List<HashMap<String,Object>> getData() {
        list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < image.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("shunbei_image", image[i]);
            map.put("shunbei_text", text[i]);
            list.add(map);

        }
        return list;
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                //点击备注
                case R.id.jizhang_bianji_btn:
                    if(shunbei_beizhu.getVisibility()==View.GONE){
                        shunbei_beizhu.setVisibility(View.VISIBLE);
                        jisuanqi.setVisibility(View.GONE);
                        jizhang_xuanzhezhanghu.setVisibility(View.GONE);
                        xuanzheshijian.setVisibility(View.GONE);
                        xuanzhechengyuan.setVisibility(View.GONE);
                    }
                    break;
                //点击选择账户
                case R.id.jizhang_xuanzhezhanghu_btn:
                    if(jizhang_xuanzhezhanghu.getVisibility()==View.GONE){
                        shunbei_beizhu.setVisibility(View.GONE);
                        jisuanqi.setVisibility(View.GONE);
                        jizhang_xuanzhezhanghu.setVisibility(View.VISIBLE);
                        xuanzheshijian.setVisibility(View.GONE);
                        xuanzhechengyuan.setVisibility(View.GONE);
                    }
                    break;
                //点击选择时间
                case R.id.jizhang_time_btn:
                    if(xuanzheshijian.getVisibility()==View.GONE){
                        shunbei_beizhu.setVisibility(View.GONE);
                        jisuanqi.setVisibility(View.GONE);
                        jizhang_xuanzhezhanghu.setVisibility(View.GONE);
                        xuanzheshijian.setVisibility(View.VISIBLE);
                        xuanzhechengyuan.setVisibility(View.GONE);
                    }
                    break;
                //点击选择成员
                case R.id.jizhang_mine_btn:
                    if(xuanzhechengyuan.getVisibility()==View.GONE){
                        shunbei_beizhu.setVisibility(View.GONE);
                        jisuanqi.setVisibility(View.GONE);
                        jizhang_xuanzhezhanghu.setVisibility(View.GONE);
                        xuanzheshijian.setVisibility(View.GONE);
                        xuanzhechengyuan.setVisibility(View.VISIBLE);
                    }
                    break;
                //点击收入
                case R.id.jizhang_shouru_btn:
                    jizhang_shouru_btn.setTextColor(getResources().getColor(R.color.green_btn));
                    jizhang_zhichu_btn.setTextColor(getResources().getColor(R.color.black_gery));
                    break;
                case R.id.jizhang_zhichu_btn:
                    jizhang_shouru_btn.setTextColor(getResources().getColor(R.color.black_gery));
                    jizhang_zhichu_btn.setTextColor(getResources().getColor(R.color.green_btn));
                    break;
                //点击返回
                case R.id.jizhang_back_btn:
                    finish();
                    break;
                //点击数字
                case R.id.jizhang_money_btn:
                    if(jisuanqi.getVisibility()==View.GONE){
                        shunbei_beizhu.setVisibility(View.GONE);
                        jisuanqi.setVisibility(View.VISIBLE);
                        jizhang_xuanzhezhanghu.setVisibility(View.GONE);
                        xuanzheshijian.setVisibility(View.GONE);
                        xuanzhechengyuan.setVisibility(View.GONE);
                    }
                    break;
                case R.id.fore:
                    if(jizhang_gou3.getVisibility()==View.GONE){
                        jizhang_gou.setVisibility(View.GONE);
                        jizhang_gou3.setVisibility(View.VISIBLE);
                        jizhang_gou2.setVisibility(View.GONE);
                        jizhang_gou1.setVisibility(View.GONE);
                    }
                    break;
                case R.id.three:
                    if(jizhang_gou2.getVisibility()==View.GONE){
                        jizhang_gou.setVisibility(View.GONE);
                        jizhang_gou2.setVisibility(View.VISIBLE);
                        jizhang_gou3.setVisibility(View.GONE);
                        jizhang_gou1.setVisibility(View.GONE);
                    }
                    break;
                case R.id.two:
                    if(jizhang_gou1.getVisibility()==View.GONE){
                        jizhang_gou.setVisibility(View.GONE);
                        jizhang_gou1.setVisibility(View.VISIBLE);
                        jizhang_gou2.setVisibility(View.GONE);
                        jizhang_gou3.setVisibility(View.GONE);
                    }
                    break;
                case R.id.one:
                    if(jizhang_gou.getVisibility()==View.GONE){
                        jizhang_gou3.setVisibility(View.GONE);
                        jizhang_gou.setVisibility(View.VISIBLE);
                        jizhang_gou2.setVisibility(View.GONE);
                        jizhang_gou1.setVisibility(View.GONE);
                    }
                    break;
                case R.id.jizhang_yuyin_btn:
                    intent = new Intent(ZhiChuActivity.this, VoiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.one_btn:
                    String str1 = jizhang_money_btn.getText().toString();
                    if(str1.equals("0.00")){
                        jizhang_money_btn.setText("1");
                    }else {
                        str1 += "1";
                        jizhang_money_btn.setText(str1);
                    }
                    break;
                case R.id.two_btn:
                    String str2 = jizhang_money_btn.getText().toString();
                    if(str2.equals("0.00")){
                        jizhang_money_btn.setText("2");
                    }else {
                        str2 += "2";
                        jizhang_money_btn.setText(str2);
                    }
                    break;
                case R.id.three_btn:
                    String str3 = jizhang_money_btn.getText().toString();
                    if(str3.equals("0.00")){
                        jizhang_money_btn.setText("3");
                    }else {
                        str3 += "3";
                        jizhang_money_btn.setText(str3);
                    }
                    break;
                case R.id.four_btn:
                    String str4 = jizhang_money_btn.getText().toString();
                    if(str4.equals("0.00")){
                        jizhang_money_btn.setText("4");
                    }else {
                        str4 += "4";
                        jizhang_money_btn.setText(str4);
                    }
                    break;
                case R.id.five_btn:
                    String str5 = jizhang_money_btn.getText().toString();
                    if(str5.equals("0.00")){
                        jizhang_money_btn.setText("5");
                    }else {
                        str5 += "5";
                        jizhang_money_btn.setText(str5);
                    }
                    break;
                case R.id.six_btn:
                    String str6 = jizhang_money_btn.getText().toString();
                    if(str6.equals("0.00")){
                        jizhang_money_btn.setText("6");
                    }else {
                        str6 += "6";
                        jizhang_money_btn.setText(str6);
                    }
                    break;
                case R.id.seven_btn:
                    String str7 = jizhang_money_btn.getText().toString();
                    if(str7.equals("0.00")){
                        jizhang_money_btn.setText("7");
                    }else {
                        str7 += "7";
                        jizhang_money_btn.setText(str7);
                    }
                    break;
                case R.id.eight_btn:
                    String str8 = jizhang_money_btn.getText().toString();
                    if(str8.equals("0.00")){
                        jizhang_money_btn.setText("8");
                    }else {
                        str8 += "8";
                        jizhang_money_btn.setText(str8);
                    }
                    break;
                case R.id.nine_btn:
                    String str9 = jizhang_money_btn.getText().toString();
                    if(str9.equals("0.00")){
                        jizhang_money_btn.setText("9");
                    }else {
                        str9 += "9";
                        jizhang_money_btn.setText(str9);
                    }
                    break;
                case R.id.clear_btn:
                    jizhang_money_btn.setText("");
                    break;
                case R.id.ok_btn:
                    if(isClickEqu == false){
                        String strok = jizhang_money_btn.getText().toString();
                        num2 = Double.valueOf(strok);
                        jizhang_money_btn.setText(null);
                        switch (op){
                            case 0:
                                Result = num1 - num2;
                                break;
                            case 1:
                                Result = num1 + num2;
                                break;
                        }
                        jizhang_money_btn.setText(""+Result);
                        isClickEqu = true;
                    }else {
                        jizhang_money_btn.setText("0.00");
                        isClickEqu = false;
                    }
                    break;
                case R.id.dot_btn:
                    if(isClickEqu==false){
                        String strdot = jizhang_money_btn.getText().toString();
                        strdot += ".";
                        jizhang_money_btn.setText(strdot);
                        isClickEqu = true ;
                    }else {
                        Toast.makeText(ZhiChuActivity.this,"你的输入有误！",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.delete_btn:
                    String str = jizhang_money_btn.getText().toString();
                    try{
                        jizhang_money_btn.setText(str.substring(0,str.length()-1));
                    }catch (Exception e){
                        jizhang_money_btn.setText("");
                    }
                    break;
                case R.id.subtract_btn:
                    String strsub = jizhang_money_btn.getText().toString();
                    num1 = Double.valueOf(strsub);
                    jizhang_money_btn.setText(null);
                    op = 0;
                    break;
                case R.id.sum_btn:
                    String strsum = jizhang_money_btn.getText().toString();
                    num1 = Double.valueOf(strsum);
                    jizhang_money_btn.setText(null);
                    op = 1;
                    break;
            }
        }
    };
    /**
     * GridView的监听方法
     *
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        jizhang_xuanzhe_image.setImageResource(image[position]);
        jizhang_xuanzhe_text.setText(text[position]);
    }
}
