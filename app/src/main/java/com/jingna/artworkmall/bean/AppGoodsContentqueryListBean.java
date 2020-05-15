package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/30.
 */

public class AppGoodsContentqueryListBean {

    /**
     * status : 200
     * data : [{"id":2,"contentTitle":"内容标题1","subTitle":"副标题1","contentImg":"内容图片1","contentType":0,"createTime":"2019-12-27 15:02:01"},{"id":1,"contentTitle":"内容标题","subTitle":"副标题","contentImg":"内容图片","contentType":0,"createTime":"2019-12-27 15:01:26"}]
     * totalPage : 0
     * totalCount : 2
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

    public static class DataBean {
        /**
         * id : 2
         * contentTitle : 内容标题1
         * subTitle : 副标题1
         * contentImg : 内容图片1
         * contentType : 0
         * createTime : 2019-12-27 15:02:01
         */

        private int id;
        private String contentTitle;
        private String subTitle;
        private String contentImg;
        private int contentType;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContentTitle() {
            return contentTitle;
        }

        public void setContentTitle(String contentTitle) {
            this.contentTitle = contentTitle;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getContentImg() {
            return contentImg;
        }

        public void setContentImg(String contentImg) {
            this.contentImg = contentImg;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
