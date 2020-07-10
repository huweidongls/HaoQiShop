package com.jingna.artworkmall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;

/**
 * Created by Administrator on 2020/7/10.
 */

public class DialogZfPwd extends Dialog {

    private Context context;
    private EditText etPwd;
    private EditText etPwd1;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;

    public DialogZfPwd(@NonNull Context context, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_zf_pwd, null);
        setContentView(view);

        etPwd = view.findViewById(R.id.et_pwd);
        etPwd1 = view.findViewById(R.id.et_pwd1);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = etPwd.getText().toString();
                String pwd1 = etPwd1.getText().toString();
                if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(pwd1)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else {
                    if(pwd.equals(pwd1)){
                        listener.onSure(pwd);
                        dismiss();
                    }else {
                        ToastUtil.showShort(context, "密码不一致");
                    }
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onSure(String pwd);
    }

}
