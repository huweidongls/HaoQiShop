package com.jingna.artworkmall.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingna.artworkmall.bean.IndexPageApiqueryGoodsContentBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.DensityTool;
import com.jingna.artworkmall.util.GlideUtils;

import java.util.List;

/**
 * ViewPager适配器
 * Create by: chenwei.li
 * Date: 2017/12/8
 * time: 15:17
 * Email: lichenwei.me@foxmail.com
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<IndexPageApiqueryGoodsContentBean.DataBean> data;

    public ViewPagerAdapter(Context context, List<IndexPageApiqueryGoodsContentBean.DataBean> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(DensityTool.dp2px(mContext,200),DensityTool.dp2px(mContext,122)));
        GlideUtils.into(mContext, NetUrl.BASE_URL+data.get(position).getContentImg(), imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
