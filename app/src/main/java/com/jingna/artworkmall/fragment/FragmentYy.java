package com.jingna.artworkmall.fragment;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.LazyFragment;
import com.jingna.artworkmall.bean.AppCooperativeMerchantSetLatLngBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.page.SubmitYuyueActivity;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/5/20.
 */

public class FragmentYy extends LazyFragment {

    @BindView(R.id.mapview)
    MapView mapView;

    private BaiduMap baiduMap;
    private LocationClient locationClient;

    private boolean isFirstLoc = true;

    private double lat = 0.00;
    private double lng = 0.00;

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
        PermissionManager.instance().request(getActivity(), new OnPermissionCallback() {
            @Override
            public void onRequestAllow(String permissionName) {
                Logger.e("123123", "allow");
                initData();
            }

            @Override
            public void onRequestRefuse(String permissionName) {
                Logger.e("123123", "refuse");
            }

            @Override
            public void onRequestNoAsk(String permissionName) {
                Logger.e("123123", "noask");
                initData();
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void initData() {

        initLocation();
        locationClient.registerLocationListener(bdLocationListener);
        locationClient.start();
        mapView.showZoomControls(false);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

    }

    @OnClick({R.id.ll_yuyue, R.id.ll_loc})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_yuyue:
                ToastUtil.showShort(getContext(), "当前城市暂未开通");
//                intent.setClass(getContext(), SubmitYuyueActivity.class);
//                startActivity(intent);
                break;
            case R.id.ll_loc:
                ToastUtil.showShort(getContext(), "当前城市暂未开通");
//                LatLng ll = new LatLng(lat,
//                        lng);
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("lat", lat+"");
//                map.put("lng", lng+"");
//                ViseUtil.Get(getContext(), NetUrl.AppCooperativeMerchantSetLatLng, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Logger.e("123123", s);
//                        Gson gson = new Gson();
//                        AppCooperativeMerchantSetLatLngBean bean = gson.fromJson(s, AppCooperativeMerchantSetLatLngBean.class);
//                        List<AppCooperativeMerchantSetLatLngBean.DataBean> list = bean.getData();
//                        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
//                        //构建Marker图标
//                        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                                .fromResource(R.mipmap.icon020);
//                        for (int i = 0; i<list.size(); i++){
//                            options.add(new MarkerOptions().position(new LatLng(Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()))).icon(bitmap));
//                        }
//                        baiduMap.addOverlays(options);
//                    }
//                });
                break;
        }
    }

    private void initLocation(){

        locationClient = new LocationClient(getContext());
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setScanSpan(0);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        locationClient.setLocOption(option);

    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(locationClient != null){
            locationClient.stop();
            locationClient.unRegisterLocationListener(bdLocationListener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    private MyLocationData locData;
    private BDLocationListener bdLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            // map view 销毁后不在处理新接收的位置
            if (bdLocation == null || mapView == null) {
                return;
            }
            lat = bdLocation.getLatitude();
            lng = bdLocation.getLongitude();
            locData = new MyLocationData.Builder()
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("lat", bdLocation.getLatitude()+"");
//                map.put("lng", bdLocation.getLongitude()+"");
//                ViseUtil.Get(getContext(), NetUrl.AppCooperativeMerchantSetLatLng, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Logger.e("123123", s);
//                        Gson gson = new Gson();
//                        AppCooperativeMerchantSetLatLngBean bean = gson.fromJson(s, AppCooperativeMerchantSetLatLngBean.class);
//                        List<AppCooperativeMerchantSetLatLngBean.DataBean> list = bean.getData();
//                        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
//                        //构建Marker图标
//                        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                                .fromResource(R.mipmap.icon020);
//                        for (int i = 0; i<list.size(); i++){
//                            options.add(new MarkerOptions().position(new LatLng(Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()))).icon(bitmap));
//                        }
//                        baiduMap.addOverlays(options);
//                    }
//                });

            }

        }
    };

}
