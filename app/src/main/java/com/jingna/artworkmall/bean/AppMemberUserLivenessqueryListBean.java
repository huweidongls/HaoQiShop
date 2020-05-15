package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/15.
 */

public class AppMemberUserLivenessqueryListBean {

    /**
     * status : 200
     * data : [{"id":301,"memberId":130,"createTime":"2020-01-15 14:39:05","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"riHuoSign":0,"type":1},{"id":299,"memberId":130,"createTime":"2020-01-15 14:23:19","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"riHuoSign":0,"type":1},{"id":298,"memberId":130,"createTime":"2020-01-15 14:22:52","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"riHuoSign":0,"type":1},{"id":297,"memberId":130,"createTime":"2020-01-15 14:22:44","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"riHuoSign":0,"type":1},{"id":296,"memberId":130,"createTime":"2020-01-15 14:12:43","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"type":1},{"id":295,"memberId":130,"createTime":"2020-01-15 14:12:41","riHuoRead":0,"riHuoTui":1,"riHuoTuiPerformance":0,"riHuoPerformance":0,"type":1},{"id":240,"memberId":130,"createTime":"2020-01-13 16:46:07","riHuoRead":0,"riHuoTui":10,"riHuoTuiPerformance":0,"riHuoPerformance":0,"type":1},{"id":239,"memberId":130,"createTime":"2020-01-13 16:42:10","riHuoRead":0,"riHuoTui":10,"riHuoTuiPerformance":0,"riHuoPerformance":0,"type":1},{"id":232,"memberId":130,"createTime":"2020-01-13 15:25:31","riHuoRead":0,"riHuoTui":10,"riHuoTuiPerformance":0,"riHuoPerformance":0,"type":1}]
     * totalPage : 1
     * totalCount : 9
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
         * id : 301
         * memberId : 130
         * createTime : 2020-01-15 14:39:05
         * riHuoRead : 0
         * riHuoTui : 1
         * riHuoTuiPerformance : 0
         * riHuoPerformance : 0
         * riHuoSign : 0
         * type : 1
         */

        private int id;
        private int memberId;
        private String createTime;
        private int riHuoRead;
        private int riHuoTui;
        private int riHuoTuiPerformance;
        private int riHuoPerformance;
        private int riHuoSign;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getRiHuoRead() {
            return riHuoRead;
        }

        public void setRiHuoRead(int riHuoRead) {
            this.riHuoRead = riHuoRead;
        }

        public int getRiHuoTui() {
            return riHuoTui;
        }

        public void setRiHuoTui(int riHuoTui) {
            this.riHuoTui = riHuoTui;
        }

        public int getRiHuoTuiPerformance() {
            return riHuoTuiPerformance;
        }

        public void setRiHuoTuiPerformance(int riHuoTuiPerformance) {
            this.riHuoTuiPerformance = riHuoTuiPerformance;
        }

        public int getRiHuoPerformance() {
            return riHuoPerformance;
        }

        public void setRiHuoPerformance(int riHuoPerformance) {
            this.riHuoPerformance = riHuoPerformance;
        }

        public int getRiHuoSign() {
            return riHuoSign;
        }

        public void setRiHuoSign(int riHuoSign) {
            this.riHuoSign = riHuoSign;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
