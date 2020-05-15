package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.FenleiLeftAdapter;
import com.jingna.artworkmall.adapter.FenleiRvAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppShopCategoryqueryChildListBean;
import com.jingna.artworkmall.bean.AppShopCategoryqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FenleiActivity extends BaseActivity {

    private Context context = FenleiActivity.this;

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv)
    RecyclerView rv;

    private FenleiLeftAdapter leftAdapter;
    private List<AppShopCategoryqueryListBean.DataBean> leftList;
    private FenleiRvAdapter adapter;
    private List<AppShopCategoryqueryChildListBean.DataBean.CommonlyBean> mList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);

        StatusBarUtil.setStatusBarColor(FenleiActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(FenleiActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(FenleiActivity.this,0x55000000);
        }
        ButterKnife.bind(FenleiActivity.this);
        initData();

    }

    private void initData() {

        ViseUtil.Get(context, NetUrl.AppShopCategoryqueryList, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppShopCategoryqueryListBean bean = gson.fromJson(s, AppShopCategoryqueryListBean.class);
                leftList = bean.getData();
                leftAdapter = new FenleiLeftAdapter(leftList, new FenleiLeftAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        onLoad(leftList.get(pos).getId());
                        id = "";
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rvLeft.setLayoutManager(manager);
                rvLeft.setAdapter(leftAdapter);
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pid", leftList.get(0).getId()+"");
                ViseUtil.Get(context, NetUrl.AppShopCategoryqueryChildList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson1 = new Gson();
                        AppShopCategoryqueryChildListBean bean1 = gson1.fromJson(s, AppShopCategoryqueryChildListBean.class);
                        mList = bean1.getData().getCommonly();
                        adapter = new FenleiRvAdapter(mList, new FenleiRvAdapter.ClickListener() {
                            @Override
                            public void onItemClick(String s) {
                                id = s;
                            }
                        });
                        LinearLayoutManager manager1 = new LinearLayoutManager(context);
                        manager1.setOrientation(LinearLayoutManager.VERTICAL);
                        rv.setLayoutManager(manager1);
                        rv.setAdapter(adapter);
                    }
                });
            }
        });

    }

    private void onLoad(int pid){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("pid", pid+"");
        ViseUtil.Get(context, NetUrl.AppShopCategoryqueryChildList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson1 = new Gson();
                AppShopCategoryqueryChildListBean bean1 = gson1.fromJson(s, AppShopCategoryqueryChildListBean.class);
                mList = bean1.getData().getCommonly();
                adapter = new FenleiRvAdapter(mList, new FenleiRvAdapter.ClickListener() {
                    @Override
                    public void onItemClick(String s) {
                        id = s;
                    }
                });
                LinearLayoutManager manager1 = new LinearLayoutManager(context);
                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager1);
                rv.setAdapter(adapter);
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.rl_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_sure:
                if(StringUtils.isEmpty(id)){
                    ToastUtil.showShort(context, "请选择分类");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("categoryId", id);
                    setResult(102, intent);
                    finish();
                }
                break;
        }
    }

}
