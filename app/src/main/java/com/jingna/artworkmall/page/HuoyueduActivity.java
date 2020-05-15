package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.HuoyueduAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppMemberUserLivenessqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuoyueduActivity extends BaseActivity {

    private Context context = HuoyueduActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_dangqian)
    TextView tvDangqian;

    private HuoyueduAdapter adapter;
    private List<AppMemberUserLivenessqueryListBean.DataBean> mList;

    private int page = 1;
    private int dangqian = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huoyuedu);

        dangqian = getIntent().getIntExtra("dangqian", 0);
        StatusBarUtil.setStatusBarColor(HuoyueduActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(HuoyueduActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(HuoyueduActivity.this,0x55000000);
        }
        ButterKnife.bind(HuoyueduActivity.this);
        initData();

    }

    private void initData() {

        tvDangqian.setText(dangqian+"");

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("id", SpUtils.getUserId(context));
                map.put("pageSize", "10");
                map.put("pageNum", "1");
                ViseUtil.Post(context, NetUrl.AppMemberUserLivenessqueryList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppMemberUserLivenessqueryListBean bean = gson.fromJson(s, AppMemberUserLivenessqueryListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = 2;
                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("id", SpUtils.getUserId(context));
                map.put("pageSize", "10");
                map.put("pageNum", page+"");
                ViseUtil.Post(context, NetUrl.AppMemberUserLivenessqueryList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppMemberUserLivenessqueryListBean bean = gson.fromJson(s, AppMemberUserLivenessqueryListBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page+1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        map.put("pageSize", "10");
        map.put("pageNum", "1");
        ViseUtil.Post(context, NetUrl.AppMemberUserLivenessqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                AppMemberUserLivenessqueryListBean bean = gson.fromJson(s, AppMemberUserLivenessqueryListBean.class);
                mList = bean.getData();
                adapter = new HuoyueduAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
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
