package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/29.
 */

public class IndexPageApiqueryCardBean {

    /**
     * status : 200
     * data : [{"id":1,"goodsName":"钱多多买产品","label":"特惠,精选","appPic":"upload/goods/2019-12-30/f67db3c583824459b9ec812b99741ef6.jpg","price":1000,"description":"钱多多专卖","originalPrice":999,"isGood":1},{"id":91,"goodsName":"测试商品","label":"特惠,精选","appPic":"upload/goods/2019-12-30/f67db3c583824459b9ec812b99741ef6.jpg","price":123,"description":"测试商品","originalPrice":123,"isGood":1},{"id":90,"goodsName":"测试商品2","label":"特惠,精选","appPic":"upload/goods/2019-12-29/930d20df0af54040bef760bda22bd170.jpg","price":123,"description":"测试商品2","originalPrice":123,"isGood":1}]
     * totalPage : 0
     * totalCount : 3
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
         * id : 1
         * goodsName : 钱多多买产品
         * label : 特惠,精选
         * appPic : upload/goods/2019-12-30/f67db3c583824459b9ec812b99741ef6.jpg
         * price : 1000
         * description : 钱多多专卖
         * originalPrice : 999
         * isGood : 1
         */

        private int id;
        private String goodsName;
        private String label;
        private String appPic;
        private double price;
        private String description;
        private double originalPrice;
        private int isGood;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getIsGood() {
            return isGood;
        }

        public void setIsGood(int isGood) {
            this.isGood = isGood;
        }
    }
}
