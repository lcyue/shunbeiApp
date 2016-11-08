package com.example.administrator.instantaneousbeiapp.homepage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.homepage.fragment.DetailFragment;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.StatementFragment;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.WalletFragment;
import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HomePageAcivity extends FragmentActivity {
    TextView iconDetailText;
    TextView iconWalleText;
    TextView iconStatisticalText;

    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        iconDetailText = (TextView) findViewById(R.id.icon_detail_text);
        iconWalleText = (TextView) findViewById(R.id.icon_wallet_text);
        iconStatisticalText = (TextView) findViewById(R.id.icon_statistical_text);

        fragmentArrayList = new ArrayList<Fragment>();
        DetailFragment detailFragment = new DetailFragment();
        WalletFragment walletFragment = new WalletFragment();
        StatementFragment statementFragment = new StatementFragment();
        fragmentArrayList.add(detailFragment);
        fragmentArrayList.add(walletFragment);
        fragmentArrayList.add(statementFragment);
        InstantaneousPagerAdapter instantaneousPagerAdapter = new InstantaneousPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);

        iconDetailText.setOnClickListener(onClickListener);
        iconWalleText.setOnClickListener(onClickListener);
        iconStatisticalText.setOnClickListener(onClickListener);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    //    重写FragmentPagerAdapter方法
    public class InstantaneousPagerAdapter extends FragmentPagerAdapter {
        public InstantaneousPagerAdapter(FragmentManager fm) {
            super(fm);
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

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    Drawable top = getResources().getDrawable(R.mipmap.icon_detail_on);
                    top.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top, null, null);
                    Drawable top1 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    top1.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top1, null, null);
                    Drawable top2 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    top2.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top2, null, null);

                    iconDetailText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case 1:
                    Drawable top3 = getResources().getDrawable(R.mipmap.icon_wallet_on);
                    top3.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top3, null, null);
                    Drawable top4 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    top4.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top4, null, null);
                    Drawable top5 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    top5.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top5, null, null);

                    iconWalleText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case 2:
                    Drawable top6 = getResources().getDrawable(R.mipmap.icon_statistical_on);
                    top6.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top6, null, null);
                    Drawable top7 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    top7.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top7, null, null);
                    Drawable top8 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    top8.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top8, null, null);

                    iconStatisticalText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.icon_detail_text:
                    viewPager.setCurrentItem(0);
                    Drawable top = getResources().getDrawable(R.mipmap.icon_detail_on);
                    top.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top, null, null);
                    Drawable top1 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    top1.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top1, null, null);
                    Drawable top2 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    top2.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top2, null, null);

                    iconDetailText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_wallet_text:
                    viewPager.setCurrentItem(1);
                    Drawable top3 = getResources().getDrawable(R.mipmap.icon_wallet_on);
                    top3.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top3, null, null);
                    Drawable top4 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    top4.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top4, null, null);
                    Drawable top5 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    top5.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top5, null, null);

                    iconWalleText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_statistical_text:
                    viewPager.setCurrentItem(2);
                    Drawable top6 = getResources().getDrawable(R.mipmap.icon_statistical_on);
                    top6.setBounds(0,0,85,85);
                    iconStatisticalText.setCompoundDrawables(null, top6, null, null);
                    Drawable top7 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    top7.setBounds(0,0,85,85);
                    iconDetailText.setCompoundDrawables(null, top7, null, null);
                    Drawable top8 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    top8.setBounds(0,0,85,85);
                    iconWalleText.setCompoundDrawables(null, top8, null, null);

                    iconStatisticalText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
            }
        }
    };
}
