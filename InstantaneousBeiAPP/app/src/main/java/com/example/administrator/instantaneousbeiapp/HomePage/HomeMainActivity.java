package com.example.administrator.instantaneousbeiapp.HomePage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.Detail.DemoCeHua;
import com.example.administrator.instantaneousbeiapp.HomePage.Fragment.DetailFragment;
import com.example.administrator.instantaneousbeiapp.HomePage.Fragment.MoreFragment;
import com.example.administrator.instantaneousbeiapp.HomePage.Fragment.StatementFragment;
import com.example.administrator.instantaneousbeiapp.HomePage.Fragment.WalletFragment;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletChangeActivity;
import com.example.administrator.instantaneousbeiapp.jizhang.ShouRu;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/11.
 */
public class HomeMainActivity extends FragmentActivity {
    TextView iconDetailText;
    TextView iconWalleText;
    TextView iconStatisticalText;
    TextView iconMoretext;
    ImageView typeSelectButton;
    DemoCeHua menu;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        iconDetailText = (TextView) findViewById(R.id.icon_detail_text);
        iconWalleText = (TextView) findViewById(R.id.icon_wallet_text);
        iconStatisticalText = (TextView) findViewById(R.id.icon_statistical_text);
        iconMoretext = (TextView) findViewById(R.id.icon_more_text);
        menu = (DemoCeHua) findViewById(R.id.menu);
        typeSelectButton = (ImageView) findViewById(R.id.type_select_button);

        Log.i("HomeMainActivity", "" + this.getTaskId());
        fragmentArrayList = new ArrayList<Fragment>();
        DetailFragment detailFragment = new DetailFragment();
        WalletFragment walletFragment = new WalletFragment();
        StatementFragment statementFragment = new StatementFragment();
        MoreFragment moreFragment = new MoreFragment();
        fragmentArrayList.add(detailFragment);
        fragmentArrayList.add(walletFragment);
        fragmentArrayList.add(statementFragment);
        fragmentArrayList.add(moreFragment);

        InstantaneousPagerAdapter instantaneousPagerAdapter = new InstantaneousPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);

        iconDetailText.setOnClickListener(onClickListener);
        iconWalleText.setOnClickListener(onClickListener);
        iconStatisticalText.setOnClickListener(onClickListener);
        iconMoretext.setOnClickListener(onClickListener);

        viewPager.setOnPageChangeListener(onPageChangeListener);
        typeSelectButton.setOnClickListener(onClickListener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void move() {
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

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.icon_detail_text:
                    viewPager.setCurrentItem(0);
                    Drawable top = getResources().getDrawable(R.mipmap.icon_detail_on);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    Drawable top1 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top1, null, null);
                    Drawable top2 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top2, null, null);
                    Drawable top15 = getResources().getDrawable(R.mipmap.icon_more_off);
                    iconMoretext.setCompoundDrawablesWithIntrinsicBounds(null, top15, null, null);


                    iconDetailText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconMoretext.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_wallet_text:
                    viewPager.setCurrentItem(1);
                    Drawable top3 = getResources().getDrawable(R.mipmap.icon_wallet_on);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top3, null, null);
                    Drawable top4 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top4, null, null);
                    Drawable top5 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top5, null, null);
                    Drawable top14 = getResources().getDrawable(R.mipmap.icon_more_off);
                    iconMoretext.setCompoundDrawablesWithIntrinsicBounds(null, top14, null, null);

                    iconWalleText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconMoretext.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_statistical_text:
                    viewPager.setCurrentItem(2);
                    Drawable top6 = getResources().getDrawable(R.mipmap.icon_statistical_on);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top6, null, null);
                    Drawable top7 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top7, null, null);
                    Drawable top8 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top8, null, null);
                    Drawable top13 = getResources().getDrawable(R.mipmap.icon_more_off);
                    iconMoretext.setCompoundDrawablesWithIntrinsicBounds(null, top13, null, null);

                    iconStatisticalText.setTextColor(getResources().getColor(R.color.green_btn));
                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconMoretext.setTextColor(getResources().getColor(R.color.gery_btn));
                    break;
                case R.id.icon_more_text:
                    viewPager.setCurrentItem(3);
                    Drawable top9 = getResources().getDrawable(R.mipmap.icon_detail_off);
                    iconDetailText.setCompoundDrawablesWithIntrinsicBounds(null, top9, null, null);
                    Drawable top10 = getResources().getDrawable(R.mipmap.icon_wallet_off);
                    iconWalleText.setCompoundDrawablesWithIntrinsicBounds(null, top10, null, null);
                    Drawable top11 = getResources().getDrawable(R.mipmap.icon_statistical_off);
                    iconStatisticalText.setCompoundDrawablesWithIntrinsicBounds(null, top11, null, null);
                    Drawable top16 = getResources().getDrawable(R.mipmap.icon_more_on);
                    iconMoretext.setCompoundDrawablesWithIntrinsicBounds(null, top16, null, null);

                    iconDetailText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconWalleText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconStatisticalText.setTextColor(getResources().getColor(R.color.gery_btn));
                    iconMoretext.setTextColor(getResources().getColor(R.color.green_btn));
                    break;
                case R.id.type_select_button:
                        if (bool == true) {
                            popupWindow.dismiss();
                            bool = popupWindow.isShowing();
                        } else {
                            createPopupWindow();//最后需要动画进行美观 以便形成半圆的效果
                            bool = popupWindow.isShowing();
                        }
                    break;
                case R.id.dudget_type:
                    //对收入支出分类的跳转
                    intent = new Intent(HomeMainActivity.this, ShouRu.class);
                    startActivity(intent);
                    break;
                case R.id.account_type:
                    //账户类型选择的跳转
                    intent = new Intent(HomeMainActivity.this, WalletChangeActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };

    //选择类型的页面
    boolean bool;
    PopupWindow popupWindow;
    Button dudgetType;
    Button accountType;
    View view;
    LayoutInflater layoutInflater;
    public void createPopupWindow() {
        layoutInflater = LayoutInflater.from(HomeMainActivity.this);
        view = layoutInflater.inflate(R.layout.type_select_button_layout, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setWidth(400);
        popupWindow.setHeight(100);
        popupWindow.setOutsideTouchable(true);
        bool = popupWindow.isShowing();
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.BOTTOM, 0, 400);

        dudgetType = (Button) view.findViewById(R.id.dudget_type);
        accountType = (Button)view.findViewById(R.id.account_type);

        dudgetType.setOnClickListener(onClickListener);
        accountType.setOnClickListener(onClickListener);


    }
}
