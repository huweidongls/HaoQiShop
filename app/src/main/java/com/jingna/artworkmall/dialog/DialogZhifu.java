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
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/1/17.
 */

public class DialogZhifu extends Dialog {

    private Context context;
    private TextView tvYue;
    private TextView tvCancel;
    private TextView tvSure;
    private EditText etPwd;
    private double yue;
    private ClickListener listener;

    private Dialog dialog;

    public DialogZhifu(@NonNull Context context, double yue, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.yue = yue;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_zhifu, null);
        setContentView(view);

        tvYue = view.findViewById(R.id.tv_yue);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        etPwd = view.findViewById(R.id.et_pwd);

        tvYue.setText("积分余额¥"+ StringUtils.roundByScale(yue, 2));
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("pwd", pwd);
                    ViseUtil.Get(context, NetUrl.MemUserverifyPwd, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if(jsonObject.optBoolean("data")){
                                    dismiss();
                                    listener.onYes();
                                }else {
                                    ToastUtil.showShort(context, "密码错误");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onNo();
            }
        });

    }

    public interface ClickListener{
        void onYes();
        void onNo();
    }

}
