package com.example.administrator.instantaneousbeiapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.mvc.AccountType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AccountTypeAdapter extends BaseAdapter {
    Context context;
    ArrayList<AccountType> list;
    LayoutInflater layoutInflater;

    public AccountTypeAdapter(Context context, ArrayList<AccountType> list) {
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
            view = layoutInflater.inflate(R.layout.jizhang_accucont_dialog_listvie_item, null);
            viewHolder.typeText = (TextView) view.findViewById(R.id.type_text);
            viewHolder.typeImge = (ImageView) view.findViewById(R.id.type_imge);
            viewHolder.balanceMoney = (TextView) view.findViewById(R.id.balance_money);
            viewHolder.gou = (ImageView) view.findViewById(R.id.jizhang_gou);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.typeText.setText(list.get(i).getTypeText());
        viewHolder.balanceMoney.setText(list.get(i).getBalanceMoney() + "");
        if (list.get(i).isGou() == false) {
            viewHolder.gou.setVisibility(View.GONE);
        } else {
            viewHolder.gou.setVisibility(View.VISIBLE);
        }

        MyHandler myHandler = new MyHandler();
        myHandler.setViewHolder(viewHolder);
        MyThread myThread = new MyThread();
        myThread.setMyHandler(myHandler);
        myThread.setImageURL(list.get(i).getTypeImge());
        myThread.start();

        return view;
    }

    class ViewHolder {
        TextView typeText;
        ImageView typeImge;
        TextView balanceMoney;
        ImageView gou;
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
        Bitmap bitmap;

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public void setViewHolder(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (bitmap != null) {
                viewHolder.typeImge.setImageBitmap(bitmap);
            }
        }
    }
}

