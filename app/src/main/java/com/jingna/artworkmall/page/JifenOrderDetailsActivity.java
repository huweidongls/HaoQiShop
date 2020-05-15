package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.LabelAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppOrdergetByGoodsBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JifenOrderDetailsActivity extends BaseActivity {

    private Context context = JifenOrderDetailsActivity.this;

    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_coupons_price)
    TextView tvCouponsPrice;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_order_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtil.setStatusBarColor(JifenOrderDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(JifenOrderDetailsActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(JifenOrderDetailsActivity.this,0x55000000);
        }
        ButterKnife.bind(JifenOrderDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.AppOrdergetByGoods, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppOrdergetByGoodsBean bean = gson.fromJson(s, AppOrdergetByGoodsBean.class);
                int num = bean.getData().getNum();
                tvAddressName.setText(bean.getData().getAddresUname());
                tvAddressPhone.setText(bean.getData().getAddresPhone());
                tvAddress.setText("地址："+bean.getData().getAddresName());
                GlideUtils.into(context, NetUrl.BASE_URL+bean.getData().getAppPic(), iv);
                tvTitle.setText(bean.getData().getGoodsName());
                tvOrderId.setText("订单编号："+bean.getData().getId());
                tvCreateTime.setText("下单时间："+bean.getData().getCreateTime());
                tvPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getOrderRealPrice(), 2));
                tvAllPrice.setText("￥"+ StringUtils.roundByScale((bean.getData().getPrice()*num), 2));
                tvCouponsPrice.setText("￥ - "+ StringUtils.roundByScale(bean.getData().getCouponPrice(), 2));
                tvNum.setText("数量："+bean.getData().getNum());
                String[] ss = bean.getData().getLabel().split(",");
                LabelAdapter labelAdapter = new LabelAdapter(ss);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv.setLayoutManager(manager);
                rv.setAdapter(labelAdapter);
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
