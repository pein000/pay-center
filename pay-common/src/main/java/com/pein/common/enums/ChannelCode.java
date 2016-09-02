package com.pein.common.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by qiuwei on 2016/9/1.
 */
public enum ChannelCode {
    ALI_PAY("0","ALI_PAY"), //支付宝
    WECHAT_PAY("1","WECHAT_PAY"),//微信
    UNION_PAY("2","UNION_PAY");//银联

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

    public static String matchValue(final String code) {
       return Arrays.asList(ChannelCode.values())
               .stream()
               .filter((filter) -> filter.getCode().equals(code))
               .map((map) -> map.getValue())
               .collect(Collectors.toList())
               .get(0);
    }
}
