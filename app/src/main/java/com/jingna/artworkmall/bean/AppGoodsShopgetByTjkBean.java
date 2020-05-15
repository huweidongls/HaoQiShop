package com.jingna.artworkmall.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/3.
 */

public class AppGoodsShopgetByTjkBean implements Serializable {

    /**
     * status : 200
     * data : {"goodsName":"测试商品","appPic":"upload/goods/2019-12-30/f67db3c583824459b9ec812b99741ef6.jpg","price":123,"subTitle":"测试商品","detailMobileHtml":"<p><span style=\"color: rgb(96, 98, 102); font-family: Avenir, Helvetica, Arial, sans-serif; font-size: 14px; text-align: center; background-color: rgb(245, 247, 250);\">测试商品<\/span><\/p>","purchaseInstructions":"测试商品","type":3}
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

    public static class DataBean implements Serializable {
        /**
         * goodsName : 测试商品
         * appPic : upload/goods/2019-12-30/f67db3c583824459b9ec812b99741ef6.jpg
         * price : 123
         * subTitle : 测试商品
         * detailMobileHtml : <p><span style="color: rgb(96, 98, 102); font-family: Avenir, Helvetica, Arial, sans-serif; font-size: 14px; text-align: center; background-color: rgb(245, 247, 250);">测试商品</span></p>
         * purchaseInstructions : 测试商品
         * type : 3
         */

        private String goodsName;
        private String appPic;
        private double price;
        private String subTitle;
        private String detailMobileHtml;
        private String purchaseInstructions;
        private int type;
        private int isGood;

        public int getIsGood() {
            return isGood;
        }

        public void setIsGood(int isGood) {
            this.isGood = isGood;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
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

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDetailMobileHtml() {
            return detailMobileHtml;
        }

        public void setDetailMobileHtml(String detailMobileHtml) {
            this.detailMobileHtml = detailMobileHtml;
        }

        public String getPurchaseInstructions() {
            return purchaseInstructions;
        }

        public void setPurchaseInstructions(String purchaseInstructions) {
            this.purchaseInstructions = purchaseInstructions;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
