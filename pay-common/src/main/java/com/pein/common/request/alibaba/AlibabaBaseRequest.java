package com.pein.common.request.alibaba;

/**
 * Created by pein on 2016/9/10.
 */
public class AlibabaBaseRequest {
    /* 支付宝资源字段 */
    private String pId;

    private String md5;

    private String priKey;

    private String pubKey;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }
}
