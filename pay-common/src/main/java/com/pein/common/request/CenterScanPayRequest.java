package com.pein.common.request;

import com.pein.common.request.alibaba.AlibabaBaseRequest;

import java.io.Serializable;

/**
 * Created by qiuwei on 2016/9/1.
 */
public class CenterScanPayRequest extends AlibabaBaseRequest implements Serializable{

    private static final long serialVersionUID = -1312804679803906802L;

    private String appKey; //分配给商户的key,用于获得商户在用户中心所提供的资料
    private String proSubject;//商品主题
    private String proDesc; // 商品描述
    private String amount; // 金额 分为单位
    private String instOrderNo; // 商户系统内部订单号
    private String channelCode; // 所调用的渠道代码
    private String notifyUrl; // 用户的回调地址

    /*加密部分*/
    private String signCode; // 加签字符 默认RSA,使用appkey加签
    private String signType; // 加签类型，默认RSA

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getProSubject() {
        return proSubject;
    }

    public void setProSubject(String proSubject) {
        this.proSubject = proSubject;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    @Override
    public String toString() {
        return "CenterScanPayRequest{" +
                "appKey='" + appKey + '\'' +
                ", proSubject='" + proSubject + '\'' +
                ", proDesc='" + proDesc + '\'' +
                ", amount='" + amount + '\'' +
                ", instOrderNo='" + instOrderNo + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", signCode='" + signCode + '\'' +
                ", signType='" + signType + '\'' +
                '}';
    }
}
