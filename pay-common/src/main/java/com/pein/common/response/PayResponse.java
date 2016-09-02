package com.pein.common.response;

import java.io.Serializable;

/**
 * Created by qiuwei on 2016/8/31.
 */
public class PayResponse implements Serializable{

    private static final long serialVersionUID = -6654009290417722069L;

    private String appKey;

    private String outTradeNo;

    private String tradeNo;

    private String channelCode;

    private String tradeStatus; //交易状态 成功success、失败fail

    private String url;

    public PayResponse() {
    }

    public PayResponse(String outTradeNo, String tradeNo, String tradeStatus) {
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
        this.tradeStatus = tradeStatus;
    }

    public PayResponse(String appKey, String outTradeNo, String tradeNo, String channelCode, String tradeStatus, String url) {
        this.appKey = appKey;
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
        this.channelCode = channelCode;
        this.tradeStatus = tradeStatus;
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PayResponse{" +
                "appKey='" + appKey + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
