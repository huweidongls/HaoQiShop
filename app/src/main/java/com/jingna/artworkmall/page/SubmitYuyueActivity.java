package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.bean.MarketingCouponUserfindByCouponsBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;

import java.util.Date;
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
    @BindView(R.id.tv_yuyue_type)
    TextView tvYuyueType;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private String addressId = "";
    private String time = "";

    private Dialog dialog;

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

    @OnClick({R.id.rl_back, R.id.rl_address, R.id.tv_shouhuo, R.id.rl_time, R.id.btn_submit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_submit:
                String type = tvYuyueType.getText().toString();
                String beizhu = etBeizhu.getText().toString();
                if(StringUtils.isEmpty(addressId)||StringUtils.isEmpty(time)){
                    ToastUtil.showShort(context, "请完善信息后提交");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("memberId", SpUtils.getUserId(context));
                    map.put("addressId", addressId);
                    map.put("deliveryTime", time);
                    map.put("appointmentTyp", type);
                    if(!StringUtils.isEmpty(beizhu)){
                        map.put("beizhu", beizhu);
                    }
                    ViseUtil.Get(context, NetUrl.AppMakeAnapPointmentOrderControllerSubmitMakeAnapPointment, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            ToastUtil.showShort(context, "提交成功");
                            finish();
                        }
                    });
                }
                break;
            case R.id.rl_time:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(SubmitYuyueActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        time = StringUtils.dateToString(date);
                        tvTime.setText(time);
                    }
                }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("期望送达时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(0xFF23C1C7)//确定按钮文字颜色
                        .setCancelColor(0xFF23C1C7)//取消按钮文字颜色
                        .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                        .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
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
