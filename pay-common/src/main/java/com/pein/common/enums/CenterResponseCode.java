package com.pein.common.enums;

/**
 * Created by qiuwei on 2016/8/31.
 */
public enum CenterResponseCode {
    SUCCESS("success"),//成功
    FAIL("fail");//失败

    private String code;

    CenterResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
