package com.jingna.artworkmall.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppMakeAnapPointmentOrderControlleryuYueGetOneBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuyueDetailsActivity extends BaseActivity {

    private Context context = YuyueDetailsActivity.this;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_xiadan_time)
    TextView tvXiadanTime;
    @BindView(R.id.tv_songda_time)
    TextView tvSongdaTime;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_jl)
    TextView tvJl;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtil.setStatusBar(YuyueDetailsActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(YuyueDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("appointmentId", id);
        ViseUtil.Get(context, NetUrl.AppMakeAnapPointmentOrderControlleryuYueGetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppMakeAnapPointmentOrderControlleryuYueGetOneBean bean = gson.fromJson(s, AppMakeAnapPointmentOrderControlleryuYueGetOneBean.class);
                tvTitle.setText(bean.getData().getType());
                tvName.setText(bean.getData().getPjy());
                tvPhone.setText(bean.getData().getPhone());
                tvAddress.setText(bean.getData().getDz());
                tvXiadanTime.setText(bean.getData().getXdTime());
                tvSongdaTime.setText(bean.getData().getSdTime());
                int status = bean.getData().getStatus();
                double jl = bean.getData().getJl();
                if(status == 0){
                    tvStatus.setText("配送中");
                }else if(status == 1){
                    tvStatus.setText("配送中");
                }else if(status == 2){
                    tvStatus.setText("已完成");
                }
                tvJl.setText(jl+"km");
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
