package com.example.administrator.instantaneousbeiapp.menu;
/**
 * Created by Administrator on 2016/10/30.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * 这是侧滑菜单的mode
 * */
public class ModeSidelipping extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_mode_sideslipping);
    }
}
