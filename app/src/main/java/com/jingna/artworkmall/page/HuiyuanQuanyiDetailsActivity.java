package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuiyuanQuanyiDetailsActivity extends BaseActivity {

    private Context context = HuiyuanQuanyiDetailsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiyuan_quanyi_details);

        StatusBarUtil.setStatusBar(HuiyuanQuanyiDetailsActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(HuiyuanQuanyiDetailsActivity.this);
        initData();

    }

    private void initData() {



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
