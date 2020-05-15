package com.jingna.artworkmall.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.IndexPageApiqueryGoodsContentBean;
import com.jingna.artworkmall.bean.IndexPageApiqueryListGywmBean;
import com.jingna.artworkmall.card.CardFxPagerAdapter;
import com.jingna.artworkmall.card.ShadowTransformer;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.HtmlFromUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.widget.GalleryTransformer;
import com.jingna.artworkmall.widget.ViewPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    private Context context = AboutActivity.this;

//    @BindView(R.id.vp)
//    ViewPager viewPager;
    @BindView(R.id.tv)
    TextView tv;

//    private ViewPagerAdapter mViewPagerAdapter;
//    private List<IndexPageApiqueryGoodsContentBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        StatusBarUtil.setStatusBarColor(AboutActivity.this, getResources().getColor(R.color.line));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(AboutActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(AboutActivity.this,0x55000000);
        }
        ButterKnife.bind(AboutActivity.this);
        initData();

    }

    private void initData() {

        ViseUtil.Get(context, NetUrl.IndexPageApiqueryListGywm, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                IndexPageApiqueryListGywmBean bean = gson.fromJson(s, IndexPageApiqueryListGywmBean.class);
                if(bean.getData().size()>0){
                    String html = bean.getData().get(0).getAboutUs();
                    HtmlFromUtils.setTextFromHtml(AboutActivity.this, tv, html);
                }
            }
        });

//        ViseUtil.Get(context, NetUrl.IndexPageApiqueryGoodsContent, null, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//                Gson gson = new Gson();
//                IndexPageApiqueryGoodsContentBean bean = gson.fromJson(s, IndexPageApiqueryGoodsContentBean.class);
//                data = bean.getData();
//                setCardView(data);
//            }
//        });

    }

//    public void setCardView(List<IndexPageApiqueryGoodsContentBean.DataBean> data) {
//
////        mCardAdapter = new CardFxPagerAdapter();
////        for (int i = 0; i < data.size(); i++) {
////            mCardAdapter.addCardItem(data.get(i).getContentImg());
////        }
//////        mFragmentCardAdapter = new CardFragmentPagerAdapter(getActivity().getSupportFragmentManager(),
//////                DensityTool.dp2px(getContext(), 1));
////        viewPager.setAdapter(mCardAdapter);
////        shadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
////        shadowTransformer.enableScaling(false);
//
//        mViewPagerAdapter = new ViewPagerAdapter(context, data);
//        viewPager.setOffscreenPageLimit(3);
//        viewPager.setPageMargin(1);
//        viewPager.setAdapter(mViewPagerAdapter);
//        viewPager.setPageTransformer(false, new GalleryTransformer());
//
//    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
