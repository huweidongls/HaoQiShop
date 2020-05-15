package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppShopCategoryqueryChildListBean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/3.
 */

public class FenleiRvAdapter extends RecyclerView.Adapter<FenleiRvAdapter.ViewHolder> {

    private Context context;
    private List<AppShopCategoryqueryChildListBean.DataBean.CommonlyBean> data;
    private int select = -1;
    private ClickListener listener;

    public FenleiRvAdapter(List<AppShopCategoryqueryChildListBean.DataBean.CommonlyBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_fenlei_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(select == position){
            holder.tv.setTextColor(Color.parseColor("#A02630"));
        }else {
            holder.tv.setTextColor(Color.parseColor("#999999"));
        }
        holder.tv.setText(data.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select != position){
                    select = position;
                    notifyDataSetChanged();
                    listener.onItemClick(data.get(position).getId()+"");
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
        void onItemClick(String s);
    }

}
