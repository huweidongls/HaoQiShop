package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/27.
 */

public class AppPlatformBalancequeryListBean {

    /**
     * status : 200
     * data : [{"createTime":"2019-12-20 16:49:38","operatingDescribe":"充值","balance":900,"operatingRecord":-100},{"createTime":"2019-12-20 16:49:05","operatingDescribe":"充值","balance":1000,"operatingRecord":100}]
     * totalPage : 0
     * totalCount : 2
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
         * createTime : 2019-12-20 16:49:38
         * operatingDescribe : 充值
         * balance : 900
         * operatingRecord : -100
         */

        private String createTime;
        private String operatingDescribe;
        private double balance;
        private double operatingRecord;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOperatingDescribe() {
            return operatingDescribe;
        }

        public void setOperatingDescribe(String operatingDescribe) {
            this.operatingDescribe = operatingDescribe;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getOperatingRecord() {
            return operatingRecord;
        }

        public void setOperatingRecord(double operatingRecord) {
            this.operatingRecord = operatingRecord;
        }
    }
}
