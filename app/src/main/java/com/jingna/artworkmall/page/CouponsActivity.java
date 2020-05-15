package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.CouponsAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.MarketingCouponUserfindByCouponsBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
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

public class CouponsActivity extends BaseActivity {

    private Context context = CouponsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private CouponsAdapter adapter;
    private List<MarketingCouponUserfindByCouponsBean.DataBean> mList;

    private double price = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        price = getIntent().getDoubleExtra("price", 0.00);
        StatusBarUtil.setStatusBarColor(CouponsActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(CouponsActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(CouponsActivity.this, 0x55000000);
        }
        ButterKnife.bind(CouponsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("type", "0");
        ViseUtil.Post(context, NetUrl.MarketingCouponUserfindByCoupons, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MarketingCouponUserfindByCouponsBean bean = gson.fromJson(s, MarketingCouponUserfindByCouponsBean.class);
                mList = bean.getData();
                adapter = new CouponsAdapter(mList, new CouponsAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        if(mList.get(pos).getMaxMoney()<=price){
                            Intent intent = new Intent();
                            intent.putExtra("bean", mList.get(pos));
                            setResult(1006, intent);
                            finish();
                        }else {
                            ToastUtil.showShort(context, "满"+ StringUtils.roundByScale(mList.get(pos).getMaxMoney(), 2)+"元可用");
                        }
                    }
                });
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
