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

import com.example.administrator.instantaneousbeiapp.R;

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
    LinearLayout one;
    LinearLayout two;
    LinearLayout three;
    LinearLayout fore;
    GridView gridView;
    ImageView jizhang_xuanzhe_image;
    TextView jizhang_xuanzhe_text;
    List<HashMap<String,Object>> list;
    int[] image ={R.mipmap.salary,R.mipmap.job,R.mipmap.red_packet,R.mipmap.alimony,R.mipmap.pin,R.mipmap.investment,
            R.mipmap.bonus, R.mipmap.apply,R.mipmap.cash,R.mipmap.refund,R.mipmap.alipay,R.mipmap.rest,R.mipmap.compile};
    String[] text={"一般","餐饮","交通","酒水饮料","水果","零食",
            "买菜","衣服","生活用品","话费","房租","医疗", "编辑"};

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
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        three = (LinearLayout) findViewById(R.id.three);
        fore = (LinearLayout) findViewById(R.id.fore);

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
        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        fore.setOnClickListener(onClickListener);
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
                    intent = new Intent(ZhiChuActivity.this,XuanzheShijianActivity.class);
                    startActivity(intent);
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
