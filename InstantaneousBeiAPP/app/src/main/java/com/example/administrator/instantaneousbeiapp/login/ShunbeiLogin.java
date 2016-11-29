package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.homepage.HomeMainActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONObject;




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
    // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
    private static Tencent tencent;
    boolean isServerSideLogin;

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

        shunbei_xiugai_btn.setOnClickListener(onClickListener);
        shunbei_login_btn.setOnClickListener(onClickListener);
        shunbei_zhuce_btn.setOnClickListener(onClickListener);
        qq_login_btn.setOnClickListener(onClickListener);
    }


    IUiListener loginListener = new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
//            updateUserInfo();
        }
    };
    /** QQ登录第三步：获取用户信息 */
//    private void updateUserInfo() {
//        if (tencent != null && tencent.isSessionValid()) {
//            IUiListener listener = new IUiListener() {
//                @Override
//                public void onError(UiError e) {
//                    Message msg = new Message();
//                    msg.obj = "把手机时间改成获取网络时间";
//                    msg.what = 1;
//                    mHandler.sendMessage(msg);
//                }
//
//                @Override
//                public void onComplete(final Object response) {
//                    Message msg = new Message();
//                    msg.obj = response;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
//                }
//                @Override
//                public void onCancel() {
//                    Message msg = new Message();
//                    msg.obj = "获取用户信息失败";
//                    msg.what = 2;
//                    mHandler.sendMessage(msg);
//                }
//            };
////            mInfo = new UserInfo(this, tencent.getQQToken());
////            mInfo.getUserInfo(listener);
//        } else {
//
//        }
//    }
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                JSONObject response = (JSONObject) msg.obj;
//                if (response.has("nickname")) {
//
//                    Log.i("","获取用户信息成功，返回结果："+response.toString());
////                        mThirdLoginResult.setText("登录成功\n"+"昵称:"+response.getString("nickname")+"\n头像地址:"+response.get("figureurl_qq_1"));
//                    intent = new Intent(ShunbeiLogin.this, HomeMainActivity.class);
//                    startActivity(intent);
//                }
//            }else if(msg.what == 1){
////                mThirdLoginResult.setText(msg+"");
//            }else if(msg.what == 2){
////                mThirdLoginResult.setText(msg+"");
//            }
//        }
//
//    };

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
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
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
                    intent = new Intent(ShunbeiLogin.this, HomeMainActivity.class);
                    startActivity(intent);
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
                    /** 判断是否登陆过 */
                    if(!tencent.isSessionValid()){
                        tencent.login(ShunbeiLogin.this,"all",loginListener);
                        isServerSideLogin =false;
                    /** 登陆过注销之后在登录 */
                    }else {
                        tencent.logout(ShunbeiLogin.this);
                    }
                    break;
            }
        }
    };

}
