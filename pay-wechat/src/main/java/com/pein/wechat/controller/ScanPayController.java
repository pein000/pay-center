package com.pein.wechat.controller;

import com.pein.common.request.wechat.WechatScanPayRequest;
import com.pein.wechat.check.CheckComponent;
import com.pein.wechat.config.WechatConfig;
import com.pein.wechat.httpClient.HttpsRequest;
import com.pein.wechat.request.ScanPayRequest;
import com.pein.wechat.utils.XmlProcessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pein on 2016/9/18.
 */
@RestController
@RequestMapping
public class ScanPayController {

    private static final Logger log = LoggerFactory.getLogger(ScanPayController.class);

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 统一下单
     */
    @RequestMapping("unified_pay")
    public void unifiedPay(@RequestBody WechatScanPayRequest wechatScanPayRequest) throws Exception {
        /* 初始化证书，发送报文 */
        HttpsRequest.init(false, wechatScanPayRequest.getCertPath(), wechatScanPayRequest.getCertPassword());
        ScanPayRequest request = new ScanPayRequest();
        BeanUtils.copyProperties(wechatScanPayRequest, request);
        String unifiedResponse = HttpsRequest.sendPost(wechatConfig.getPayApi(), request);

        /* 验签并接受报文 */
        XmlProcessUtil rsMap_All = new XmlProcessUtil(unifiedResponse);
        boolean checkSuccess = CheckComponent.checkIsSignValidFromResponseString(rsMap_All.getElementToMap(), wechatScanPayRequest.getApiKey());
        if (!checkSuccess) {
            log.error("LOG01220: 验签失败");
        }

    }
}
