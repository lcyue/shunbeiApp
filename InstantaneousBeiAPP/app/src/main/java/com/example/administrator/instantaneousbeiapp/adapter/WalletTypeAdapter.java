package com.example.administrator.instantaneousbeiapp.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.mvc.WalletTypeItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/25.
 */
public class WalletTypeAdapter extends BaseAdapter {
    Context context;
    ArrayList<WalletTypeItem> list;
    LayoutInflater layoutInflater;

    public WalletTypeAdapter(Context context, ArrayList<WalletTypeItem> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item_waller_type, null);
            viewHolder.walletDepositCardRelativelayout = (RelativeLayout) view.findViewById(R.id.wallet_deposit_card_relativelayout);
            viewHolder.walletTypeText = (TextView) view.findViewById(R.id.wallet_deposit_card_text);
            viewHolder.walletTypeBalance = (TextView) view.findViewById(R.id.wallet_deposit_card_text_balance);
            viewHolder.walletMoney = (TextView) view.findViewById(R.id.wallet_deposit_card_money_text);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.walletTypeText.setText(list.get(i).getType());
        viewHolder.walletTypeBalance.setText(list.get(i).getBalance());
        viewHolder.walletMoney.setText(list.get(i).getMoney() + "");

        MyHandler myHandler = new MyHandler();
        myHandler.setViewHolder(viewHolder);
        MyThread myThread = new MyThread();
        myThread.setMyHandler(myHandler);
        myThread.setImageURL(list.get(i).getColor());
        myThread.start();

        return view;
    }

    class ViewHolder {
        TextView walletTypeText;
        TextView walletTypeBalance;
        TextView walletMoney;
        RelativeLayout walletDepositCardRelativelayout;
    }

    class MyThread extends Thread {
        String imageURL;

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        MyHandler myHandler;

        public void setMyHandler(MyHandler myHandler) {
            this.myHandler = myHandler;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageURL);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                if (bitmap != null) {
                    myHandler.setBitmap(bitmap);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myHandler.sendEmptyMessage(0);
        }
    }


    class MyHandler extends android.os.Handler {
        ViewHolder viewHolder;
        Bitmap drawable;

        public void setBitmap(Bitmap drawable) {
            this.drawable = drawable;
        }

        public void setViewHolder(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (drawable != null) {
                BitmapDrawable bd = new BitmapDrawable(drawable);
                viewHolder.walletDepositCardRelativelayout.setBackground(bd);
            }
        }
    }
}
