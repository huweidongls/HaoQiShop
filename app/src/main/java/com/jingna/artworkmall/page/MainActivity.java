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
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.app.MyApplication;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.VersionBean;
import com.jingna.artworkmall.dialog.DialogVersion;
import com.jingna.artworkmall.dialog.ProgressDialog;
import com.jingna.artworkmall.fragment.Fragment1;
import com.jingna.artworkmall.fragment.Fragment3;
import com.jingna.artworkmall.fragment.Fragment5;
import com.jingna.artworkmall.fragment.FragmentYy;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StatusBarUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.VersionUtils;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;
import com.xuexiang.xupdate._XUpdate;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.mViewPager)
    ViewPager mViewPger;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.theme));
        SpUtils.setLanyaTime(context, false);
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
                                    .setDirName("haoqi")
                                    .setFileName("haoqi.apk")
                                    .request(new ACallback<DownProgress>() {
                                        @Override
                                        public void onSuccess(DownProgress data) {
                                            progressDialog.setInfo(data.getFormatStatusString(), data.getPercent());
                                            if (data.isDownComplete()){
                                                progressDialog.dismiss();
                                                String appFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/haoqi/"+"haoqi.apk";
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

        File file = new File(fileSavePath);
        _XUpdate.startInstallApk(context, file); //填写文件所在的路径

//        File file=new File(Uri.parse(fileSavePath).getPath());
//        String filePath = file.getAbsolutePath();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri data = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
//            // 生成文件的uri，，
//            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
//            data = FileProvider.getUriForFile(context, "com.jingna.artworkmall.fileprovider", new File(filePath));
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
//        } else {
//            data = Uri.fromFile(file);
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(data, "application/vnd.android.package-archive");
//        startActivity(intent);
    }

    /**
     * 初始化各个组件
     */
    private void init() {

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPger.setOffscreenPageLimit(4);
        mViewPger.setAdapter(mainAdapter);
        mViewPger.addOnPageChangeListener(mainAdapter);

        alphaTabsIndicator.setViewPager(mViewPger);

    }

    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new FragmentYy();
        Fragment fragment3 = new Fragment3();
        Fragment fragment5 = new Fragment5();

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(fragment1);
            fragments.add(fragment2);
            fragments.add(fragment3);
            fragments.add(fragment5);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                StatusBarUtils.setStatusBar(MainActivity.this, getResources().getColor(R.color.theme));
            } else if (1 == position) {
                StatusBarUtils.setStatusBar(MainActivity.this, getResources().getColor(R.color.theme));
            } else if (2 == position) {
                StatusBarUtils.setStatusBar(MainActivity.this, getResources().getColor(R.color.theme));
            } else if (3 == position) {
                StatusBarUtils.setStatusBar(MainActivity.this, getResources().getColor(R.color.theme));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
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
