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
import com.jingna.artworkmall.adapter.TijianOrderAdapter;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.AppOrderqueryListTjkBean;
import com.jingna.artworkmall.util.SpUtils;
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
 * Created by Administrator on 2019/12/30.
 */

public class FragmentTijianOrder extends BaseFragment {

    public static FragmentTijianOrder newInstance(String id) {
        FragmentTijianOrder newFragment = new FragmentTijianOrder();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private TijianOrderAdapter adapter;
    private List<AppOrderqueryListTjkBean.DataBean> mList;

    private String id = "";
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tijian_order, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
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
                map.put("pageNum", "1");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(getContext()));
                if(!id.equals("0")){
                    map.put("status", id);
                }
                ViseUtil.Get(getContext(), NetUrl.AppOrderqueryListTjk, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppOrderqueryListTjkBean bean = gson.fromJson(s, AppOrderqueryListTjkBean.class);
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
                map.put("pageNum", page+"");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(getContext()));
                if(!id.equals("0")){
                    map.put("status", id);
                }
                ViseUtil.Get(getContext(), NetUrl.AppOrderqueryListTjk, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppOrderqueryListTjkBean bean = gson.fromJson(s, AppOrderqueryListTjkBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page+1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        map.put("userId", SpUtils.getUserId(getContext()));
        if(!id.equals("0")){
            map.put("status", id);
        }
        ViseUtil.Get(getContext(), NetUrl.AppOrderqueryListTjk, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppOrderqueryListTjkBean bean = gson.fromJson(s, AppOrderqueryListTjkBean.class);
                mList = bean.getData();
                adapter = new TijianOrderAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }
        });

    }
}
