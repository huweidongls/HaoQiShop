package com.jingna.artworkmall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.app.MyApplication;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/19.
 */

public class BankCodeDialog extends Dialog {

    private Context context;
    private RelativeLayout rlGetCode;
    private TextView tvGetCode;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;
    private String phone;
    private TextView tv;
    private EditText etCode;
    private String codeNet;

    public BankCodeDialog(@NonNull Context context, String phone, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
        this.phone = phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.bankCodeTimeCount.setActivity(this);
        init();
    }

    public TextView getCode_btn(){
        return tvGetCode;
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bank_code, null);
        setContentView(view);

        rlGetCode = view.findViewById(R.id.rl_get_code);
        tvGetCode = view.findViewById(R.id.tv_get_code);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tv = view.findViewById(R.id.tv);
        etCode = view.findViewById(R.id.et_code);

        tv.setText("输入尾号"+phone+"的短信验证码");

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString();
                if(StringUtils.isEmpty(code)){
                    ToastUtil.showShort(context, "验证码不能为空");
                }else if(!codeNet.equals(code)){
                    ToastUtil.showShort(context, "验证码不正确");
                }else {
                    dismiss();
                    listener.onSure();
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rlGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.bankCodeTimeCount.start();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("phone", phone);
                ViseUtil.Get(context, NetUrl.MemUsersendMessage, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(jsonObject.optString("status").equals("200")){
                                codeNet = jsonObject.optString("data");
                                ToastUtil.showShort(context,"验证码发送成功，请注意查收!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
