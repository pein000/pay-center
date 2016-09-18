package com.pein.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by qiuwei on 2016/9/18.
 */
@Component
@PropertySource("${wechat.config}")
public class WechatConfig {

    /*统一下单接口*/
    @Value("${PAY_API}")
    private String payApi;

    /*扫码支付查询接口*/
    @Value("${PAY_QUERY_API}")
    private String payQueryApi;

    /*退款接口*/
    @Value("${REFUND_API}")
    private String refundApi;

    /*退款查询接口*/
    @Value("${REFUND_QUERY_API}")
    private String refundQueryApi;

    /*关闭订单接口*/
    @Value("${CLOSE_ORDER_API}")
    private String closeOrderApi;

    /*微信回调地址*/
    @Value("${NOTIFY_URL}")
    private String notifyURL;

    /*网关的接口地址*/
    @Value("${PAY_NOTIFY}")
    private String payNotify;

    /*网关查询查询商户信息接口*/
    @Value("${QUERY_MC_INFO}")
    private String queryMcInfo;

    public String getPayApi() {
        return payApi;
    }

    public String getPayQueryApi() {
        return payQueryApi;
    }

    public String getRefundApi() {
        return refundApi;
    }

    public String getRefundQueryApi() {
        return refundQueryApi;
    }

    public String getCloseOrderApi() {
        return closeOrderApi;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public String getPayNotify() {
        return payNotify;
    }

    public String getQueryMcInfo() {
        return queryMcInfo;
    }
}
