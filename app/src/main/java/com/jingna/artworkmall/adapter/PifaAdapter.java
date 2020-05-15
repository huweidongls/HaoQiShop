package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.util.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/12/12.
 */

public class PifaAdapter extends RecyclerView.Adapter<PifaAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public PifaAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_pifa, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideUtils.into(context, data.get(position), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }

}
