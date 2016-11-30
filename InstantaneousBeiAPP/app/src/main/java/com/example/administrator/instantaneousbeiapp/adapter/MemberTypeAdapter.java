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
import com.example.administrator.instantaneousbeiapp.mvc.MemberType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/29.
 */
public class MemberTypeAdapter extends BaseAdapter {
    Context context;
    ArrayList<MemberType> list;
    LayoutInflater layoutInflater;
    public MemberTypeAdapter(Context context, ArrayList<MemberType> list){
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
            view = layoutInflater.inflate(R.layout.jizhang_select_member_listview_item, null);
            viewHolder.memberType = (TextView) view.findViewById(R.id.member_type_text);
            viewHolder.memberGou = (ImageView) view.findViewById(R.id.member_gou);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.memberType.setText(list.get(i).getMemberType());
        if (list.get(i).isMemberGou() == false) {
            viewHolder.memberGou.setVisibility(View.GONE);
        } else {
            viewHolder.memberGou.setVisibility(View.VISIBLE);
        }



        return view;
    }

    class ViewHolder {
        TextView memberType;
        ImageView memberGou;
    }


}

