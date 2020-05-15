package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PtJifenActivity extends BaseActivity {

    private Context context = PtJifenActivity.this;

    @BindView(R.id.tv_ptb)
    TextView tvPtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_jifen);

        StatusBarUtil.setStatusBarColor(PtJifenActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(PtJifenActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(PtJifenActivity.this,0x55000000);
        }
        ButterKnife.bind(PtJifenActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppPlatformBalanceMybalance, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    double ptb = jsonObject.optDouble("data");
                    tvPtb.setText(StringUtils.roundByScale(ptb, 2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_details, R.id.btn_tixian, R.id.btn_chongzhi})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_details:
                intent.setClass(context, PtJifenDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tixian:
                intent.setClass(context, CommissionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_chongzhi:
                ToastUtil.showShort(context, "暂未开放");
//                intent.setClass(context, ChongzhiActivity.class);
//                startActivity(intent);
                break;
        }
    }

}
