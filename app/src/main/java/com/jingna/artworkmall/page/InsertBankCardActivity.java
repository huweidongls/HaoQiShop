package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.dialog.BankCodeDialog;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertBankCardActivity extends BaseActivity {

    private Context context = InsertBankCardActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.btn_agree)
    Button btnAgree;
    @BindView(R.id.et_bank_card)
    EditText etBankCard;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_real_name)
    EditText etRealName;

    private String userId = "";

    private boolean isAgree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bank_card);

        userId = SpUtils.getUserId(context);
        StatusBarUtil.setStatusBarColor(InsertBankCardActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(InsertBankCardActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(InsertBankCardActivity.this,0x55000000);
        }
        ButterKnife.bind(InsertBankCardActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.iv_agree, R.id.btn_agree})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_agree:
                if(!isAgree){
                    isAgree = true;
                    Glide.with(context).load(R.mipmap.apply_true).into(ivAgree);
                    btnAgree.setBackgroundResource(R.drawable.bg_ff0004_2dp);
                }else {
                    isAgree = false;
                    Glide.with(context).load(R.mipmap.apply_false).into(ivAgree);
                    btnAgree.setBackgroundResource(R.drawable.bg_cccccc_2dp);
                }
                break;
            case R.id.btn_agree:
                if(isAgree){
                    final String bankCard = etBankCard.getText().toString();
                    final String phoneNum = etPhonenum.getText().toString();
                    final String bankName = etBankName.getText().toString();
                    final String realName = etRealName.getText().toString();
                    if(StringUtils.isEmpty(bankCard)||StringUtils.isEmpty(phoneNum)||StringUtils.isEmpty(bankName)
                            ||StringUtils.isEmpty(realName)){
                        ToastUtil.showShort(context, "请完善信息后提交");
                    }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
                        ToastUtil.showShort(context, "请输入正确格式的手机号码");
                    }else {
                        BankCodeDialog dialog = new BankCodeDialog(context, phoneNum, new BankCodeDialog.ClickListener() {
                            @Override
                            public void onSure() {
                                Map<String, String> map = new LinkedHashMap<>();
                                map.put("cardMemberId", SpUtils.getUserId(context));
                                map.put("cardNumber", bankCard);
                                map.put("cardPhone", phoneNum);
                                map.put("cardName", bankName);
                                map.put("realName", realName);
                                map.put("cardChannel", "银行卡");
                                ViseUtil.Post(context, NetUrl.AppBankCardtoUpdate, map, new ViseUtil.ViseListener() {
                                    @Override
                                    public void onReturn(String s) {
                                        Intent intent = new Intent();
                                        intent.setClass(context, InsertBankCardSuccessActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        });
                        dialog.show();
                    }
                }else {
                    ToastUtil.showShort(context, "需同意支付协议才能绑定！");
                }
                break;
        }
    }

}
