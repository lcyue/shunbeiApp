
package com.example.administrator.instantaneousbeiapp.Homepage.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.instantaneousbeiapp.Detail.Calendar;
import com.example.administrator.instantaneousbeiapp.Detail.DemoCeHua;
import com.example.administrator.instantaneousbeiapp.HomePage.HomeMainActivity;
import com.example.administrator.instantaneousbeiapp.Homepage.HomeMainActivity;
import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class DetailFragment extends Fragment {

    ImageView textView;
    ImageView cancelButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_detail_particulars, null); //明细页面的视图
        textView = (ImageView) view.findViewById(R.id.detail_menu_btn);
        cancelButton = (ImageView) view.findViewById(R.id.cancel_button);

        textView.setOnClickListener(onClickListener);
        cancelButton.setOnClickListener(onClickListener);

        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.detail_menu_btn:
                    HomeMainActivity homeMainActivity = (HomeMainActivity) getActivity();
                    homeMainActivity.move();
                    break;
                case R.id.cancel_button:
                    intent = new Intent(getActivity(), Calendar.class);//挑战到日历页面
                    startActivity(intent);
                    break;
            }
        }
    };
}
