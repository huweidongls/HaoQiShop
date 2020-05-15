package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vise.xsnow.http.ViseHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    private String code ="";
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_yqm)
    EditText et_yqm;

    private int REQUEST_CODE = 1000;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StatusBarUtil.setStatusBarColor(RegisterActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(RegisterActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(RegisterActivity.this,0x55000000);
        }
        ButterKnife.bind(RegisterActivity.this);
        MyApplication.registerTimeCount.setActivity(RegisterActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.login_btn, R.id.tv_msg, R.id.tv_phone, R.id.iv_code})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                MyApplication.registerTimeCount.start();
                String phones = et_phone.getText().toString();
                if(!StringUtils.isPhoneNumberValid(phones)){
                    ToastUtil.showShort(context, "请输入正确格式的电话号码!");
                }else{
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("phone",phones);
                    ViseUtil.Get(context, NetUrl.MemUsersendMessage, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if(jsonObject.optString("status").equals("200")){
                                    code = jsonObject.optString("data");
                                    ToastUtil.showShort(context,"验证码发送成功，请注意查收!");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                break;
            case R.id.login_btn:
                String tel = et_phone.getText().toString();
                String code_msg = et_code.getText().toString();
                String yq = et_yqm.getText().toString();
                if(StringUtils.isEmpty(tel) || StringUtils.isEmpty(code_msg) || StringUtils.isEmpty(yq)){
                    ToastUtil.showShort(context,"请填写完整信息!");
                }else{
                    if(!code.equals(code_msg)){
                        ToastUtil.showShort(context,"短信验证码不正确!");
                    }else{
                        intent.setClass(context, Register2Activity.class);
                        intent.putExtra("tel", tel);
                        intent.putExtra("yqm", yq);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.tv_msg:
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_phone:
                intent.setClass(context, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_code:
                intent.setClass(context, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    et_yqm.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showShort(context, "解析二维码失败");
                }
            }
        }
    }
}
