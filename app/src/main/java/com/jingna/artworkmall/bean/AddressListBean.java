package com.jingna.artworkmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class AddressListBean implements Serializable {

    /**
     * status : 200
     * data : [{"id":20,"memberId":"3","consignee":"宁","adress":"顶楼哦","acquiescentAdress":"1","location":"北京市-北京市-东城区","consigneeTel":"18643356464","idNumber":"34656565353464644","zipCode":"000000","status":"1","createDate":"2019-12-30 10:30:41"}]
     * totalPage : 0
     * totalCount : 1
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 20
         * memberId : 3
         * consignee : 宁
         * adress : 顶楼哦
         * acquiescentAdress : 1
         * location : 北京市-北京市-东城区
         * consigneeTel : 18643356464
         * idNumber : 34656565353464644
         * zipCode : 000000
         * status : 1
         * createDate : 2019-12-30 10:30:41
         */

        private int id;
        private String memberId;
        private String consignee;
        private String adress;
        private String acquiescentAdress;
        private String location;
        private String consigneeTel;
        private String idNumber;
        private String zipCode;
        private String status;
        private String createDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getAcquiescentAdress() {
            return acquiescentAdress;
        }

        public void setAcquiescentAdress(String acquiescentAdress) {
            this.acquiescentAdress = acquiescentAdress;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getConsigneeTel() {
            return consigneeTel;
        }

        public void setConsigneeTel(String consigneeTel) {
            this.consigneeTel = consigneeTel;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
