package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppOrderqueryListJfscBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.JifenOrderDetailsActivity;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/16.
 */

public class JifenOrderAdapter extends RecyclerView.Adapter<JifenOrderAdapter.ViewHolder> {

    private Context context;
    private List<AppOrderqueryListJfscBean.DataBean> data;

    public JifenOrderAdapter(List<AppOrderqueryListJfscBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_jifen_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideUtils.into(context, NetUrl.BASE_URL+data.get(position).getAppPic(), holder.iv);
        holder.tvTitle.setText(data.get(position).getGoodsName());
        holder.tvPrice.setText("合计"+ StringUtils.roundByScale(data.get(position).getOrderRealPrice(), 2));
        String status = data.get(position).getOrderStatus();
        holder.tvNum.setText("数量："+data.get(position).getNum());
        String[] s = data.get(position).getLabel().split(",");
        LabelAdapter labelAdapter = new LabelAdapter(s);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(labelAdapter);
        if(status.equals("1")){
            holder.tvStatus.setText("待发货");
        }else if(status.equals("2")){
            holder.tvStatus.setText("待收货");
        }else if(status.equals("3")){
            holder.tvStatus.setText("已签收");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, JifenOrderDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvStatus;
        private RecyclerView rv;
        private TextView tvNum;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvStatus = itemView.findViewById(R.id.tv_status);
            rv = itemView.findViewById(R.id.rv);
            tvNum = itemView.findViewById(R.id.tv_num);
        }
    }

}
