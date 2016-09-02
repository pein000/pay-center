package com.pein.scan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by qiuwei on 2016/8/8.
 */
@Component
@PropertySource("${spring.external.config}")
public class SystemConfig {

    /*支付宝地址*/
    @Value("${url.alibaba}")
    private String urlAlibaba;

    /*微信地址*/
    @Value("${url.wechat}")
    private String urlWechat;

    /*银联地址*/
    @Value("${url.union}")
    private String urlUnion;

    /*扫码支付调用地址*/
    @Value("${scan.pay}")
    private String scanPay;

    /*支付宝回调地址*/
    @Value("${url.notify.alibaba}")
    private String urlNotifyAlibaba;

    /*微信回调地址*/
    @Value("${url.notify.wechat}")
    private String urlNotifyWechat;

    /*银联回调地址*/
    @Value("${url.notify.union}")
    private String urlNotifyUnion;

    public String getUrlAlibaba() {
        return urlAlibaba;
    }

    public String getUrlWechat() {
        return urlWechat;
    }

    public String getUrlUnion() {
        return urlUnion;
    }

    public String getScanPay() {
        return scanPay;
    }

    public String getUrlNotifyAlibaba() {
        return urlNotifyAlibaba;
    }

    public String getUrlNotifyWechat() {
        return urlNotifyWechat;
    }

    public String getUrlNotifyUnion() {
        return urlNotifyUnion;
    }
}
