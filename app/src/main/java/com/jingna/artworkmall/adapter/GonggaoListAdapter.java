package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppNoticequeryListBean;
import com.jingna.artworkmall.page.GonggaoDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */

public class GonggaoListAdapter extends RecyclerView.Adapter<GonggaoListAdapter.ViewHolder> {

    private Context context;
    private List<AppNoticequeryListBean.DataBean> data;

    public GonggaoListAdapter(List<AppNoticequeryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_gonggao_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvTime.setText(data.get(position).getUpdateTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GonggaoDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
