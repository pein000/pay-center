package com.pein.alibaba.controller;

import com.pein.alibaba.utils2.AliAllPayRequest;
import com.pein.alibaba.utils2.AlipaySubmitUtil;
import com.pein.alibaba.utils2.AllPayReqData;
import com.pein.alibaba.utils2.Configure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiuwei on 2016/9/14.
 */
@RestController
@RequestMapping
public class DirectPay2Controller {

    private static final Logger log = LoggerFactory.getLogger(DirectPay2Controller.class);

    /**
     * 统一下单
     *
     * @return
     */
    @RequestMapping("/all_pay")
    @ResponseBody
    public void allPay(AliAllPayRequest allPayRequest) {
        log.info("LOG01100:allPayRequest {}. ",allPayRequest);
        Configure.setAli_url("https://mapi.alipay.com/gateway.do?");
        Configure.setService_pay("create_direct_pay_by_user");
        Configure.setService_query("single_trade_query");
        Configure.setService_refund("refund_fastpay_by_platform_pwd");
        Configure.setService_refund_query("refund_fastpay_query");
        Configure.setPayment_type("1");
        Configure.setPaymethod("bankPay");
        Configure.setInputCharset("utf-8");
        Configure.setAliPaySellerAccount("2088021524218801");
        Configure.setSellerEmail("zhoue@163.com");
        Configure.setReturn_url("");
        Configure.setShow_url("");
        Configure.setNotify_url("http://func618.vfinance.cn/gateway-alipay/notify");
        Configure.setQr_pay_mode("0");
        Configure.setTRADE_BODY("this is a kusoe.");
        Configure.setTRADE_SUBJECT("kusoe");
        Configure.setPrivate_key_md5("uptwpc5z3i7alf0yy1ggqm1w676igdor");
        Configure.setMERCHANT_NO("2088021524218801");
        AllPayReqData aliPayReqData = new AllPayReqData(allPayRequest.getInstOrderNo(), allPayRequest.getAmount());
        String postUrl = Configure.getAli_url()+AlipaySubmitUtil.bulidFormMd5(aliPayReqData.toMap(), log, Configure.getPrivate_key_md5());

        log.info("统一下单返回结果:{}" + postUrl);
    }


    /**
     * 统一下单
     *
     * @return
     */
    @RequestMapping("/all_pay_RSA")
    @ResponseBody
    public void allPayRSA(AliAllPayRequest allPayRequest) {
        log.info("LOG01100:allPayRequest {}. ",allPayRequest);
        Configure.setAli_url("https://mapi.alipay.com/gateway.do?");
        Configure.setService_pay("create_direct_pay_by_user");
        Configure.setService_query("single_trade_query");
        Configure.setService_refund("refund_fastpay_by_platform_pwd");
        Configure.setService_refund_query("refund_fastpay_query");
        Configure.setPayment_type("1");
        Configure.setPaymethod("bankPay");
        Configure.setInputCharset("utf-8");
        Configure.setAliPaySellerAccount("2088021524218801");
        Configure.setSellerEmail("zhoue@163.com");
        Configure.setReturn_url("");
        Configure.setShow_url("");
        Configure.setNotify_url("http://func618.vfinance.cn/gateway-alipay/notify");
        Configure.setQr_pay_mode("0");
        Configure.setTRADE_BODY("this is a kusoe.");
        Configure.setTRADE_SUBJECT("kusoe");
        Configure.setPrivate_key_md5("uptwpc5z3i7alf0yy1ggqm1w676igdor");
        Configure.setAliKeyRSA("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALnG/xvMwRilW9J4PnW9S0hRABR+YmCLCQUq7NqQma79ECRBzDaeH1pekNployzFDSMqwGjUXA/GcBRdY1Q1/76GRapnnu0jWIw68/hdBiwe1yCYH7aG41YNnjj0AjJrSbOZdM4PKOzgv4yDkgLRv35C3c1mRYZHtY2pF0q9jippAgMBAAECgYEAsognf4rNAqdez6vYsoOAhnW5MvimCAt1Vq8CwaR23LC7w2wsGUzp6om4gPltz8dqsaITGGsEMfDMovWAUFw4cTgl0Oyi9N+MZbmb5pSe7OGZyXizE3OaqIzQynnPiaq96++21WIihQsmvRymK2vAtszXhgIQIHgJyxhWw6bqWAECQQDaxAbx0E6A0f8oCym5VTjZezVQW33yMP16Akmhf2ifiV3JznWnF2EJk5MO3JqEw45ZXbIuG3Xwww3OPgHgVIsBAkEA2WWcq5Z+/COq5Yx1yFYu3qg85/FOZswj2/4C4RjaBWgtRWfVabkogh5R6i6Qbpc2ER85cTdVPXCp5lgoHbQnaQJACRpVC6TWT2ftjRYB03NnXS1SR5i3Aefl9Di6JZ0ulkzZ+Ta9EInZgNOCMAEf8NxORQuwCVkdLDqBjY5o95OtAQJBANI5p7JqWyvaFfXayT9M0BAyooss10MjH34qeYBRNuvJnRWj3kVZbym8xjvugW7gPMF772FjP8OlQjbkgw6OWxkCQA5MBV351r2x6l7MOdRZ1FW1OmkJt743cX8P7je2T4wHvj8sBqxn17tEakkiHy0bvMmGUck4TDi4golh3+/GSN4=");
        Configure.setMERCHANT_NO("2088021524218801");
        AllPayReqData aliPayReqData = new AllPayReqData(allPayRequest.getInstOrderNo(), allPayRequest.getAmount());
        String postUrl = Configure.getAli_url()+AlipaySubmitUtil.buildFormRSA(aliPayReqData.toMap(), log, Configure.getAliKeyRSA());

        log.info("统一下单返回结果:{}" + postUrl);
    }
}
