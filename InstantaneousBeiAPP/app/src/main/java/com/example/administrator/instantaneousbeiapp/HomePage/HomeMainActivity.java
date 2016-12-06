package com.example.administrator.instantaneousbeiapp.homepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.detail.MyCalendard;
import com.example.administrator.instantaneousbeiapp.menu.RenmidChargeAccountDelete;
import com.example.administrator.instantaneousbeiapp.menu.RenmidChargeAccoutSave;
import com.example.administrator.instantaneousbeiapp.view.DemoArcMenu;
import com.example.administrator.instantaneousbeiapp.view.DemoCeHua;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.DetailFragment;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.MoreFragment;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.StatementFragment;
import com.example.administrator.instantaneousbeiapp.homepage.fragment.WalletFragment;
import com.example.administrator.instantaneousbeiapp.jizhang.ZhiChuActivity;
import com.example.administrator.instantaneousbeiapp.login.ShunbeiLogin;
import com.example.administrator.instantaneousbeiapp.menu.MenuSet;
import com.example.administrator.instantaneousbeiapp.voice.VoiceActivity;
import com.example.administrator.instantaneousbeiapp.wallet.WalletChangeActivity;

import java.util.ArrayList;
import java.util.Calendar;


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
    LinearLayout remind;
    LinearLayout voice;
    LinearLayout seting;
    LinearLayout qiandao;
    TextView qiandaotext;
    Button outBtn;
    ImageView clock_btn;
    ImageView card_btn;
    ImageView class_btn;
    DemoArcMenu ArcMenu;

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
        remind = (LinearLayout) findViewById(R.id.remind);
        voice = (LinearLayout) findViewById(R.id.voice);
        seting = (LinearLayout) findViewById(R.id.setting);
        qiandao = (LinearLayout) findViewById(R.id.qiandao);
        qiandaotext = (TextView) findViewById(R.id.qiandao_text);
        outBtn = (Button) findViewById(R.id.out_btn);

        ArcMenu = (DemoArcMenu) findViewById(R.id.DemoArcmenu);
        clock_btn = (ImageView) ArcMenu.getChildAt(2);
        clock_btn.setTag(new Tag(Tag.BUTTON_TYPE_A));
        clock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeMainActivity.this, "1111111111", Toast.LENGTH_SHORT).show();
            }
        });
        card_btn = (ImageView) ArcMenu.getChildAt(3);
        card_btn.setTag(new Tag(Tag.BUTTON_TYPE_B));
        card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeMainActivity.this, "2222222222", Toast.LENGTH_SHORT).show();
            }
        });
        class_btn = (ImageView) ArcMenu.getChildAt(4);
        class_btn.setTag(new Tag(Tag.BUTTON_TYPE_C));
        class_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeMainActivity.this, "3333333333", Toast.LENGTH_SHORT).show();
            }
        });

//        clock_btn = (ImageView) findViewById(R.id.clock_btn);
//        card_btn = (ImageView) findViewById(R.id.card_btn);
//        class_btn = (ImageView) findViewById(R.id.classification);

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
        remind.setOnClickListener(onClickListener);
        voice.setOnClickListener(onClickListener);
        seting.setOnClickListener(onClickListener);
        qiandao.setOnClickListener(onClickListener);
        outBtn.setOnClickListener(onClickListener);
        viewPager.setOnPageChangeListener(onPageChangeListener);


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

    public class Tag {
        public static final int BUTTON_TYPE_A = 0;
        public static final int BUTTON_TYPE_B = 1;
        public static final int BUTTON_TYPE_C = 2;

        public final int mType;

        public Tag(int type) {
            mType = type;
        }
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        int i = 1;

        @Override
        public void onClick(View view) {
            Intent intent;
            Tag type = (Tag) view.getTag();

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
//                case R.id.clock_btn:
//                    //Toast.makeText(HomeMainActivity.this, "111111111", Toast.LENGTH_SHORT).show();
//
//                    break;
//                case R.id.card_btn:
//                    intent = new Intent(HomeMainActivity.this, WalletChangeActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.class_btn:
//                    //Toast.makeText(HomeMainActivity.this, "33333333", Toast.LENGTH_SHORT).show();
//                    intent = new Intent(HomeMainActivity.this, XuanzheShijianActivity.class);
//                    startActivity(intent);
//                    break;
                case R.id.dudget_type:
                    //对收入支出分类的跳转
                    intent = new Intent(HomeMainActivity.this, ZhiChuActivity.class);
                    startActivity(intent);
                    break;
                case R.id.account_type:
                    //账户类型选择的跳转
                    intent = new Intent(HomeMainActivity.this, WalletChangeActivity.class);
                    startActivity(intent);
                    break;

                case R.id.remind:
                    //记账提醒
                    intent = new Intent(HomeMainActivity.this, RenmidChargeAccountDelete.class);
                    startActivity(intent);
                    break;
                case R.id.voice:
                    //语音记账
                    intent = new Intent(HomeMainActivity.this, VoiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.setting:
                    //设置
                    intent = new Intent(HomeMainActivity.this, MenuSet.class);
                    startActivity(intent);
                    break;

                case R.id.qiandao:
                    signData();
                    intent = new  Intent(HomeMainActivity.this, MyCalendard.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ischeck", "fause");
                    intent.putExtras(bundle);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    Log.i("phone_numb","");
                    startActivityForResult(intent,1000);//第二个参数为请求码
                    break;
                case R.id.out_btn:
                    intent = new Intent(HomeMainActivity.this, ShunbeiLogin.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    /*当需要从第二个页面获得数据返回的时候，重写该方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //请求码      ，    结果码       ， intent对象（包含绑定的数据）
        if (resultCode == 1001){
            if (data != null){//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                Log.i("intent",data.toString());
                Bundle bundle = data.getExtras();
                String Text=bundle.getString("Text");//第二个页面新传的数据
                qiandaotext.setText(""+Text);
                String ischeck =bundle.getString("ischeck");//从第一个页面传递给第二个页面的数据，没有修改然后原装返回
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    // 签到逻辑判断
    boolean sign = false; //进行对每天签到的判断
    String signstring = "kkkk";
    SharedPreferences day;// 用于缓存签到的日期
    public void signData() {
        day = getSharedPreferences("day" , Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor=day.edit();
        editor.putString("day", signstring);

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        String nowDay = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        int nowHour = calendar.get(Calendar.MONTH) + 1;
        Log.i("signstring", "66666666>" + day.getString("day", signstring).toString());
        if (!(day.getString("day", signstring).toString().equals(nowDay))){
            signstring = nowDay;
            sign = true;
            Log.i("sign", "======>" + sign);
            editor.putString("day" , signstring);
            Log.i("signstring", "======>" + signstring);
            Log.i("signstring", "+++++++>" + day.getString("day", signstring).toString());
        }
    }

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
        accountType = (Button) view.findViewById(R.id.account_type);

        dudgetType.setOnClickListener(onClickListener);
        accountType.setOnClickListener(onClickListener);

    }
}
