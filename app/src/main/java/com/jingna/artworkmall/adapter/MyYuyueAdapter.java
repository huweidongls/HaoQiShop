package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppMakeAnapPointmentOrderControlleryuYueListBean;
import com.jingna.artworkmall.page.YuyueDetailsActivity;
import com.jingna.artworkmall.util.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2020/5/25.
 */

public class MyYuyueAdapter extends RecyclerView.Adapter<MyYuyueAdapter.ViewHolder> {

    private Context context;
    private List<AppMakeAnapPointmentOrderControlleryuYueListBean.DataBean> data;

    public MyYuyueAdapter(List<AppMakeAnapPointmentOrderControlleryuYueListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_my_yuyue, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(data.get(position).getAppointmentTyp());
        holder.tvTime.setText("下单时间: "+data.get(position).getCreateTime());
        final int status = data.get(position).getStatus();
        if(status == 0){
            holder.tvType.setText("待抢单");
        }else if(status == 1){
            holder.tvType.setText("配送中");
        }else if(status == 2){
            holder.tvType.setText("已完成");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 0){
                    ToastUtil.showShort(context, "暂无配送员接单");
                }else if(status == 1){
                    Intent intent = new Intent();
                    intent.setClass(context, YuyueDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }else if(status == 2){
                    Intent intent = new Intent();
                    intent.setClass(context, YuyueDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }
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
        private TextView tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }

}
