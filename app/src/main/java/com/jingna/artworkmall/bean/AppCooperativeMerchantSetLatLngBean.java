package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/2.
 */

public class AppCooperativeMerchantSetLatLngBean {

    /**
     * status : 200
     * data : [{"merchantName":"海思艾瑞总公司","isPartner":0,"lat":"45.784935","lng":"126.634788"},{"merchantName":"ceshi","isPartner":0,"lat":"45.784615","lng":"126.634918"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * merchantName : 海思艾瑞总公司
         * isPartner : 0
         * lat : 45.784935
         * lng : 126.634788
         */

        private String merchantName;
        private int isPartner;
        private String lat;
        private String lng;

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public int getIsPartner() {
            return isPartner;
        }

        public void setIsPartner(int isPartner) {
            this.isPartner = isPartner;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }
}
