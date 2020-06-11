package com.jingna.artworkmall.page;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.MemUsergetOneBean;
import com.jingna.artworkmall.dialog.DialogCustom;
import com.jingna.artworkmall.dialog.InformationNicknameDialog;
import com.jingna.artworkmall.dialog.InformationSexDialog;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.GlideUtils;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInformationActivity extends BaseActivity {

    private Context context = PersonInformationActivity.this;

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    private int REQUEST_CODE = 101;

    private int mYear;
    private int mMonth;
    private int mDay;

    private String invitationCode = "";
    private String loginUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        StatusBarUtil.setStatusBarColor(PersonInformationActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(PersonInformationActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(PersonInformationActivity.this,0x55000000);
        }
        ButterKnife.bind(PersonInformationActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemUsergetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MemUsergetOneBean bean = gson.fromJson(s, MemUsergetOneBean.class);
                if(!StringUtils.isEmpty(bean.getData().getMemberUserInfo().getHeadPhoto())){
                    GlideUtils.into(context, NetUrl.BASE_URL+bean.getData().getMemberUserInfo().getHeadPhoto(), ivAvatar);
                }
                tvNickname.setText(bean.getData().getMemberUserInfo().getMemName());
                if(bean.getData().getMemberUserInfo().getGender().equals("0")){
                    tvSex.setText("未填写");
                }else if(bean.getData().getMemberUserInfo().getGender().equals("1")){
                    tvSex.setText("男");
                }else if(bean.getData().getMemberUserInfo().getGender().equals("2")){
                    tvSex.setText("女");
                }
                tvBirthday.setText(bean.getData().getMemberUserInfo().getMemBirthday());
                invitationCode = bean.getData().getMemberUserInfo().getInvitationCode();
                loginUrl = bean.getData().getMemberUserInfo().getLoginUrl();
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_avatar, R.id.rl_birthday, R.id.rl_sex, R.id.rl_nickname, R.id.btn_out, R.id.rl_yqm})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_avatar:
                //单选并剪裁
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setCrop(true)  // 设置是否使用图片剪切功能。
                        .setSingle(true)  //设置是否单选
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(PersonInformationActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.rl_birthday:
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.rl_sex:
                InformationSexDialog sexDialog = new InformationSexDialog(context, new InformationSexDialog.ClickListener() {
                    @Override
                    public void onNan() {
                        onSex(1);
                    }

                    @Override
                    public void onNv() {
                        onSex(2);
                    }
                });
                sexDialog.show();
                break;
            case R.id.rl_nickname:
                InformationNicknameDialog nicknameDialog = new InformationNicknameDialog(context, new InformationNicknameDialog.ClickListener() {
                    @Override
                    public void onSure(String name) {
                        onNickname(name);
                    }
                });
                nicknameDialog.show();
                break;
            case R.id.btn_out:
                DialogCustom dialogCustom = new DialogCustom(context, "是否退出登录", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        SpUtils.clear(context);
                        Intent intent = new Intent(PersonInformationActivity.this, PhoneLoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                dialogCustom.show();
                break;
            case R.id.rl_yqm:
                intent.setClass(context, YqmActivity.class);
                intent.putExtra("code", invitationCode);
                intent.putExtra("url", loginUrl);
                startActivity(intent);
                break;
        }
    }

    /**
     * 走接口设置昵称
     */
    private void onNickname(final String name) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        map.put("memName", name);
        ViseUtil.Post(context, NetUrl.MemUsertoUpdate, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                tvNickname.setText(name);
            }
        });

    }

    /**
     * 走接口设置性别
     */
    private void onSex(final int sex) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        map.put("gender", sex+"");
        ViseUtil.Post(context, NetUrl.MemUsertoUpdate, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                if(sex == 1){
                    tvSex.setText("男");
                }else if(sex == 2){
                    tvSex.setText("女");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            final ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            File file = new File(images.get(0));

            ViseHttp.UPLOAD("/MemUser/toUpdate")
                    .addParam("id", SpUtils.getUserId(context))
                    .addFile("file0", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "设置成功");
                                    Glide.with(context).load(images.get(0)).into(ivAvatar);
                                }else {
                                    ToastUtil.showShort(context, "设置失败");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Log.e("123123", errMsg);
                        }
                    });

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            Map<String, String> map = new LinkedHashMap<>();
            map.put("id", SpUtils.getUserId(context));
            map.put("memBirthday", days);
            ViseUtil.Post(context, NetUrl.MemUsertoUpdate, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "设置成功");
                    tvBirthday.setText(days);
                }
            });
        }
    };

}
