package com.example.administrator.instantaneousbeiapp.homepage.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class StatementBasedapter extends BaseAdapter {
    Context context;
    List<Statementlist> list;
    LayoutInflater layoutInflater;
    public StatementBasedapter(Context context,List<Statementlist> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.statement_list_layout,null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.text_type = (TextView) convertView.findViewById(R.id.text_type);
            viewHolder.text_number = (TextView) convertView.findViewById(R.id.text_number);
            viewHolder.text_percentage = (TextView) convertView.findViewById(R.id.text_percentage);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.text_type.setText(list.get(position).getText_type());
        viewHolder.text_number.setText((int)list.get(position).getText_number());
        viewHolder.text_percentage.setText((int)list.get(position).getText_percentage());

        return convertView;
    }

    public class ViewHolder{
        ImageView image;
        TextView text_type;
        TextView text_number;
        TextView text_percentage;
    }
}
