package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.app.MyApplication;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.LoginBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity {
    private Context context = PhoneLoginActivity.this;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.edget_password)
    EditText edget_password;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        StatusBarUtil.setStatusBar(PhoneLoginActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(PhoneLoginActivity.this);
    }
    @OnClick({R.id.rl_back, R.id.btn_login,R.id.tv_phone_pwd,R.id.tv_register, R.id.tv_forgot})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_phone_pwd:
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                LoginApp();
                break;
            case R.id.tv_forgot:
                intent.setClass(context, ForgotPwdActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 登录APP
     */
    public void LoginApp(){
        String phone = et_phone.getText().toString();
        String pwd = edget_password.getText().toString();
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "请输入账号和密码!");
        } else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String,String> map = new LinkedHashMap<>();
            map.put("username",phone);
            map.put("password",pwd);
            ViseUtil.Get(context, NetUrl.MemUserloginAppPassword, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    LoginBean bean = gson.fromJson(s,LoginBean.class);
                    SpUtils.setToken(context, bean.getData().getToken());
                    SpUtils.setUserId(context, bean.getData().getUserId()+"");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("fxToken", bean.getData().getToken());
                    ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                            .readTimeout(MyApplication.TIME_OUT)
                            .writeTimeout(MyApplication.TIME_OUT)
                            .connectTimeout(MyApplication.TIME_OUT)
                            .globalHeaders(map);
                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
