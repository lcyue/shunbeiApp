package com.example.administrator.instantaneousbeiapp.menu;

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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class WalletTypeAdapter extends BaseAdapter {

    Context context;
    List<WalletType> list;
    LayoutInflater layoutInflater;
    public WalletTypeAdapter(Context context, List<WalletType> list){
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
        if (view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.layout_wallet_account_type_text,null);
            viewHolder.type = (TextView) view.findViewById(R.id.layout_wallet_type_text);
            viewHolder.image = (ImageView) view.findViewById(R.id.layout_wallet_type_img);
            viewHolder.check = (ImageView)view.findViewById(R.id.layout_wallet_type_cancel_img);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.type.setText(list.get(i).getType());
        viewHolder.ischeck = list.get(i).ischeck;
        viewHolder.image.setImageResource(list.get(i).getImage());
        viewHolder.check.setVisibility(view.GONE);
        if (viewHolder.ischeck == true){
            viewHolder.check.setVisibility(View.VISIBLE);
        }

        MyHandler myHandler = new MyHandler();
        myHandler.setViewHolder(viewHolder);
        MyThread myThread = new MyThread();
        myThread.setMyHandler(myHandler);
        //myThread.setImageURL(list.get(i).getImage());
        myThread.start();
        return view;
    }

    class ViewHolder{
        TextView type;
        ImageView image;
        ImageView check;
        Boolean ischeck;
    }

    class MyThread extends Thread{
        String imageURL;
        public void setImageURL(String imageURL){
            this.imageURL = imageURL;
        }
        MyHandler myHandler;
        public void setMyHandler(MyHandler myHandler){
            this.myHandler = myHandler;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageURL);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                if (bitmap != null){
                    myHandler.setBitmap(bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }myHandler.sendEmptyMessage(0);
        }
    }


    class MyHandler extends android.os.Handler{
        ViewHolder viewHolder;
        Bitmap bitmap;
        public void setBitmap(Bitmap bitmap){
            this.bitmap = bitmap;
        }
        public void setViewHolder(ViewHolder viewHolder){
            this.viewHolder = viewHolder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (bitmap != null){
                viewHolder.image.setImageBitmap(bitmap);
            }
        }
    }
}
