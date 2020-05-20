package com.jingna.artworkmall.fragment;

import android.view.View;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.LazyFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/5/20.
 */

public class FragmentYy extends LazyFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_yy;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

}
