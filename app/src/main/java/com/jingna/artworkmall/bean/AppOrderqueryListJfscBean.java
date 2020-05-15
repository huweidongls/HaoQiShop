package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/2.
 */

public class AppOrderqueryListJfscBean {

    /**
     * status : 200
     * data : [{"id":"1578393752905","orderStatus":"1","orderRealPrice":246,"list":[],"num":2,"appPic":"upload/headPhoto/ceshitupian.jpg","label":"特惠,精选","goodsName":"测试商品1"}]
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
         * id : 1578393752905
         * orderStatus : 1
         * orderRealPrice : 246
         * list : []
         * num : 2
         * appPic : upload/headPhoto/ceshitupian.jpg
         * label : 特惠,精选
         * goodsName : 测试商品1
         */

        private String id;
        private String orderStatus;
        private double orderRealPrice;
        private int num;
        private String appPic;
        private String label;
        private String goodsName;
        private List<?> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
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
