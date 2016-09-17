package com.pein.common.response;

import com.pein.common.enums.CenterResponseCode;
import com.pein.common.enums.ExceptionCode;

import java.io.Serializable;

/**
 * Created by qiuwei on 2016/9/2.
 */
public class CenterScanPayResponse implements Serializable{

    private static final long serialVersionUID = 8546330700727127044L;

    private String success;//调用渠道是否成功 success 、fail

    private String errorCode;//错误码

    private String errorMessage;//错误信息

    private String data;//通过渠道加密验证之后的扫码url

    public CenterScanPayResponse() {
    }

    public CenterScanPayResponse(CenterResponseCode success) {
        this.success = success.getCode();
    }

    public CenterScanPayResponse(CenterResponseCode fail,ExceptionCode exceptionCode) {
        this.success = fail.getCode();
        this.errorCode = exceptionCode.getCode();
        this.errorMessage = exceptionCode.getMessage();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
                "success='" + success + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
