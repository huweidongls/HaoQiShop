package com.jingna.artworkmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/12/30.
 */

public class MarketingCouponUserfindByCouponsBean implements Serializable {

    /**
     * status : 200
     * data : [{"name":"满100-10优惠券","usageMode":0,"type":10,"parameter":10,"maxMoney":100,"sumDiscount":0,"createTime":"2019-12-20 19:57:01","pastTime":"2019-12-23 20:02:44"},{"name":"满100-10优惠券","usageMode":0,"type":10,"parameter":10,"maxMoney":100,"sumDiscount":0,"createTime":"2019-12-20 19:44:52","pastTime":"2019-12-23 20:02:44"}]
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

    public static class DataBean implements Serializable {
        /**
         * name : 满100-10优惠券
         * usageMode : 0
         * type : 10
         * parameter : 10
         * maxMoney : 100
         * sumDiscount : 0
         * createTime : 2019-12-20 19:57:01
         * pastTime : 2019-12-23 20:02:44
         */

        private int id;
        private String name;
        private int usageMode;
        private int type;
        private double parameter;
        private double maxMoney;
        private double sumDiscount;
        private String createTime;
        private String pastTime;
        private String ucId;

        public String getUcId() {
            return ucId;
        }

        public void setUcId(String ucId) {
            this.ucId = ucId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUsageMode() {
            return usageMode;
        }

        public void setUsageMode(int usageMode) {
            this.usageMode = usageMode;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getParameter() {
            return parameter;
        }

        public void setParameter(double parameter) {
            this.parameter = parameter;
        }

        public double getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(double maxMoney) {
            this.maxMoney = maxMoney;
        }

        public double getSumDiscount() {
            return sumDiscount;
        }

        public void setSumDiscount(double sumDiscount) {
            this.sumDiscount = sumDiscount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPastTime() {
            return pastTime;
        }

        public void setPastTime(String pastTime) {
            this.pastTime = pastTime;
        }
    }
}
