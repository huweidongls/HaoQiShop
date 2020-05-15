package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.page.InsertAddressActivity;
import com.jingna.artworkmall.page.MainActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/12.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressListBean.DataBean> data;
    private ClickListener listener;

    public AddressAdapter(List<AddressListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_address_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_name.setText(data.get(position).getConsignee());
        holder.tv_phonenum.setText(data.get(position).getConsigneeTel());
        if(data.get(position).getAcquiescentAdress().equals("0")){
            holder.tv_moren.setVisibility(View.GONE);
        }else{
            holder.tv_moren.setVisibility(View.VISIBLE);
        }
        holder.tv_address.setText(data.get(position).getLocation()+"-"+data.get(position).getAdress());
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, InsertAddressActivity.class);
                intent.putExtra("id", data.get(position).getId()+"");
                intent.putExtra("type", "1");
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_phonenum;
        private TextView tv_moren;
        private TextView tv_address;
        private ImageView iv_edit;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phonenum = itemView.findViewById(R.id.tv_phonenum);
            tv_moren = itemView.findViewById(R.id.tv_moren);
            tv_address = itemView.findViewById(R.id.tv_address);
            iv_edit = itemView.findViewById(R.id.iv_edit);
        }
    }

    public interface ClickListener{
        void onClick(int pos);
    }

}
