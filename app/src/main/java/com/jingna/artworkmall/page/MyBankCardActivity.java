package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.MyBankCardAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.BankCardListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBankCardActivity extends BaseActivity {

    private Context context = MyBankCardActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MyBankCardAdapter adapter;
    private List<BankCardListBean.DataBean> mList;

    private String userId = "";

    private PopupWindow popupWindow;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);

        type = getIntent().getStringExtra("type");
        userId = SpUtils.getUserId(context);
        StatusBarUtil.setStatusBarColor(MyBankCardActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(MyBankCardActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(MyBankCardActivity.this,0x55000000);
        }
        ButterKnife.bind(MyBankCardActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppBankCardqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                BankCardListBean bean = gson.fromJson(s, BankCardListBean.class);
                mList = bean.getData();
                adapter = new MyBankCardAdapter(mList, new MyBankCardAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int pos, String bankName, String card) {
                        if(type.equals("my")){
                            showDelPop(pos, bankName, card);
                        }else {
                            Intent intent = new Intent();
                            intent.putExtra("bean", mList.get(pos));
                            setResult(1000, intent);
                            finish();
                        }
                    }

                    @Override
                    public void onAdd() {
                        showInsertPop();
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void showInsertPop(){

        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_bank_insert, null);

        TextView tvZfb = view.findViewById(R.id.tv_zfb);
        TextView tvYhk = view.findViewById(R.id.tv_yhk);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        tvZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, InsertZfbActivity.class);
                context.startActivity(intent);
                popupWindow.dismiss();
            }
        });

        tvYhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, InsertBankCardActivity.class);
                context.startActivity(intent);
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

    }

    private void showDelPop(final int pos, String bankName, String card){

        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_bank_delete, null);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvDel = view.findViewById(R.id.tv_del);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvDefault = view.findViewById(R.id.tv_default);

        tvTitle.setText("您可对"+bankName+"尾号"+card+"的储蓄卡进行操作");

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        tvDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("id", mList.get(pos).getId()+"");
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.AppBankCardupdateDefault, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        popupWindow.dismiss();
                        ToastUtil.showShort(context, "设置成功");
                    }
                });
            }
        });

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("id", mList.get(pos).getId()+"");
                ViseUtil.Post(context, NetUrl.AppBankCardtoDelete, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        popupWindow.dismiss();
                        ToastUtil.showShort(context, "删除成功");
                        mList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
