package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/3.
 */

public class AppShopCategoryqueryListBean {

    /**
     * status : 200
     * data : [{"id":1151,"categoryName":"测试分类","appCategoryPic":"upload/shopCategory/2019-12-27/8fb45e50ea25462a887e27d5678e8d9c.jpg","homeType":1,"productAttributeList":[]},{"id":1,"categoryName":"玩具","appCategoryPic":"upload/headPhoto/ceshitupian.jpg","homeType":0,"productAttributeList":[]},{"id":3,"categoryName":"体检卡","appCategoryPic":"upload/headPhoto/ceshitupian.jpg","homeType":0,"productAttributeList":[]},{"id":1153,"categoryName":"家电","appCategoryPic":"upload/shopCategory/2019-12-29/edaa05b2086e4e4d808d863b966992d6.png","homeType":0,"productAttributeList":[]}]
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
         * id : 1151
         * categoryName : 测试分类
         * appCategoryPic : upload/shopCategory/2019-12-27/8fb45e50ea25462a887e27d5678e8d9c.jpg
         * homeType : 1
         * productAttributeList : []
         */

        private int id;
        private String categoryName;
        private String appCategoryPic;
        private int homeType;
        private List<?> productAttributeList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String appCategoryPic) {
            this.appCategoryPic = appCategoryPic;
        }

        public int getHomeType() {
            return homeType;
        }

        public void setHomeType(int homeType) {
            this.homeType = homeType;
        }

        public List<?> getProductAttributeList() {
            return productAttributeList;
        }

        public void setProductAttributeList(List<?> productAttributeList) {
            this.productAttributeList = productAttributeList;
        }
    }
}
