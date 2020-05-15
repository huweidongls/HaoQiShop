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
 * Created by Administrator on 2019/2/27.
 */

public class InformationNicknameDialog extends Dialog {

    private Context context;

    private EditText etName;
    private TextView tvSure;
    private TextView tvCancel;

    private ClickListener listener;

    public InformationNicknameDialog(@NonNull Context context, ClickListener listener) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_information_nickname, null);
        setContentView(view);

        etName = view.findViewById(R.id.et_nickname);
        tvSure = view.findViewById(R.id.tv_sure);
        tvCancel = view.findViewById(R.id.tv_cancel);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if(StringUtils.isEmpty(name)){
                    ToastUtil.showShort(context, "昵称不能为空");
                }else {
                    listener.onSure(name);
                    dismiss();
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
        void onSure(String name);
    }

}
