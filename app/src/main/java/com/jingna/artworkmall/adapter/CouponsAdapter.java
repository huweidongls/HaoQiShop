package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.MarketingCouponUserfindByCouponsBean;
import com.jingna.artworkmall.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/27.
 */

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    private Context context;
    private List<MarketingCouponUserfindByCouponsBean.DataBean> data;
    private ClickListener listener;

    public CouponsAdapter(List<MarketingCouponUserfindByCouponsBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_coupons, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int type = data.get(position).getType();
        if(type == 0){
            holder.llManjian.setVisibility(View.VISIBLE);
            holder.llZhekou.setVisibility(View.GONE);
            holder.tvManjian.setText(((int)data.get(position).getParameter())+"");
            holder.tvBottom.setText("满"+StringUtils.roundByScale(data.get(position).getMaxMoney(), 2)+"元立减");
            holder.rl.setBackgroundResource(R.mipmap.cheng);
            holder.tvMax.setVisibility(View.GONE);
        }else if(type == 1){
            holder.llManjian.setVisibility(View.GONE);
            holder.llZhekou.setVisibility(View.VISIBLE);
            holder.tvZhekou.setText((data.get(position).getParameter()*10)+"");
            holder.tvBottom.setText("满"+StringUtils.roundByScale(data.get(position).getMaxMoney(), 2)+"元可用");
            holder.rl.setBackgroundResource(R.mipmap.lan);
            holder.tvMax.setVisibility(View.VISIBLE);
            holder.tvMax.setText("最高抵扣"+ StringUtils.roundByScale(data.get(position).getSumDiscount(), 2)+"积分");
        }
        holder.tvTitle.setText(data.get(position).getName());
        holder.tvTime.setText(data.get(position).getCreateTime()+"-"+data.get(position).getPastTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llManjian;
        private LinearLayout llZhekou;
        private TextView tvManjian;
        private TextView tvZhekou;
        private TextView tvBottom;
        private TextView tvTitle;
        private TextView tvTime;
        private RelativeLayout rl;
        private TextView tvMax;

        public ViewHolder(View itemView) {
            super(itemView);
            llManjian = itemView.findViewById(R.id.ll_manjian);
            llZhekou = itemView.findViewById(R.id.ll_zhekou);
            tvManjian = itemView.findViewById(R.id.tv_manjian);
            tvZhekou = itemView.findViewById(R.id.tv_zhekou);
            tvBottom = itemView.findViewById(R.id.tv_bottom);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            rl = itemView.findViewById(R.id.rl);
            tvMax = itemView.findViewById(R.id.tv_max);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos);
    }

}
