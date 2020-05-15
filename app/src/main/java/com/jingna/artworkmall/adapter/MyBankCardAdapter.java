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
import com.jingna.artworkmall.bean.BankCardListBean;
import com.jingna.artworkmall.page.InsertBankCardActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class MyBankCardAdapter extends RecyclerView.Adapter<MyBankCardAdapter.ViewHolder> {

    private Context context;
    private List<BankCardListBean.DataBean> data;
    private ClickListener listener;

    public MyBankCardAdapter(List<BankCardListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_bank_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String phone = "";
        String card = "";
        if(getItemViewType(position) == 1){
            holder.rlBank.setVisibility(View.GONE);
            holder.tvAdd.setVisibility(View.VISIBLE);
        }else {
            if(data.get(position).getCardChannel().equals("支付宝")){
                holder.rlBank.setBackgroundResource(R.drawable.bg_00a0e9_6dp);
                Glide.with(context).load(R.mipmap.zhifubao).into(holder.ivType);
                holder.tvType.setText("支付宝");
            }else if(data.get(position).getCardChannel().equals("银行卡")){
                holder.rlBank.setBackgroundResource(R.drawable.bg_ca566b_6dp);
                Glide.with(context).load(R.mipmap.yinhangka).into(holder.ivType);
                holder.tvType.setText("储蓄卡");
            }
            holder.rlBank.setVisibility(View.VISIBLE);
            holder.tvAdd.setVisibility(View.GONE);
            holder.tvBankName.setText(data.get(position).getCardName());
            phone = data.get(position).getCardPhone();
            phone = phone.substring(phone.length()-4, phone.length());
            holder.tvPhoneNum.setText("手机尾号"+phone);
            card = data.get(position).getCardNumber();
//            card = card.substring(card.length()-4, card.length());
            holder.tvBankCard.setText(card);
        }
        final String finalCard = card;
        holder.rlBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, data.get(position).getCardName(), finalCard);
            }
        });
        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAdd();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position == data.size()){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout rlBank;
        private TextView tvAdd;
        private TextView tvBankName;
        private TextView tvPhoneNum;
        private TextView tvBankCard;
        private ImageView ivType;
        private TextView tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            rlBank = itemView.findViewById(R.id.rl_bank);
            tvAdd = itemView.findViewById(R.id.tv_add);
            tvBankName = itemView.findViewById(R.id.tv_bank_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phonenum);
            tvBankCard = itemView.findViewById(R.id.tv_bank_card);
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos, String bankName, String card);
        void onAdd();
    }

}
