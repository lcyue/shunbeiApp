
package com.example.administrator.instantaneousbeiapp.HomePage.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.Detail.DemoCeHua;
import com.example.administrator.instantaneousbeiapp.HomePage.HomeMainActivity;
import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class DetailFragment extends Fragment {

    ImageView textView;
    DemoCeHua menu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_detail_particulars, null); //明细页面的视图
        textView = (ImageView) view.findViewById(R.id.detail_menu_btn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeMainActivity homeMainActivity = (HomeMainActivity) getActivity();
                homeMainActivity.move();
            }
        });
        return view;
    }
}
