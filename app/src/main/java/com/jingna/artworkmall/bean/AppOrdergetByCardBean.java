package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/2.
 */

public class AppOrdergetByCardBean {

    /**
     * status : 200
     * data : {"id":"1577763381240","addresPhone":"13236636363","addresUname":"佛祖","idNumber":"69886888","orderStatus":"1","paymentMode":"平台币支付","createTime":"2019-12-31 11:36:12","orderRealPrice":113,"couponPrice":10,"list":[],"electronicCode":"KFISNASJSASF","goodsName":"测试商品"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1577763381240
         * addresPhone : 13236636363
         * addresUname : 佛祖
         * idNumber : 69886888
         * orderStatus : 1
         * paymentMode : 平台币支付
         * createTime : 2019-12-31 11:36:12
         * orderRealPrice : 113
         * couponPrice : 10
         * list : []
         * electronicCode : KFISNASJSASF
         * goodsName : 测试商品
         */

        private String id;
        private String addresPhone;
        private String addresUname;
        private String idNumber;
        private String orderStatus;
        private String paymentMode;
        private String createTime;
        private double orderRealPrice;
        private double couponPrice;
        private double price;
        private String electronicCode;
        private String goodsName;
        private List<?> list;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresUname() {
            return addresUname;
        }

        public void setAddresUname(String addresUname) {
            this.addresUname = addresUname;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getOrderRealPrice() {
            return orderRealPrice;
        }

        public void setOrderRealPrice(double orderRealPrice) {
            this.orderRealPrice = orderRealPrice;
        }

        public double getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(double couponPrice) {
            this.couponPrice = couponPrice;
        }

        public String getElectronicCode() {
            return electronicCode;
        }

        public void setElectronicCode(String electronicCode) {
            this.electronicCode = electronicCode;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
