package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AppGoodsShopgetByTjkBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ViseUtil;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianDetailsActivity extends BaseActivity {

    private Context context = TijianDetailsActivity.this;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.pro)
    ProgressBar progressBar;

    private String id = "";
    private AppGoodsShopgetByTjkBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtil.setStatusBar(TijianDetailsActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(TijianDetailsActivity.this);
        initWebview();
        initData();

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
        String url = NetUrl.H5BASE_URL+"/#/app_shopdetail?id="+id;
        webview.loadUrl(url);

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

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.AppGoodsShopgetByTjk, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                bean = gson.fromJson(s, AppGoodsShopgetByTjkBean.class);
                tvPrice.setText("¥"+StringUtils.roundByScale(bean.getData().getPrice(), 2));
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_next, R.id.rl_index})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_index:
                finish();
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_next:
                intent.setClass(context, TijianSureActivity.class);
                intent.putExtra("type", "0");
                intent.putExtra("id", id);
                intent.putExtra("bean", bean);
                startActivity(intent);
                finish();
                break;
        }
    }

}
