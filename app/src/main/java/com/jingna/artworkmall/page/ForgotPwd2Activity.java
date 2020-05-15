package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwd2Activity extends AppCompatActivity {

    private Context context = ForgotPwd2Activity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    private String tel = "";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd2);

        tel = getIntent().getStringExtra("tel");
        StatusBarUtil.setStatusBarColor(ForgotPwd2Activity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(ForgotPwd2Activity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(ForgotPwd2Activity.this, 0x55000000);
        }
        ButterKnife.bind(ForgotPwd2Activity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                sure();
                break;
        }
    }

    private void sure() {

        String pwd = etPwd.getText().toString();
        if(StringUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "密码不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("phone", tel);
            map.put("newPassword", pwd);
            ViseUtil.Post(context, NetUrl.MemUserretrievePassword, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "设置成功");
                    Intent intent = new Intent();
                    intent.setClass(context, PhoneLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

}
