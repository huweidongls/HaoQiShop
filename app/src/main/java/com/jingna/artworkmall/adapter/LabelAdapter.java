package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.LabelBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/1/7.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.ViewHolder> {

    private Context context;
    private String[] data;
    private List<LabelBean> colors;

    public LabelAdapter(String[] data) {
        this.data = data;
        colors = new ArrayList<>();
        colors.add(new LabelBean(R.drawable.bg_ff500b_10dp_bord, "#FF500B"));
        colors.add(new LabelBean(R.drawable.bg_66a7fd_10dp_bord, "#66A7FD"));
        colors.add(new LabelBean(R.drawable.bg_f19c27_10dp_bord, "#F19C27"));
        colors.add(new LabelBean(R.drawable.bg_94a3bb_10dp_bord, "#94A3BB"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_label, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Random random = new Random();
        int index = random.nextInt(4);
        holder.tv.setBackgroundResource(colors.get(index).getBg());
        holder.tv.setTextColor(Color.parseColor(colors.get(index).getColor()));
        holder.tv.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
