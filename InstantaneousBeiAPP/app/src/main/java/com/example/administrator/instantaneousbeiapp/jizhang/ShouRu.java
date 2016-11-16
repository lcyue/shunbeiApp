package com.example.administrator.instantaneousbeiapp.jizhang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ShouRu extends Activity implements AdapterView.OnItemClickListener{
    GridView gridView;
    ImageView jizhang_xuanzhe_image;
    TextView jizhang_xuanzhe_text;
    List<HashMap<String,Object>> list;
    int[] image ={R.mipmap.salary,R.mipmap.job,R.mipmap.red_packet,R.mipmap.alimony,R.mipmap.pin,R.mipmap.investment,
            R.mipmap.bonus, R.mipmap.apply,R.mipmap.cash,R.mipmap.refund,R.mipmap.alipay,R.mipmap.rest,R.mipmap.compile};
    String[] text={"工资","兼职","红包","生活费","零花钱","投资",
            "奖金","报销","现金","退款","支付宝","其它", "编辑"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_shouru_layout);

        jizhang_xuanzhe_image = (ImageView) findViewById(R.id.jizhang_xuanzhe_image);
        jizhang_xuanzhe_text = (TextView) findViewById(R.id.jizhang_xuanzhe_text);
        gridView= (GridView) findViewById(R.id.gridview);

        /**
         * 简单适配器
         */
        list = getData();//获取数据源
        String[] from ={"shunbei_image","shunbei_text"};
        int[] to = {R.id.shunbei_image,R.id.shunbei_text};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.gridview_layout,from,to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);//设置点击事件
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
