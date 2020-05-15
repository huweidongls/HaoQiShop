package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2019/12/26.
 */

public class AliBean {

    /**
     * status : 200
     * data : {"data":"alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019032963745653&biz_content=%7B%22body%22%3A%22%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E%E6%94%AF%E4%BB%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221577346340534%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B9%B3%E5%8F%B0%E5%B8%81%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Flijunjie.5ijiaoyu.cn%2FZhiFuBao%2Fpay&sign=CdcNPGp4Yj57XuatWRlnf%2BO3YDtX5p50zZQST5ZJw8u4F%2F3JBs40nYhI%2FFkLmg80OeaPtkrlmREQb7eadh5FvdIsju26xWHGCLoN8TC%2BpgBww%2Fjz4WRZ1dN7mueJ%2BNGpAztfKojYBuYMUj6QfM5HAftJn84SbzDkswG75c%2F%2FYEy14P98sgvuKq0MHJ66RaA9rsuddvtRSDrpxZMqbPdZr7%2FRoD%2FY%2BkElMPlBIymWMIqd3QGc1NYY39cEYMAdxht7izkCaaPZclDYog2nJISA9euW5hOvHfaRGiEehO%2BZGpLfspyB43ZTTiiwguuxIaEI7lAEcx9LNlwSmMHpuhcrMg%3D%3D&sign_type=RSA2&timestamp=2019-12-26+15%3A45%3A36&version=1.0","status":0}
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
         * data : alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019032963745653&biz_content=%7B%22body%22%3A%22%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E%E6%94%AF%E4%BB%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221577346340534%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B9%B3%E5%8F%B0%E5%B8%81%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Flijunjie.5ijiaoyu.cn%2FZhiFuBao%2Fpay&sign=CdcNPGp4Yj57XuatWRlnf%2BO3YDtX5p50zZQST5ZJw8u4F%2F3JBs40nYhI%2FFkLmg80OeaPtkrlmREQb7eadh5FvdIsju26xWHGCLoN8TC%2BpgBww%2Fjz4WRZ1dN7mueJ%2BNGpAztfKojYBuYMUj6QfM5HAftJn84SbzDkswG75c%2F%2FYEy14P98sgvuKq0MHJ66RaA9rsuddvtRSDrpxZMqbPdZr7%2FRoD%2FY%2BkElMPlBIymWMIqd3QGc1NYY39cEYMAdxht7izkCaaPZclDYog2nJISA9euW5hOvHfaRGiEehO%2BZGpLfspyB43ZTTiiwguuxIaEI7lAEcx9LNlwSmMHpuhcrMg%3D%3D&sign_type=RSA2&timestamp=2019-12-26+15%3A45%3A36&version=1.0
         * status : 0
         */

        private String data;
        private int status;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
