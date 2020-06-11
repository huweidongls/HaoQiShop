package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.HuiyuanQuanyiAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppShopMemberEquityControllerqueryListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.GlideUtils;
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
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    private HuiyuanQuanyiAdapter adapter;
    private List<AppShopMemberEquityControllerqueryListBean.DataBean.ListBean> mList;

    private String type = "";//0是从首页来

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiyuan_quanyi);

        type = getIntent().getStringExtra("type");
        StatusBarUtil.setStatusBar(HuiyuanQuanyiActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(HuiyuanQuanyiActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemUsergetUserByLlk, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppShopMemberEquityControllerqueryListBean bean = gson.fromJson(s, AppShopMemberEquityControllerqueryListBean.class);

                GlideUtils.into(context, NetUrl.BASE_URL+bean.getData().getHeadPhoto(), ivAvatar);
                tvName.setText(bean.getData().getMemName());
                int i = bean.getData().getSfStatus();
                if(i == 1){
                    tvStatus.setText("vip");
                }else if(i == 2){
                    tvStatus.setText("区域经理");
                }else if(i == 3){
                    tvStatus.setText("大区经理");
                }

                mList = bean.getData().getList();
                adapter = new HuiyuanQuanyiAdapter(mList, new HuiyuanQuanyiAdapter.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        if(type.equals("0")){
                            Intent intent = new Intent();
                            intent.putExtra("qyid", mList.get(pos).getId()+"");
                            setResult(10003, intent);
                            finish();
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
