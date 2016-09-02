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

    /*支付宝回调地址*/
    private String urlNotifyAlibaba;

    /*微信回调地址*/
    private String urlNotifyWechat;

    /*银联回调地址*/
    private String urlNotifyUnion;

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

    public String getUrlNotifyAlibaba() {
        return urlNotifyAlibaba;
    }

    public void setUrlNotifyAlibaba(String urlNotifyAlibaba) {
        this.urlNotifyAlibaba = urlNotifyAlibaba;
    }

    public String getUrlNotifyWechat() {
        return urlNotifyWechat;
    }

    public void setUrlNotifyWechat(String urlNotifyWechat) {
        this.urlNotifyWechat = urlNotifyWechat;
    }

    public String getUrlNotifyUnion() {
        return urlNotifyUnion;
    }

    public void setUrlNotifyUnion(String urlNotifyUnion) {
        this.urlNotifyUnion = urlNotifyUnion;
    }

    @Override
    public String toString() {
        return "ConfigValue{" +
                "urlAlibaba='" + urlAlibaba + '\'' +
                ", urlWechat='" + urlWechat + '\'' +
                ", urlUnion='" + urlUnion + '\'' +
                ", scanPay='" + scanPay + '\'' +
                ", urlNotifyAlibaba='" + urlNotifyAlibaba + '\'' +
                ", urlNotifyWechat='" + urlNotifyWechat + '\'' +
                ", urlNotifyUnion='" + urlNotifyUnion + '\'' +
                '}';
    }
}
