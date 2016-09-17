package com.pein.service.impl;

import com.pein.common.config.ConfigValue;
import com.pein.common.enums.CenterResponseCode;
import com.pein.common.enums.ExceptionCode;
import com.pein.common.enums.TradeStatus;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.response.CenterScanPayResponse;
import com.pein.common.response.PayResponse;
import com.pein.repository.entity.MerchantChannelAli;
import com.pein.repository.entity.TraderFlow;
import com.pein.repository.persistence.MerchantChannelAliMapper;
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
    private MerchantChannelAliMapper merchantChannelAliMapper;

    @Autowired
    private NetworkService networkService;

    @Transactional
    public CenterScanPayResponse pay(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) {
        try {
            LOGGER.info("LOG01000: centerScanPayRequest :{}.", centerScanPayRequest);
            List<TraderFlow> traderFlowList = traderFlowMapper.selectByAppKeyAndOutNo(ScanBuilder.buildScanPayQueryTradeFlow(centerScanPayRequest));
            if (traderFlowList == null || traderFlowList.isEmpty()) {
                LOGGER.info("LOG01020: no trade flow exists.insert operate flow and trade flow.");
                return allPayNotExists(centerScanPayRequest, configValue);
            } else if (this.existsPayed(traderFlowList)) { //存在已成功订单
                LOGGER.info("LOG_QW00020: payed flow . need not to insert.");
                return new CenterScanPayResponse(CenterResponseCode.FAIL, ExceptionCode.TRADE_SUCCESS_ERROR);
            } else if (this.existsPaying(traderFlowList)) { //存在处理中订单
                LOGGER.info("LOG_QW00030:paying flow . need not to insert.");
                return new CenterScanPayResponse(CenterResponseCode.FAIL, ExceptionCode.TRADE_PAYING_ERROR);
            } else if (this.existsOnlyCurrent(centerScanPayRequest.getChannelCode(), traderFlowList)) { //仅存在当前渠道订单
                LOGGER.info("LOG_QW00040: only current flow . update status if need.");
                return new CenterScanPayResponse(CenterResponseCode.SUCCESS);
            } else if (this.existsOnlyOthers(centerScanPayRequest.getChannelCode(), traderFlowList)) { //仅存在其他渠道订单
                LOGGER.info("LOG_QW00050: only others flow . update others status if need and insert current. ");
                return this.allPayOnlyOthers(centerScanPayRequest, configValue);
            } else if (this.existsCurrentOthers(centerScanPayRequest.getChannelCode(), traderFlowList)) {//存在当前渠道订单和其他渠道订单
                LOGGER.info("LOG_QW00060: current and others flow . update current and others status if need. ");
                return this.allPayCurrentOthers(centerScanPayRequest, configValue);
            } else { //不存在的情况，如出现，则报错
                LOGGER.error("LOG_QW00070: impossible case occurred");
                return new CenterScanPayResponse(CenterResponseCode.FAIL, ExceptionCode.NOT_EXISTS_SCENE);
            }
        } catch (Exception e) {
            LOGGER.error("LOG01060: san pay error : {} .", e);
            return ScanAnalysor.analyse();
        }

    }

    private CenterScanPayResponse allPayNotExists(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) throws Exception {
        MerchantChannelAli merchantChannelAli =  merchantChannelAliMapper.select(ScanBuilder.buildScanPaySelectMerchantChannel(centerScanPayRequest));
        PayResponse payResponse = networkService.scanPay(
                ScanBuilder.buildScanPaynetWorkRequest(centerScanPayRequest,merchantChannelAli), configValue);
        TraderFlow scanPayInsertTradeFlow = ScanBuilder.buildScanPayInsertTradeFlow(centerScanPayRequest, payResponse.getUrl());
        operateFlowMapper.insertSelective(ScanBuilder.buildScanPayInsertTradeFlow(scanPayInsertTradeFlow.getId()));
        traderFlowMapper.insertSelective(scanPayInsertTradeFlow);
        return ScanAnalysor.analyse(payResponse);
    }

    private CenterScanPayResponse allPayOnlyOthers(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) throws Exception {
        PayResponse payResponse = networkService.scanPay(centerScanPayRequest, configValue);
        TraderFlow scanPayInsertTradeFlow = ScanBuilder.buildScanPayInsertTradeFlow(centerScanPayRequest, payResponse.getUrl());
        operateFlowMapper.insertSelective(ScanBuilder.buildScanPayInsertTradeFlow(scanPayInsertTradeFlow.getId()));
        traderFlowMapper.updateByAppKeyAndOutNo(ScanBuilder.buildScanPayCancelTradeFlowAllChannel(centerScanPayRequest));
        traderFlowMapper.insertSelective(scanPayInsertTradeFlow);
        return ScanAnalysor.analyse(payResponse);
    }

    private CenterScanPayResponse allPayCurrentOthers(CenterScanPayRequest centerScanPayRequest, ConfigValue configValue) throws Exception {
        PayResponse payResponse = networkService.scanPay(centerScanPayRequest, configValue);
        TraderFlow scanPayInsertTradeFlow = ScanBuilder.buildScanPayInsertTradeFlow(centerScanPayRequest, payResponse.getUrl());
        operateFlowMapper.insertSelective(ScanBuilder.buildScanPayInsertTradeFlow(scanPayInsertTradeFlow.getId()));
        //更新其他渠道为取消支付
        traderFlowMapper.updateByAppKeyAndOutNoAndCode(ScanBuilder.buildScanPayCancelTradeFlow(centerScanPayRequest));
        //更新当前渠道为等待支付
        traderFlowMapper.updateByAppKeyAndOutNoAndCode(ScanBuilder.buildScanPayWaitTradeFlow(centerScanPayRequest));
        return ScanAnalysor.analyse(payResponse);
    }

    /**
     * 存在已支付成功订单
     *
     * @param traderFlowList
     * @return
     */

    private boolean existsPayed(List<TraderFlow> traderFlowList) {
        return traderFlowList.stream().anyMatch((traderFlow) -> TradeStatus.SUCCESS.getStatus().equals(traderFlow.getTradeStates()));
    }


    /**
     * 存在处理中的订单
     *
     * @param traderFlowList
     * @return
     */
    private boolean existsPaying(List<TraderFlow> traderFlowList) {
        return traderFlowList.stream().anyMatch((traderFlow) -> TradeStatus.PAYING.getStatus().equals(traderFlow.getTradeStates()));
    }

    /**
     * 仅存在当前渠道订单
     *
     * @param traderFlowList
     * @return
     */
    private boolean existsOnlyCurrent(String channelCode, List<TraderFlow> traderFlowList) {
        return traderFlowList != null
                && traderFlowList.size() == 1
                && channelCode.equals(traderFlowList.get(0).getChannelCode());
    }

    /**
     * 仅存在其他渠道订单
     *
     * @param channelCode
     * @param traderFlowList
     * @return
     */
    private boolean existsOnlyOthers(String channelCode, List<TraderFlow> traderFlowList) {
        return traderFlowList.stream().noneMatch((traderFlow -> channelCode.equals(traderFlow.getChannelCode())));
    }

    /**
     * 存在当前渠道和其他渠道订单
     *
     * @param channelCode
     * @param traderFlowList
     * @return
     */
    private boolean existsCurrentOthers(String channelCode, List<TraderFlow> traderFlowList) {
        return traderFlowList.stream().anyMatch((traderFlow -> channelCode.equals(traderFlow.getChannelCode()))) &&
                traderFlowList.stream().anyMatch((traderFlow -> !channelCode.equals(traderFlow.getChannelCode())));
    }

}
