package com.example.administrator.instantaneousbeiapp.HomePage.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_detail_particulars, null); //明细页面的视图
        return view;
    }
}
