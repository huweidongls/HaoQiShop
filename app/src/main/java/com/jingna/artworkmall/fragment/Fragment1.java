package com.jingna.artworkmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.Fragment1RvAdapter;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.bean.IndexPageApifindBannerCategoryBean;
import com.jingna.artworkmall.bean.IndexPageApiqueryCardBean;
import com.jingna.artworkmall.bean.IndexPageApiqueryGoodsContentBean;
import com.jingna.artworkmall.bean.IndexPageApiqueryNoticeBean;
import com.jingna.artworkmall.card.CardFxPagerAdapter;
import com.jingna.artworkmall.card.ShadowTransformer;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.GonggaoListActivity;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.widget.ScrollTextView;
import com.jingna.artworkmall.widget.ViewPagerAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/12/10.
 */

public class Fragment1 extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_scroll)
    ScrollTextView tvScroll;

    private CardFxPagerAdapter mCardAdapter;
    private ShadowTransformer shadowTransformer;
//    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<IndexPageApiqueryGoodsContentBean.DataBean> data;

    private Fragment1RvAdapter adapter;
    private List<IndexPageApiqueryCardBean.DataBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);

        ButterKnife.bind(this, view);
        initRefresh();
        initData();
        initGonggao();

        return view;
    }

    private void initGonggao() {

        ViseUtil.Get(getContext(), NetUrl.IndexPageApiqueryNotice, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApiqueryNoticeBean bean = gson.fromJson(s, IndexPageApiqueryNoticeBean.class);
                if(bean.getData().size()>0){
                    List<String> list = new ArrayList<>();
                    for (IndexPageApiqueryNoticeBean.DataBean dataBean : bean.getData()){
                        list.add(dataBean.getTitle());
                    }
                    tvScroll.setList(list);
                    tvScroll.startScroll();
                }
            }
        });

    }

    private void initRefresh() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
                refreshLayout.finishRefresh(1000);
            }
        });

    }

    public void setCardView(List<IndexPageApiqueryGoodsContentBean.DataBean> data) {

        mCardAdapter = new CardFxPagerAdapter(getContext());
        for (int i = 0; i < data.size(); i++) {
            mCardAdapter.addCardItem(data.get(i));
        }
//        mFragmentCardAdapter = new CardFragmentPagerAdapter(getActivity().getSupportFragmentManager(),
//                DensityTool.dp2px(getContext(), 1));
        viewPager.setAdapter(mCardAdapter);
//        shadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
//        shadowTransformer.enableScaling(false);

//        mViewPagerAdapter = new ViewPagerAdapter(getContext(), data);
//        viewPager.setOffscreenPageLimit(3);
//        viewPager.setPageMargin(1);
//        viewPager.setAdapter(mViewPagerAdapter);
//        viewPager.setPageTransformer(false, new GalleryTransformer());

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", "0");
        ViseUtil.Get(getContext(), NetUrl.IndexPageApifindBannerCategory, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApifindBannerCategoryBean bean = gson.fromJson(s, IndexPageApifindBannerCategoryBean.class);
                List<String> list = new ArrayList<>();
                for (IndexPageApifindBannerCategoryBean.DataBean b : bean.getData()){
                    list.add(NetUrl.BASE_URL+b.getAppPic());
                }
//                init(banner, list);
            }
        });

        ViseUtil.Get(getContext(), NetUrl.IndexPageApiqueryGoodsContent, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApiqueryGoodsContentBean bean = gson.fromJson(s, IndexPageApiqueryGoodsContentBean.class);
                data = bean.getData();
                setCardView(data);
            }
        });

        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("pageNum", "0");
        map1.put("pageSize", "0");
        ViseUtil.Get(getContext(), NetUrl.IndexPageApiqueryCard, map1, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApiqueryCardBean bean = gson.fromJson(s, IndexPageApiqueryCardBean.class);
                mList = bean.getData();
                adapter = new Fragment1RvAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(getContext()){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.ll_gonggao})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_gonggao:
                intent.setClass(getContext(), GonggaoListActivity.class);
                startActivity(intent);
                break;
        }
    }

}
