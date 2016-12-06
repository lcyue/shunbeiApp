package com.example.administrator.instantaneousbeiapp.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/5.
 */
public class remindAdapter extends BaseAdapter {
    Context context;
    List<MRemind> list;
    LayoutInflater layoutInflater;
    public remindAdapter(Context context, List<MRemind> list){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.slip_list_item,null);
            viewHolder.RemindTime = (TextView) view.findViewById(R.id.menu_time1);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.RemindTime.setText(list.get(i).getRemindTime());

        final SwipeListLayout sll_main = (SwipeListLayout) view
                .findViewById(R.id.sll_main);
        TextView tv_top = (TextView) view.findViewById(R.id.top);
        TextView tv_delete = (TextView) view.findViewById(R.id.delete);
        sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(
                sll_main));
        tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);
                MRemind str = list.get(i);
                list.remove(i);
                list.add(0, str);
                notifyDataSetChanged();
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    class ViewHolder{
        TextView RemindTime;
    }

    public void addItem(MRemind time) {
        list.add(time);
        notifyDataSetChanged();
    }

    private Set<SwipeListLayout> sets = new HashSet();
    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }
}
