package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppShopMemberEquityControllerqueryListBean;
import com.jingna.artworkmall.page.HuiyuanQuanyiDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/5/23.
 */

public class HuiyuanQuanyiAdapter extends RecyclerView.Adapter<HuiyuanQuanyiAdapter.ViewHolder> {

    private Context context;
    private List<AppShopMemberEquityControllerqueryListBean.DataBean> data;

    public HuiyuanQuanyiAdapter(List<AppShopMemberEquityControllerqueryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_huiyuanquanyi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitle.setText(data.get(position).getShopName());
        holder.tvTime.setText(data.get(position).getStopTime());
        holder.tvCs.setText(data.get(position).getShopCs()+"");

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, HuiyuanQuanyiDetailsActivity.class);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvCs;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvCs = itemView.findViewById(R.id.tv_cs);
        }
    }

}
