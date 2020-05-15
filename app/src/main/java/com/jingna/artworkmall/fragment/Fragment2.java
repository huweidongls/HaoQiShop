package com.jingna.artworkmall.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.artworkmall.base.BaseFragment;
import com.jingna.artworkmall.custom.ScaleTransitionPagerTitleView;
import com.youth.banner.Banner;

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

/**
 * Created by Administrator on 2019/12/10.
 */

public class Fragment2 extends BaseFragment {

    private List<Fragment> fragmentList;
    private ArrayList<String> mTitleDataList;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private FragmentManager mFragmentManager;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.banner)
    Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);

        ButterKnife.bind(this, view);
        mFragmentManager = getChildFragmentManager();
        initBanner();
        initData();

        return view;
    }

    private void initBanner() {

        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684803&di=b2955f5c7f53d25a2a66942ae32d992d&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F00667u01jw1erufiv0fd1j30k00b9q48.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684802&di=d75ad5b4e7d8b79100f9a520ecf5362a&imgtype=0&src=http%3A%2F%2Fzjnews.zjol.com.cn%2Fgdxw%2Fycxw_zxtf%2F201611%2FW020161116816118043260.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124704707&di=509a494541450addeee6532972bad420&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2637673594%2C2560483073%26fm%3D214%26gp%3D0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124712771&di=972c90108393a191c7ba19a3719c0a34&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D3011731684%2C1303327354%26fm%3D214%26gp%3D0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684800&di=c16a4a9ff8ced70464fe924aca6ef57c&imgtype=0&src=http%3A%2F%2Fstatic.chayuqing.com%2F65cbb7e0729bb0ea9be3f26a0b6ec5c6.jpg");
        init(banner, list);

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("全部");
        mTitleDataList.add("山水");
        mTitleDataList.add("花鸟");
        mTitleDataList.add("人物");
        mTitleDataList.add("书法");
        fragmentList.add(FragmentPifa.newInstance("1"));
        fragmentList.add(FragmentPifa.newInstance("1"));
        fragmentList.add(FragmentPifa.newInstance("1"));
        fragmentList.add(FragmentPifa.newInstance("1"));
        fragmentList.add(FragmentPifa.newInstance("1"));
        GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
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
                simplePagerTitleView.setPadding(70, 0, 70, 0);
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

    }

}
