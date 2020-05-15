package com.jingna.artworkmall.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AliBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongzhiActivity extends BaseActivity {

    private Context context = ChongzhiActivity.this;

    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl4)
    RelativeLayout rl4;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.rl5)
    RelativeLayout rl5;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.rl6)
    RelativeLayout rl6;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.rl7)
    RelativeLayout rl7;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.rl8)
    RelativeLayout rl8;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.rl9)
    RelativeLayout rl9;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.ll_jine)
    LinearLayout llJine;
    @BindView(R.id.et_jine)
    EditText etJine;

    private static final int SDK_PAY_FLAG = 1;
    private String money = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);

        StatusBarUtil.setStatusBarColor(ChongzhiActivity.this, getResources().getColor(R.color.line));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(ChongzhiActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(ChongzhiActivity.this,0x55000000);
        }
        ButterKnife.bind(ChongzhiActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.rl6, R.id.rl7, R.id.rl8, R.id.rl9,
    R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl1:
                select(rl1);
                money = "10";
                break;
            case R.id.rl2:
                select(rl2);
                money = "50";
                break;
            case R.id.rl3:
                select(rl3);
                money = "100";
                break;
            case R.id.rl4:
                select(rl4);
                money = "300";
                break;
            case R.id.rl5:
                select(rl5);
                money = "500";
                break;
            case R.id.rl6:
                select(rl6);
                money = "1000";
                break;
            case R.id.rl7:
                select(rl7);
                money = "1500";
                break;
            case R.id.rl8:
                select(rl8);
                money = "2000";
                break;
            case R.id.rl9:
                select(rl9);
                money = "";
                break;
            case R.id.btn_sure:
                String jine = etJine.getText().toString();
                if(StringUtils.isEmpty(money)&&StringUtils.isEmpty(jine)){
                    ToastUtil.showShort(context, "请选择或填写充值金额");
                }else {
                    onSure();
                }
                break;
        }
    }

    private void onSure() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        map.put("str", "充值平台币");
        String jine = etJine.getText().toString();
        if(!StringUtils.isEmpty(money)){
            map.put("ptb", money);
        }else {
            map.put("ptb", jine);
        }
        ViseUtil.Get(context, NetUrl.AppRechargeExtractrechargePtb, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AliBean aliBean = gson.fromJson(s, AliBean.class);
                aliPay(aliBean.getData().getData());
            }
        });

    }

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ChongzhiActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if(result.get("resultStatus").equals("9000")){
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };

    private void select(View view){
        llJine.setVisibility(View.GONE);
        rl1.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv1.setTextColor(Color.parseColor("#9C9C9C"));
        rl2.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv2.setTextColor(Color.parseColor("#9C9C9C"));
        rl3.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv3.setTextColor(Color.parseColor("#9C9C9C"));
        rl4.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv4.setTextColor(Color.parseColor("#9C9C9C"));
        rl5.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv5.setTextColor(Color.parseColor("#9C9C9C"));
        rl6.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv6.setTextColor(Color.parseColor("#9C9C9C"));
        rl7.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv7.setTextColor(Color.parseColor("#9C9C9C"));
        rl8.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv8.setTextColor(Color.parseColor("#9C9C9C"));
        rl9.setBackgroundResource(R.drawable.bg_c6c6c6_2dp_bord);
        tv9.setTextColor(Color.parseColor("#9C9C9C"));
        switch (view.getId()){
            case R.id.rl1:
                rl1.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv1.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl2:
                rl2.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv2.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl3:
                rl3.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv3.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl4:
                rl4.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv4.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl5:
                rl5.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv5.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl6:
                rl6.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv6.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl7:
                rl7.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv7.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl8:
                rl8.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv8.setTextColor(Color.parseColor("#D62424"));
                break;
            case R.id.rl9:
                rl9.setBackgroundResource(R.drawable.bg_d62424_2dp_bord);
                tv9.setTextColor(Color.parseColor("#D62424"));
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.dialog_enter);
                llJine.startAnimation(animation);
                llJine.setVisibility(View.VISIBLE);
                break;
        }
    }

}
