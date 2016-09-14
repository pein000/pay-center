package com.pein.alibaba.utils2;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class AliAllPayRequest implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2813566858083082639L;

	private String proSubject;//商品主题
	private String proDesc; // 商品描述
	private String notifyUrl; // 用户的回调地址
	private String tradeType; // 交易类型
	private String createTime; // 订单创建时间
	private String expiryTime; // 订单失效时间
	private String appKey; // 维金系统分配给商户的key,用于获得商户在用户中心所提供的资料
	private String instOrderNo; // 商户系统内部订单号
    private String getTime; // 源调用时间
    private String getIp; // 源调用ip
    private String amount; // 金额分为单位
    private String channelCode; // 所调用的渠道代码

	/* 支付宝资源字段 */
	private String aliPayPid; // 支付宝合作者身份(PID)
	private String aliPayPriKey; // 支付宝RSA私钥
	private String aliPayPubKey; // 支付宝公钥
	private String aliPaySellerAccount;  // 支付宝卖家账户
	private String alipayCheckKey; // 安全校验码(Key)

	public String getAliPayPid() {
		return aliPayPid;
	}

	public void setAliPayPid(String aliPayPid) {
		this.aliPayPid = aliPayPid;
	}

	public String getAliPayPriKey() {
		return aliPayPriKey;
	}

	public void setAliPayPriKey(String aliPayPriKey) {
		this.aliPayPriKey = aliPayPriKey;
	}

	public String getAliPayPubKey() {
		return aliPayPubKey;
	}

	public void setAliPayPubKey(String aliPayPubKey) {
		this.aliPayPubKey = aliPayPubKey;
	}

	public String getAliPaySellerAccount() {
		return aliPaySellerAccount;
	}

	public void setAliPaySellerAccount(String aliPaySellerAccount) {
		this.aliPaySellerAccount = aliPaySellerAccount;
	}

	public String getAlipayCheckKey() {
		return alipayCheckKey;
	}

	public void setAlipayCheckKey(String alipayCheckKey) {
		this.alipayCheckKey = alipayCheckKey;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getInstOrderNo() {
		return instOrderNo;
	}

	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getGetIp() {
		return getIp;
	}

	public void setGetIp(String getIp) {
		this.getIp = getIp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String toString(){
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
