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
import com.jingna.artworkmall.bean.AppGoodsContentqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.FxDetailsActivity;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/11.
 */

public class Fragment3Adapter extends RecyclerView.Adapter<Fragment3Adapter.ViewHolder> {

    private Context context;
    private List<AppGoodsContentqueryListBean.DataBean> data;

    public Fragment3Adapter(List<AppGoodsContentqueryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_fragment3, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTime.setText(StringUtils.friendly_time(data.get(position).getCreateTime()));
        GlideUtils.into(context, NetUrl.BASE_URL+data.get(position).getContentImg(), holder.iv);
        holder.tvTitle.setText(data.get(position).getSubTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, FxDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTime;
        private ImageView iv;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
