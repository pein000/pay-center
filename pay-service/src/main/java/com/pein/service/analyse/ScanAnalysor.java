package com.pein.service.analyse;

import com.pein.common.enums.CenterResponseCode;
import com.pein.common.enums.ExceptionCode;
import com.pein.common.response.CenterScanPayResponse;
import com.pein.common.response.PayResponse;

/**
 * Created by qiuwei on 2016/9/2.
 */
public class ScanAnalysor {

    public static CenterScanPayResponse analyse(PayResponse payResponse) {
        CenterScanPayResponse centerScanPayResponse = new CenterScanPayResponse();
        centerScanPayResponse.setSuccess(CenterResponseCode.SUCCESS.getCode());
        centerScanPayResponse.setData(payResponse.getUrl());
        return centerScanPayResponse;
    }

    public static CenterScanPayResponse analyse() {
        CenterScanPayResponse centerScanPayResponse = new CenterScanPayResponse();
        centerScanPayResponse.setSuccess(CenterResponseCode.FAIL.getCode());
        centerScanPayResponse.setErrorCode(ExceptionCode.INVOKE_CENTER_ERROR.getCode());
        centerScanPayResponse.setErrorMessage(ExceptionCode.INVOKE_CENTER_ERROR.getMessage());
        return centerScanPayResponse;
    }
}
