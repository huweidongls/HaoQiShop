package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/5/29.
 */

public class AppShopMemberEquityControllerqueryListBean {

    /**
     * status : 200
     * data : [{"id":13,"memberId":220,"shopId":1,"shopName":"用户注册赠送理疗卡","shopCs":1,"stopTime":"2020-05-29 19:53:22","isZs":0}]
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
         * id : 13
         * memberId : 220
         * shopId : 1
         * shopName : 用户注册赠送理疗卡
         * shopCs : 1
         * stopTime : 2020-05-29 19:53:22
         * isZs : 0
         */

        private int id;
        private int memberId;
        private int shopId;
        private String shopName;
        private int shopCs;
        private String stopTime;
        private int isZs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getShopCs() {
            return shopCs;
        }

        public void setShopCs(int shopCs) {
            this.shopCs = shopCs;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }

        public int getIsZs() {
            return isZs;
        }

        public void setIsZs(int isZs) {
            this.isZs = isZs;
        }
    }
}
