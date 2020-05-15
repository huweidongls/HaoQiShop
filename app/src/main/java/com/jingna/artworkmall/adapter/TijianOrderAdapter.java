package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.page.AppOrderqueryListTjkBean;
import com.jingna.artworkmall.page.TijianOrderDetailsActivity;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.StringUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/30.
 */

public class TijianOrderAdapter extends RecyclerView.Adapter<TijianOrderAdapter.ViewHolder> {

    private Context context;
    private List<AppOrderqueryListTjkBean.DataBean> data;

    public TijianOrderAdapter(List<AppOrderqueryListTjkBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_tijian_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String status = data.get(position).getOrderStatus();
        if(status.equals("1")){
            holder.rlDaishiyong.setVisibility(View.VISIBLE);
            holder.rlYishiyong.setVisibility(View.GONE);
            holder.tvTitle.setText(data.get(position).getGoodsName());
            holder.tvName.setText("联系人："+data.get(position).getAddresUname());
            holder.tvPhone.setText("手机号："+data.get(position).getAddresPhone());
//            holder.tvIdnum.setText("身份证："+data.get(position).getIdNumber());
            holder.tvPrice.setText("合计  "+ StringUtils.roundByScale(data.get(position).getOrderRealPrice(), 2));
            String code = data.get(position).getElectronicCode();
            holder.tvElectronicCode.setText(code);
            if(!StringUtils.isEmpty(code)){
                Glide.with(context).load(CodeUtils.createImage(code, 400, 400, null)).into(holder.ivCode);
            }
        }else if(status.equals("4")){
            holder.rlDaishiyong.setVisibility(View.GONE);
            holder.rlYishiyong.setVisibility(View.VISIBLE);
            holder.tvYiTitle.setText(data.get(position).getGoodsName());
            holder.tvYiName.setText("联系人："+data.get(position).getAddresUname());
            holder.tvYiPhone.setText("手机号："+data.get(position).getAddresPhone());
            holder.tvYiIdnum.setText("身份证："+data.get(position).getIdNumber());
            holder.tvYiPrice.setText("合计  "+ StringUtils.roundByScale(data.get(position).getOrderRealPrice(), 2));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, TijianOrderDetailsActivity.class);
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

        private RelativeLayout rlYishiyong;
        private RelativeLayout rlDaishiyong;
        private TextView tvYiTitle;
        private TextView tvYiName;
        private TextView tvYiPhone;
        private TextView tvYiIdnum;
        private TextView tvYiPrice;
        private TextView tvTitle;
        private ImageView ivCode;
        private TextView tvElectronicCode;
        private TextView tvName;
        private TextView tvPhone;
//        private TextView tvIdnum;
        private TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            rlYishiyong = itemView.findViewById(R.id.rl_yishiyong);
            rlDaishiyong = itemView.findViewById(R.id.rl_daishiyong);
            tvYiTitle = itemView.findViewById(R.id.tv_yi_title);
            tvYiName = itemView.findViewById(R.id.tv_yi_name);
            tvYiPhone = itemView.findViewById(R.id.tv_yi_phone);
            tvYiIdnum = itemView.findViewById(R.id.tv_yi_idnum);
            tvYiPrice = itemView.findViewById(R.id.tv_yi_price);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivCode = itemView.findViewById(R.id.iv_code);
            tvElectronicCode = itemView.findViewById(R.id.tv_electronic_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
//            tvIdnum = itemView.findViewById(R.id.tv_idnum);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

}
