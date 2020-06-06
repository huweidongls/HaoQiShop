package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.bean.AppGoodsShopgetByTjkBean;
import com.jingna.artworkmall.bean.MarketingCouponUserfindByCouponsBean;
import com.jingna.artworkmall.dialog.DialogZhifu;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianSureActivity extends BaseActivity {

    private Context context = TijianSureActivity.this;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phonenum)
    TextView tvPhoneNum;
    @BindView(R.id.tv_moren)
    TextView tvMoren;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_shouhuo)
    TextView tvShouhuo;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_coupons)
    TextView tvCoupons;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_coupons_price)
    TextView tvCouponsPrice;
    @BindView(R.id.rl_num)
    RelativeLayout rlNum;

    private String id = "";
    private String addressId = "";
    private String couponsId = "";
    private int goodsNum = 1;

    private AppGoodsShopgetByTjkBean bean;
    private double price = 0.00;
    private double allPrice = 0.00;
    private double couponsPrice = 0.00;

    private String type = "";//0为体检卡1为优选

    private Dialog dialog;

    private double yue = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_sure);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        bean = (AppGoodsShopgetByTjkBean) getIntent().getSerializableExtra("bean");
        StatusBarUtil.setStatusBarColor(TijianSureActivity.this, getResources().getColor(R.color.line));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(TijianSureActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(TijianSureActivity.this,0x55000000);
        }
        ButterKnife.bind(TijianSureActivity.this);
        initData();
        initYue();

    }

    private void initYue() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppPlatformBalanceMybalance, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    yue = jsonObject.optDouble("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initData() {

        if(bean.getData().getIsGood() == 1){
            rlNum.setVisibility(View.GONE);
        }else if(bean.getData().getIsGood() == 2){
            rlNum.setVisibility(View.VISIBLE);
        }
        price = bean.getData().getPrice();
        allPrice = bean.getData().getPrice();
        GlideUtils.into(context, NetUrl.BASE_URL+bean.getData().getAppPic(), iv);
        tvGoodsName.setText(bean.getData().getGoodsName());
        tvAllPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
        tvPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
        Map<String,String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemAdressqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AddressListBean bean = gson.fromJson(s, AddressListBean.class);
                List<AddressListBean.DataBean> list = bean.getData();
                for (int i = 0; i < list.size(); i++){
                    if(list.get(i).getAcquiescentAdress().equals("1")){
                        addressId = list.get(i).getId()+"";
                        tvName.setText(list.get(i).getConsignee());
                        tvPhoneNum.setText(list.get(i).getConsigneeTel());
                        tvAddress.setText(list.get(i).getLocation()+"-"+list.get(i).getAdress());
                        tvMoren.setVisibility(View.VISIBLE);
                        rlAddress.setVisibility(View.VISIBLE);
                    }
                }
                if(rlAddress.getVisibility() == View.GONE){
                    tvShouhuo.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_coupons, R.id.rl_address, R.id.tv_shouhuo, R.id.tv_jian, R.id.tv_jia, R.id.tv_zhifu})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_coupons:
                intent.setClass(context, TijiaoCouponsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("num", goodsNum+"");
                startActivityForResult(intent, 1005);
                break;
            case R.id.rl_address:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("order", "1");
                startActivityForResult(intent, 1001);
                break;
            case R.id.tv_shouhuo:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("order", "1");
                startActivityForResult(intent, 1001);
                break;
            case R.id.tv_jian:
                if(goodsNum>1){
                    goodsNum = goodsNum - 1;
                    tvNum.setText(goodsNum+"");
                    allPrice = price * goodsNum;
                    tvAllPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
                    tvPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
                    tvCoupons.setText("未选择");
                    couponsPrice = 0.00;
                    tvCouponsPrice.setText("－¥"+StringUtils.roundByScale(couponsPrice, 2));
                    couponsId = "";
                }
                break;
            case R.id.tv_jia:
                goodsNum = goodsNum + 1;
                tvNum.setText(goodsNum+"");
                allPrice = price * goodsNum;
                tvAllPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
                tvPrice.setText("¥"+StringUtils.roundByScale(allPrice, 2));
                tvCoupons.setText("未选择");
                couponsPrice = 0.00;
                tvCouponsPrice.setText("－¥"+StringUtils.roundByScale(couponsPrice, 2));
                couponsId = "";
                break;
            case R.id.tv_zhifu:
                if((allPrice-couponsPrice)>yue){
                    ToastUtil.showShort(context, "余额不足");
                }else {
                    DialogZhifu dialogZhifu = new DialogZhifu(context, yue, new DialogZhifu.ClickListener() {
                        @Override
                        public void onYes() {
                            tijiao();
                        }

                        @Override
                        public void onNo() {

                        }
                    });
                    dialogZhifu.show();
                }
                break;
        }
    }

    private synchronized void tijiao() {

        if(StringUtils.isEmpty(addressId)){
            ToastUtil.showShort(context, "请选择收货地址");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("member", SpUtils.getUserId(context));
            map.put("addressId", addressId);
            map.put("goodsId", id);
            map.put("num", goodsNum+"");
            if(!StringUtils.isEmpty(couponsId)){
                map.put("couponId", couponsId);
            }
            ViseUtil.Get(context, NetUrl.AppOrderordersSubmitted, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "积分支付成功");
                    Intent intent = new Intent();
                    intent.setClass(context, TijiaoSuccessActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001&&resultCode == 1002&&data != null){
            AddressListBean.DataBean dataBean = (AddressListBean.DataBean) data.getSerializableExtra("bean");
            addressId = dataBean.getId()+"";
            tvName.setText(dataBean.getConsignee());
            tvPhoneNum.setText(dataBean.getConsigneeTel());
            tvAddress.setText(dataBean.getLocation()+"-"+dataBean.getAdress());
            if(dataBean.getAcquiescentAdress().equals("0")){
                tvMoren.setVisibility(View.GONE);
            }else{
                tvMoren.setVisibility(View.VISIBLE);
            }
            rlAddress.setVisibility(View.VISIBLE);
            tvShouhuo.setVisibility(View.GONE);
        }
        if(requestCode == 1005&&resultCode == 1006&&data != null){
            MarketingCouponUserfindByCouponsBean.DataBean dataBean = (MarketingCouponUserfindByCouponsBean.DataBean) data.getSerializableExtra("bean");
            couponsId = dataBean.getUcId();
            tvCoupons.setText("已选择");
            int type = dataBean.getType();
            if(type == 0){
                couponsPrice = dataBean.getParameter();
                if(allPrice>couponsPrice){
                    tvCouponsPrice.setText("－¥"+StringUtils.roundByScale(couponsPrice, 2));
                    tvPrice.setText("¥"+StringUtils.roundByScale((allPrice-couponsPrice), 2));
                }else {
                    tvCouponsPrice.setText("－¥"+StringUtils.roundByScale(allPrice, 2));
                    tvPrice.setText("¥"+StringUtils.roundByScale((0.00), 2));
                }
            }else if(type == 1){
                double zhekou = dataBean.getParameter();
                double youhui = allPrice - (allPrice * zhekou);
                double shangxian = dataBean.getSumDiscount();
                if(youhui>shangxian){
                    couponsPrice = shangxian;
                }else {
                    couponsPrice = youhui;
                }
                tvCouponsPrice.setText("－¥"+StringUtils.roundByScale(couponsPrice, 2));
                tvPrice.setText("¥"+StringUtils.roundByScale((allPrice-couponsPrice), 2));
            }
        }
    }
}
