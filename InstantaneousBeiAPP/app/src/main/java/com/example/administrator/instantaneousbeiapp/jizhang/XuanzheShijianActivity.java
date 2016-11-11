package com.example.administrator.instantaneousbeiapp.jizhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class XuanzheShijianActivity extends Activity {
    ImageView jizhang_back_btn;
    TextView jizhang_shouru_btn;
    TextView jizhang_zhichu_btn;
    TextView jizhang_xuanzhezhanghu_btn;
    TextView jizhang_time_btn;
    ImageView jizhang_mine_btn;
    ImageView jizhang_bianji_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_xuanzheshijian_layout);

        jizhang_back_btn = (ImageView) findViewById(R.id.jizhang_back_btn);
        jizhang_shouru_btn= (TextView) findViewById(R.id.jizhang_shouru_btn);
        jizhang_zhichu_btn= (TextView) findViewById(R.id.jizhang_zhichu_btn);
        jizhang_xuanzhezhanghu_btn= (TextView) findViewById(R.id.jizhang_xuanzhezhanghu_btn);
        jizhang_time_btn= (TextView) findViewById(R.id.jizhang_time_btn);
        jizhang_mine_btn= (ImageView) findViewById(R.id.jizhang_mine_btn);
        jizhang_bianji_btn= (ImageView) findViewById(R.id.jizhang_bianji_btn);

        jizhang_bianji_btn.setOnClickListener(onClickListener);
        jizhang_back_btn.setOnClickListener(onClickListener);
        jizhang_mine_btn.setOnClickListener(onClickListener);
        jizhang_time_btn.setOnClickListener(onClickListener);
        jizhang_xuanzhezhanghu_btn.setOnClickListener(onClickListener);
        jizhang_shouru_btn.setOnClickListener(onClickListener);
        jizhang_zhichu_btn.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.jizhang_bianji_btn:
                    intent = new Intent(XuanzheShijianActivity.this,TianjiaoshouruActivity.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_xuanzhezhanghu_btn:
                    intent = new Intent(XuanzheShijianActivity.this,XuanzheZhanghuActivity.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_time_btn:
                    intent = new Intent(XuanzheShijianActivity.this,ShouRu.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_mine_btn:
                    intent = new Intent(XuanzheShijianActivity.this,XuanzheChengyuanActivity.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_zhichu_btn:
                    intent = new Intent(XuanzheShijianActivity.this,ZhiChuActivity.class);
                    startActivity(intent);
                    break;
                case R.id.jizhang_back_btn:
                    finish();
                    break;
            }
        }
    };
}
