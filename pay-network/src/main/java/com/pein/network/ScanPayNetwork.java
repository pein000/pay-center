package com.pein.network;

import com.pein.common.request.alibaba.DirectPayRequest;
import com.pein.common.response.PayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuwei on 2016/9/1.
 */
@Component
public class ScanPayNetwork {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanPayNetwork.class);

    @Autowired
    private RestTemplate restTemplate;

    public PayResponse alibabaPay(String url, DirectPayRequest directPayRequest) {
        LOGGER.info("LOG01030: begin to rest alibaba channel. url={},directPayRequest={}.", url, directPayRequest);
        PayResponse payResponse = restTemplate.postForObject(url, directPayRequest, PayResponse.class);
        LOGGER.info("LOG01050: success to rest alibaba channel. payResponse : {}", payResponse);
        return payResponse;
    }

    public PayResponse wechatPay(String url, DirectPayRequest directPayRequest) {
        LOGGER.info("LOG01030: begin to rest wechat channel. url={},directPayRequest={}.", url, directPayRequest);
        PayResponse payResponse = restTemplate.postForObject(url, directPayRequest, PayResponse.class);
        LOGGER.info("LOG01050: success to rest wechat channel. payResponse : {}", payResponse);
        return payResponse;
    }

    public PayResponse unionPay(String url, DirectPayRequest directPayRequest) {
        LOGGER.info("LOG01030: begin to rest union channel. url={},directPayRequest={}.", url, directPayRequest);
        PayResponse payResponse = restTemplate.postForObject(url, directPayRequest, PayResponse.class);
        LOGGER.info("LOG01050: success to rest union channel. payResponse : {}", payResponse);
        return payResponse;
    }
}
