package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.IndexPageApiqueryListYszcBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.HtmlFromUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ViseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinsiActivity extends BaseActivity {

    private Context context = YinsiActivity.this;

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinsi);

        StatusBarUtil.setStatusBarColor(YinsiActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(YinsiActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(YinsiActivity.this,0x55000000);
        }
        ButterKnife.bind(YinsiActivity.this);
        initData();

    }

    private void initData() {

        ViseUtil.Get(context, NetUrl.IndexPageApiqueryListYszc, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApiqueryListYszcBean bean = gson.fromJson(s, IndexPageApiqueryListYszcBean.class);
                if(bean.getData().size()>0){
                    String html = bean.getData().get(0).getRightsPrivacy();
                    HtmlFromUtils.setTextFromHtml(YinsiActivity.this, tv, html);
                }
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
