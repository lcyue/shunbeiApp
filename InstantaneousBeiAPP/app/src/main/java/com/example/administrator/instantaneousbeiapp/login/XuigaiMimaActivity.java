package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/10/22.
 */
public class XuigaiMimaActivity extends Activity implements View.OnClickListener{
    TextView shunbei_xuigai_wancheng_btn;
    ImageView back_btn;
    TextView requestCode;// 获取验证码
    String APPKEY = "19830917b7f50";
    String APPSECRETE = "288d02456e5f174e9fafc65b31a05079";
    // 手机号输入框
    private EditText inputPhoneEt;
    // 验证码输入框
    private EditText inputCodeEt;
    EditText password_edit;
    EditText repassword_edit;
    int user_login_id;
    String password;
    String repassword;
    int i = 60;
    Boolean aBoolean = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_xuigaimima_layout);

        shunbei_xuigai_wancheng_btn = (TextView) findViewById(R.id.shunbei_xuigai_wancheng_btn);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        requestCode = (TextView) findViewById(R.id.test_get_code_text);
        inputCodeEt = (EditText) findViewById(R.id.inputCodeEt_edit);
        inputPhoneEt = (EditText) findViewById(R.id.inputPhoneEt_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        repassword_edit = (EditText) findViewById(R.id.repassword_edit);

        requestCode.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        shunbei_xuigai_wancheng_btn.setOnClickListener(this);
        password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });
        repassword_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                repassword = editable.toString();
            }
        });

        init();
        getSharePreferences();
    }
    //读取登录时存储本地资源
    public void getSharePreferences(){
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("shunbei", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Integer user_id =sharedPreferences.getInt("user_id", 0);
        String user_name =sharedPreferences.getString("user_name", "");
        String token =sharedPreferences.getString("token", "");
        this.user_login_id = user_id;
    }

    public void changpassword(){
        String httpurl = "http://10.0.2.2/index.php/home/index/changpassword?" + "user_login_id="+user_login_id
                +"&user_new_password="+password+"&user_new_repassword"+repassword;
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
                int status = jsonObject.getInt("status");
                String message = jsonObject.getString("message");
                if(aBoolean = true && status == 200){
                    Intent intent = new Intent(XuigaiMimaActivity.this,
                            XugaiChenggongActivity.class);
                    startActivity(intent);
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
                new Thread(){
                    @Override
                    public void run() {
                        changpassword();
                    }
                }.start();
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
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        aBoolean = true;
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
    //上传信息
    public void submitUserInfo(String country,String phone){
        Random r = new Random();
        String uid = Math.abs(r.nextInt())+"";
        String nickName ="瞬贝";
        SMSSDK.submitUserInfo(uid,nickName,null,country,phone);
    }
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
