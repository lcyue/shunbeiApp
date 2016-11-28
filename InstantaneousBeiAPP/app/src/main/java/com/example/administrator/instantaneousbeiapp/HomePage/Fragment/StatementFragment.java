<<<<<<< HEAD
package com.example.administrator.instantaneousbeiapp.Homepage.Fragment;


=======
package com.example.administrator.instantaneousbeiapp.homePage.Fragment;
>>>>>>> 37280b3161b9597bce3c01053b7d6f0cd9e05f7d
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.administrator.instantaneousbeiapp.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class StatementFragment extends Fragment {
    RadioButton weekBtton;
    RadioButton mothBtton;
    RadioButton yearBtton;
    RadioButton dayBtton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statement, null);//报表的视图转化

        weekBtton = (RadioButton) view.findViewById(R.id.week_btton);
        mothBtton = (RadioButton) view.findViewById(R.id.moth_btton);
        yearBtton = (RadioButton) view.findViewById(R.id.year_btton);
        dayBtton = (RadioButton) view.findViewById(R.id.day_btton);

        weekBtton.setOnClickListener(onClickListener);
        mothBtton.setOnClickListener(onClickListener);
        yearBtton.setOnClickListener(onClickListener);
        dayBtton.setOnClickListener(onClickListener);

        return view;
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.week_btton:
                    weekBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    weekBtton.setChecked(true);
                    break;
                case R.id.moth_btton:
                    mothBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setChecked(true);
                    break;
                case R.id.year_btton:
                    yearBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setChecked(true);
                    break;
                case R.id.day_btton:
                    dayBtton.setTextColor(getResources().getColor(R.color.green_btn));
                    weekBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    mothBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    yearBtton.setTextColor(getResources().getColor(R.color.black_nomal));
                    dayBtton.setChecked(true);
                    break;
            }
        }
    };
}
