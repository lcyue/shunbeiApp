package com.example.administrator.instantaneousbeiapp.HomePage.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletChangeActivity;
import com.example.administrator.instantaneousbeiapp.Wallet.WalletDepositCardActivity;


/**
 * Created by Administrator on 2016/11/1.
 */
public class WalletFragment extends Fragment {
    TextView walletAddText;
    RelativeLayout walletCashRelativelayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet_remind_layout, null);//钱包页面的视图化
        walletAddText = (TextView) view.findViewById(R.id.wallet_add_text);
        walletCashRelativelayout = (RelativeLayout) view.findViewById(R.id.wallet_cash_relativelayout);

        walletAddText.setOnClickListener(onClickListener);
        walletCashRelativelayout.setOnClickListener(onClickListener);

        return view;
    }

    //    点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.wallet_add_text:
                    intent = new Intent(getActivity(), WalletChangeActivity.class);//跳转到添加页面
                    startActivity(intent);
                    break;
                case R.id.wallet_cash_relativelayout:
                    intent = new Intent(getActivity(), WalletDepositCardActivity.class);//跳转到消费和支出的详细页面
                    startActivity(intent);
                    break;
            }
        }
    };
}
