package com.jingna.artworkmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.bean.AppMemberSignqueryListBean;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2019/12/29.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private Context context;
    private List<AppMemberSignqueryListBean.DataBean> data;
    private boolean is366 = false;
    private String yearMonth;

    public CalendarAdapter(String yearMonth, List<AppMemberSignqueryListBean.DataBean> data) {
        this.data = data;
        this.yearMonth = yearMonth;
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        ca.set(year, Calendar.DECEMBER, 31);
        if (ca.get(Calendar.DAY_OF_YEAR) == 366) {
            is366 = true;
        } else {
            is366 = false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_calendar, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        for (int i = 0; i<data.size(); i++){
            if(data.get(i).getSignDay().equals(formatTimeUnit(position+1))){
                holder.iv.setVisibility(View.VISIBLE);
                break;
            }else {
                holder.iv.setVisibility(View.GONE);
            }
        }
//        for (AppMemberSignqueryListBean.DataBean bean : data){
//            if(bean.getSignDay().equals(formatTimeUnit(position+1))){
//                holder.iv.setVisibility(View.VISIBLE);
//            }else {
//                holder.iv.setVisibility(View.GONE);
//            }
//        }

        holder.tv.setText(formatTimeUnit(position+1));
    }

    /**
     * 将“0-9”转换为“00-09”
     */
    private String formatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

    @Override
    public int getItemCount() {
        String month = yearMonth.split("-")[1];
        if(month.equals("1")){
            return 31;
        }else if(month.equals("2")){
            if(is366){
                return 29;
            }else {
                return 28;
            }
        }else if(month.equals("3")){
            return 31;
        }else if(month.equals("4")){
            return 30;
        }else if(month.equals("5")){
            return 31;
        }else if(month.equals("6")){
            return 30;
        }else if(month.equals("7")){
            return 31;
        }else if(month.equals("8")){
            return 31;
        }else if(month.equals("9")){
            return 30;
        }else if(month.equals("10")){
            return 31;
        }else if(month.equals("11")){
            return 30;
        }else if(month.equals("12")){
            return 31;
        }
        return 31;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
