package com.example.administrator.instantaneousbeiapp.menu;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.view.PatterView;

/**
 * Created by Administrator on 2016/10/30.
 */
public class GesturesPassWord extends Activity implements PatterView.OnPatterChangeListener {
    Button create_password; //创建密码
    PatterView lockPatternView; //九宫格
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_gestures_password);

        create_password = (Button) findViewById(R.id.create_password);
        lockPatternView = (PatterView) findViewById(R.id.lockPatternView);

        lockPatternView.setPatterChangeListener(this);
    }

    @Override
    public void onPatterChange(String passwordStr) {
        if(!TextUtils.isEmpty(passwordStr)){
            create_password.setText("确认");
            Log.i("onPatterChange",""+passwordStr);
        }else {
            create_password.setText("密码错误");
            lockPatternView.errorPoint();
        }
    }

    @Override
    public void onPatterStart(boolean isStart) {
        if(isStart){
            Log.i("onPatterStart========","onPatterStart");
        }
    }
}
