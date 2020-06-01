package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/5/29.
 */

public class AppShopMemberEquityControllerqueryListBean {

    /**
     * status : 200
     * data : {"headPhoto":"upload/headPhoto/ceshitupian.jpg","memName":"hhdjkjxxhkkhffjoivc","sfStatus":2,"list":[{"id":8,"memberId":217,"shopId":1,"shopName":"用户注册赠送理疗卡","shopCs":1,"stopTime":"2020-05-26 09:45:37","isZs":0},{"id":12,"memberId":217,"shopId":123,"shopName":"单次体验卡","shopCs":1,"stopTime":"2020-05-29 19:52:21","isZs":1},{"id":43,"memberId":217,"shopId":119,"shopName":"VIP会员精英卡","shopCs":30,"stopTime":"2020-06-01 09:44:45","url":"111","isZs":1}]}
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
         * headPhoto : upload/headPhoto/ceshitupian.jpg
         * memName : hhdjkjxxhkkhffjoivc
         * sfStatus : 2
         * list : [{"id":8,"memberId":217,"shopId":1,"shopName":"用户注册赠送理疗卡","shopCs":1,"stopTime":"2020-05-26 09:45:37","isZs":0},{"id":12,"memberId":217,"shopId":123,"shopName":"单次体验卡","shopCs":1,"stopTime":"2020-05-29 19:52:21","isZs":1},{"id":43,"memberId":217,"shopId":119,"shopName":"VIP会员精英卡","shopCs":30,"stopTime":"2020-06-01 09:44:45","url":"111","isZs":1}]
         */

        private String headPhoto;
        private String memName;
        private int sfStatus;
        private List<ListBean> list;

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public String getMemName() {
            return memName;
        }

        public void setMemName(String memName) {
            this.memName = memName;
        }

        public int getSfStatus() {
            return sfStatus;
        }

        public void setSfStatus(int sfStatus) {
            this.sfStatus = sfStatus;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 8
             * memberId : 217
             * shopId : 1
             * shopName : 用户注册赠送理疗卡
             * shopCs : 1
             * stopTime : 2020-05-26 09:45:37
             * isZs : 0
             * url : 111
             */

            private int id;
            private int memberId;
            private int shopId;
            private String shopName;
            private int shopCs;
            private String stopTime;
            private int isZs;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
