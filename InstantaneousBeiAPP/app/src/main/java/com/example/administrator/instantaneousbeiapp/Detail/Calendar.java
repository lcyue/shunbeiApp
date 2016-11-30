package com.example.administrator.instantaneousbeiapp.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.R;
<<<<<<< HEAD
import com.example.administrator.instantaneousbeiapp.jizhang.ZhiChuActivity;

=======
import com.example.administrator.instantaneousbeiapp.jizhang.XuanzheShijianActivity;
>>>>>>> develop

/**
 * Created by Administrator on 2016/10/26.
 */
public class Calendar extends Activity {
    ImageView returnBtton;
    ImageView addBtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_calendar);

        returnBtton = (ImageView) findViewById(R.id.return_btton);
        addBtton = (ImageView) findViewById(R.id.add_btton);

        returnBtton.setOnClickListener(onClickListener);
        addBtton.setOnClickListener(onClickListener);
    }

    //点击事件
     View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.return_btton:
                    finish();
                    break;
                case R.id.add_btton:
                        intent = new Intent(Calendar.this, ZhiChuActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
