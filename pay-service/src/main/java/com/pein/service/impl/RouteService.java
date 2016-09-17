package com.pein.service.impl;

import com.pein.common.config.ConfigValue;
import com.pein.common.enums.ChannelCode;
import com.pein.common.enums.ExceptionCode;
import org.springframework.stereotype.Service;

/**
 * Created by qiuwei on 2016/9/2.
 */
@Service
public class RouteService {

    public String routeScanPayUrl(ConfigValue configValue,String channelCode) throws Exception {
        if (channelCode.equals(ChannelCode.ALI_PAY.getCode())) {
            return new StringBuilder(configValue.getUrlAlibaba()).append("/").append(configValue.getScanPay()).toString();
        }
        throw new Exception(ExceptionCode.UNSUPPORTED_CHANNEL_ERROR.getCode());
    }

    public String routeScanPayNofityUrl(ConfigValue configValue,String channelCode) throws Exception {
        if (channelCode.equals(ChannelCode.ALI_PAY.getCode())) {
            return new StringBuilder(configValue.getUrlAlibaba()).append("/").append(configValue.getUrlNotify()).toString();
        }else if(channelCode.equals(ChannelCode.WECHAT_PAY.getCode())){
            return new StringBuilder(configValue.getUrlWechat()).append("/").append(configValue.getUrlNotify()).toString();
        }else if(channelCode.equals(ChannelCode.UNION_PAY.getCode())){
            return new StringBuilder(configValue.getUrlUnion()).append("/").append(configValue.getUrlNotify()).toString();
        }
        throw new Exception(ExceptionCode.UNSUPPORTED_CHANNEL_ERROR.getCode());
    }
}
