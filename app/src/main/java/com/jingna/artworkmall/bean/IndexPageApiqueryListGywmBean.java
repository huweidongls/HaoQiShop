package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */

public class IndexPageApiqueryListGywmBean {

    /**
     * status : 200
     * data : [{"id":1,"aboutUs":"<p style=\"text-align: right;\">关于0.01234<\/p><p style=\"text-align:center\"><img src=\"http://35.201.165.105:8000/storage/image/20190329/1553848304759918.jpg\" width=\"284\" height=\"179\"/><\/p><p style=\"text-align: right;\"><br/><\/p>","updateTime":"2019-03-29 16:33:22"}]
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
         * aboutUs : <p style="text-align: right;">关于0.01234</p><p style="text-align:center"><img src="http://35.201.165.105:8000/storage/image/20190329/1553848304759918.jpg" width="284" height="179"/></p><p style="text-align: right;"><br/></p>
         * updateTime : 2019-03-29 16:33:22
         */

        private int id;
        private String aboutUs;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
