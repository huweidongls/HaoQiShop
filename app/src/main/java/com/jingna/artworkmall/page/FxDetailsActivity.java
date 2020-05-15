package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FxDetailsActivity extends BaseActivity {

    private Context context = FxDetailsActivity.this;

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.pro)
    ProgressBar progressBar;

    private String id = "";
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fx_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtil.setStatusBarColor(FxDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(FxDetailsActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(FxDetailsActivity.this,0x55000000);
        }
        ButterKnife.bind(FxDetailsActivity.this);
        initData();
        initWebview();

    }

    private void initData() {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("memberId", SpUtils.getUserId(context));
                ViseUtil.Get(context, NetUrl.AppGoodsContentaddActivityLevel, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
//                        ToastUtil.showShort(context, "111");
                    }
                });
            }
        }, 10000);

    }

    private void initWebview() {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setTextZoom(100);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setCacheMode(
                webview.getSettings().LOAD_NO_CACHE); // 缓存设置
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //使用控件ProgressDialog来显示更新进度条示数
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有网站的证书
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载错误时的回调
            }
        });
        webview.addJavascriptInterface(new JsInterface(), "android");
        String userId = SpUtils.getUserId(context);
        String url = NetUrl.H5BASE_URL+"/#/app_fxdetail?id="+id+"&memberId="+ userId;
        webview.loadUrl(url);

    }

    public class JsInterface {
        //JS中调用Android中的方法 和返回值处理的一种方法

        /****
         * Html中的点击事件 onclick
         * @param
         */
        @JavascriptInterface
        public void share(String s) {
            Intent intent = new Intent();
//            ToastUtil.showShort(context, s);
            if(!StringUtils.isEmpty(s)){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int isGoods = jsonObject.optInt("isGoods");
                    int id = jsonObject.optInt("id");
                    if(isGoods == 1){
                        intent.setClass(context, TijianDetailsActivity.class);
                        intent.putExtra("id", id+"");
                        startActivity(intent);
                    }else if(isGoods == 2){
                        intent.setClass(context, JifenDetailsActivity.class);
                        intent.putExtra("id", id+"");
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer != null){
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            //再次打开页面时，若界面没有消亡，会导致进度条不显示并且界面崩溃
            webview.stopLoading();
            webview.onPause();
            webview.clearCache(true);
            webview.clearHistory();
            //动态创建webview调用
            //ViewGroup parent = (ViewGroup) mWebView.getParent();
            //if (parent != null) {
            //  parent.removeView(mWebView);
            //}
            webview.removeAllViews();
            //先结束未结束线程，以免可能会导致空指针异常
            webview.destroy();
            webview = null;
            super.onDestroy();
        }
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
