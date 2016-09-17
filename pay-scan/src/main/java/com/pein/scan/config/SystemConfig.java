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

    /*渠道回调地址*/
    @Value("${url.notify}")
    private String urlNotify;

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

    public String getUrlNotify() {
        return urlNotify;
    }
}
