package com.pein.service.impl;

import com.pein.common.config.ConfigValue;
import com.pein.common.enums.ChannelCode;
import com.pein.common.exception.ExceptionCode;
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
        throw new Exception(ExceptionCode.UNSUPPORTED_CHANNEL_ERROR);
    }

    public String routeScanPayNofityUrl(ConfigValue configValue,String channelCode) throws Exception {

        if (channelCode.equals(ChannelCode.ALI_PAY.getCode())) {
            return configValue.getUrlNotifyAlibaba();
        }
        throw new Exception(ExceptionCode.UNSUPPORTED_CHANNEL_ERROR);
    }
}
