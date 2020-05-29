package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.HuiyuanQuanyiAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppShopMemberEquityControllerqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.Logger;
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

public class HuiyuanQuanyiActivity extends BaseActivity {

    private Context context = HuiyuanQuanyiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private HuiyuanQuanyiAdapter adapter;
    private List<AppShopMemberEquityControllerqueryListBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiyuan_quanyi);

        StatusBarUtil.setStatusBar(HuiyuanQuanyiActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(HuiyuanQuanyiActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppShopMemberEquityControllerqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppShopMemberEquityControllerqueryListBean bean = gson.fromJson(s, AppShopMemberEquityControllerqueryListBean.class);
                mList = bean.getData();
                adapter = new HuiyuanQuanyiAdapter(mList);
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
