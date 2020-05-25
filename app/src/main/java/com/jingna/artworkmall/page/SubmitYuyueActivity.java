package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.bean.MarketingCouponUserfindByCouponsBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitYuyueActivity extends BaseActivity {

    private Context context = SubmitYuyueActivity.this;

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

    private String addressId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_yuyue);

        ButterKnife.bind(SubmitYuyueActivity.this);
        initData();

    }

    private void initData() {

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

    @OnClick({R.id.rl_back, R.id.rl_address, R.id.tv_shouhuo})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
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
    }

}
