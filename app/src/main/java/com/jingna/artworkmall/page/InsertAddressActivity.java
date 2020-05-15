package com.jingna.artworkmall.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.bean.AddressInfoBean;
import com.jingna.artworkmall.bean.AddressListBean;
import com.jingna.artworkmall.bean.JsonBean;
import com.jingna.artworkmall.net.NetUrl;
import com.jingna.artworkmall.util.GetJsonDataUtil;
import com.jingna.artworkmall.util.SpUtils;
import com.jingna.artworkmall.util.StatusBarUtil;
import com.jingna.artworkmall.util.StringUtils;
import com.jingna.artworkmall.util.ToastUtil;
import com.jingna.artworkmall.util.ViseUtil;
import com.jingna.artworkmall.util.WeiboDialogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertAddressActivity extends BaseActivity {

    private Context context = InsertAddressActivity.this;

    private String aid="";//要修改的地址ID

    private String type="";//修改的type

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.tv_title)
    TextView tv_title;
//    @BindView(R.id.et_id_num)
//    EditText etIdNum;
    @BindView(R.id.rl_moren)
    RelativeLayout rlMoren;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String acquiescentAdress = "0";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_address);
        aid = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        StatusBarUtil.setStatusBarColor(InsertAddressActivity.this, getResources().getColor(R.color.white_ffffff));
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(InsertAddressActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(InsertAddressActivity.this,0x55000000);
        }
        ButterKnife.bind(InsertAddressActivity.this);
        initData();

    }



    private void initData() {
        if(type.equals("0") && aid.equals("0")){
            initJsonData();
            tv_title.setText("新建收货地址");
        }else{
            tv_title.setText("编辑收货地址");
            initJsonData();
            Map<String,String> map = new LinkedHashMap<>();
            map.put("id",aid);
            ViseUtil.Get(context, NetUrl.MemAdressgetOne, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    AddressInfoBean bean = gson.fromJson(s, AddressInfoBean.class);
                    etName.setText(bean.getData().getConsignee());
                    etPhoneNum.setText(bean.getData().getConsigneeTel());
                    tvCity.setText(bean.getData().getLocation());
                    etAddress.setText(bean.getData().getAdress());
//                    etIdNum.setText(bean.getData().getIdNumber());
                    if(bean.getData().getAcquiescentAdress().equals("0")){
                        Glide.with(context).load(R.mipmap.address_off).into(ivSet);
                        acquiescentAdress = "0";
                    }else {
                        Glide.with(context).load(R.mipmap.address_on).into(ivSet);
                        acquiescentAdress = "1";
                        rlMoren.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @OnClick({R.id.rl_back, R.id.ll_city, R.id.btn_save, R.id.iv_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_city:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);
                        tvCity.setText(tx);
                    }
                })
                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
                break;
            case R.id.btn_save:
                String UserName = etName.getText().toString();
                String phone = etPhoneNum.getText().toString();
                String city = tvCity.getText().toString();
                String UseretAddress = etAddress.getText().toString();
//                String idNum = etIdNum.getText().toString();
                if(StringUtils.isEmpty(UserName) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(city) ||
                        StringUtils.isEmpty(UseretAddress)){
                    ToastUtil.showShort(context,"请将信息填写完整!");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context,"请输入正确格式的手机号码!");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String,String> map = new LinkedHashMap<>();
                    map.put("memberId", SpUtils.getUserId(context));//会员ID
                    if(!aid.equals("0")){
                        map.put("id",aid);
                    }
                    map.put("consignee", UserName);//收货人
                    map.put("adress", UseretAddress);//收货地址
                    map.put("acquiescentAdress", acquiescentAdress);//默认地址(0为正常/1为默认)
                    map.put("location", city);//所在地区
                    map.put("consigneeTel", phone);//收货人电话
//                    map.put("idNumber", idNum);
                    map.put("zipCode", "000000");
                    ViseUtil.Post(context, NetUrl.MemAdresstoUpdate, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context,"添加成功!");
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.iv_set:
                if(acquiescentAdress.equals("0")){
                    Glide.with(context).load(R.mipmap.address_on).into(ivSet);
                    acquiescentAdress = "1";
                }else {
                    Glide.with(context).load(R.mipmap.address_off).into(ivSet);
                    acquiescentAdress = "0";
                }
                break;
        }
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
