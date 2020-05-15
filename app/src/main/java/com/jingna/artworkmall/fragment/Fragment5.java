package com.jingna.artworkmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.bean.AppMemberSignqueryListBean;
import com.jingna.artworkmall.bean.MemUsergetByInformationBean;
import com.jingna.artworkmall.dialog.DialogCalendar;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.AboutActivity;
import com.jingna.artworkmall.page.AddressActivity;
import com.jingna.artworkmall.page.BanquanActivity;
import com.jingna.artworkmall.page.CouponsActivity;
import com.jingna.artworkmall.page.HuoyueduActivity;
import com.jingna.artworkmall.page.JifenOrderActivity;
import com.jingna.artworkmall.page.MyBankCardActivity;
import com.jingna.artworkmall.page.MyDianpuActivity;
import com.jingna.artworkmall.page.PersonInformationActivity;
import com.jingna.artworkmall.page.PtJifenActivity;
import com.jingna.artworkmall.page.TijianOrderActivity;
import com.jingna.artworkmall.page.YinsiActivity;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/12/10.
 */

public class Fragment5 extends BaseFragment {

    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_ptb)
    TextView tvPtb;
    @BindView(R.id.tv_coupons_num)
    TextView tvCouponsNum;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_huoyuedu)
    TextView tvHuoyuedu;

    private int isSignIn = 0;
    private int dangqian = 0;

    private Calendar c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, null);

        c = Calendar.getInstance();
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        if(SpUtils.getUserId(getContext()).equals("0")){

        }else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("id", SpUtils.getUserId(getContext()));
            ViseUtil.Get(getContext(), NetUrl.MemUsergetByInformation, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    MemUsergetByInformationBean bean = gson.fromJson(s, MemUsergetByInformationBean.class);
                    tvNickname.setText(bean.getData().getMemName());
                    GlideUtils.into(getContext(), NetUrl.BASE_URL+bean.getData().getHeadPhoto(), ivAvatar);
                    isSignIn = bean.getData().getIsSignIn();
                    tvPtb.setText(StringUtils.roundByScale(bean.getData().getMemIntegral(), 2));
                    tvCouponsNum.setText(bean.getData().getCouponNum()+"");
                    int shangxian = bean.getData().getActivityLevelNum();
                    dangqian = bean.getData().getMemberUserLiveness();
                    progressBar.setMax(shangxian);
                    progressBar.setProgress(dangqian);
                    tvHuoyuedu.setText(dangqian+"");
                }
            });
        }

    }

    @OnClick({R.id.rl_address, R.id.ll_pt_jifen, R.id.rl_jifen_order, R.id.ll_head, R.id.rl_bank, R.id.rl_dianpu,
    R.id.ll_coupons, R.id.ll_qiandao, R.id.rl_all_order, R.id.rl_daishiyong, R.id.rl_yishiyong, R.id.rl_about,
    R.id.rl_banquan, R.id.rl_yinsi, R.id.rl_huoyuedu})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_address:
                intent.setClass(getContext(), AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_pt_jifen:
                intent.setClass(getContext(), PtJifenActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jifen_order:
                intent.setClass(getContext(), JifenOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_head:
                intent.setClass(getContext(), PersonInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_bank:
                intent.setClass(getContext(), MyBankCardActivity.class);
                intent.putExtra("type", "my");
                startActivity(intent);
                break;
            case R.id.rl_dianpu:
                intent.setClass(getContext(), MyDianpuActivity.class);
                startActivity(intent);
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("id", SpUtils.getUserId(getContext()));
//                map.put("ptb", "1");
//                ViseUtil.Get(getContext(), "AppRechargeExtract/rechargePtb", map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Gson gson = new Gson();
//                        AliBean aliBean = gson.fromJson(s, AliBean.class);
//                        aliPay(aliBean.getData().getData());
//                    }
//                });
                break;
            case R.id.ll_coupons:
                intent.setClass(getContext(), CouponsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_qiandao:
                //签到
                if(isSignIn == 0){
                    qiandao();
                }else if(isSignIn == 1){
                    ToastUtil.showShort(getContext(), "已签到");
                    final String yearMonth = c.get(Calendar.YEAR)+"-"+formatTimeUnit((c.get(Calendar.MONTH)+1));
                    Map<String, String> map1 = new LinkedHashMap<>();
                    map1.put("id", SpUtils.getUserId(getContext()));
                    map1.put("yearMonth", yearMonth);
                    map1.put("pageSize", "0");
                    map1.put("pageNum", "0");
                    ViseUtil.Get(getContext(), NetUrl.AppMemberSignqueryList, map1, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Gson gson = new Gson();
                            AppMemberSignqueryListBean bean = gson.fromJson(s, AppMemberSignqueryListBean.class);
                            DialogCalendar dialogCalendar = new DialogCalendar(getContext(), yearMonth, bean.getData());
                            dialogCalendar.show();
                        }
                    });
                }
                break;
            case R.id.rl_all_order:
                intent.setClass(getContext(), TijianOrderActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            case R.id.rl_daishiyong:
                intent.setClass(getContext(), TijianOrderActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            case R.id.rl_yishiyong:
                intent.setClass(getContext(), TijianOrderActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            case R.id.rl_about:
                intent.setClass(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_banquan:
                intent.setClass(getContext(), BanquanActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_yinsi:
                intent.setClass(getContext(), YinsiActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_huoyuedu:
                intent.setClass(getContext(), HuoyueduActivity.class);
                intent.putExtra("dangqian", dangqian);
                startActivity(intent);
                break;
        }
    }

    /**
     * 签到
     */
    private void qiandao() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("memberUserId", SpUtils.getUserId(getContext()));
        ViseUtil.Post(getContext(), NetUrl.AppMemberSigntoUpdate, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    if(jsonObject.optInt("data") == 1){
                        ToastUtil.showShort(getContext(), "签到成功");
                        isSignIn = 1;
                    }else {
                        ToastUtil.showShort(getContext(), "已签到");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 将“0-9”转换为“00-09”
     */
    private String formatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

}
