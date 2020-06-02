package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/2.
 */

public class AppMakeAnapPointmentOrderControlleryuYueListBean {

    /**
     * status : 200
     * data : [{"id":5,"memberId":217,"addressId":52,"createTime":"2020-06-02 10:17:29","deliveryTime":"2020-06-01 17:35:07","appointmentTyp":"腰间盘理疗","beizhu":"需要美女送达","lng":"126.63206915516946","lat":"45.743105212785348","status":0},{"id":6,"memberId":217,"addressId":52,"createTime":"2020-06-01 17:40:21","deliveryTime":"2020-06-01 17:35:07","appointmentTyp":"腰间盘理疗","beizhu":"需要美女送达","lng":"126.63206915516946","lat":"45.743105212785348","status":0}]
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

    public static class DataBean {
        /**
         * id : 5
         * memberId : 217
         * addressId : 52
         * createTime : 2020-06-02 10:17:29
         * deliveryTime : 2020-06-01 17:35:07
         * appointmentTyp : 腰间盘理疗
         * beizhu : 需要美女送达
         * lng : 126.63206915516946
         * lat : 45.743105212785348
         * status : 0
         */

        private int id;
        private int memberId;
        private int addressId;
        private String createTime;
        private String deliveryTime;
        private String appointmentTyp;
        private String beizhu;
        private String lng;
        private String lat;
        private int status;

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

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getAppointmentTyp() {
            return appointmentTyp;
        }

        public void setAppointmentTyp(String appointmentTyp) {
            this.appointmentTyp = appointmentTyp;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
