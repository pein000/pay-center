package com.pein.alibaba.analyse;

import com.pein.common.enums.ChannelCode;
import com.pein.common.request.alibaba.DirectPayRequest;
import com.pein.common.response.PayResponse;

/**
 * Created by qiuwei on 2016/9/1.
 */
public class DirectPayAnalysor {

    public static PayResponse analyse(DirectPayRequest directPayRequest, String response) {
        PayResponse payResponse = new PayResponse();
        payResponse.setAppKey(directPayRequest.getAppKey());
        payResponse.setOutTradeNo(directPayRequest.getOut_trade_no());
        payResponse.setUrl(response);
        payResponse.setChannelCode(ChannelCode.ALI_PAY.getCode());

        return payResponse;
    }

}
