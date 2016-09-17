package com.pein.common.enums;

/**
 * Created by qiuwei on 2016/9/2.
 */
public enum ExceptionCode {

    UNSUPPORTED_CHANNEL_ERROR("01","不支持的渠道异常"), //不支持的渠道异常
    INVOKE_CHANNEL_ERROR("02","渠道调用异常"),//渠道调用异常
    TRADE_SUCCESS_ERROR("03","此订单已支付成功，勿重复支付"),//此订单已支付成功,勿重复支付
    TRADE_PAYING_ERROR("04","此订单正在交易，勿重复提交"),//此订单正在交易，勿重复提交
    NOT_EXISTS_SCENE("05","交易场景超出预期，无法处理"),//此订单正在交易，勿重复提交
    INVOKE_CENTER_ERROR("06","支付中心调用异常"),//渠道调用异常
    ;
    private String code;

    private String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
