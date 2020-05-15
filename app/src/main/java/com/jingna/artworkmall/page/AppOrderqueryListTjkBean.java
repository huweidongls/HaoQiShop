package com.jingna.artworkmall.page;

import java.util.List;

/**
 * Created by Administrator on 2020/1/2.
 */

public class AppOrderqueryListTjkBean {

    /**
     * status : 200
     * data : [{"id":"1577932673267","addresPhone":"13236636363","addresUname":"佛祖","idNumber":"69886888","orderStatus":"1","orderRealPrice":123,"list":[],"electronicCode":"89901577932668973","goodsName":"测试商品"}]
     * totalPage : 1
     * totalCount : 1
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1577932673267
         * addresPhone : 13236636363
         * addresUname : 佛祖
         * idNumber : 69886888
         * orderStatus : 1
         * orderRealPrice : 123
         * list : []
         * electronicCode : 89901577932668973
         * goodsName : 测试商品
         */

        private String id;
        private String addresPhone;
        private String addresUname;
        private String idNumber;
        private String orderStatus;
        private double orderRealPrice;
        private String electronicCode;
        private String goodsName;
        private List<?> list;

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

        public double getOrderRealPrice() {
            return orderRealPrice;
        }

        public void setOrderRealPrice(double orderRealPrice) {
            this.orderRealPrice = orderRealPrice;
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
