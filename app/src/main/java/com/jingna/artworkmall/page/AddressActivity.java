package com.jingna.artworkmall.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.adapter.AddressAdapter;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {

    private Context context = AddressActivity.this;
    private boolean isFirst = true;
    @BindView(R.id.rv)
    SwipeMenuRecyclerView recyclerView;

    private AddressAdapter adapter;
    private List<AddressListBean.DataBean> mList;

    private String order = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        order = getIntent().getStringExtra("order");
        StatusBarUtil.setStatusBarColor(AddressActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(AddressActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(AddressActivity.this,0x55000000);
        }
        ButterKnife.bind(AddressActivity.this);
        initData();

    }
    public void onStart() {
        super.onStart();
        if(mList != null){
            Map<String,String> map = new LinkedHashMap<>();
            map.put("memberId", SpUtils.getUserId(context));
            ViseUtil.Get(context, NetUrl.MemAdressqueryList, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    AddressListBean bean = gson.fromJson(s, AddressListBean.class);
                    mList.clear();
                    mList.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
    private void initData() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemAdressqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AddressListBean bean = gson.fromJson(s, AddressListBean.class);
                mList = bean.getData();
                adapter = new AddressAdapter(mList, new AddressAdapter.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        if(!StringUtils.isEmpty(order)&&order.equals("1")){
                            Intent intent = new Intent();
                            intent.putExtra("bean", mList.get(pos));
                            setResult(1002, intent);
                            finish();
                        }
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
                recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.btn_insert})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert:
                intent.putExtra("id", "0");
                intent.putExtra("type", "0");
                intent.setClass(context, InsertAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem sendItem = new SwipeMenuItem(context)
                    .setBackgroundColor(Color.parseColor("#F6F6F6"))
                    .setText("设为默认")
                    .setTextColor(Color.parseColor("#2D2D2D"))
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(sendItem);// 添加菜单到右侧。

            SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                switch (menuPosition){
                    case 0:
                        //设为默认
                        Map<String,String> map = new LinkedHashMap<>();
                        //Log.e("78789789789",mList.get(adapterPosition).getId()+"");
                        map.put("id",mList.get(adapterPosition).getId()+"");
                        map.put("memberId",SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.MemAdresssetDefault, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "设置成功!");
                                        for (int i=0; i<mList.size();i++){
                                            if(i == adapterPosition){
                                                mList.get(i).setAcquiescentAdress("1");
                                            }else{
                                                mList.get(i).setAcquiescentAdress("0");
                                            }
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        break;
                    case 1:
                        //删除 MemAdresstoDelete
                        Map<String,String> map1 = new LinkedHashMap<>();
                        map1.put("id",mList.get(adapterPosition).getId()+"");
                        ViseUtil.Post(context, NetUrl.MemAdresstoDelete, map1, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "删除成功!");
                                        //mList.get(adapterPosition).
                                        mList.remove(adapterPosition);
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        break;
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

}
