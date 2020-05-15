package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;

import java.util.List;

/**
 * Created by Administrator on 2019/12/29.
 */

public class YouxuanItemAdapter extends RecyclerView.Adapter<YouxuanItemAdapter.ViewHolder> {

    private Context context;
    private String[] data;

    public YouxuanItemAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_youxuan_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
