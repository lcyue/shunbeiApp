package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.login.GesturesPasswordActivity;
import com.example.administrator.instantaneousbeiapp.login.PhoneBindingActivity;
import com.example.administrator.instantaneousbeiapp.login.XuigaiMimaActivity;

/**
 * Created by Administrator on 2016/10/30.
 */
public class MenuSet extends Activity {
    ImageView backBtn;
    ImageView userImage;
    TextView userName;
    TextView phoneBindingBtton;
    TextView handPaswrodBtn;
    LinearLayout asRegardsBtton;
    LinearLayout changePasswordBtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_set);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        userImage = (ImageView) findViewById(R.id.user_image);
        userName = (TextView) findViewById(R.id.user_name);
        phoneBindingBtton = (TextView) findViewById(R.id.phone_binding_btton);
        handPaswrodBtn = (TextView) findViewById(R.id.hand_paswrod_btn);
        asRegardsBtton = (LinearLayout) findViewById(R.id.as_regards_btton);
        changePasswordBtton = (LinearLayout) findViewById(R.id.change_password_btton);

        backBtn.setOnClickListener(onClickListener);
        userImage.setOnClickListener(onClickListener);
        userName.setOnClickListener(onClickListener);
        phoneBindingBtton.setOnClickListener(onClickListener);
        handPaswrodBtn.setOnClickListener(onClickListener);
        asRegardsBtton.setOnClickListener(onClickListener);
        changePasswordBtton.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.user_image:
                    //动态获取用户的头像
                    break;
                case R.id.user_name:
                    //动态获取用户的名字
                    break;
                case R.id.phone_binding_btton:
                    intent = new Intent(MenuSet.this, PhoneBindingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.hand_paswrod_btn:
                    intent = new Intent(MenuSet.this, GesturesPasswordActivity.class);
                    startActivity(intent);
                    break;
                case R.id.as_regards_btton:
                    intent = new Intent(MenuSet.this, RespectInsranraneoous.class);
                    startActivity(intent);
                    break;
                case R.id.change_password_btton:
                    intent = new Intent(MenuSet.this, XuigaiMimaActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
