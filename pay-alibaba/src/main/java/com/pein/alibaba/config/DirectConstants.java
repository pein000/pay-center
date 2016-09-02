package com.pein.alibaba.config;

/**
 * Created by qiuwei on 2016/8/31.
 */
public class DirectConstants {

    /*即时到账接口*/
    public final static String DIRECT_PAY_SERVICE = "create_direct_pay_by_user";
    /*即时到账 扫码支付方式*/
    /* 0：订单码-简约前置模式，对应iframe宽度不能小于600px，高度不能小于300px*/
    public final static String DIRECT_PAY_MODE_0 = "0";
    /*1：订单码-前置模式，对应iframe宽度不能小于300px，高度不能小于600px；*/
    public final static String DIRECT_PAY_MODE_1 = "1";
    /*2：订单码-跳转模式 用户的扫码界面是由支付宝生成的，不在商户的域名下。*/
    public final static String DIRECT_PAY_MODE_2 = "2";
    /*3：订单码-迷你前置模式，对应iframe宽度不能小于75px，高度不能小于75px。*/
    public final static String DIRECT_PAY_MODE_3 = "3";
    /*4：订单码-可定义宽度的嵌入式二维码，商户可根据需要设定二维码的大小。*/
    public final static String DIRECT_PAY_MODE_4 = "4";
    /*utf8编码*/
    public final static String CHASET_UTF8 = "utf-8";




}
