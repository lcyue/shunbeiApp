package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.administrator.instantaneousbeiapp.homepage.HomeMainActivity;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

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
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/10/22.
 */
public class ShunbeiLogin extends Activity {
    TextView shunbei_login_btn;
    TextView shunbei_zhuce_btn;
    TextView shunbei_xiugai_btn;
    ImageView qq_login_btn;
    ImageView xinlang_login_btn;
    ImageView weixing_login_btn;
    EditText login_nums;
    EditText login_password;
    // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
    private static Tencent tencent;
    boolean isServerSideLogin;
    //以下三个参数是新浪登录接口需要
    SsoHandler mSsoHandler;
    AuthInfo mAuthInfo;
    Oauth2AccessToken mAccessToken;

    @Override
    protected void onDestroy() {
        Log.i("onDestroy","===========onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_login_layout);

        shunbei_login_btn = (TextView) findViewById(R.id.shunbei_login_btn);
        shunbei_zhuce_btn = (TextView) findViewById(R.id.shunbei_zhuce_btn);
        shunbei_xiugai_btn = (TextView) findViewById(R.id.shunbei_xiugai_btn);
        qq_login_btn = (ImageView) findViewById(R.id.qq_login_btn);
        xinlang_login_btn = (ImageView) findViewById(R.id.xinlang_login_btn);
        weixing_login_btn = (ImageView) findViewById(R.id.weixing_login_btn);
        login_nums = (EditText) findViewById(R.id.login_nums);
        login_password = (EditText) findViewById(R.id.login_password);

        shunbei_xiugai_btn.setOnClickListener(onClickListener);
        shunbei_login_btn.setOnClickListener(onClickListener);
        shunbei_zhuce_btn.setOnClickListener(onClickListener);
        qq_login_btn.setOnClickListener(onClickListener);
        xinlang_login_btn.setOnClickListener(onClickListener);

        //输入框监听
        login_nums.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneNums = editable.toString();
            }
        });
        //输入密码框监听
        login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
            }
        });
    }

    String phoneNums;
    String password;
    int status;
    String message;
    public void login(){
        String httpurl = "http://10.0.2.2/index.php/home/index/login?" + "user_name="+phoneNums+"&user_password="
                +password;
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
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    int user_id = jsonObject.getInt("user_id");
                    String user_name = jsonObject.getString("user_name");
                    String token = jsonObject.getString("token");
                }
                if (status == 200) {
                    Intent intent = new Intent(ShunbeiLogin.this, HomeMainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ShunbeiLogin.this, "" + message, Toast.LENGTH_SHORT).show();
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



    class AuthListener  implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            Log.i("mAccessToken","==="+mAccessToken);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(ShunbeiLogin.this, mAccessToken);
                Toast.makeText(ShunbeiLogin.this,"授权成功", Toast.LENGTH_SHORT).show();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
            }
        }


        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }




    //weibo授权需要在Activity的onActivityResult函数中，调用以下方法：
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }

    IUiListener loginListener = new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
//            updateUserInfo();
        }
    };

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
//                Util.showResultDialog(ShunbeiLogin.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Util.showResultDialog(ShunbeiLogin.this, "返回为空", "登录失败");
                return;
            }
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {}

        @Override
        public void onError(UiError e) {
//            Util.toastMessage(ShunbeiLogin.this, "onError: " + e.errorDetail);
//            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
//            Util.toastMessage(ShunbeiLogin.this, "onCancel: ");
//            Util.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    /** QQ登录第二步：存储token和openid */
    public  void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                tencent.setAccessToken(token, expires);
                tencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.shunbei_login_btn:
                    new Thread(){
                        @Override
                        public void run() {
                            login();
                        }
                    }.start();
                    break;
                case R.id.shunbei_zhuce_btn:
                    intent = new Intent(ShunbeiLogin.this,ZhuceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.shunbei_xiugai_btn:
                    intent = new Intent(ShunbeiLogin.this,XuigaiMimaActivity.class);
                    startActivity(intent);
                    break;
                case R.id.qq_login_btn:
                    tencent = Tencent.createInstance("1105853108",getApplicationContext());
                    intent = new Intent(ShunbeiLogin.this,HomeMainActivity.class);
                    startActivity(intent);
                    /** 判断是否登陆过 */
                    if(!tencent.isSessionValid()){
                        tencent.login(ShunbeiLogin.this,"all",loginListener);
                        isServerSideLogin = false;
                    /** 登陆过注销之后在登录 */
                    }else {
                        tencent.logout(ShunbeiLogin.this);
                    }
                    break;
                case R.id.xinlang_login_btn:
                    mAccessToken = AccessTokenKeeper.readAccessToken(ShunbeiLogin.this);
                    mAuthInfo = new AuthInfo(ShunbeiLogin.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
                    mSsoHandler = new SsoHandler(ShunbeiLogin.this, mAuthInfo);
                    mSsoHandler.authorize(new AuthListener());
//                    intent = new Intent(ShunbeiLogin.this,HomeMainActivity.class);
//                    startActivity(intent);
                    break;
            }
        }
    };


}
