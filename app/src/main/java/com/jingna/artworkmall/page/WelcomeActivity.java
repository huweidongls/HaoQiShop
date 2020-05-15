package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.SpUtils;

public class WelcomeActivity extends BaseActivity {

    private Context context = WelcomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initData();

    }

    private void initData() {

        Logger.e("123123", SpUtils.getUserId(context));
        Intent intent = new Intent();
        if(SpUtils.getUserId(context).equals("0")){
            intent.setClass(context, PhoneLoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            intent.setClass(context, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
