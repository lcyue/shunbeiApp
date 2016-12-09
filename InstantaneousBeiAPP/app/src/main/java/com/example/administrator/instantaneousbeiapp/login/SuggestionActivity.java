package com.example.administrator.instantaneousbeiapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.homepage.HomeMainActivity;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.MoreFragment;

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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/30.
 */
public class SuggestionActivity extends Activity {
    ImageView backBtn;
    Button putinto;
    EditText ticking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suggestions);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        putinto = (Button) findViewById(R.id.putinto);
        ticking = (EditText) findViewById(R.id.ticking);

        backBtn.setOnClickListener(onClickListener);
        putinto.setOnClickListener(onClickListener);
        ticking.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ticking_message = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    String ticking_message;
    int status;
    String message;
    public void ticking(){
        String httpurl = "http://10.0.2.2/index.php/home/index/ticking?" + "ticking_message="+ticking_message;
        Log.i("============",""+ticking_message);
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoOutput(true);
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.putinto:
                    new Thread(){
                        @Override
                        public void run() {
                            ticking();
                        }
                    }.start();
                    break;
            }
        }
    };
}
