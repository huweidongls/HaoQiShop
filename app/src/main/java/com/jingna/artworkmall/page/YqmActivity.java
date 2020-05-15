package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.util.BitmapUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ToastUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YqmActivity extends BaseActivity {

    private Context context = YqmActivity.this;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.rl)
    RelativeLayout rl;

    private String invitationCode = "";
    private String loginUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yqm);

        loginUrl = getIntent().getStringExtra("url");
        invitationCode = getIntent().getStringExtra("code");
        StatusBarUtil.setStatusBarColor(YqmActivity.this, getResources().getColor(R.color.yqm));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(YqmActivity.this, false)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(YqmActivity.this,0x55000000);
        }
        ButterKnife.bind(YqmActivity.this);
        initData();

    }

    private void initData() {

        tvCode.setText(invitationCode);
        Glide.with(context).load(CodeUtils.createImage(loginUrl+invitationCode, 400, 400, null)).into(iv);

    }

    @OnClick({R.id.rl_back, R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_save:
                onSave();
                break;
        }
    }

    /**
     * 保存图片到本地
     */
    private void onSave() {

        Bitmap bitmap = BitmapUtils.getViewBitmap(rl);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(path+"/sls/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File someFile = new File(dir.getAbsoluteFile(), System.currentTimeMillis()+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(someFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Intent intent1 = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(someFile);
            intent1.setData(uri);
            context.sendBroadcast(intent1);
            ToastUtil.showShort(context, "保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
