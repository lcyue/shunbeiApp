package com.example.administrator.instantaneousbeiapp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.guide.GuideTransitionTwo;

/**
 * Created by Administrator on 2016/10/31.
 */
public class FratmentFour extends Fragment {
    TextView guide_btn;
    View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_guide_four,null);
        guide_btn = (TextView) view.findViewById(R.id.guide_btn);

        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),GuideTransitionTwo.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
