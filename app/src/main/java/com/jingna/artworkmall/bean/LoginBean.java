package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2019/12/23.
 */

public class LoginBean {
    /**
     * status : 200
     * data : {"userId":1,"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTA3OTc2MzQxMTYsImV4cCI6MTU1MTQwMjQzNH0.N_zlb3KTnpOeANSTtK2wNxMCNivceKKlsyn-n_bl3RKdXSZo16plOYYJ7furUg20OaCHX6Dwxc5xPXzmvCBTAg"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTA3OTc2MzQxMTYsImV4cCI6MTU1MTQwMjQzNH0.N_zlb3KTnpOeANSTtK2wNxMCNivceKKlsyn-n_bl3RKdXSZo16plOYYJ7furUg20OaCHX6Dwxc5xPXzmvCBTAg
         */

        private int userId;
        private String token;
        private String loginUrl;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLoginUrl() {
            return loginUrl;
        }

        public void setLoginUrl(String loginUrl) {
            this.loginUrl = loginUrl;
        }
    }
}
