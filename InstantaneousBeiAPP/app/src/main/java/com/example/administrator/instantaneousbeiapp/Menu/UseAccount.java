package com.example.administrator.instantaneousbeiapp.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.login.PhoneBindingActivity;
import com.example.administrator.instantaneousbeiapp.login.RegisterActivity;


/**
 * Created by Administrator on 2016/10/25.
 */
public class UseAccount extends Activity {
    ImageView walletGuideBtn;
    ImageView useImg;
    TextView signaturebtton;
    TextView birthdayBtton;
    TextView phoneBindingBtton;
    TextView userName;
    Button saveingBtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_use_accout);

        walletGuideBtn = (ImageView) findViewById(R.id.wallet_guide_btn);
        useImg = (ImageView) findViewById(R.id.use_img);
        signaturebtton = (TextView) findViewById(R.id.signature_btton);
        birthdayBtton = (TextView) findViewById(R.id.birthday_btton);
        phoneBindingBtton = (TextView) findViewById(R.id.phone_binding_btton);
        saveingBtton = (Button) findViewById(R.id.saveing_btton);
        userName = (TextView) findViewById(R.id.user_name);

        walletGuideBtn.setOnClickListener(onClickListener);
        useImg.setOnClickListener(onClickListener);
        signaturebtton.setOnClickListener(onClickListener);
        birthdayBtton.setOnClickListener(onClickListener);
        phoneBindingBtton.setOnClickListener(onClickListener);
        saveingBtton.setOnClickListener(onClickListener);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.wallet_guide_btn:
                    Toast.makeText(UseAccount.this, "没有进行保存，设置不成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.use_img:
                    //需要获取用户的头像
                    break;
                case R.id.signature_btton:
                    intent = new Intent(UseAccount.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.birthday_btton:

                    if (bool == true) {
                        popupWindow.dismiss();
                        bool = popupWindow.isShowing();
                    } else {
                        createPopupWindow();
                        bool = popupWindow.isShowing();
                    }
                    break;
                case R.id.phone_binding_btton:
                    intent = new Intent(UseAccount.this, PhoneBindingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.saveing_btton:
                    Toast.makeText(UseAccount.this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.user_name:
                    //需要获取用户的名字
                    break;
                case R.id.cancel_button:
                    popupWindow.dismiss();
                    break;
                case R.id.confm_btton:
                    //这里需要传你选择的时间的值并隐藏掉气泡
                    popupWindow.dismiss();
                    break;
            }
        }
    };

    //生日的气泡
    boolean bool;
    PopupWindow popupWindow;
    ViewGroup view;
    LayoutInflater layoutInflater;
    TextView confmBtton;
    TextView cancelBtton;

    public void createPopupWindow() {
        layoutInflater = LayoutInflater.from(UseAccount.this);
        view = (ViewGroup) layoutInflater.inflate(R.layout.layout_register_deadline_popupwin, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        bool = popupWindow.isShowing();
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        cancelBtton = (TextView) view.findViewById(R.id.cancel_button);
        confmBtton = (TextView) view.findViewById(R.id.confm_btton);

        cancelBtton.setOnClickListener(onClickListener);
        confmBtton.setOnClickListener(onClickListener);
    }
}
