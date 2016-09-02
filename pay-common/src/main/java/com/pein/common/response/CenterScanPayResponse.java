package com.pein.common.response;

import java.io.Serializable;

/**
 * Created by qiuwei on 2016/9/2.
 */
public class CenterScanPayResponse implements Serializable{

    private static final long serialVersionUID = 8546330700727127044L;

    private String success;//调用渠道是否成功 success 、fail

    private String error;//错误信息

    private String data;//通过渠道加密验证之后的扫码url

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CenterScanPayResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
