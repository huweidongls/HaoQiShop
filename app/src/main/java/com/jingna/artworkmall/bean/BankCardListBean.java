package com.jingna.artworkmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/7/23.
 */

public class BankCardListBean implements Serializable {

    /**
     * status : 200
     * data : [{"id":3,"cardNumber":"5412541521452","cardName":"建设银行","cardPhone":"13796068265"},{"id":2,"cardNumber":"11","cardName":"啊啊啊","cardPhone":"11"}]
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
         * id : 3
         * cardNumber : 5412541521452
         * cardName : 建设银行
         * cardPhone : 13796068265
         */

        private int id;
        private String cardNumber;
        private String cardName;
        private String cardPhone;
        private String cardChannel;

        public String getCardChannel() {
            return cardChannel;
        }

        public void setCardChannel(String cardChannel) {
            this.cardChannel = cardChannel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardPhone() {
            return cardPhone;
        }

        public void setCardPhone(String cardPhone) {
            this.cardPhone = cardPhone;
        }
    }
}
