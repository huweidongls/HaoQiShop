package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppPlatformBalancequeryListBean;
import com.jingna.artworkmall.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/13.
 */

public class PtJifenDetailsAdapter extends RecyclerView.Adapter<PtJifenDetailsAdapter.ViewHolder> {

    private Context context;
    private List<AppPlatformBalancequeryListBean.DataBean> data;

    public PtJifenDetailsAdapter(List<AppPlatformBalancequeryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_ptjifen_details, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getOperatingDescribe());
        holder.tvRecord.setText(StringUtils.roundByScale(data.get(position).getOperatingRecord(), 2)+"");
        holder.tvTime.setText(data.get(position).getCreateTime());
        holder.tvYue.setText("余额"+ StringUtils.roundByScale(data.get(position).getBalance(), 2));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvRecord;
        private TextView tvTime;
        private TextView tvYue;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRecord = itemView.findViewById(R.id.tv_record);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvYue = itemView.findViewById(R.id.tv_yue);
        }
    }

}
