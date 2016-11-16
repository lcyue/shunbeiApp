package com.example.administrator.instantaneousbeiapp.Guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ActivityShunBei extends FragmentActivity {
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewpage);

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        fragmentArrayList = new ArrayList<Fragment>();

        FragmentOne fragmentOne = new FragmentOne();
        FratmentTwo fratmentTwo = new FratmentTwo();
        FratmentThree fratmentThree = new FratmentThree();
        FratmentFour fratmentFour = new FratmentFour();

        fragmentArrayList.add(fragmentOne);
        fragmentArrayList.add(fratmentTwo);
        fragmentArrayList.add(fratmentThree);
        fragmentArrayList.add(fratmentFour);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }
}
