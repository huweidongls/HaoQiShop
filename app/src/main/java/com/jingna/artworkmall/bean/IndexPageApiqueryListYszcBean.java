package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */

public class IndexPageApiqueryListYszcBean {

    /**
     * status : 200
     * data : [{"id":1,"rightsPrivacy":"测试","createTime":"2019-12-20 17:17:40","updateTime":"2019-12-20 17:18:45"}]
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
         * id : 1
         * rightsPrivacy : 测试
         * createTime : 2019-12-20 17:17:40
         * updateTime : 2019-12-20 17:18:45
         */

        private int id;
        private String rightsPrivacy;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRightsPrivacy() {
            return rightsPrivacy;
        }

        public void setRightsPrivacy(String rightsPrivacy) {
            this.rightsPrivacy = rightsPrivacy;
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
    }
}
