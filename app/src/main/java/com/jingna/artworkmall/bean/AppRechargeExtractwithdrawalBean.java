package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2020/1/6.
 */

public class AppRechargeExtractwithdrawalBean {

    /**
     * status : 200
     * data : {"balance":9.999726747E7,"cardname":"中国银行","cardid":5,"cardChannel":"银行卡"}
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
         * balance : 9.999726747E7
         * cardname : 中国银行
         * cardid : 5
         * cardChannel : 银行卡
         */

        private double balance;
        private String cardname;
        private int cardid;
        private String cardChannel;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getCardname() {
            return cardname;
        }

        public void setCardname(String cardname) {
            this.cardname = cardname;
        }

        public int getCardid() {
            return cardid;
        }

        public void setCardid(int cardid) {
            this.cardid = cardid;
        }

        public String getCardChannel() {
            return cardChannel;
        }

        public void setCardChannel(String cardChannel) {
            this.cardChannel = cardChannel;
        }
    }
}
