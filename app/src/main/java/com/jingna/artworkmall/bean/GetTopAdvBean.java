package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class GetTopAdvBean {

    /**
     * data : [{"id":1,"title":"测试广告","image":"uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg"},{"id":2,"title":"测试广告","image":"uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg"},{"id":3,"title":"测试广告","image":"uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg"},{"id":4,"title":"测试广告","image":"uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg"},{"id":6,"title":"测试广告","image":"uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg"}]
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * title : 测试广告
         * image : uploads/20180531/2219890e722b6a8be4502306f032b3f1.jpg
         */

        private int id;
        private String title;
        private String image;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
