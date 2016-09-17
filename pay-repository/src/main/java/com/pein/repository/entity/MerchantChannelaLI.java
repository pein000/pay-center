package com.pein.repository.entity;

/**
 * Created by pein on 2016/9/10.
 */
public class MerchantChannelAli {

    private String appKey;

    private String pId;

    private String code;

    private String md5;

    private String rsaPub;

    private String rsaPri;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getRsaPub() {
        return rsaPub;
    }

    public void setRsaPub(String rsaPub) {
        this.rsaPub = rsaPub;
    }

    public String getRsaPri() {
        return rsaPri;
    }

    public void setRsaPri(String rsaPri) {
        this.rsaPri = rsaPri;
    }
}
