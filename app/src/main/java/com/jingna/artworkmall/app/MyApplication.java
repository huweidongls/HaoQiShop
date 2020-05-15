package com.jingna.artworkmall.app;

import android.app.Activity;
import android.app.Application;

import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.BankCodeTimeCount;
import com.jingna.artworkmall.util.ForgotTimeCount;
import com.jingna.artworkmall.util.LoginTimeCount;
import com.jingna.artworkmall.util.RegisterTimeCount;
import com.jingna.artworkmall.util.SpUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/10.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    // 修改密码获取验证码倒计时
    public static BankCodeTimeCount bankCodeTimeCount;
    public static LoginTimeCount loginTimeCount;
    public static RegisterTimeCount registerTimeCount;
    public static ForgotTimeCount forgotTimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fxToken", SpUtils.getToken(getApplicationContext()));
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                .globalHeaders(map);
        ZXingLibrary.initDisplayOpinion(this);
        bankCodeTimeCount = new BankCodeTimeCount(60000, 1000);
        loginTimeCount = new LoginTimeCount(60000, 1000);
        registerTimeCount = new RegisterTimeCount(60000, 1000);
        forgotTimeCount = new ForgotTimeCount(60000, 1000);
//        RichText.initCacheDir(this);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
