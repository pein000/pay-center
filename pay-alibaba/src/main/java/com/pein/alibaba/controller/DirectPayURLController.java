package com.pein.alibaba.controller;

import com.pein.alibaba.analyse.DirectPayAnalysor;
import com.pein.alibaba.assemble.PayAssemble;
import com.pein.alibaba.utils.AlipaySubmitUtil;
import com.pein.common.request.alibaba.DirectPayRequest;
import com.pein.common.response.PayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pein on 2016/9/12.
 */
@RestController
@RequestMapping
public class DirectPayURLController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectPayURLController.class);

    @Autowired
    private PayAssemble payAssembor;

    @Value("{open_api_domain}")
    private String openApiDomain;

    @RequestMapping("scan_pay")
    public PayResponse pay(@RequestBody DirectPayRequest directPayRequest) {
        LOGGER.info("LOG_QW00080: direct_pay_url : directPayRequest = {}. ", directPayRequest);
        //建立请求
//        String response = new StringBuilder("https://openapi.alipay.com/gateway.do").append("?").append(AlipaySubmitUtil.buildFormRSA(payAssembor.assemble(directPayRequest))).toString();
        String response = new StringBuilder("https://mapi.alipay.com/gateway.do").append("?").append(AlipaySubmitUtil.bulidFormMd5(payAssembor.assemble(directPayRequest))).toString();
        LOGGER.info("LOG_QW00100: direct_pay_url : response : {}.", response);
        return DirectPayAnalysor.analyse(directPayRequest, response);
    }
}
