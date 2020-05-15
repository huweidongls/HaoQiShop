package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2020/1/16.
 */

public class VersionBean {

    /**
     * status : 200
     * data : {"id":1,"versionName":"第一版","downloadUrl":"www.huxiaoniiu.cn","onOff":1,"verDesc":"哼,老马","isDelete":1,"createTime":"2020-01-17 10:03:15","qrCode":"www.huxiaoniu.cn","updateTime":"2020-01-17 10:03:54","versionCode":1}
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
         * id : 1
         * versionName : 第一版
         * downloadUrl : www.huxiaoniiu.cn
         * onOff : 1
         * verDesc : 哼,老马
         * isDelete : 1
         * createTime : 2020-01-17 10:03:15
         * qrCode : www.huxiaoniu.cn
         * updateTime : 2020-01-17 10:03:54
         * versionCode : 1
         */

        private int id;
        private String versionName;
        private String downloadUrl;
        private int onOff;
        private String verDesc;
        private int isDelete;
        private String createTime;
        private String qrCode;
        private String updateTime;
        private int versionCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public int getOnOff() {
            return onOff;
        }

        public void setOnOff(int onOff) {
            this.onOff = onOff;
        }

        public String getVerDesc() {
            return verDesc;
        }

        public void setVerDesc(String verDesc) {
            this.verDesc = verDesc;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }
    }
}
