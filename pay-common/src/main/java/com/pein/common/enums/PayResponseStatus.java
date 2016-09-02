package com.pein.common.enums;

/**
 * Created by qiuwei on 2016/8/31.
 */
public enum PayResponseStatus {
    SUCCESS("success"),//成功
    FAIL("fail");//失败

    private String status;

    PayResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
