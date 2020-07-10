package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class MemUsergetOneBean {

    /**
     * status : 200
     * data : {"goodsNum":0,"browseRecord":0,"memberUserInfo":{"id":3,"memBirthday":"2019-12-26","memName":"18686817319","headPhoto":"upload/headPhoto/ceshitupian.jpg","username":"18686817319","password":"e10adc3949ba59abbe56e057f20f883e","memIntegral":9.964731155E7,"memStatus":0,"phoneNum":"18686817319","isFrozen":0,"gender":"1","updateTime":"2020-01-07 09:53:07","invitationCode":"j36948d64N","idNumber":"230502199308181313","realName":"123","isAuthentication":1,"pidPhone":"13045134573","expireCreateTime":"2020-01-09 17:15:02","businessValue":123,"memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[]}}
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
         * goodsNum : 0
         * browseRecord : 0
         * memberUserInfo : {"id":3,"memBirthday":"2019-12-26","memName":"18686817319","headPhoto":"upload/headPhoto/ceshitupian.jpg","username":"18686817319","password":"e10adc3949ba59abbe56e057f20f883e","memIntegral":9.964731155E7,"memStatus":0,"phoneNum":"18686817319","isFrozen":0,"gender":"1","updateTime":"2020-01-07 09:53:07","invitationCode":"j36948d64N","idNumber":"230502199308181313","realName":"123","isAuthentication":1,"pidPhone":"13045134573","expireCreateTime":"2020-01-09 17:15:02","businessValue":123,"memberUserInfos":[],"platformBalances":[],"verificationSheetRecords":[]}
         */

        private int goodsNum;
        private int browseRecord;
        private MemberUserInfoBean memberUserInfo;

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getBrowseRecord() {
            return browseRecord;
        }

        public void setBrowseRecord(int browseRecord) {
            this.browseRecord = browseRecord;
        }

        public MemberUserInfoBean getMemberUserInfo() {
            return memberUserInfo;
        }

        public void setMemberUserInfo(MemberUserInfoBean memberUserInfo) {
            this.memberUserInfo = memberUserInfo;
        }

        public static class MemberUserInfoBean {
            /**
             * id : 3
             * memBirthday : 2019-12-26
             * memName : 18686817319
             * headPhoto : upload/headPhoto/ceshitupian.jpg
             * username : 18686817319
             * password : e10adc3949ba59abbe56e057f20f883e
             * memIntegral : 9.964731155E7
             * memStatus : 0
             * phoneNum : 18686817319
             * isFrozen : 0
             * gender : 1
             * updateTime : 2020-01-07 09:53:07
             * invitationCode : j36948d64N
             * idNumber : 230502199308181313
             * realName : 123
             * isAuthentication : 1
             * pidPhone : 13045134573
             * expireCreateTime : 2020-01-09 17:15:02
             * businessValue : 123
             * memberUserInfos : []
             * platformBalances : []
             * verificationSheetRecords : []
             */

            private int id;
            private String memBirthday;
            private String memName;
            private String headPhoto;
            private String username;
            private String password;
            private double memIntegral;
            private int memStatus;
            private String phoneNum;
            private int isFrozen;
            private String gender;
            private String updateTime;
            private String invitationCode;
            private String idNumber;
            private String realName;
            private int isAuthentication;
            private String pidPhone;
            private String expireCreateTime;
            private int businessValue;
            private List<?> memberUserInfos;
            private List<?> platformBalances;
            private List<?> verificationSheetRecords;
            private String loginUrl;
            private String payPassword;

            public String getPayPassword() {
                return payPassword;
            }

            public void setPayPassword(String payPassword) {
                this.payPassword = payPassword;
            }

            public String getLoginUrl() {
                return loginUrl;
            }

            public void setLoginUrl(String loginUrl) {
                this.loginUrl = loginUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMemBirthday() {
                return memBirthday;
            }

            public void setMemBirthday(String memBirthday) {
                this.memBirthday = memBirthday;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public double getMemIntegral() {
                return memIntegral;
            }

            public void setMemIntegral(double memIntegral) {
                this.memIntegral = memIntegral;
            }

            public int getMemStatus() {
                return memStatus;
            }

            public void setMemStatus(int memStatus) {
                this.memStatus = memStatus;
            }

            public String getPhoneNum() {
                return phoneNum;
            }

            public void setPhoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
            }

            public int getIsFrozen() {
                return isFrozen;
            }

            public void setIsFrozen(int isFrozen) {
                this.isFrozen = isFrozen;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public int getIsAuthentication() {
                return isAuthentication;
            }

            public void setIsAuthentication(int isAuthentication) {
                this.isAuthentication = isAuthentication;
            }

            public String getPidPhone() {
                return pidPhone;
            }

            public void setPidPhone(String pidPhone) {
                this.pidPhone = pidPhone;
            }

            public String getExpireCreateTime() {
                return expireCreateTime;
            }

            public void setExpireCreateTime(String expireCreateTime) {
                this.expireCreateTime = expireCreateTime;
            }

            public int getBusinessValue() {
                return businessValue;
            }

            public void setBusinessValue(int businessValue) {
                this.businessValue = businessValue;
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
}
