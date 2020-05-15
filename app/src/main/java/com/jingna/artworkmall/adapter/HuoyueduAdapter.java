package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppMemberUserLivenessqueryListBean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/15.
 */

public class HuoyueduAdapter extends RecyclerView.Adapter<HuoyueduAdapter.ViewHolder> {

    private Context context;
    private List<AppMemberUserLivenessqueryListBean.DataBean> data;

    public HuoyueduAdapter(List<AppMemberUserLivenessqueryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_huoyuedu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTime.setText(data.get(position).getCreateTime());
        int type = data.get(position).getType();
        if(type == 0){
            holder.tvType.setText("阅读内容");
            holder.tv.setText("+ "+data.get(position).getRiHuoRead());
        }else if(type == 1){
            holder.tvType.setText("直推注册账号");
            holder.tv.setText("+ "+data.get(position).getRiHuoTui());
        }else if(type == 2){
            holder.tvType.setText("直推购买业绩商品");
            holder.tv.setText("+ "+data.get(position).getRiHuoTuiPerformance());
        }else if(type == 3){
            holder.tvType.setText("商城消费");
            holder.tv.setText("+ "+data.get(position).getRiHuoPerformance());
        }else if(type == 4){
            holder.tvType.setText("会员签到");
            holder.tv.setText("+ "+data.get(position).getRiHuoSign());
        }else if(type == 5){
            holder.tvType.setText("本期可获取活跃度已达到上限");
            holder.tv.setText("+ 0");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvType;
        private TextView tvTime;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
            tvTime = itemView.findViewById(R.id.tv_time);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
