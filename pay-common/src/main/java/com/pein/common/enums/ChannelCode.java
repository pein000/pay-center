package com.pein.common.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by qiuwei on 2016/9/1.
 */
public enum ChannelCode {
    ALI_PAY("0","ALI_PAY"), //支付宝
    ALI_APP_PAY("1","ALI_PAY"), //支付宝APP支付
    WECHAT_PAY("2","WECHAT_PAY"),//微信
    WECHAT_APP_PAY("3","WECHAT_PAY"),//微信APP支付
    UNION_PAY("4","UNION_PAY");//银联

    private String code;

    private String value;

    ChannelCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String matchCode(final String code) {
       return Arrays.asList(ChannelCode.values())
               .stream()
               .filter((filter) -> filter.getCode().equals(code))
               .map((map) -> map.getCode())
               .collect(Collectors.toList())
               .get(0);
    }
}
