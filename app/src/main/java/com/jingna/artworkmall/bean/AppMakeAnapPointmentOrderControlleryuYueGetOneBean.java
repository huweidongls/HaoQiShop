package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2020/6/2.
 */

public class AppMakeAnapPointmentOrderControlleryuYueGetOneBean {

    /**
     * status : 200
     * data : {"type":"测试服务","pjy":"祥业","phone":"18888888888","dz":"黑龙江省-哈尔滨市-南岗区汉广街41号金华大厦407","xdTime":"2020-06-02 09:57:22","sdTime":"2020-06-10 20:00:00","status":1,"jl":5}
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
         * type : 测试服务
         * pjy : 祥业
         * phone : 18888888888
         * dz : 黑龙江省-哈尔滨市-南岗区汉广街41号金华大厦407
         * xdTime : 2020-06-02 09:57:22
         * sdTime : 2020-06-10 20:00:00
         * status : 1
         * jl : 5
         */

        private String type;
        private String pjy;
        private String phone;
        private String dz;
        private String xdTime;
        private String sdTime;
        private int status;
        private double jl;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPjy() {
            return pjy;
        }

        public void setPjy(String pjy) {
            this.pjy = pjy;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDz() {
            return dz;
        }

        public void setDz(String dz) {
            this.dz = dz;
        }

        public String getXdTime() {
            return xdTime;
        }

        public void setXdTime(String xdTime) {
            this.xdTime = xdTime;
        }

        public String getSdTime() {
            return sdTime;
        }

        public void setSdTime(String sdTime) {
            this.sdTime = sdTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getJl() {
            return jl;
        }

        public void setJl(double jl) {
            this.jl = jl;
        }
    }
}
