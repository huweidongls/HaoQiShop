package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/29.
 */

public class AppMemberSignqueryListBean {

    /**
     * status : 200
     * data : [{"signYearMonth":"21","signDay":"2019-12","memberUserId":1},{"signYearMonth":"20","signDay":"2019-12","memberUserId":1}]
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
         * signYearMonth : 21
         * signDay : 2019-12
         * memberUserId : 1
         */

        private String signYearMonth;
        private String signDay;
        private int memberUserId;

        public String getSignYearMonth() {
            return signYearMonth;
        }

        public void setSignYearMonth(String signYearMonth) {
            this.signYearMonth = signYearMonth;
        }

        public String getSignDay() {
            return signDay;
        }

        public void setSignDay(String signDay) {
            this.signDay = signDay;
        }

        public int getMemberUserId() {
            return memberUserId;
        }

        public void setMemberUserId(int memberUserId) {
            this.memberUserId = memberUserId;
        }
    }
}
