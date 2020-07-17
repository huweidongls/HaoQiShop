package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;
    private String codes="";
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.edget_code)
    EditText edget_code;

    @BindView(R.id.et_phone)
    EditText et_phone;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtil.setStatusBar(LoginActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(LoginActivity.this);
        MyApplication.loginTimeCount.setActivity(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.tv_register,R.id.btn_login,R.id.tv_phone_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                String phone = et_phone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtil.showShort(context, "请输入电话号码!");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确格式的电话号码!");
                }else {
                    MyApplication.loginTimeCount.start();
                    PhoneCode(phone);
                }
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_phone_pwd:
                intent.setClass(context, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                LoginApp();
                break;
        }
    }
    /**
     *
     * 发送短信验证码
     */
    public void PhoneCode(String phone){
        if(StringUtils.isEmpty(phone)){
            ToastUtil.showShort(context,"电话号为空!");
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("phone",phone);
            ViseUtil.Get(context, NetUrl.MemUsersendMessage, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if(jsonObject.optString("status").equals("200")){
                            codes = jsonObject.optString("data");
                            ToastUtil.showShort(context,"验证码发送成功，请注意查收!");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    /**
     * APP短信登录
     */
    public void LoginApp(){
        String phone = et_phone.getText().toString();
        String code = edget_code.getText().toString();
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
            ToastUtil.showShort(context,"请填写完整信息在提交!");
        }else{
            if(!codes.equals(code)){
                ToastUtil.showShort(context,"短信验证码不正确!");
            }else{
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                Map<String,String> map = new LinkedHashMap<>();
                map.put("phone",phone);
                map.put("code",code);
                ViseUtil.Get(context, NetUrl.MemUserloginAPP, map, dialog, new ViseUtil.ViseListener() {
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
}
