package com.jingna.artworkmall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.CalendarAdapter;
import com.jingna.artworkmall.bean.AppMemberSignqueryListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/29.
 */

public class DialogCalendar extends Dialog {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView iv;
    private TextView tvTime;
    private CalendarAdapter adapter;
    private String yearMonth;
    private List<AppMemberSignqueryListBean.DataBean> data;

    public DialogCalendar(@NonNull Context context, String yearMonth, List<AppMemberSignqueryListBean.DataBean> data) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.yearMonth = yearMonth;
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_calendar, null);
        setContentView(view);

        recyclerView = view.findViewById(R.id.rv);
        iv = view.findViewById(R.id.iv);
        tvTime = view.findViewById(R.id.tv_time);

        tvTime.setText(yearMonth);
        adapter = new CalendarAdapter(yearMonth, data);
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}
