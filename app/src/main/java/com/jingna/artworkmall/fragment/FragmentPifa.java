package com.jingna.artworkmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.PifaAdapter;
import com.jingna.artworkmall.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/12/12.
 */

public class FragmentPifa extends BaseFragment {

    public static FragmentPifa newInstance(String id) {
        FragmentPifa newFragment = new FragmentPifa();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private String id = "";
    private PifaAdapter adapter;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pifa, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684803&di=b2955f5c7f53d25a2a66942ae32d992d&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F00667u01jw1erufiv0fd1j30k00b9q48.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684802&di=d75ad5b4e7d8b79100f9a520ecf5362a&imgtype=0&src=http%3A%2F%2Fzjnews.zjol.com.cn%2Fgdxw%2Fycxw_zxtf%2F201611%2FW020161116816118043260.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124704707&di=509a494541450addeee6532972bad420&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2637673594%2C2560483073%26fm%3D214%26gp%3D0.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124712771&di=972c90108393a191c7ba19a3719c0a34&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D3011731684%2C1303327354%26fm%3D214%26gp%3D0.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684800&di=c16a4a9ff8ced70464fe924aca6ef57c&imgtype=0&src=http%3A%2F%2Fstatic.chayuqing.com%2F65cbb7e0729bb0ea9be3f26a0b6ec5c6.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684803&di=b2955f5c7f53d25a2a66942ae32d992d&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F00667u01jw1erufiv0fd1j30k00b9q48.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684802&di=d75ad5b4e7d8b79100f9a520ecf5362a&imgtype=0&src=http%3A%2F%2Fzjnews.zjol.com.cn%2Fgdxw%2Fycxw_zxtf%2F201611%2FW020161116816118043260.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124704707&di=509a494541450addeee6532972bad420&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2637673594%2C2560483073%26fm%3D214%26gp%3D0.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124712771&di=972c90108393a191c7ba19a3719c0a34&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D3011731684%2C1303327354%26fm%3D214%26gp%3D0.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576124684800&di=c16a4a9ff8ced70464fe924aca6ef57c&imgtype=0&src=http%3A%2F%2Fstatic.chayuqing.com%2F65cbb7e0729bb0ea9be3f26a0b6ec5c6.jpg");
        adapter = new PifaAdapter(mList);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
