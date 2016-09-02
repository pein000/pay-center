package com.pein.common.enums;

/**
 * Created by qiuwei on 2016/9/1.
 */
public enum TradeStatus {
    WAITPAY("WAITPAY"),//等待支付
    PAYING("PAYING"),//渠道处理中
    CANCELED("CANCELED"),//已取消
    FAILED("FAILED"),//支付失败
    SUCCESS("SUCCESS");//支付成功

    private String status;

    TradeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
