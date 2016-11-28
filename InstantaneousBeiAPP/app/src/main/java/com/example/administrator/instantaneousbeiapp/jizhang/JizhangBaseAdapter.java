package com.example.administrator.instantaneousbeiapp.jizhang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class JizhangBaseAdapter extends BaseAdapter {
    Context context;
    List<JizhangView> list;

    public JizhangBaseAdapter(Context context,List<JizhangView> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.gridview_layout,null);
            viewHolder.jizhang_xuanzhe_image= (ImageView) convertView.findViewById(R.id.shunbei_image);
            viewHolder.jizhang_xuanzhe_text= (TextView) convertView.findViewById(R.id.shunbei_text);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        JizhangView jizhangView = list.get(position);
        Log.i("jizhangView","========"+jizhangView.getJizhang_xuanzhe_text());
        viewHolder.jizhang_xuanzhe_text.setText(jizhangView.getJizhang_xuanzhe_text());
        viewHolder.jizhang_xuanzhe_image.setImageResource(jizhangView.getJizhang_xuanzhe_image());
        XuanzheShijianActivity xuanzheShijianActivity = new XuanzheShijianActivity();

        return convertView;
    }
    public class ViewHolder{
        ImageView jizhang_xuanzhe_image;
        TextView jizhang_xuanzhe_text;
    }
}
