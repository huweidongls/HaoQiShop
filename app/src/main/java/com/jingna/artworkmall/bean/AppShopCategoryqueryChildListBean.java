package com.jingna.artworkmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/1/3.
 */

public class AppShopCategoryqueryChildListBean {

    /**
     * status : 200
     * data : {"Recommend":[],"Commonly":[{"id":2,"categoryName":"魔方","appCategoryPic":"app图片upload/headPhoto/ceshitupian.jpg","oftenType":0,"productAttributeList":[]}]}
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
        private List<?> Recommend;
        private List<CommonlyBean> Commonly;

        public List<?> getRecommend() {
            return Recommend;
        }

        public void setRecommend(List<?> Recommend) {
            this.Recommend = Recommend;
        }

        public List<CommonlyBean> getCommonly() {
            return Commonly;
        }

        public void setCommonly(List<CommonlyBean> Commonly) {
            this.Commonly = Commonly;
        }

        public static class CommonlyBean {
            /**
             * id : 2
             * categoryName : 魔方
             * appCategoryPic : app图片upload/headPhoto/ceshitupian.jpg
             * oftenType : 0
             * productAttributeList : []
             */

            private int id;
            private String categoryName;
            private String appCategoryPic;
            private int oftenType;
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

            public int getOftenType() {
                return oftenType;
            }

            public void setOftenType(int oftenType) {
                this.oftenType = oftenType;
            }

            public List<?> getProductAttributeList() {
                return productAttributeList;
            }

            public void setProductAttributeList(List<?> productAttributeList) {
                this.productAttributeList = productAttributeList;
            }
        }
    }
}
