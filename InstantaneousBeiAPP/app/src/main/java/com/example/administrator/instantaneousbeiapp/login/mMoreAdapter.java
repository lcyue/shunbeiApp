package com.example.administrator.instantaneousbeiapp.login;

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
 * Created by Administrator on 2016/11/25.
 */
public class mMoreAdapter extends BaseAdapter {
    Context context;
    List<MoreData> list;
    LayoutInflater layoutInflater;
    public mMoreAdapter(Context context, List<MoreData> list){
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
            view = layoutInflater.inflate(R.layout.layout_item_more_user,null);
            viewHolder.content = (TextView) view.findViewById(R.id.more_user_content);
            viewHolder.picUrl = (ImageView) view.findViewById(R.id.more_user_img);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.content.setText(list.get(i).getContent());

        MyHandler myHandler = new MyHandler();
        myHandler.setViewHolder(viewHolder);
        MyThread myThread = new MyThread();
        myThread.setMyHandler(myHandler);
        myThread.setImageURL(list.get(i).getUrl());
        myThread.start();

        return view;
    }
    class ViewHolder{
        TextView content;
        ImageView picUrl;
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
                viewHolder.picUrl.setImageBitmap(bitmap);
            }
        }
    }
}
