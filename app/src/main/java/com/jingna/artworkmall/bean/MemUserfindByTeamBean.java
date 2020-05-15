package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/2.
 */

public class MemUserfindByTeamBean {

    /**
     * status : 200
     * data : [{"id":3,"memName":"18686817319","headPhoto":"upload/headPhoto/ceshitupian.jpg","memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[],"sumPrice":3236,"sumYjdl":0,"sumCount":3},{"id":20,"memName":"子不语","headPhoto":"/upload/279e06cf3daa421c909cb7cd6afe8d57.jpeg","memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[],"sumPrice":4113,"sumYjdl":0,"sumCount":5},{"id":102,"memName":"饭多多","headPhoto":"upload/headPhoto/ceshitupian.jpg","memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[],"sumPrice":6000,"sumYjdl":6.6,"sumCount":3},{"id":103,"memName":"金多多","headPhoto":"upload/headPhoto/ceshitupian.jpg","memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[],"sumPrice":1950,"sumYjdl":2.34,"sumCount":1}]
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
         * id : 3
         * memName : 18686817319
         * headPhoto : upload/headPhoto/ceshitupian.jpg
         * memberUserInfos : []
         * platformBalances : []
         * verificationSheetRecords : []
         * sumPrice : 3236
         * sumYjdl : 0
         * sumCount : 3
         */

        private int id;
        private String memName;
        private String headPhoto;
        private double sumPrice;
        private double sumYjdl;
        private int sumCount;
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

        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }

        public double getSumYjdl() {
            return sumYjdl;
        }

        public void setSumYjdl(double sumYjdl) {
            this.sumYjdl = sumYjdl;
        }

        public int getSumCount() {
            return sumCount;
        }

        public void setSumCount(int sumCount) {
            this.sumCount = sumCount;
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
