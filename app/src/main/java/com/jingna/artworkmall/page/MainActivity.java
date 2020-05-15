package com.jingna.artworkmall.page;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.app.MyApplication;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.VersionBean;
import com.jingna.artworkmall.dialog.DialogVersion;
import com.jingna.artworkmall.dialog.ProgressDialog;
import com.jingna.artworkmall.fragment.Fragment1;
import com.jingna.artworkmall.fragment.Fragment2;
import com.jingna.artworkmall.fragment.Fragment3;
import com.jingna.artworkmall.fragment.Fragment4;
import com.jingna.artworkmall.fragment.Fragment5;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.VersionUtils;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.menu_1)
    ImageButton ib1;
    @BindView(R.id.menu_2)
    ImageButton ib2;
    @BindView(R.id.menu_3)
    ImageButton ib3;
    @BindView(R.id.menu_4)
    ImageButton ib4;
    @BindView(R.id.menu_5)
    ImageButton ib5;
    @BindView(R.id.menu1)
    RelativeLayout rl1;
    @BindView(R.id.menu2)
    RelativeLayout rl2;
    @BindView(R.id.menu3)
    RelativeLayout rl3;
    @BindView(R.id.menu4)
    RelativeLayout rl4;
    @BindView(R.id.menu5)
    RelativeLayout rl5;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;

    private long exitTime = 0;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MenuOnClickListener listener = new MenuOnClickListener();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
        PermissionManager.instance().request(this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_allow) + "\n" + permissionName);
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_refuse) + "\n" + permissionName);
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_noAsk) + "\n" + permissionName);
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE);
        MyApplication.getInstance().addActivity(MainActivity.this);
        ButterKnife.bind(MainActivity.this);
        init();
        initVersion();

    }

    /**
     * 版本
     */
    private void initVersion() {

        final int versionCode = VersionUtils.packageCode(context);
        ViseUtil.Get(context, NetUrl.AppVersionNumversionNumNew, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                final VersionBean bean = gson.fromJson(s, VersionBean.class);
                if(bean.getData().getVersionCode()>versionCode){
                    DialogVersion dialogVersion = new DialogVersion(context, bean.getData().getVersionName(), bean.getData().getVerDesc()
                            , new DialogVersion.ClickListener() {
                        @Override
                        public void onSure() {
                            final ProgressDialog progressDialog = new ProgressDialog(context);
                            progressDialog.setCancelable(false);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                            String downloadUrl = bean.getData().getDownloadUrl();
                            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                            ViseHttp.DOWNLOAD(downloadUrl)
                                    .setRootName(path)
                                    .setDirName("sls")
                                    .setFileName("sls.apk")
                                    .request(new ACallback<DownProgress>() {
                                        @Override
                                        public void onSuccess(DownProgress data) {
                                            progressDialog.setInfo(data.getFormatStatusString(), data.getPercent());
                                            if (data.isDownComplete()){
                                                progressDialog.dismiss();
                                                String appFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/sls/"+"sls.apk";
                                                openAPK(appFile);
                                            }
                                        }

                                        @Override
                                        public void onFail(int errCode, String errMsg) {

                                        }
                                    });
                        }

                        @Override
                        public void onCancel() {
                            if(bean.getData().getOnOff() == 0){
                                MyApplication.getInstance().exit();
                            }
                        }
                    });
                    dialogVersion.setCancelable(false);
                    dialogVersion.setCanceledOnTouchOutside(false);
                    dialogVersion.show();
                }
            }
        });

    }

    /**
     * 安装apk
     * @param fileSavePath
     */
    private void openAPK(String fileSavePath){
        File file=new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            data = FileProvider.getUriForFile(context, "com.jingna.artworkmall.fileprovider", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    /**
     * 初始化各个组件
     */
    private void init() {

        ib1.setOnClickListener(listener);
        ib2.setOnClickListener(listener);
        ib3.setOnClickListener(listener);
        ib4.setOnClickListener(listener);
        ib5.setOnClickListener(listener);

        rl1.setOnClickListener(listener);
        rl2.setOnClickListener(listener);
        rl3.setOnClickListener(listener);
        rl4.setOnClickListener(listener);
        rl5.setOnClickListener(listener);
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new Fragment3();
        Fragment fragment4 = new Fragment4();
        Fragment fragment5 = new Fragment5();

        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);

        fragmentTransaction.add(R.id.fl_container, fragment1);
        fragmentTransaction.add(R.id.fl_container, fragment2);
        fragmentTransaction.add(R.id.fl_container, fragment3);
        fragmentTransaction.add(R.id.fl_container, fragment4);
        fragmentTransaction.add(R.id.fl_container, fragment5);

        fragmentTransaction.show(fragment1).hide(fragment2).hide(fragment3).hide(fragment4).hide(fragment5);
        fragmentTransaction.commit();

        selectButton(ib1);
        selectText(tv1);

    }

    private class MenuOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu_1:
                    selectButton(ib1);
                    selectText(tv1);
                    switchFragment(0);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, false)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu_2:
                    selectButton(ib2);
                    selectText(tv2);
                    switchFragment(1);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.white_ffffff));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu_3:
                    selectButton(ib3);
                    selectText(tv3);
                    switchFragment(2);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, false)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu_4:
                    selectButton(ib4);
                    selectText(tv4);
                    switchFragment(3);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.white_ffffff));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu_5:
                    selectButton(ib5);
                    selectText(tv5);
                    switchFragment(4);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.line));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu1:
                    selectText(tv1);
                    selectButton(ib1);
                    switchFragment(0);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, false)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu2:
                    selectButton(ib2);
                    selectText(tv2);
                    switchFragment(1);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.white_ffffff));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu3:
                    selectButton(ib3);
                    selectText(tv3);
                    switchFragment(2);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, false)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu4:
                    selectButton(ib4);
                    selectText(tv4);
                    switchFragment(3);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.white_ffffff));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
                case R.id.menu5:
                    selectButton(ib5);
                    selectText(tv5);
                    switchFragment(4);
                    StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.line));
                    //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                    //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                    if (!StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true)) {
                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                        //这样半透明+白=灰, 状态栏的文字能看得清
                        StatusBarUtil.setStatusBarColor(MainActivity.this,0x55000000);
                    }
                    break;
            }

        }
    }

    /**
     * 选择隐藏与显示的Fragment
     *
     * @param index 显示的Frgament的角标
     */
    public void switchFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (int i = 0; i < fragmentList.size(); i++) {
            if (index == i) {
                fragmentTransaction.show(fragmentList.get(index));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void selectText(View v) {
        tv1.setSelected(false);
        tv2.setSelected(false);
        tv3.setSelected(false);
        tv4.setSelected(false);
        tv5.setSelected(false);
        v.setSelected(true);
    }

    /**
     * 控制底部菜单按钮的选中
     *
     * @param v
     */
    public void selectButton(View v) {
        ib1.setSelected(false);
        ib2.setSelected(false);
        ib3.setSelected(false);
        ib4.setSelected(false);
        ib5.setSelected(false);
        v.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        backtrack();
    }

    /**
     * 退出销毁所有activity
     */
    private void backtrack() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.showShort(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
            exitTime = 0;
        }
    }

}
