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
import com.jingna.artworkmall.bean.IndexPageApiqueryCardBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.TijianDetailsActivity;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/19.
 */

public class Fragment1RvAdapter extends RecyclerView.Adapter<Fragment1RvAdapter.ViewHolder> {

    private Context context;
    private List<IndexPageApiqueryCardBean.DataBean> data;

    public Fragment1RvAdapter(List<IndexPageApiqueryCardBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_fragment1_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideUtils.into(context, NetUrl.BASE_URL+data.get(position).getAppPic(), holder.iv);
        holder.tvPrice.setText(StringUtils.roundByScale(data.get(position).getPrice(), 2));
        holder.tvName.setText(data.get(position).getGoodsName());
        holder.tvSub.setText(data.get(position).getDescription());
        if(data.get(position).getLabel() != null){
            String[] s = data.get(position).getLabel().split(",");
            LabelAdapter labelAdapter = new LabelAdapter(s);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rv.setLayoutManager(manager);
            holder.rv.setAdapter(labelAdapter);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, TijianDetailsActivity.class);
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

        private ImageView iv;
        private TextView tvPrice;
        private TextView tvName;
        private RecyclerView rv;
        private TextView tvSub;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvName = itemView.findViewById(R.id.tv_name);
            rv = itemView.findViewById(R.id.rv);
            tvSub = itemView.findViewById(R.id.tv_sub);
        }
    }

}
