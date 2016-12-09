package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.menu.MenuSet;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/11/27.
 */
public class PhoneBindingActivity extends Activity implements View.OnClickListener{
    ImageView backBtn;
    TextView wanchengBtn;
    String APPKEY = "19830917b7f50";
    String APPSECRETE = "288d02456e5f174e9fafc65b31a05079";
    TextView requestCode;// 获取验证码
    // 手机号输入框
    private EditText inputPhoneEt;
    // 验证码输入框
    private EditText inputCodeEt;
    int i = 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phone_binding);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        wanchengBtn = (TextView) findViewById(R.id.shunbei_xuigai_wancheng_btn);
        requestCode = (TextView) findViewById(R.id.test_get_code_text);
        inputCodeEt = (EditText) findViewById(R.id.inputCodeEt_edit);
        inputPhoneEt = (EditText) findViewById(R.id.inputPhoneEt_edit);

        init();
        requestCode.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        wanchengBtn.setOnClickListener(this);
    }
    public void init(){

        // 启动短信验证sdk
        SMSSDK.initSDK(this, APPKEY, APPSECRETE);
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);


            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        String phoneNums = inputPhoneEt.getText().toString();
        switch (v.getId()) {
            case R.id.test_get_code_text:
                // 1. 通过规则判断手机号
                if (!judgePhoneNums(phoneNums)) {
                    return;
                } // 2. 通过sdk发送短信验证
                SMSSDK.getVerificationCode("86", phoneNums);

                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                requestCode.setClickable(false);
                requestCode.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;

            case R.id.shunbei_xuigai_wancheng_btn:
                //将收到的验证码和手机号提交再次核对
                SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt
                        .getText().toString());
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                requestCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                requestCode.setText("获取验证码");
                requestCode.setClickable(true);
                i = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                if(result==SMSSDK.RESULT_COMPLETE){
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "绑定手机号码成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PhoneBindingActivity.this,
                                MenuSet.class);
                        MenuSet menuSet = new MenuSet();
                        startActivity(intent);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }else {
                    ((Throwable) data).printStackTrace();
                }
            }
        }
    };
    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

}
