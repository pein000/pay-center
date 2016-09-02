package com.pein.service.impl;

import com.pein.common.config.ConfigValue;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.response.CenterScanPayResponse;
import com.pein.common.response.PayResponse;
import com.pein.repository.entity.TraderFlow;
import com.pein.repository.persistence.OperateFlowMapper;
import com.pein.repository.persistence.TraderFlowMapper;
import com.pein.service.analyse.ScanAnalysor;
import com.pein.service.build.ScanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qiuwei on 2016/9/1.
 */
@Service
public class ScanService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ScanService.class);

    @Autowired
    private TraderFlowMapper traderFlowMapper;

    @Autowired
    private OperateFlowMapper operateFlowMapper;

    @Autowired
    private NetworkService networkService;

    @Transactional
    public CenterScanPayResponse pay(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) {
        try {
            LOGGER.info("LOG01000: centerScanPayRequest :{}.", centerScanPayRequest);
//        List<TradeFlow> tradeFlowExists = tradeFlowMapper.selectTradeList(
//                this.buildAllPayTradeFlow(allpayReqData.getAppKey(), allpayReqData.getInstOrderNo()));
//        if (tradeFlowExists == null || tradeFlowExists.isEmpty()) { //不存在任何订单
//            logger.info("none flow .insert tradeFlow  and operateFlow. ");
//            return this.allPayNone(allpayReqData, url, pConfigValue);
//        } else if (this.existsPayed(tradeFlowExists)) { //存在已成功订单
//            logger.info("payed flow . need not to insert. ");
//            return new ChannelResponse(AtomPayCode.PAYED);
//        } else if (this.existsPaying(tradeFlowExists)) { //存在处理中订单
//            logger.info("paying flow . need not to insert. ");
//            return new ChannelResponse(AtomPayCode.PAYING);
//        } else if (this.existsOnlyCurrent(allpayReqData.getChannelCode(), tradeFlowExists)) { //仅存在当前渠道订单
//            logger.info("only current flow . update status if need. ");
//            return this.allPayOnlyCurrent(tradeFlowExists);
//        } else if (this.existsOthers(allpayReqData.getChannelCode(), tradeFlowExists)) { //仅存在其他渠道订单
//            logger.info("only others flow . update others status if need and insert current ");
//            return this.allPayOnlyOthers(allpayReqData, url, pConfigValue, tradeFlowExists);
//        } else if (this.existsCurrentOthers(allpayReqData.getChannelCode(), tradeFlowExists)) {//存在当前渠道订单和其他渠道订单
//            logger.info("current and others flow . update current and others status if need.  ");
//            return this.allPayCurrentOthers(allpayReqData, tradeFlowExists);
//        } else { //不存在的情况，如出现，则报错
//            logger.error("impossible case occurred");
//            return new ChannelResponse(AtomPayCode.ERROR);
//        }
            List<TraderFlow> exitsFlow = traderFlowMapper.selectByAppKeyAndOutNo(ScanBuilder.buildScanPayQueryTradeFlow(centerScanPayRequest));
            if (exitsFlow == null || exitsFlow.isEmpty()) {
                LOGGER.info("LOG01020: no trade flow exists.insert operate flow and trade flow.");
                PayResponse payResponse = networkService.scanPay(centerScanPayRequest, configValue);
                TraderFlow scanPayInsertTradeFlow = ScanBuilder.buildScanPayInsertTradeFlow(centerScanPayRequest, payResponse.getUrl());
                operateFlowMapper.insertSelective(ScanBuilder.buildScanPayInsertTradeFlow(scanPayInsertTradeFlow.getId()));
                traderFlowMapper.insertSelective(scanPayInsertTradeFlow);
                return ScanAnalysor.analyse(payResponse);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("LOG01060: san pay error : {} .", e);
            return ScanAnalysor.analyse();
        }

    }


}
