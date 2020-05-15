package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */

public class IndexPageApiqueryListBbsmBean {

    /**
     * status : 200
     * data : [{"id":1,"updateTime":"2019-04-26 10:39:34","usinghelp":"<p><img src=\"https://img.alicdn.com/imgextra/i1/746866993/O1CN01dH2QaD21WqA9YWdsF_!!746866993.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/746866993/O1CN0121Wq9omj1SmyWXJ_!!746866993.jpg\" class=\"img-ks-lazyload\"/><br/><\/p>"}]
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
         * updateTime : 2019-04-26 10:39:34
         * usinghelp : <p><img src="https://img.alicdn.com/imgextra/i1/746866993/O1CN01dH2QaD21WqA9YWdsF_!!746866993.jpg" class="img-ks-lazyload"/><img src="https://img.alicdn.com/imgextra/i3/746866993/O1CN0121Wq9omj1SmyWXJ_!!746866993.jpg" class="img-ks-lazyload"/><br/></p>
         */

        private int id;
        private String updateTime;
        private String usinghelp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUsinghelp() {
            return usinghelp;
        }

        public void setUsinghelp(String usinghelp) {
            this.usinghelp = usinghelp;
        }
    }
}
