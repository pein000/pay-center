package com.pein.common.config;

/**
 * Created by qiuwei on 2016/9/2.
 */
public class ConfigValue {
    /*支付宝地址*/
    private String urlAlibaba;

    /*微信地址*/
    private String urlWechat;

    /*银联地址*/
    private String urlUnion;

    /*扫码支付调用地址*/
    private String scanPay;

    /*渠道回调地址*/
    private String urlNotify;



    public String getUrlAlibaba() {
        return urlAlibaba;
    }

    public void setUrlAlibaba(String urlAlibaba) {
        this.urlAlibaba = urlAlibaba;
    }

    public String getUrlWechat() {
        return urlWechat;
    }

    public void setUrlWechat(String urlWechat) {
        this.urlWechat = urlWechat;
    }

    public String getUrlUnion() {
        return urlUnion;
    }

    public void setUrlUnion(String urlUnion) {
        this.urlUnion = urlUnion;
    }

    public String getScanPay() {
        return scanPay;
    }

    public void setScanPay(String scanPay) {
        this.scanPay = scanPay;
    }

    public String getUrlNotify() {
        return urlNotify;
    }

    public void setUrlNotify(String urlNotify) {
        this.urlNotify = urlNotify;
    }

    @Override
    public String toString() {
        return "ConfigValue{" +
                "urlAlibaba='" + urlAlibaba + '\'' +
                ", urlWechat='" + urlWechat + '\'' +
                ", urlUnion='" + urlUnion + '\'' +
                ", scanPay='" + scanPay + '\'' +
                ", urlNotify='" + urlNotify + '\'' +
                '}';
    }
}
