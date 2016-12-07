package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import com.example.administrator.instantaneousbeiapp.login.XugaiChenggongActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


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

    private static final int SIGNATURE_REQUESTCODE = 1010;
    private static final int SIGNATURE_RESULTCODE = 1020;
    private static final int BRITH_REQUESTCODE = 1030;
    private static final int BRITH_RESULTCODE = 1040;
    private static final int NICKNAME_REQUESTCODE = 1050;
    private static final int NICKNAME_RESULTCODE = 1060;

    private static final int ALBUM_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 3;

    String nickname;
    String signature;
    String brithday;

    Boolean isnull = false;
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
        userName.setOnClickListener(onClickListener);
        getSharePreferences();
        putSharePreferences();

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
                    getSD();
                    break;
                case R.id.user_name:
                    intent = new Intent(UseAccount.this, ActivityNickname.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ischeck", "fause");
                    intent.putExtras(bundle);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent,NICKNAME_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.signature_btton:
                    intent = new Intent(UseAccount.this, RegisterActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("ischeck", "fause");
                    intent.putExtras(bundle1);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent,SIGNATURE_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.birthday_btton:
                    intent = new Intent(UseAccount.this, ActivityBrithDay.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("ischeck", "fause");
                    intent.putExtras(bundle2);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent,BRITH_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.phone_binding_btton:
                    intent = new Intent(UseAccount.this, PhoneBindingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.saveing_btton:
                    saveSharePreferences();//保存本地
                    setUserData();//保存服务器
                    Toast.makeText(UseAccount.this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };
    //判断SD卡是否存在
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    //读取sd卡
    public void getSD(){
        if (isSdCardExist()){
            // 1. 获取SD卡的根目录
            String  sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
//            File mFile=new File(sdCardRoot+"1.jpg");
//            //若该文件存在
//            if (mFile.exists()) {
//                Bitmap bitmap= BitmapFactory.decodeFile(sdCardRoot+"1.jpg");
//            }

            //打开系统的相册
            Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
            albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(albumIntent, ALBUM_REQUEST_CODE);


        }else {
            Toast.makeText(UseAccount.this, "SD卡不存在", Toast.LENGTH_SHORT).show();
        }
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
        this.user_name = user_name;
        userName.setText(user_name);
    }
    //按保存后数据保存到本地
    public void saveSharePreferences(){
        isnull = true;
        SharedPreferences sharedPreferences1 = getSharedPreferences("personMessage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("nickname",""+nickname);
        editor.putString("brithday",""+brithday);
        editor.putString("signature",""+signature);
        editor.putBoolean("isnull",isnull);
        editor.commit();
    }
    //读取保存的数据并赋值
    public void putSharePreferences(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("personMessage", Context.MODE_PRIVATE);
        String nickname = sharedPreferences1.getString("nickname","");
        String brithday = sharedPreferences1.getString("brithday","");
        String signature = sharedPreferences1.getString("signature","");
        Boolean isnull = sharedPreferences1.getBoolean("isnull",false);
        if (isnull == true){
            if (nickname != null){
                userName.setText(nickname);
            }
            birthdayBtton.setText(brithday);
            signaturebtton.setText(signature);
        }
    }

    //查询用户资料接口
    int user_message_id;
    int user_login_id;
    String user_brith;
    int user_telphone;
    String user_content;
    String user_image;
    String user_name;
    String user_message_name;
    public void searchUserData(){
        String httpurl = "http://10.0.2.2/index.php/home/index/userdata";
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
                JSONObject jsonArray = jsonObject.getJSONObject("ueserdata");
                user_message_id = jsonArray.getInt("user_message_id");//用户信息id
                user_login_id = jsonArray.getInt("user_login_id");    //用户id
                user_brith = jsonArray.getString("user_brith");     //用户生日
                user_telphone = jsonArray.getInt("user_telphone");    //用户的手机号
                user_content = jsonArray.getString("user_content"); //用户签名
                user_image = jsonArray.getString("user_image");     //用户头像
                user_name = jsonArray.getString("user_name");     //用户手机号
                user_message_name = jsonArray.getString("user_message_name"); //用户昵称
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



    //设置个人资料接口
    public void setUserData(){
        String httpurl = "http://10.0.2.2/index.php/home/index/userdatasave?" + "user_login_id=" + user_login_id
               + "&user_brith=" + user_brith + "&user_telphone=" + user_telphone + "&user_content=" +user_content
                +"&user_image" +user_image +"&user_message_name="+user_message_name;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
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
                JSONObject jsonArray = jsonObject.getJSONObject("ueserdata");
                user_message_id = jsonArray.getInt("user_message_id");//用户信息id
                user_login_id = jsonArray.getInt("user_login_id");    //用户id
                user_brith = jsonArray.getString("user_brith");     //用户生日
                user_telphone = jsonArray.getInt("user_telphone");    //用户的手机号
                user_content = jsonArray.getString("user_content"); //用户签名
                user_image = jsonArray.getString("user_image");     //用户头像
                user_message_name = jsonArray.getString("user_name");     //用户昵称
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


    /*当需要从第二个页面获得数据返回的时候，重写该方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //请求码      ，    结果码       ， intent对象（包含绑定的数据）
        switch (resultCode){
            case SIGNATURE_RESULTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Log.i("SIGNATURE_RESULTCODE", data.toString());
                    Bundle bundle = data.getExtras();
                    signature = bundle.getString("signature");//第二个页面新传的数据
                    signaturebtton.setText("" + signature);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case BRITH_RESULTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Log.i("BRITH_RESULTCODE", data.toString());
                    Bundle bundle = data.getExtras();
                    brithday = bundle.getString("brithday");//第二个页面新传的数据
                    birthdayBtton.setText("" + brithday);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case NICKNAME_RESULTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Log.i("NICKNAME_RESULTCODE", data.toString());
                    Bundle bundle = data.getExtras();
                    nickname = bundle.getString("nickname");//第二个页面新传的数据
                    userName.setText("" + nickname);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }
}
