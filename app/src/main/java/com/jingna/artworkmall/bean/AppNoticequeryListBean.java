package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */

public class AppNoticequeryListBean {

    /**
     * status : 200
     * data : [{"id":1,"title":"干啥啥不行,咋玩第二名","subtitle":"副标题","contentText":"内容","createId":1,"status":0,"createTime":"2020-01-10 15:43:30","updateTime":"2020-01-16 15:16:04","nickName":"管理员"},{"id":2,"title":"沙皇吃鸡不太香","subtitle":"副标题","contentText":"内容","createId":1,"status":0,"createTime":"2020-01-10 15:43:30","updateTime":"2020-01-16 15:16:02","nickName":"管理员"},{"id":3,"title":"删除英雄保太平","subtitle":"副标题","contentText":"内容","createId":1,"status":0,"createTime":"2020-01-10 15:43:30","updateTime":"2020-01-16 15:16:01","nickName":"管理员"}]
     * totalPage : 1
     * totalCount : 3
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
         * id : 1
         * title : 干啥啥不行,咋玩第二名
         * subtitle : 副标题
         * contentText : 内容
         * createId : 1
         * status : 0
         * createTime : 2020-01-10 15:43:30
         * updateTime : 2020-01-16 15:16:04
         * nickName : 管理员
         */

        private int id;
        private String title;
        private String subtitle;
        private String contentText;
        private int createId;
        private int status;
        private String createTime;
        private String updateTime;
        private String nickName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getContentText() {
            return contentText;
        }

        public void setContentText(String contentText) {
            this.contentText = contentText;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
