package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppShopCategoryqueryListBean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/3.
 */

public class FenleiLeftAdapter extends RecyclerView.Adapter<FenleiLeftAdapter.ViewHolder> {

    private Context context;
    private List<AppShopCategoryqueryListBean.DataBean> data;
    private int select = 0;
    private ClickListener listener;

    public FenleiLeftAdapter(List<AppShopCategoryqueryListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_fenlei_left, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(select == position){
            holder.tv.setTextColor(Color.parseColor("#333333"));
            holder.tv.setBackgroundResource(R.color.white_ffffff);
        }else {
            holder.tv.setTextColor(Color.parseColor("#999999"));
            holder.tv.setBackgroundResource(R.color.gray_fbfbfb);
        }
        holder.tv.setText(data.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select != position){
                    select = position;
                    notifyDataSetChanged();
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos);
    }

}
