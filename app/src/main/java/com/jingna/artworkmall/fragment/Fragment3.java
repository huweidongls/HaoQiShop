package com.jingna.artworkmall.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.Fragment3Adapter;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.bean.AppGoodsContentqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/12/10.
 */

public class Fragment3 extends BaseFragment {

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private Fragment3Adapter adapter;
    private List<AppGoodsContentqueryListBean.DataBean> mList;

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageSize", "10");
                map.put("pageNum", "1");
                ViseUtil.Get(getContext(), NetUrl.AppGoodsContentqueryList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppGoodsContentqueryListBean bean = gson.fromJson(s, AppGoodsContentqueryListBean.class);
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
                map.put("pageSize", "10");
                map.put("pageNum", page+"");
                ViseUtil.Get(getContext(), NetUrl.AppGoodsContentqueryList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppGoodsContentqueryListBean bean = gson.fromJson(s, AppGoodsContentqueryListBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "10");
        map.put("pageNum", "1");
        ViseUtil.Get(getContext(), NetUrl.AppGoodsContentqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppGoodsContentqueryListBean bean = gson.fromJson(s, AppGoodsContentqueryListBean.class);
                mList = bean.getData();
                adapter = new Fragment3Adapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }
        });

    }

}
