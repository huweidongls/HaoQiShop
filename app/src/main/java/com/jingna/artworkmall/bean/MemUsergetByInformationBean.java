package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/2.
 */

public class MemUsergetByInformationBean {

    /**
     * status : 200
     * data : {"id":220,"memName":"IVLZ_18686817319","headPhoto":"upload/headPhoto/ceshitupian.jpg","memIntegral":870100,"memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[],"couponNum":2,"isSignIn":0,"sfStatus":1,"csdlStatus":0,"isHhr":0}
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
         * id : 220
         * memName : IVLZ_18686817319
         * headPhoto : upload/headPhoto/ceshitupian.jpg
         * memIntegral : 870100
         * memberUserInfos : []
         * platformBalances : []
         * verificationSheetRecords : []
         * couponNum : 2
         * isSignIn : 0
         * sfStatus : 1
         * csdlStatus : 0
         * isHhr : 0
         */

        private int id;
        private String memName;
        private String headPhoto;
        private int memIntegral;
        private int couponNum;
        private int isSignIn;
        private int sfStatus;
        private int csdlStatus;
        private int isHhr;
        private List<?> memberUserInfos;
        private List<?> platformBalances;
        private List<?> verificationSheetRecords;

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

        public int getMemIntegral() {
            return memIntegral;
        }

        public void setMemIntegral(int memIntegral) {
            this.memIntegral = memIntegral;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public int getIsSignIn() {
            return isSignIn;
        }

        public void setIsSignIn(int isSignIn) {
            this.isSignIn = isSignIn;
        }

        public int getSfStatus() {
            return sfStatus;
        }

        public void setSfStatus(int sfStatus) {
            this.sfStatus = sfStatus;
        }

        public int getCsdlStatus() {
            return csdlStatus;
        }

        public void setCsdlStatus(int csdlStatus) {
            this.csdlStatus = csdlStatus;
        }

        public int getIsHhr() {
            return isHhr;
        }

        public void setIsHhr(int isHhr) {
            this.isHhr = isHhr;
        }

        public List<?> getMemberUserInfos() {
            return memberUserInfos;
        }

        public void setMemberUserInfos(List<?> memberUserInfos) {
            this.memberUserInfos = memberUserInfos;
        }

        public List<?> getPlatformBalances() {
            return platformBalances;
        }

        public void setPlatformBalances(List<?> platformBalances) {
            this.platformBalances = platformBalances;
        }

        public List<?> getVerificationSheetRecords() {
            return verificationSheetRecords;
        }

        public void setVerificationSheetRecords(List<?> verificationSheetRecords) {
            this.verificationSheetRecords = verificationSheetRecords;
        }
    }
}
