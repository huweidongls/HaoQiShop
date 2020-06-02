package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.MyYuyueAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppMakeAnapPointmentOrderControlleryuYueListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyYuyueActivity extends BaseActivity {

    private Context context = MyYuyueActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MyYuyueAdapter adapter;
    private List<AppMakeAnapPointmentOrderControlleryuYueListBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yuyue);

        StatusBarUtil.setStatusBar(MyYuyueActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(MyYuyueActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppMakeAnapPointmentOrderControlleryuYueList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppMakeAnapPointmentOrderControlleryuYueListBean bean = gson.fromJson(s, AppMakeAnapPointmentOrderControlleryuYueListBean.class);
                mList = bean.getData();
                adapter = new MyYuyueAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
