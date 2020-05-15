package com.jingna.artworkmall.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.PifaAdapter;
import com.jingna.artworkmall.adapter.YouxuanAdapter;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.bean.AppGoodsShopqueryListBean;
import com.jingna.artworkmall.bean.IndexPageApifindBannerCategoryBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.FenleiActivity;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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

public class Fragment4 extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;

    private YouxuanAdapter adapter;
    private List<AppGoodsShopqueryListBean.DataBean> mList;
    private int page = 1;

    private PopupWindow popupWindow;
    private String status = "";
    private String categoryId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, null);

        ButterKnife.bind(this, view);
        initBanner();
        initData();

        return view;
    }

    private void initBanner() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", "1");
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
                if(!StringUtils.isEmpty(status)){
                    map.put("status", status);
                }
                if(!StringUtils.isEmpty(categoryId)){
                    map.put("categoryId", categoryId);
                }
                ViseUtil.Get(getContext(), NetUrl.AppGoodsShopqueryList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppGoodsShopqueryListBean bean = gson.fromJson(s, AppGoodsShopqueryListBean.class);
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
                if(!StringUtils.isEmpty(status)){
                    map.put("status", status);
                }
                if(!StringUtils.isEmpty(categoryId)){
                    map.put("categoryId", categoryId);
                }
                ViseUtil.Get(getContext(), NetUrl.AppGoodsShopqueryList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AppGoodsShopqueryListBean bean = gson.fromJson(s, AppGoodsShopqueryListBean.class);
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
        if(!StringUtils.isEmpty(status)){
            map.put("status", status);
        }
        if(!StringUtils.isEmpty(categoryId)){
            map.put("categoryId", categoryId);
        }
        ViseUtil.Get(getContext(), NetUrl.AppGoodsShopqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppGoodsShopqueryListBean bean = gson.fromJson(s, AppGoodsShopqueryListBean.class);
                mList = bean.getData();
                adapter = new YouxuanAdapter(mList);
                GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }
        });

    }

    @OnClick({R.id.rl_fenlei, R.id.rl_xiaoliang, R.id.rl_jiage})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_fenlei:
                intent.setClass(getContext(), FenleiActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.rl_xiaoliang:
                status = "1";
                tvXiaoliang.setTextColor(Color.parseColor("#A02630"));
                tvJiage.setTextColor(Color.parseColor("#777777"));
                tvJiage.setText("价格排序");
                refreshData();
                break;
            case R.id.rl_jiage:
                showJiage();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101&&resultCode == 102&&data != null){
            categoryId = data.getStringExtra("categoryId");
            refreshData();
        }
    }

    private void showJiage(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_youxuan_jiage, null);

        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "2";
                tvXiaoliang.setTextColor(Color.parseColor("#777777"));
                tvJiage.setTextColor(Color.parseColor("#A02630"));
                tvJiage.setText("价格升序");
                popupWindow.dismiss();
                refreshData();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "3";
                tvXiaoliang.setTextColor(Color.parseColor("#777777"));
                tvJiage.setTextColor(Color.parseColor("#A02630"));
                tvJiage.setText("价格降序");
                popupWindow.dismiss();
                refreshData();
            }
        });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(ll);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
            }
        });

    }

    private void refreshData(){

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        if(!StringUtils.isEmpty(status)){
            map.put("status", status);
        }
        if(!StringUtils.isEmpty(categoryId)){
            map.put("categoryId", categoryId);
        }
        ViseUtil.Get(getContext(), NetUrl.AppGoodsShopqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppGoodsShopqueryListBean bean = gson.fromJson(s, AppGoodsShopqueryListBean.class);
                mList.clear();
                mList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                page = 2;
            }
        });

    }

}
