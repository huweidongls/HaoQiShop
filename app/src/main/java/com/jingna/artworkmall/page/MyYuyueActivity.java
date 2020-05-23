package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;

import butterknife.ButterKnife;

public class MyYuyueActivity extends BaseActivity {

    private Context context = MyYuyueActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yuyue);

        ButterKnife.bind(MyYuyueActivity.this);
        initData();

    }

    private void initData() {



    }

}
