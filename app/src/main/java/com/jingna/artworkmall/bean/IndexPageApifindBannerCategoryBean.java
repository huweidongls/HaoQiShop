package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/27.
 */

public class IndexPageApifindBannerCategoryBean {

    /**
     * status : 200
     * data : [{"appPic":"upload/sysBanner/2019-04-25/05f53dad01ba4e7489a7aec9e194439b.jpg","skipSite":"www.baidu.com"},{"appPic":"upload/sysBanner/2019-04-25/d38eb81bdc294a6a952d39a07a35e536.jpg","skipSite":"www.baidu.com"}]
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
         * appPic : upload/sysBanner/2019-04-25/05f53dad01ba4e7489a7aec9e194439b.jpg
         * skipSite : www.baidu.com
         */

        private String appPic;
        private String skipSite;

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public String getSkipSite() {
            return skipSite;
        }

        public void setSkipSite(String skipSite) {
            this.skipSite = skipSite;
        }
    }
}
