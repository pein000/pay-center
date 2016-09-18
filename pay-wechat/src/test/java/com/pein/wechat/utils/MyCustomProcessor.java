package com.pein.wechat.utils;

import br.com.six2six.fixturefactory.processor.Processor;
import com.pein.common.request.wechat.WechatScanPayRequest;

import java.util.HashMap;
import java.util.Map;

public class MyCustomProcessor implements Processor {

    public void execute(Object object) {
        //do something with the created object
        WechatScanPayRequest wechatScanPayRequest = (WechatScanPayRequest) object;
        Map<String, Object> map = new HashMap<>();
        map.put("appid", wechatScanPayRequest.getAppid());
        map.put("mch_id", wechatScanPayRequest.getMch_id());
        map.put("nonce_str", wechatScanPayRequest.getNonce_str());
        map.put("body", wechatScanPayRequest.getBody());
        map.put("out_trade_no", wechatScanPayRequest.getOut_trade_no());
        map.put("total_fee", String.valueOf(wechatScanPayRequest.getTotal_fee()));
        map.put("spbill_create_ip", wechatScanPayRequest.getSpbill_create_ip());
        map.put("notify_url", wechatScanPayRequest.getNotify_url());
        map.put("trade_type", wechatScanPayRequest.getTrade_type());
        wechatScanPayRequest.setSign(SignUtil.getSign(map,wechatScanPayRequest.getApiKey()));
    }
}