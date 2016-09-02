package com.pein.common.enums;

/**
 * Created by qiuwei on 2016/7/25.
 */
public enum OperateStyle {
    CUSTOMER("0"),//用户发起
    SCHEDULE("1"),//定时任务发起
    CHANNEL("2"),//渠道异步发起
    MERCHANT("3");//商户发起

    private String value;

    OperateStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
