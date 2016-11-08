
package com.example.administrator.instantaneousbeiapp.homepage.fragment;

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
public class StatementFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statement, null);//报表的视图转化
        return view;
    }
}
