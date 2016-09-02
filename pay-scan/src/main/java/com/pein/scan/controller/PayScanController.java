package com.pein.scan.controller;


import com.pein.common.config.ConfigValue;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.response.CenterScanPayResponse;
import com.pein.scan.config.SystemConfig;
import com.pein.service.impl.ScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiuwei on 2016/8/31.
 */
@RestController
@RequestMapping
public class PayScanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayScanController.class);
    @Autowired
    private ScanService scanService;

    @Autowired
    private SystemConfig systemConfig;

    @RequestMapping("pay_scan")
    public CenterScanPayResponse pay(CenterScanPayRequest centerScanPayRequest){
        LOGGER.info("LOG01070: begin to pay san. centerScanPayRequest : {}.", centerScanPayRequest);
        ConfigValue configValue = new ConfigValue();
        BeanUtils.copyProperties(systemConfig, configValue);
        CenterScanPayResponse centerScanPayResponse = scanService.pay(centerScanPayRequest,configValue);
        LOGGER.info("LOG01090: end to pay scan. centerScanPayResponse : {}.",centerScanPayResponse);
        return centerScanPayResponse;
    }
}
