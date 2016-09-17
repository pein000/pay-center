package com.pein.service.impl;

import com.pein.common.config.ConfigValue;
import com.pein.common.enums.ChannelCode;
import com.pein.common.enums.ExceptionCode;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.response.PayResponse;
import com.pein.network.ScanPayNetwork;
import com.pein.service.build.ScanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiuwei on 2016/9/2.
 */
@Service
public class NetworkService {

    @Autowired
    private ScanPayNetwork scanPayNetwork;

    @Autowired
    private RouteService routeService;

    public PayResponse scanPay(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) throws Exception {
        String url = routeService.routeScanPayUrl(configValue, centerScanPayRequest.getChannelCode());
        String notifyUrl = routeService.routeScanPayNofityUrl(configValue, centerScanPayRequest.getChannelCode());
        if(ChannelCode.ALI_PAY.getCode().equals(centerScanPayRequest.getChannelCode())){
            return scanPayNetwork.alibabaPay(url, ScanBuilder.buildScanPayDirectPayRequest(centerScanPayRequest,notifyUrl));
        }else if (ChannelCode.WECHAT_PAY.getCode().equals(centerScanPayRequest.getChannelCode())){
            return scanPayNetwork.wechatPay(url, ScanBuilder.buildScanPayDirectPayRequest(centerScanPayRequest,notifyUrl));
        }else if (ChannelCode.UNION_PAY.getCode().equals(centerScanPayRequest.getChannelCode())){
            return scanPayNetwork.wechatPay(url, ScanBuilder.buildScanPayDirectPayRequest(centerScanPayRequest,notifyUrl));
        }else {
            throw new Exception(ExceptionCode.UNSUPPORTED_CHANNEL_ERROR.getCode());
        }
    }

}
