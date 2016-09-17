package com.pein.common.enums;

/**
 * Created by pein on 2016/9/10.
 */
public enum SignType {
    RSA("RSA"),
    MD5("MD5");

    private String type;

    SignType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
