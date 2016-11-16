package com.example.administrator.instantaneousbeiapp.Homepage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.Detail.DemoCeHua;
import com.example.administrator.instantaneousbeiapp.Homepage.Fragment.DetailFragment;
import com.example.administrator.instantaneousbeiapp.Homepage.Fragment.StatementFragment;
import com.example.administrator.instantaneousbeiapp.Homepage.Fragment.WalletFragment;
import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/11.
 */
public class HomeMainActivity extends FragmentActivity {
    TextView iconDetailText;
    TextView iconWalleText;
    TextView iconStatisticalText;
    TextView icon_more_text;
    DemoCeHua menu;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        iconDetailText = (TextView) findViewById(R.id.icon_detail_text);
        iconWalleText = (TextView) findViewById(R.id.icon_wallet_text);
        iconStatisticalText = (TextView) findViewById(R.id.icon_statistical_text);
        icon_more_text = (TextView) findViewById(R.id.icon_more_text);
        menu = (DemoCeHua)findViewById(R.id.menu);
Log.i("HomeMainActivity",""+this.getTaskId());
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
        icon_more_text.setOnClickListener(onClickListener);

        viewPager.setOnPageChangeListener(onPageChangeListener);


    }

    public void move(){
        menu.togleMenu();
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
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    Drawable top1 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top1, null, null);
                    Drawable top2 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top2, null, null);


                    iconDetailText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case 1:
                    Drawable top3 = getResources().getDrawable(R.mipmap.icon_wallet_on);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top3, null, null);
                    Drawable top4 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top4, null, null);
                    Drawable top5 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top5, null, null);

                    iconWalleText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case 2:
                    Drawable top6 = getResources().getDrawable(R.mipmap.icon_statistical_on);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top6, null, null);
                    Drawable top7 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top7, null, null);
                    Drawable top8 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top8, null, null);

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
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    Drawable top1 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top1, null, null);
                    Drawable top2 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top2, null, null);


                    iconDetailText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_wallet_text:
                    viewPager.setCurrentItem(1);
                    Drawable top3 = getResources().getDrawable(R.mipmap.icon_wallet_on);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top3, null, null);
                    Drawable top4 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top4, null, null);
                    Drawable top5 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top5, null, null);

                    iconWalleText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_statistical_text:
                    viewPager.setCurrentItem(2);
                    Drawable top6 = getResources().getDrawable(R.mipmap.icon_statistical_on);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top6, null, null);
                    Drawable top7 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top7, null, null);
                    Drawable top8 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top8, null, null);

                    iconStatisticalText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_more_text:
                    move();
                    break;
            }
        }
    };
}
