package com.jingna.artworkmall.util;

/**
 * Created by Administrator on 2018/7/13.
 */

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.page.LoginActivity;

public class LoginTimeCount extends CountDownTimer {

    private SpannableString msp = null;
    private int timeNum = 60;
    private LoginActivity activity;

    public int getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }

    public LoginActivity getActivity() {
        return activity;
    }

    public void setActivity(LoginActivity activity) {
        this.activity = activity;
    }

    public LoginTimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * 倒计时中
     */
    @Override
    public void onTick(long millisUntilFinished) {
        activity.getCode_btn().setEnabled(
                false);
        int timeInt = (int) (millisUntilFinished / 1000);
        setTimeNum(timeInt);

        // 创建一个 SpannableString对象
        msp = new SpannableString("重新发送" + timeInt + "秒");

        activity.getCode_btn().setTextColor(Color.parseColor("#B0B0B0"));
        activity.getCode_btn().setText(msp);
    }

    /**
     * 倒计时结束
     */
    @Override
    public void onFinish() {
        // 创建一个 SpannableString对象
        msp = new SpannableString("获取验证码");

        // 设置字体背景色
//        msp.setSpan(new ForegroundColorSpan(activity
//                        .getResources().getColor(R.color.white_ffffff)), 0, 0,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为

        activity.getCode_btn().setText(msp);
        activity.getCode_btn().setTextColor(activity.getResources().getColor(R.color.theme));
        activity.getCode_btn().setEnabled(true);
        setTimeNum(60);
    }
}
