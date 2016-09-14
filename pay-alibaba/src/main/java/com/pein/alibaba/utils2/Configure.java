package com.pein.alibaba.utils2;

/**
 * 这里放置各种配置数据
 *
 * @author lujiahua_yf
 */
public class Configure {

    public static String aliPaySellerAccount;  // 支付宝卖家账户
    public static String payment_type; // 支付类型
    public static String paymethod; // 支付模式
    public static String inputCharset; // 输入字符格式
    public static String sellerEmail; // email
    public static String return_url; // 返回地址
    public static String show_url; // 显示地址
    public static String notify_url; //异步通知
    public static String qr_pay_mode; // 扫码的模式
    public static String TRADE_SUBJECT; // 交易主题
    public static String TRADE_BODY; // 交易主题
    public static String private_key_md5; // md5
    public static String MERCHANT_NO; // 商户Id
    public static String aliKeyRSA; // aliRSA


    /* 接口名称 */
    public static String service_pay; // 下单
    public static String service_query; // 交易查询
    public static String service_refund; // 退款
    public static String service_refund_query; // 退款查询、
    public static String ali_url; // 请求的url

    // 状态码：
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String PROCESSED = "PROCESSED";

    public static final String SUCCESS_COED = "0000";
    public static final String FAIL_CODE = "0002";
    public static final String PROCESS_CODE = "0003";

    //支付宝返回数据KEY 系统关心的
    public static final String SERVICE = "service";
    public static final String VERSION = "v";
    public static final String SIGN = "sign";
    public static final String SEC_ID = "sec_id";
    public static final String NOTIFY_DATA = "notify_data";

    //支付宝返回业务数据KEY 系统关心的
    public static final String NOTIFY_ID = "notify_id";
    public static final String TRADE_NO = "trade_no";
    public static final String OUT_TRADE_NO = "out_trade_no";
    public static final String TOTAL_FEE = "total_fee";
    public static final String PARTNER = "seller_id";
    public static final String TRADE_STATUS = "trade_status";
    public static final String NOTIFY = "//notify/";

    public static String getShow_url() {
        return show_url;
    }

    public static void setShow_url(String show_url) {
        Configure.show_url = show_url;
    }

    public static String getSellerEmail() {
        return sellerEmail;
    }

    public static void setSellerEmail(String sellerEmail) {
        Configure.sellerEmail = sellerEmail;
    }

    public static String getReturn_url() {
        return return_url;
    }

    public static void setReturn_url(String return_url) {
        Configure.return_url = return_url;
    }

    public static String getInputCharset() {
        return inputCharset;
    }

    public static void setInputCharset(String inputCharset) {
        Configure.inputCharset = inputCharset;
    }

    public static String getAliPaySellerAccount() {
        return aliPaySellerAccount;
    }

    public static void setAliPaySellerAccount(String aliPaySellerAccount) {
        Configure.aliPaySellerAccount = aliPaySellerAccount;
    }

    public static String getPayment_type() {
        return payment_type;
    }

    public static void setPayment_type(String payment_type) {
        Configure.payment_type = payment_type;
    }

    public static String getPaymethod() {
        return paymethod;
    }

    public static void setPaymethod(String paymethod) {
        Configure.paymethod = paymethod;
    }

    public static String getService_pay() {
        return service_pay;
    }

    public static void setService_pay(String service_pay) {
        Configure.service_pay = service_pay;
    }

    public static String getService_query() {
        return service_query;
    }

    public static void setService_query(String service_query) {
        Configure.service_query = service_query;
    }

    public static String getService_refund() {
        return service_refund;
    }

    public static void setService_refund(String service_refund) {
        Configure.service_refund = service_refund;
    }

    public static String getService_refund_query() {
        return service_refund_query;
    }

    public static void setService_refund_query(String service_refund_query) {
        Configure.service_refund_query = service_refund_query;
    }

    public static String getAli_url() {
        return ali_url;
    }

    public static void setAli_url(String ali_url) {
        Configure.ali_url = ali_url;
    }

    public static String getNotify_url() {
        return notify_url;
    }

    public static void setNotify_url(String notify_url) {
        Configure.notify_url = notify_url;
    }

    public static String getQr_pay_mode() {
        return qr_pay_mode;
    }

    public static void setQr_pay_mode(String qr_pay_mode) {
        Configure.qr_pay_mode = qr_pay_mode;
    }

    public static String getTRADE_SUBJECT() {
        return TRADE_SUBJECT;
    }

    public static void setTRADE_SUBJECT(String tRADE_SUBJECT) {
        TRADE_SUBJECT = tRADE_SUBJECT;
    }

    public static String getTRADE_BODY() {
        return TRADE_BODY;
    }

    public static void setTRADE_BODY(String tRADE_BODY) {
        TRADE_BODY = tRADE_BODY;
    }

    public static String getPrivate_key_md5() {
        return private_key_md5;
    }

    public static void setPrivate_key_md5(String private_key_md5) {
        Configure.private_key_md5 = private_key_md5;
    }

    public static String getMERCHANT_NO() {
        return MERCHANT_NO;
    }

    public static void setMERCHANT_NO(String mERCHANT_NO) {
        MERCHANT_NO = mERCHANT_NO;
    }

    public static String getAliKeyRSA() {
        return aliKeyRSA;
    }

    public static void setAliKeyRSA(String aliKeyRSA) {
        Configure.aliKeyRSA = aliKeyRSA;
    }

}
