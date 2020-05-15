package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppOrdergetByCardBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ViseUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianOrderDetailsActivity extends BaseActivity {

    private Context context = TijianOrderDetailsActivity.this;

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_electronic_code)
    TextView tvElectronicCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
//    @BindView(R.id.tv_idnum)
//    TextView tvIdnum;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.view_code)
    View viewCode;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.tv_title_bottom)
    TextView tvTitleBottom;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_coupons_price)
    TextView tvCouponsPrice;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_order_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtil.setStatusBarColor(TijianOrderDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(TijianOrderDetailsActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(TijianOrderDetailsActivity.this,0x55000000);
        }
        ButterKnife.bind(TijianOrderDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.AppOrdergetByCard, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppOrdergetByCardBean bean = gson.fromJson(s, AppOrdergetByCardBean.class);
                String status = bean.getData().getOrderStatus();
                if(status.equals("1")){
                    viewCode.setVisibility(View.VISIBLE);
                    llCode.setVisibility(View.VISIBLE);
                    tvTopTitle.setText("待使用");
                    tvTitle.setText(bean.getData().getGoodsName());
                    tvTitleBottom.setText(bean.getData().getGoodsName());
                    String code = bean.getData().getElectronicCode();
                    tvElectronicCode.setText(code);
                    Glide.with(context).load(CodeUtils.createImage(code, 400, 400, null)).into(ivCode);
                    tvName.setText("联系人："+bean.getData().getAddresUname());
                    tvPhone.setText("手机号："+bean.getData().getAddresPhone());
//                    tvIdnum.setText("身份证："+bean.getData().getIdNumber());
                    tvOrderId.setText("订单编号："+bean.getData().getId());
                    tvCreateTime.setText("下单时间："+bean.getData().getCreateTime());
                    tvAllPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getPrice(), 2));
                    tvCouponsPrice.setText("￥ - "+ StringUtils.roundByScale(bean.getData().getCouponPrice(), 2));
                    tvPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getOrderRealPrice(), 2));
                }else if(status.equals("4")){
                    viewCode.setVisibility(View.GONE);
                    llCode.setVisibility(View.GONE);
                    tvTopTitle.setText("已使用");
                    tvTitle.setText(bean.getData().getGoodsName());
                    tvName.setText("联系人："+bean.getData().getAddresUname());
                    tvPhone.setText("手机号："+bean.getData().getAddresPhone());
//                    tvIdnum.setText("身份证："+bean.getData().getIdNumber());
                    tvOrderId.setText("订单编号："+bean.getData().getId());
                    tvCreateTime.setText("下单时间："+bean.getData().getCreateTime());
                    tvAllPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getPrice(), 2));
                    tvCouponsPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getCouponPrice(), 2));
                    tvPrice.setText("￥"+ StringUtils.roundByScale(bean.getData().getOrderRealPrice(), 2));
                }
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
