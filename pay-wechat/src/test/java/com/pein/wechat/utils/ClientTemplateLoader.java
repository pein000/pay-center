package com.pein.wechat.utils;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.pein.common.request.wechat.WechatScanPayRequest;

import java.util.UUID;

public class ClientTemplateLoader implements TemplateLoader {

    public final static String TEMPLATE = "valid";

    @Override
    public void load() {
        Fixture.of(WechatScanPayRequest.class).addTemplate(TEMPLATE, new Rule(){{
            add("appid", "wx678ad9de0bf9d684");
            add("mch_id", "1370335602");
            add("nonce_str", UUID.randomUUID().toString().replace("-",""));
            add("body", regex("\\w{8}"));
            add("body", regex("\\w{8}"));
            add("out_trade_no", regex("\\d{8}"));
            add("total_fee", random(Integer.class,range(1,10000)));
            add("spbill_create_ip", "127.0.0.1");
            add("notify_url", regex("\\w{8}"));
            add("trade_type", "NATIVE");
            add("certPath", "/opt/pay/bankcer/wechat/apiclient_cert.p12");
            add("certPassword", "1370335602");
            add("apiKey", "1cd134c747c4c4f6981f82bae186aea3");
        }});
    }

}