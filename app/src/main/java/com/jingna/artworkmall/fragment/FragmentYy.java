package com.jingna.artworkmall.fragment;

import android.Manifest;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.LazyFragment;
import com.jingna.artworkmall.util.Logger;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/5/20.
 */

public class FragmentYy extends LazyFragment {

    @BindView(R.id.mapview)
    MapView mapView;

    private BaiduMap baiduMap;
    private LocationClient locationClient;

    private boolean isFirstLoc = true;

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
        locationClient.stop();
        locationClient.unRegisterLocationListener(bdLocationListener);
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
            }

        }
    };

}
