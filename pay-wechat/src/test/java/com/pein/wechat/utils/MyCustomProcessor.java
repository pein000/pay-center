package com.pein.wechat.utils;

import br.com.six2six.fixturefactory.processor.Processor;
import com.pein.common.request.wechat.WechatScanPayRequest;

import java.util.HashMap;

public class MyCustomProcessor implements Processor {

    public void execute(Object object) {
        //do something with the created object
        WechatScanPayRequest wechatScanPayRequest = (WechatScanPayRequest) object;
        wechatScanPayRequest.setSign(SignUtil.getSign(new HashMap<>(),wechatScanPayRequest.getApiKey()));
    }
}