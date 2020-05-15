package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.LoginBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.Logger;
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

public class Register2Activity extends BaseActivity {

    private Context context = Register2Activity.this;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idnum)
    EditText etIdnum;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private Dialog dialog;

    private String tel = "";
    private String yqm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        tel = getIntent().getStringExtra("tel");
        yqm = getIntent().getStringExtra("yqm");
        StatusBarUtil.setStatusBarColor(Register2Activity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(Register2Activity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(Register2Activity.this,0x55000000);
        }
        ButterKnife.bind(Register2Activity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {

//        String name = etName.getText().toString();
//        String idnum = etIdnum.getText().toString();
        String name = "123123";
        String idnum = "123123";
        String pwd = etPwd.getText().toString();
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(idnum)||StringUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "请完善信息之后提交");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String,String> map = new LinkedHashMap<>();
            map.put("phone",tel);
            map.put("invitationCode",yqm);
            map.put("password",pwd);
            map.put("idNumber",idnum);
            map.put("realName",name);
            ViseUtil.Get(context, NetUrl.MemUseraddMember, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Logger.e("123123", s);
                    Gson gson = new Gson();
                    LoginBean bean = gson.fromJson(s,LoginBean.class);
                    SpUtils.setToken(context, bean.getData().getToken());
                    SpUtils.setUserId(context, bean.getData().getUserId()+"");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("fxToken", bean.getData().getToken());
                    ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
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
