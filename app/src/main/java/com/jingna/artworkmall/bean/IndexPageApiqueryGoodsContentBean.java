package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/10.
 */

public class IndexPageApiqueryGoodsContentBean {

    /**
     * status : 200
     * data : [{"id":5,"contentImg":"upload/headPhoto/ceshitupian.jpg"},{"id":4,"contentImg":"upload/headPhoto/ceshitupian.jpg"},{"id":3,"contentImg":"upload/headPhoto/ceshitupian.jpg"}]
     * totalPage : 0
     * totalCount : 5
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
         * id : 5
         * contentImg : upload/headPhoto/ceshitupian.jpg
         */

        private int id;
        private String contentImg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContentImg() {
            return contentImg;
        }

        public void setContentImg(String contentImg) {
            this.contentImg = contentImg;
        }
    }
}
