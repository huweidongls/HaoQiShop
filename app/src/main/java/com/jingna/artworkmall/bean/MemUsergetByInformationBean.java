package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2020/1/2.
 */

public class MemUsergetByInformationBean {

    /**
     * status : 200
     * data : {"id":3,"memName":"18686817319","headPhoto":"upload/headPhoto/ceshitupian.jpg","memIntegral":99997267,"couponNum":3,"activityLevelNum":1000,"memberUserLiveness":150,"isSignIn":1}
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
         * id : 3
         * memName : 18686817319
         * headPhoto : upload/headPhoto/ceshitupian.jpg
         * memIntegral : 99997267
         * couponNum : 3
         * activityLevelNum : 1000
         * memberUserLiveness : 150
         * isSignIn : 1
         */

        private int id;
        private String memName;
        private String headPhoto;
        private double memIntegral;
        private int couponNum;
        private int activityLevelNum;
        private int memberUserLiveness;
        private int isSignIn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemName() {
            return memName;
        }

        public void setMemName(String memName) {
            this.memName = memName;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public double getMemIntegral() {
            return memIntegral;
        }

        public void setMemIntegral(double memIntegral) {
            this.memIntegral = memIntegral;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public int getActivityLevelNum() {
            return activityLevelNum;
        }

        public void setActivityLevelNum(int activityLevelNum) {
            this.activityLevelNum = activityLevelNum;
        }

        public int getMemberUserLiveness() {
            return memberUserLiveness;
        }

        public void setMemberUserLiveness(int memberUserLiveness) {
            this.memberUserLiveness = memberUserLiveness;
        }

        public int getIsSignIn() {
            return isSignIn;
        }

        public void setIsSignIn(int isSignIn) {
            this.isSignIn = isSignIn;
        }
    }
}
