package com.jingna.artworkmall.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.custom.ScaleTransitionPagerTitleView;
import com.jingna.artworkmall.fragment.FragmentPifa;
import com.jingna.artworkmall.fragment.FragmentTijianOrder;
import com.jingna.artworkmall.util.StatusBarUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianOrderActivity extends BaseActivity {

    private Context context = TijianOrderActivity.this;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager mViewPager;

    private FragmentManager mFragmentManager;
    private List<Fragment> fragmentList;
    private ArrayList<String> mTitleDataList;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_order);

        position = getIntent().getIntExtra("position", 0);
        mFragmentManager = getSupportFragmentManager();
        StatusBarUtil.setStatusBarColor(TijianOrderActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(TijianOrderActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(TijianOrderActivity.this,0x55000000);
        }
        ButterKnife.bind(TijianOrderActivity.this);
        initData();

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("全部");
        mTitleDataList.add("待使用");
        mTitleDataList.add("已使用");
        fragmentList.add(FragmentTijianOrder.newInstance("0"));
        fragmentList.add(FragmentTijianOrder.newInstance("1"));
        fragmentList.add(FragmentTijianOrder.newInstance("4"));
        GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                //设置字体
                simplePagerTitleView.setPadding(90, 0, 90, 0);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                simplePagerTitleView.setNormalColor(Color.parseColor("#4F545F"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#ffffff"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(position);

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
